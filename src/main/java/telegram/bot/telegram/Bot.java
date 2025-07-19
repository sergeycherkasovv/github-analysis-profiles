package telegram.bot.telegram;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import telegram.bot.component.TelegramProperties;
import telegram.bot.services.UpdateService;

@Component
@AllArgsConstructor
public class Bot implements SpringLongPollingBot {
    private final TelegramProperties telegramProperties;
    private final UpdateService updateService;


    @Override
    public String getBotToken() {
        return telegramProperties.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return updateService;
    }
}
