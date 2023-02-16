package org.example.Telegram.KeyBoard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InLineKeyboardWeekday {
    private final ArrayList<String> weekDay= new ArrayList<>(Arrays.asList("пн","вт","ср","чт","пт","сб"));
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;

    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }
    public InlineKeyboardMarkup choiceOfWeekday() {

        initializationInlineKeyboard();
        for (String day : weekDay){
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(rowInLine.size()-1).setText(day);
            rowInLine.get(rowInLine.size()-1).setCallbackData(day);
        }

        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();

        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(0).setText("Предыдущая");
        rowInLine.get(0).setCallbackData("пред");

        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(1).setText("Следующая");
        rowInLine.get(1).setCallbackData("след");

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }
}
