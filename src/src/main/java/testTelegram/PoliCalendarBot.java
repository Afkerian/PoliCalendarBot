package testTelegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PoliCalendarBot extends TelegramLongPollingBot {
    Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            logger.log(Level.INFO,update.getMessage().getText());
            logger.log(Level.INFO,update.getMessage().getChat().getUserName());
            logger.log(Level.INFO,update.getMessage().getChat().getId().toString());

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "policalendar";
    }

    @Override
    public String getBotToken() {
        return "1650255487:AAELdT_-BBQ2RvS63higt2Ni4mrBiE4YmRM";
    }
}
