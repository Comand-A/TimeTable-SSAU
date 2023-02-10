package org.example.Telegram.KeyBoard;


import org.example.Telegram.Model.DirectionIIK;
import org.example.Telegram.Model.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardButtonUser {
    private SendMessage message;
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;
    private InlineKeyboardButton button;
    private final DirectionIIK[] directionIIK = DirectionIIK.values();

    private void initializationMessage(long chatId, String textSend) {
        message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textSend);
    }

    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }

    private void initializationButton(String value) {
        button = new InlineKeyboardButton();
        button.setText(value);
        button.setCallbackData(value);
        rowInLine.add(button);

    }

    public SendMessage choiceOfDirectionIIK(long chatId, List<String> directionOfCourse) {
        initializationMessage(chatId, "Выберите ваше направление");

        initializationInlineKeyboard();


        int i = 0;
        for (String s : directionOfCourse) {
            if (s.length() <= 11 && i < 3) {
                initializationButton(s);
                i++;
            } else {
                rowInLine = new ArrayList<>();
                initializationButton(s);
                i = 1;
                rowsInLine.add(rowInLine);
            }
            if (i==3)
                rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        return message;

    }

    public SendMessage setOfGroupNumber(long chatId, List<String> amount) {
        initializationMessage(chatId, "Выберите номер вашей группы");

        initializationInlineKeyboard();
        for (String s : amount) {
            initializationButton(s);
        }

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;
    }

}
