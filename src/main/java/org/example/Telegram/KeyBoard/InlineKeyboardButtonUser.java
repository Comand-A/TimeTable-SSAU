package org.example.Telegram.KeyBoard;

import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.ListDirection;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardButtonUser {
    public SendMessage choiceOfDirection(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите ваше направление");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
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
}
