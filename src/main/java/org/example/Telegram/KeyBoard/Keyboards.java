package org.example.Telegram.KeyBoard;

import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.NameButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboards {
    public static ReplyKeyboardMarkup keyboardStart() {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow rowOne = new KeyboardRow();

        rowOne.add(Emoji.ROCKET.get() + NameButton.SHUDELE.get() + Emoji.ROCKET.get());
        rowOne.add(Emoji.AUTHOR.get() + NameButton.AUTHORS.get() + Emoji.AUTHOR.get());
        keyboardRows.add(rowOne);
        rowOne = new KeyboardRow();

        rowOne.add("моё расписание");
        keyboardRows.add(rowOne);
        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

    public static ReplyKeyboardMarkup keyboardAuthorsProject() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow rowOne = new KeyboardRow();

        rowOne.add(Emoji.BACK.get() + NameButton.BACK.get() + Emoji.BACK.get());
        keyboardRows.add(rowOne);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }
    public static InlineKeyboardMarkup numberOfCourse() {

        //создаю клавиатуру
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //создаю список с списками кнопок
        List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();

        //создаю список с кнопками
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();

        //1-ая кнопка
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("1");
        button.setCallbackData("1COURSE_BTN");
        inlineKeyboardRow.add(button);

        //2-ая кнопка
        button = new InlineKeyboardButton();
        button.setText("2");
        button.setCallbackData("2COURSE_BTN");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("3");
        button.setCallbackData("3COURSE_BTN");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("4");
        button.setCallbackData("4COURSE_BTN");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("5");
        button.setCallbackData("5COURSE_BTN");
        inlineKeyboardRow.add(button);

        //добавляю кнопки в ряд
        inlineKeyboardRows.add(inlineKeyboardRow);

        //создаю клавиатуру
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);

        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup directionIIK(List<String> directionOfCourse){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //создаю список с списками кнопок
        List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();

        //создаю список с кнопками
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();

        InlineKeyboardButton button;
        int i = 0;

        for (String s : directionOfCourse) {
            if (s.length() <= 11 && i < 3) {
                button = new InlineKeyboardButton();
                button.setText(s);
                button.setCallbackData(s+"D_BTN");
                inlineKeyboardRow.add(button);
                i++;
            } else {
                inlineKeyboardRow = new ArrayList<>();
                button = new InlineKeyboardButton();
                button.setText(s);
                button.setCallbackData(s+"D_BTN");
                inlineKeyboardRow.add(button);
                i = 1;
                inlineKeyboardRows.add(inlineKeyboardRow);
            }
            if (i==3)
                inlineKeyboardRows.add(inlineKeyboardRow);
        }

        //создаю клавиатуру
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup groupIIK(List<String> directionOfCourse){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //создаю список с списками кнопок
        List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();

        //создаю список с кнопками
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();

        InlineKeyboardButton button;

        for (String s : directionOfCourse) {
                button = new InlineKeyboardButton();
                button.setText(s);
                button.setCallbackData(s+"G_BTN");
                inlineKeyboardRow.add(button);
        }
        inlineKeyboardRows.add(inlineKeyboardRow);
        //создаю клавиатуру
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup week() {

        //создаю клавиатуру
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //создаю список с списками кнопок
        List<List<InlineKeyboardButton>> inlineKeyboardRows = new ArrayList<>();

        //создаю список с кнопками
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>();

        //1-ая кнопка
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("пн");
        button.setCallbackData("1DAY");
        inlineKeyboardRow.add(button);

        //2-ая кнопка
        button = new InlineKeyboardButton();
        button.setText("вт");
        button.setCallbackData("2DAY");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("ср");
        button.setCallbackData("3DAY");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("чт");
        button.setCallbackData("4DAY");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("пт");
        button.setCallbackData("5DAY");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText("сб");
        button.setCallbackData("6DAY");
        inlineKeyboardRow.add(button);

        //добавляю кнопки в ряд
        inlineKeyboardRows.add(inlineKeyboardRow);
        inlineKeyboardRow = new ArrayList<>();

        button = new InlineKeyboardButton();
        button.setText(Emoji.PAST_WEEK.get() + NameButton.PAST_WEEK.get());
        button.setCallbackData("PAST_WEEK");
        inlineKeyboardRow.add(button);

        button = new InlineKeyboardButton();
        button.setText(NameButton.NEXT_WEEK.get() + Emoji.NEXT_WEEK.get());
        button.setCallbackData("NEXT_WEEK");
        inlineKeyboardRow.add(button);

        //добавляю кнопки в ряд
        inlineKeyboardRows.add(inlineKeyboardRow);

        inlineKeyboardRow = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Запомнить");
        button.setCallbackData("MEMORY");
        inlineKeyboardRow.add(button);
        inlineKeyboardRows.add(inlineKeyboardRow);

        //создаю клавиатуру
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardRows);

        return inlineKeyboardMarkup;
    }
}
