package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum DirectionIIK {
    FIIT(Emoji.CROWN.get() + "ФИИТ" + Emoji.CROWN.get()),
    PMF("ПМФ"),
    PMI("ПМИ"),
    IVT("ИВТ"),
    RADIO_TECH("РАДИОТЕХНИКА"),
    ELECTRONICS("ЭЛЕКТРОНИКА И НАНОЭЛЕКТРОНИКА"),
    BIO_TECH("БИО СИС И ТЕХНОЛОГИИ"),
    LASER_TECH("ЛАЗЕРНАЯ ТЕХНИКА"),
    CONSTRUCTION_AND_TECH_ELECTRONICS("КОНСТРУИРОВАНИЕ И ТЕХНОЛОГИИ"),
    RADIO_ELECTRONICS_AND_COMPLEXES("РАДИОЭЛЕКТРОННЫЕ СИСТЕМЫ"),
    IBAS("ИБАС");



    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    DirectionIIK(String value) {
        this.value = value;
    }
}
