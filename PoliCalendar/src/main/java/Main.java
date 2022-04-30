import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new PoliCalendarBot());
            System.out.println("Hello, World!");
            LinkLecture.lectureICS();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
