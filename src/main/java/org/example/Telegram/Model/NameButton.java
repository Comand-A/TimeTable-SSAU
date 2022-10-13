package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum NameButton {
    SHUDELE(Emoji.ROCKET.get() + "К расписанию" + Emoji.ROCKET.get()),
    AUTHORS(Emoji.AUTHOR.get() + "Авторы" + Emoji.AUTHOR.get()),
    BACK("Назад");
    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    NameButton(String value) {
        this.value = value;
    }
}
