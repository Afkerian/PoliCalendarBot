import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.toIntExact;

public class PoliCalendarBot extends TelegramLongPollingBot {
    private long message_id=0;
    private long chat_id=0;
    private String lastMessageReceived="";
    private Logger logger = Logger.getLogger(this.getClass().getName());
    public void onRegister() {
        super.onRegister();
    }
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
    public PoliCalendarBot() {
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if(update.getMessage().getText().equals("edit")){
                if(message_id!=0 && chat_id!=0){
                    String answer = this.lastMessageReceived+"(Updated message text)";
                    EditMessageText new_message = new EditMessageText();
                    new_message.setChatId(String.valueOf(toIntExact(chat_id)));
                    new_message.setMessageId(toIntExact(message_id));
                    new_message.setText(answer);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            message_id = update.getMessage().getMessageId()+1;
            chat_id = update.getMessage().getChatId();
            lastMessageReceived = update.getMessage().getText();
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            this.logger.log(Level.INFO, update.getMessage().getText());
            this.logger.log(Level.INFO, update.getMessage().getChat().getUserName());
            this.logger.log(Level.INFO, update.getMessage().getChat().getId().toString());
            this.logger.log(Level.INFO, String.valueOf(update.getMessage().getMessageId()));

            try {
                this.execute(message);
            } catch (TelegramApiException var4) {
                var4.printStackTrace();
            }


        }else if(update.hasCallbackQuery()){
            logger.log(Level.INFO,update.getCallbackQuery().getData());
            logger.log(Level.INFO,update.getCallbackQuery().getMessage().getText());
            logger.log(Level.INFO, String.valueOf(update.getCallbackQuery().getMessage().getMessageId()));
            logger.log(Level.INFO, String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        }


    }

    public String getBotUsername() {
        return "policalendar";
    }

    public String getBotToken() {
        return "5389177234:AAE5JdMUq5GQTMECmQZ3248_-wH3eqJZbHs";
    }
}

