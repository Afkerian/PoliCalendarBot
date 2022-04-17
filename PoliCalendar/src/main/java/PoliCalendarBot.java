import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
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

    /**
     *  Agrega botones bajo el mensaje indicado
     * @param chatId
     * @param message
     * @return
     */
    public static SendMessage sendInlineKeyBoardMessage(long chatId, SendMessage message){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Btn1");
        inlineKeyboardButton1.setCallbackData("Button \"Btn1\" has been pressed");
        inlineKeyboardButton2.setText("Btn2");
        inlineKeyboardButton2.setCallbackData("Button \"Btn2\" has been pressed");
        inlineKeyboardButton3.setText("Btn3");
        inlineKeyboardButton3.setCallbackData("Button \"Btn3\" has been pressed");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        //List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow1.add(inlineKeyboardButton3);
        //keyboardButtonsRow2.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        //rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage inlineKeyBoardMessage = new SendMessage();
        inlineKeyBoardMessage.setChatId(Long.toString(chatId));
        inlineKeyBoardMessage.setText(message.getText());
        inlineKeyBoardMessage.setReplyMarkup(inlineKeyboardMarkup);
        return inlineKeyBoardMessage;
    }



}
