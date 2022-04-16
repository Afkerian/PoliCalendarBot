
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class PoliCalendarBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();

        if (update.hasMessage() && update.getMessage().hasText()) {
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("¡Hola! " + update.getMessage().getFrom().getUserName() + ".\n\nPoliCalendar te da la bienvenida!");
            try{
                //execute(message);
                execute(sendInlineKeyBoardMessage(update.getMessage().getChatId(), message));
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

    public static SendMessage sendInlineKeyBoardMessage(long chatId, SendMessage message){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Btn1");
        inlineKeyboardButton1.setCallbackData("Button \"Btn1\" has been pressed");
        inlineKeyboardButton2.setText("Btn2");
        inlineKeyboardButton2.setCallbackData("Button \"Btn2\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        InlineKeyboardButton k = new InlineKeyboardButton();
        k.setText("Btn3");
        k.setCallbackData("CallBtn3");
        keyboardButtonsRow1.add(k);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage f = new SendMessage();
        f.setChatId(Long.toString(chatId));
        f.setText(message.getText());
        f.setReplyMarkup(inlineKeyboardMarkup);
        return f;
    }



}
