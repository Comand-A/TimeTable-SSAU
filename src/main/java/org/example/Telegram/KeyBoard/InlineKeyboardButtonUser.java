package org.example.Telegram.KeyBoard;

import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.Direction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Emoji.CROWN.get() + Direction.FIIT.get() + Emoji.CROWN.get());
        button.setCallbackData(Direction.FIIT.get());
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText(Direction.PMF.get());
        button.setCallbackData(Direction.PMF.get());
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText(Direction.PMI.get());
        button.setCallbackData(Direction.PMI.get());
        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        rowInLine = new ArrayList<>();

        button = new InlineKeyboardButton();
        button.setText(Direction.IVT.get());
        button.setCallbackData(Direction.IVT.get());
        rowInLine.add(button);

        button = new InlineKeyboardButton();
        button.setText(Direction.RADIO_TECH.get());
        button.setCallbackData(Direction.RADIO_TECH.get());
        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        rowInLine = new ArrayList<>();

        button = new InlineKeyboardButton();
        button.setText(Direction.ELECTRONICS.get());
        button.setCallbackData(Direction.ELECTRONICS.get());
        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        rowInLine = new ArrayList<>();

        button = new InlineKeyboardButton();
        button.setText(Direction.BIOTECH.get());
        button.setCallbackData(Direction.BIOTECH.get());
        rowInLine.add(button);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        return message;

    }

    public SendMessage setOfGroupNumber(long chatId, List<String> amount) {
       initializationMessage(chatId, "Выберите номер вашей группы");

        initializationInlineKeyboard();
        for (int i = 0; i < amount.size(); i++) {
            initializationButton(amount.get(i));
        }

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;
    }

}
