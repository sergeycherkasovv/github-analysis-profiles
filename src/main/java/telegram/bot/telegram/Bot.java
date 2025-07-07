package telegram.bot.telegram;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.properties.TelegramProperties;
import telegram.bot.services.UpdateService;

@Component
public class Bot extends TelegramWebhookBot {
    private final TelegramProperties telegramProperties;
    private final UpdateService updateService;

    Bot(TelegramProperties telegramProperties, UpdateService updateService) {
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
        this.updateService = updateService;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return updateService.distribute(update, this);
    }

    @Override
    public String getBotPath() {
        return null;
    }

    @Override
    public String getBotUsername() {
        return null;
    }
}
