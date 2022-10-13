package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum Emoji {
    ROCKET(":rocket:"),
    CROWN(":crown:"),
    AUTHOR(":man_technologist:"),
    WELCOME(":wave:");

    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Emoji(String value) {
        this.value = value;
    }
}
