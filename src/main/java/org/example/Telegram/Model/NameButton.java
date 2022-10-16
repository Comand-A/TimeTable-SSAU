package org.example.Telegram.Model;

import com.vdurmont.emoji.EmojiParser;

public enum NameButton {
    SHUDELE("К расписанию"),
    AUTHORS("Авторы"),
    BACK("Назад"),
    PAST_WEEK("Предыдущая"),
    THIS_WEEK("Текущая"),
    NEXT_WEEK("Следующая"),
    USER_WEEK("Своя неделя");

    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    NameButton(String value) {
        this.value = value;
    }
}
