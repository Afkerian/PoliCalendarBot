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

        if(update.hasCallbackQuery()){
            try {
                execute(AnswerCallbackInlineButtons(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData()));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
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
     * @param chatId Identificación del chat donde se enviará el mensaje
     * @param message Mensaje al que se quiere agregar los botones
     * @return Mensaje y los botones agregados
     */
    public static SendMessage sendInlineKeyBoardMessage(long chatId, SendMessage message){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Btn1");
        inlineKeyboardButton1.setCallbackData("Button1");

        inlineKeyboardButton2.setText("Btn2");
        inlineKeyboardButton2.setCallbackData("Button2");

        inlineKeyboardButton3.setText("Btn3");
        inlineKeyboardButton3.setCallbackData("Button3");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        keyboardButtonsRow2.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage inlineKeyboardMessage = new SendMessage();
        inlineKeyboardMessage.setChatId(Long.toString(chatId));
        inlineKeyboardMessage.setText(message.getText());
        inlineKeyboardMessage.setReplyMarkup(inlineKeyboardMarkup);

        return inlineKeyboardMessage;
    }

    /**
     *
     * @param chatId Se usa para configurar el mensaje que se retornará al aplastar un boton del inline Keyboard
     * @param opt Callback data returned of InlineKeyboardButtons
     * @return Mensaje a imprimir para comprobar la respuesta al aplastar el botón
     */
    public static SendMessage AnswerCallbackInlineButtons(long chatId, String opt) {

        SendMessage s1 = new SendMessage();
        s1.setChatId(Long.toString(chatId));

        switch (opt) {
            case "Button1":
                s1.setText("Has seleccionado el botón 1");
                break;
            case "Button2":
                s1.setText("Has seleccionado el botón 2");
                break;
            case "Button3":
                s1.setText("Has seleccionado el botón 3");
                break;
        }

        return s1;
    }


}