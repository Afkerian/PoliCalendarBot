
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;

public class PoliCalendarBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();

        if (update.hasMessage() && update.getMessage().hasText()) {
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("¡Hola! " + update.getMessage().getFrom().getUserName() + ".\n\nPoliCalendar  te da la bienvenida!");
            try{
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();

            }
        }

    }

    public String getBotUsername() {
        return TokenReader.readUserName();
    }

    public String getBotToken() {

        return TokenReader.readToken();
    }





}
