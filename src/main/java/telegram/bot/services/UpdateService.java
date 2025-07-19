package telegram.bot.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import telegram.bot.exception.GitHubAPIException;
import telegram.bot.component.TelegramProperties;
import telegram.bot.message.StatisticMessage;
import telegram.bot.util.MarkdownV2Util;

import static telegram.bot.enums.CommandName.START;
import static telegram.bot.enums.Message.EXCEPTION;
import static telegram.bot.enums.Message.NOT_COMMAND;
import static telegram.bot.enums.Message.START_MESSAGE;


@Component
public class UpdateService implements LongPollingSingleThreadUpdateConsumer {
    private final StatisticMessage statisticMessage;
    private final TelegramClient telegramClient;
    private final MarkdownV2Util markdownV2Util;

    UpdateService(TelegramProperties telegramProperties,
                  StatisticMessage statisticMessage,
                  MarkdownV2Util markdownV2Util) {
        this.markdownV2Util = markdownV2Util;
        this.statisticMessage = statisticMessage;
        this.telegramClient = new OkHttpTelegramClient(telegramProperties.getToken());
    }


    @SneakyThrows
    @Override
    public void consume(Update updates) {
        SendMessage text = null;

        try {
            if (updates.hasMessage()) {
                var chatId = updates.getMessage().getChatId();
                var message = updates.getMessage().getText();
                if (START.getCommandName().equals(message)) {
                    text = getMessage(chatId, markdownV2Util.escapeMarkdownV2(START_MESSAGE.getMessage()));
                } else if (message.matches("/.*")) {
                    text = getMessage(chatId, markdownV2Util.escapeMarkdownV2(NOT_COMMAND.getMessage()));
                } else {
                    text = getMessage(chatId, statisticMessage.getMessage(message));
                }
            }
        } catch (GitHubAPIException ex) {
            var chatId = updates.getMessage().getChatId();

            text = getMessage(chatId, markdownV2Util.escapeMarkdownV2(EXCEPTION.getMessage()));
        }

        telegramClient.execute(text);
    }

    private SendMessage getMessage(Long chatId, String message) {
        return SendMessage
                .builder()
                .text(message)
                .chatId(chatId)
                .parseMode(ParseMode.MARKDOWNV2)
                .build();
    }
}
