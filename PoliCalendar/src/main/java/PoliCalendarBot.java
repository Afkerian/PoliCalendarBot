
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class PoliCalendarBot extends TelegramLongPollingBot {
public int aux_buttons =0;
public int message_id_inline_buttons =0;
public int message_id_inlsine_buttons =0;

    /**
     * Se agregaron los comandos:
     * /inline_buttons: Muestra al usuario un conjunto de inlinebuttons y al apretar una opcion desaparecen los inlinebuttons
     * /buttons: Muestra botones normales y al escoger una opcion desaparecen los botones
     * hascallbackquery: Especifica una respuesta ante la seleccion de un inlinebutton, contiene la desaparicion de los botones al realizar la eleccion
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();

        if (update.hasMessage() && update.getMessage().hasText()) {

            String command = update.getMessage().getText();

            if(command.equals("/buttons") && aux_buttons ==0){

                sendCustomKeyboard(update.getMessage().getChatId().toString());
                aux_buttons++;
                System.out.println(aux_buttons);

            }else if(aux_buttons !=0){
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Eliminando botones");
            message.setReplyMarkup(new ReplyKeyboardRemove(true));

            aux_buttons =0;
            try{
                execute(message);
            }catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

            }

            if(command.equals("/inline_buttons")) {

                try {
                    String enunciado= "¡Hola! " + update.getMessage().getFrom().getFirstName() + ".\n\nPoliCalendar te da la bienvenida!";
                    SendMessage s1 = sendInlineKeyBoardMessage(enunciado, update.getMessage().getChatId());
                    message_id_inline_buttons = update.getMessage().getMessageId() + 1;
                    execute(s1);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }

        if(update.hasCallbackQuery()){
            try {
                execute(AnswerCallbackInlineButtons(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData()));

                EditMessageReplyMarkup edit = new EditMessageReplyMarkup();
                edit.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
                edit.setMessageId(message_id_inline_buttons);
                edit.setReplyMarkup(null);
                System.out.println(edit);
                execute(edit);


            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onCallbackQueryReceived(Update update){

    }

    public String getBotUsername() {
        return TokenReader.readUserName();
    }

    public String getBotToken() {
        return TokenReader.readToken();
    }

    /**
     *
     * @param chatId Se usa para configurar el chat al que se enviar+a el inlineKeyboardButton
     * @return  Mensaje a enviarle al usuario
     */
    public static SendMessage sendInlineKeyBoardMessage(String InlineText, long chatId) {
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

        SendMessage f = new SendMessage();
        f.setChatId(Long.toString(chatId));
        f.setText(InlineText);
        f.setReplyMarkup(inlineKeyboardMarkup);

        return f;
    }
    /**
     *
     * @param chatId Se usa para configurar el mensaje que se retornará al aplastar un boton del inline Keyboarg
     * @param opt Callback data returned of InlineKeyboardButtons
     * @return Mensaje a imprimir para comprobar la respuesta al aplastar el botón 
     */
    public static SendMessage AnswerCallbackInlineButtons(long chatId, String opt) {

        SendMessage s1 = new SendMessage();
        s1.setChatId(Long.toString(chatId));

        if(opt.equals("Button1")){
            s1.setText("Has seleccionado el boton 1");

        }
        else if(opt.equals("Button2")){
            s1.setText("Has seleccionado el boton 2");
        }
        else if(opt.equals("Button3")){
            s1.setText("Has seleccionado el boton 3");
        }
        //editMessageReplyMarkup

        return s1;
    }

    /**
     * Este metodo muestra keyboard buttons como opcion en caso de desear usarse
     * @param chatId: Parametro para el envio de keyboardButtons
     */
    public void sendCustomKeyboard(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Custom message text");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Row 1 Button 1");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Row 2 Button 1");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);

        try {
            // Send the message
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



}
