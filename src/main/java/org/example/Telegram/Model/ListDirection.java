package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum ListDirection {
    FIIT("ФИИТ"),
    PMF("ПМФ"),
    PMI("ПМИ");
    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    ListDirection(String value) {
        this.value = value;
    }
}
