package org.example.Telegram.KeyBoard;


import org.example.DirectionSSAU.IIK.IIKCourse;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardButtonUser {
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;
    private InlineKeyboardButton button;

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

    public InlineKeyboardMarkup directionsCourse(String callBackData) {

        initializationInlineKeyboard();
        IIKCourse directionsCourse = new IIKCourse();
        List<String> directionOfCourse = directionsCourse.directionsCourse.get(callBackData);

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

        return markupInLine;

    }
}
