import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class PoliCalendarBot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "TelegramBotName";
    }

    public String getBotToken() {
        return "TelegramBotToken";
    }

    public void onRegister() {
        super.onRegister();
    }

    public void onUpdateReceived(Update update) {

    }

    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
