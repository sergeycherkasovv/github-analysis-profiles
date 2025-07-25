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
import telegram.bot.util.MarkdownV2Util;

import static telegram.bot.enums.Message.ERROR_MESSAGE;

@Component
public class UpdateService implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    private final MarkdownV2Util markdownV2Util;
    private final MessageService messageService;
    private final GitHubService gitHubService;

    UpdateService(TelegramProperties telegramProperties,
                  MarkdownV2Util markdownV2Util,
                  MessageService messageService,
                  GitHubService gitHubService) {
        this.gitHubService = gitHubService;
        this.messageService = messageService;
        this.markdownV2Util = markdownV2Util;
        this.telegramClient = new OkHttpTelegramClient(telegramProperties.getToken());
    }

    @SneakyThrows
    @Override
    public void consume(Update updates) {

            if (updates.hasMessage()) {
                var chatId = updates.getMessage().getChatId();
                var message = updates.getMessage().getText();

                try {
                    telegramClient.execute(getMessage(chatId, messageService
                                                                .generateMessage(message, gitHubService)));
                } catch (GitHubAPIException ex) {
                    telegramClient.execute(getMessage(chatId, markdownV2Util
                                                                .escapeMarkdownV2(ERROR_MESSAGE.getMessage())));
                }

            }
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
