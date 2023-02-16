package org.example.Telegram.KeyBoard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InLineKeyboardGroup {
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;

    public InlineKeyboardMarkup directionGroups(List<String> returnList) {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
        for (int i = 0; i < returnList.size(); i++) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(i).setText(returnList.get(i));
            rowInLine.get(i).setCallbackData(returnList.get(i));
        }

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }
}
