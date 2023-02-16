package org.example.Telegram.Service;

import org.example.DirectionSSAU.IIK.IIKCorseID.IIKCoursesID;
import org.example.DirectionSSAU.IIK.IIKDirectionOfGroups.*;
import org.example.Parser.Day;
import org.example.Parser.Parser;
import org.example.Parser.RealDate;
import org.example.Telegram.KeyBoard.*;
import org.example.Telegram.Model.Emoji;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {
    private ArrayList<String> directionOfGroup;
    private long numberOfWeek = 1;
    private List<Day> timeTable;
    private String idDirection, course;
    private boolean courseSelection = false;
    private boolean directionSelection = false;
    private boolean groupSelection = false;


    public String getBotUsername() {
        return "@TimeTableSSAUBot";
    }

    public String getBotToken() {
        return "5600469375:AAGiD2pqJ78SYQ2NvuyGPg8CBQ3dkEst2js";
    }


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    keyboardStart(chatId, Emoji.WELCOME.get());
                    keyboardStart(chatId, "Привет, " + update.getMessage().getChat().getFirstName() + ", рад тебя видеть");
                    break;
                case "↩Назад↩":
                    keyboardStart(chatId, "Вернулись...");
                    break;
                case "\uD83D\uDE80К расписанию\uD83D\uDE80":
                    SendMessage message = new SendMessage();
                    message.setChatId(chatId);
                    message.setText("Выберите курс");
                    InLineKeyboardButtonOfCourses keyboard = new InLineKeyboardButtonOfCourses();
                    sendMessage(message, keyboard.choiceOfCourse());
                    idDirection = "";
                    directionSelection = false;
                    groupSelection = false;
                    courseSelection = true;
                    break;
                case "\uD83D\uDC68\u200D\uD83D\uDCBBАвторы\uD83D\uDC68\u200D\uD83D\uDCBB":
                    keyboardAuthorsProject(chatId);
                    break;
                case "Посхалка":
                    keyboardStart(chatId, "Комплимент дня: ты самый - самый");
                    break;
                default:
                    keyboardStart(chatId, "sorry was not recognized");
                    break;
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (directionSelection) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText("Вы выбрали " + callbackData);
                idDirection += callbackData;
                sendMessage(message);
                directionSelection = false;
                groupSelection = true;
                SendMessage message1 = new SendMessage();
                message1.setChatId(chatId);
                message1.setText("Выберите группу");
                InLineKeyboardGroup keyboard = new InLineKeyboardGroup();
                if (course.equals("1")) {
                    IIKDirectionOfGroupFirstCourse group = new IIKDirectionOfGroupFirstCourse(callbackData);
                    sendMessage(message1, keyboard.directionGroups(group.returnList()));
                } else if (course.equals("2")) {
                    IIKDirectionOfGroupSecondCourse group = new IIKDirectionOfGroupSecondCourse(callbackData);
                    sendMessage(message1, keyboard.directionGroups(group.returnList()));
                } else if (course.equals("3")) {
                    IIKDirectionOfGroupThirdCourse group = new IIKDirectionOfGroupThirdCourse(callbackData);
                    sendMessage(message1, keyboard.directionGroups(group.returnList()));
                } else if (course.equals("4")) {
                    IIKDirectionOfGroupFourthCourse group = new IIKDirectionOfGroupFourthCourse(callbackData);
                    sendMessage(message1, keyboard.directionGroups(group.returnList()));
                } else {
                    IIKDirectionOfGroupFifthCourse group = new IIKDirectionOfGroupFifthCourse(callbackData);
                    sendMessage(message1, keyboard.directionGroups(group.returnList()));
                }
            } else if (courseSelection) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText("Вы выбрали " + callbackData + " курс");
                course = callbackData;
                courseSelection = false;
                directionSelection = true;
                sendMessage(message);
                SendMessage message1 = new SendMessage();
                message1.setChatId(chatId);
                message1.setText("Выберите напрвление");
                InlineKeyboardButtonUser keyboard = new InlineKeyboardButtonUser();
                sendMessage(message1, keyboard.directionsCourse(callbackData));
            } else if (groupSelection) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText("Вы выбрали группу №" + callbackData);
                idDirection += callbackData;
                groupSelection = false;
                sendMessage(message);
                IIKCoursesID courseID = new IIKCoursesID();
                Parser parser = new Parser(courseID.map.get(idDirection), numberOfWeek, false);
                RealDate realDate = new RealDate();
                try {
                    realDate.getNumberOfWeek(update.getCallbackQuery().getMessage().getDate());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                timeTable = parser.Print();
                SendMessage message1 = new SendMessage();
                message1.setChatId(chatId);
                message1.setText(String.valueOf(timeTable.get(0)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message1, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("пн")) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(0)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("вт")) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(1)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("ср")) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(2)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("чт")) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(3)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("пт")) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(4)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("сб")) {
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(5)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("след")) {
                numberOfWeek += 1;
                IIKCoursesID courseID = new IIKCoursesID();
                Parser parser = new Parser(courseID.map.get(idDirection), numberOfWeek, false);
                timeTable = parser.Print();
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(0)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            } else if (callbackData.equals("пред")) {
                if (numberOfWeek > 1)
                    numberOfWeek -= 1;
                IIKCoursesID courseID = new IIKCoursesID();
                Parser parser = new Parser(courseID.map.get(idDirection), numberOfWeek, false);
                timeTable = parser.Print();
                EditMessageText message = new EditMessageText();
                message.setChatId(chatId);
                message.setMessageId((int) messageId);
                message.setText(String.valueOf(timeTable.get(0)));
                InLineKeyboardWeekday keyboard = new InLineKeyboardWeekday();
                sendMessage(message, keyboard.choiceOfWeekday());
            }
        }
    }


    private void keyboardAuthorsProject(long chatId) {
        SendMessage message = new SendMessage();
        message.enableHtml(true);
        message.setChatId(chatId);
        message.setText("Студенты 1 курса Самарского Университета\n\n<a href='https://vk.com/etsukanov0'><i>Егор Цуканов</i></a>\nБИО: Разработчик (Автор идеи)" +
                "\n\n<a href='https://vk.com/arsenk1ng'><i>Арсений Замулин</i></a>\nБИО: Разработчик");

        ReplyKeyboardUser replyKeyboard = new ReplyKeyboardUser();

        message.setReplyMarkup(replyKeyboard.keyboardAuthorsProject());
        sendMessage(message);
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void sendMessage(SendMessage message, InlineKeyboardMarkup keyboard) {
        try {
            message.setReplyMarkup(keyboard);
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void sendMessage(SendMessage message, ReplyKeyboard keyboard) {
        try {
            message.setReplyMarkup(keyboard);
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void sendMessage(EditMessageText message, InlineKeyboardMarkup keyboard) {
        try {
            message.setReplyMarkup(keyboard);
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void sendMessage(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void keyboardStart(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        ReplyKeyboardUser replyKeyboard = new ReplyKeyboardUser();

        message.setReplyMarkup(replyKeyboard.keyboardStart());
        sendMessage(message);

    }
}