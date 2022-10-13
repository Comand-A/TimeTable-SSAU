package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum NameButton {
    SHUDELE("К расписанию"),
    AUTHORS("Авторы"),
    BACK("Назад");
    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    NameButton(String value) {
        this.value = value;
    }
}
