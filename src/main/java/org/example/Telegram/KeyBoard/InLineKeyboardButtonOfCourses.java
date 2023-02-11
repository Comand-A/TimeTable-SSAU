package org.example.Telegram.KeyBoard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InLineKeyboardButtonOfCourses {
    private SendMessage message;
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;

    private void initializationMessage(long chatId, String textSend) {
        message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textSend);
    }

    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }

    public SendMessage choiceOfCourse(long chatId) {
        initializationMessage(chatId, "Выберите ваш курс");

        initializationInlineKeyboard();
        for (int i = 1; i<6;i++ ) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(i-1).setText(String.valueOf(i));
            rowInLine.get(i-1).setCallbackData(String.valueOf(i));
        }

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        return message;
    }
}