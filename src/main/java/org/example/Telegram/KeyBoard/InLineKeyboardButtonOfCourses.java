package org.example.Telegram.KeyBoard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InLineKeyboardButtonOfCourses {
    private SendMessage message;
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;
    private InlineKeyboardButton button;

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

//    private void initializationButton(String value) {
//        button = new InlineKeyboardButton();
//        button.setText(value);
//        button.setCallbackData(value);
//        rowInLine.add(button);
//    }

    public SendMessage choiceOfCourse(long chatId) {
        initializationMessage(chatId, "Выберите ваш курс");

        initializationInlineKeyboard();
        button = new InlineKeyboardButton();
        button.setText("1");
        button.setCallbackData("1");
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText("2");
        button.setCallbackData("2");
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText("3");
        button.setCallbackData("3");
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText("4");
        button.setCallbackData("4");
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText("5");
        button.setCallbackData("5");
        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        return message;
    }
}