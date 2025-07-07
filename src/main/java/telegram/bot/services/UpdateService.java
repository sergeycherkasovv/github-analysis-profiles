package telegram.bot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.telegram.Bot;

@Service
public class UpdateService {
    public BotApiMethod<?> distribute(Update update, Bot bot) {
        return SendMessage
                    .builder()
                    .text("Privet")
                    .build();
    }
}
