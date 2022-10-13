package org.example.Telegram.KeyBoard;

import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.ListDirection;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardButtonUser {
    private SendMessage message;
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;
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
    }

    private void initializationButton(String value) {
        button = new InlineKeyboardButton();
        button.setText(value);
        button.setCallbackData(value);
        rowInLine.add(button);

    }

    public SendMessage choiceOfDirection(long chatId) {
        initializationMessage(chatId, "Выберите ваше направление");

        initializationInlineKeyboard();

        InlineKeyboardButton buttonFiit = new InlineKeyboardButton();

        buttonFiit.setText(Emoji.CROWN.get() + ListDirection.FIIT.get() + Emoji.CROWN.get());
        buttonFiit.setCallbackData(ListDirection.FIIT.get());

        InlineKeyboardButton buttonPmf = new InlineKeyboardButton();

        buttonPmf.setText(ListDirection.PMF.get());
        buttonPmf.setCallbackData(ListDirection.PMF.get());
        rowInLine.add(buttonFiit);
        rowInLine.add(buttonPmf);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        return message;

    }

    public SendMessage choiceOfGroupNumber(long chatId, int amount) {
       initializationMessage(chatId, "Выберите номер вашей группы");

        initializationInlineKeyboard();
        for (int i = 1; i <= amount; i++) {
            initializationButton(String.valueOf(i));
        }

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;
    }

}
