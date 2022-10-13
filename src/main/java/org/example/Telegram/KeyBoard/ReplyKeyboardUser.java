package org.example.Telegram.KeyBoard;

import org.example.Telegram.Model.NameButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplyKeyboardUser {
    private ReplyKeyboardMarkup keyboardMarkup;
    private List<KeyboardRow> keyboardRows;

    private KeyboardRow rowOne;

    public ReplyKeyboard keyboardStart() {
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardRows = new ArrayList<>();

        rowOne = new KeyboardRow();

        rowOne.add(NameButton.SHUDELE.get());
        rowOne.add(NameButton.AUTHORS.get());
        keyboardRows.add(rowOne);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup getAuthorsProject() {
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardRows = new ArrayList<>();

        rowOne = new KeyboardRow();

        rowOne.add(NameButton.BACK.get());
        keyboardRows.add(rowOne);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }
}

