package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum Direction {
    FIIT("ФИИТ"),
    PMF("ПМФ"),
    PMI("ПМИ"),
    IVT("ИВТ"),
    RADIO_TECH("РАДИОТЕХНИКА"),
    ELECTRONICS("ЭЛЕКТРОНИКА И НАНОЭЛЕКТРОНИКА"),
    BIOTECH("БИОТЕХНИЧЕСКИЕ СИСТЕМЫ И ТЕХНОЛОГИИ");

    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Direction(String value) {
        this.value = value;
    }
}
