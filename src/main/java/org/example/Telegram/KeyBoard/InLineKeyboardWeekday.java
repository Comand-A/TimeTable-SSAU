package org.example.Telegram.KeyBoard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InLineKeyboardWeekday {
    private final ArrayList<String> weekDay= new ArrayList<>(Arrays.asList("пн","вт","ср","чт","пт","сб"));
    private SendMessage message;
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine,rowInLine2;
    private InlineKeyboardButton button;

    private void initializationMessage(long chatId, String textSend) {
        message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textSend);
    }

    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
        rowInLine2 = new ArrayList<>();
    }


    public SendMessage choiceOfWeekday(long chatId, String mes) {
        initializationMessage(chatId, mes);

        initializationInlineKeyboard();
        for (String day : weekDay){
            button = new InlineKeyboardButton();
            button.setText(day);
            button.setCallbackData(day);
            rowInLine.add(button);
        }


//        button = new InlineKeyboardButton();
//        button.setText("вт");
//        button.setCallbackData("вт");
//        rowInLine.add(button);
//
//        button = new InlineKeyboardButton();
//        button.setText("ср");
//        button.setCallbackData("ср");
//        rowInLine.add(button);
//
//        button = new InlineKeyboardButton();
//        button.setText("чт");
//        button.setCallbackData("чт");
//        rowInLine.add(button);
//
//        button = new InlineKeyboardButton();
//        button.setText("пт");
//        button.setCallbackData("пт");
//        rowInLine.add(button);
//
//        button = new InlineKeyboardButton();
//        button.setText("сб");
//        button.setCallbackData("сб");
//        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        button = new InlineKeyboardButton();
        button.setText("Предыдущая");
        button.setCallbackData("пред");
        rowInLine2.add(button);

        button = new InlineKeyboardButton();
        button.setText("Следующая");
        button.setCallbackData("след");
        rowInLine2.add(button);

        rowsInLine.add(rowInLine2);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        return message;
    }
}
