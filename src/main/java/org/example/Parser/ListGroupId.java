package org.example.Parser;

import com.vdurmont.emoji.EmojiParser;

public enum ListGroupId {
    ID_FIIT_GROUP_1("755922237"),
    ID_FIIT_GROUP_2("755922246"),
    ID_FIIT_GROUP_3("755922248"),
    ID_FIIT_GROUP_4("762577970"),
    ID_PMF_GROUP_1("755922274"),
    ID_PMF_GROUP_2("755922277");
    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    ListGroupId(String value) {
        this.value = value;
    }
}
