package org.example.Telegram.KeyBoard;

import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.NameButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
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

        rowOne.add(Emoji.ROCKET.get() + NameButton.SHUDELE.get() + Emoji.ROCKET.get());
        rowOne.add(Emoji.AUTHOR.get() + NameButton.AUTHORS.get() + Emoji.AUTHOR.get());
        keyboardRows.add(rowOne);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup keyboardAuthorsProject() {
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardRows = new ArrayList<>();

        rowOne = new KeyboardRow();

        rowOne.add(Emoji.BACK.get() + NameButton.BACK.get() + Emoji.BACK.get());
        keyboardRows.add(rowOne);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }
}

