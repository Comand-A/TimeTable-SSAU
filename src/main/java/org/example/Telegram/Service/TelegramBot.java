package org.example.Telegram.Service;

import org.example.DirectionSSAU.IIK.IIKCourse;
import org.example.DirectionSSAU.IIK.IIKDirectionOfGroups.*;
import org.example.DirectionSSAU.IIK.IIKCorseID.IIKFirstCourseID;
import org.example.Parser.Day;
import org.example.Parser.Parser;
import org.example.Telegram.KeyBoard.InLineKeyboardButtonOfCourses;
import org.example.Telegram.KeyBoard.InlineKeyboardButtonUser;
import org.example.Telegram.KeyBoard.ReplyKeyboardUser;
import org.example.Telegram.Model.Emoji;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


public class TelegramBot extends TelegramLongPollingBot {
    private ArrayList<String> directionOfGroup;
    private long numberOfWeek;
    private List<Day> timeTable;
    private String idDirection;

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
            if (tryParseInt(messageText, 915989239) != 915989239) {
                numberOfWeek = Integer.parseInt(update.getMessage().getText());
                callParser(chatId, true);
            } else {
                switch (messageText) {
                    case "/start":
                        keyboardStart(chatId, Emoji.WELCOME.get());
                        keyboardStart(chatId, "Привет, " + update.getMessage().getChat().getFirstName() + ", рад тебя видеть");
                        break;
                    case "↩Назад↩":
                        keyboardStart(chatId, "Вернулись...");
                        break;
                    case "\uD83D\uDE80К расписанию\uD83D\uDE80":
                        choiceOfCourse(chatId);
                        break;
                    case "\uD83D\uDC68\u200D\uD83D\uDCBBАвторы\uD83D\uDC68\u200D\uD83D\uDCBB":
                        keyboardAuthorsProject(chatId);
                        break;
                    case "⏪Предыдущая":
                        numberOfWeek = -1;
                        callParser(chatId, false);
                        break;
                    case "Текущая":
                        numberOfWeek = 0;
                        callParser(chatId, false);
                        break;
                    case "Следующая⏩":
                        numberOfWeek = 1;
                        callParser(chatId, false);
                        break;
                    case "Своя неделя":
                        SendMessage message = new SendMessage();
                        message.setChatId(chatId);
                        message.setText("Введите свою неделю");
                        sendMessage(message);
                        break;
                    case "Посхалка":
                        keyboardStart(chatId, "Комплимент дня: ты самый - самый");
                        break;
                    default:
                        keyboardStart(chatId, "sorry was not recognized");
                        break;
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (checkAvailabilityCoursesOfDirection(callbackData))
                executeEditMessageText(chatId, messageId, callbackData, getDirectionOfCourses(callbackData), "Вы выбрали Курс ");
            else if (checkAvailabilityDirection(callbackData, directionOfGroup.get(0)))
                executeEditMessageText(chatId, messageId, callbackData, getGroupOfDirection(callbackData, directionOfGroup.get(0)), "Вы выбрали направление ");
            else {
                directionOfGroup.add(callbackData);
                executeEditMessageText(chatId, messageId, callbackData, null, "Вы выбрали группу №");
            }
        }
    }

    private boolean checkAvailabilityCoursesOfDirection(String course) {
        IIKCourse iikCourse = new IIKCourse(course);
        return iikCourse.checkAvailabilityCoursesOfDirection();
    }

    public int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    private void executeEditMessageText(long chatId, long messageId, String nameDirection, List<String> volatileData, String message) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(String.valueOf(chatId));
        messageText.setText(message + nameDirection);
        messageText.setMessageId((int) messageId);

        sendMessage(messageText);
        if (checkAvailabilityCoursesOfDirection(nameDirection)) {
            InlineKeyboardButtonUser lineKeyboard = new InlineKeyboardButtonUser();
            sendMessage(lineKeyboard.choiceOfDirectionIIK(chatId, volatileData));
            directionOfGroup = new ArrayList<>(Collections.singletonList(nameDirection));
        } else if (checkAvailabilityDirection(nameDirection, directionOfGroup.get(0))) {
            InlineKeyboardButtonUser lineKeyboard = new InlineKeyboardButtonUser();
            sendMessage(lineKeyboard.setOfGroupNumber(chatId, volatileData));
            directionOfGroup.add(nameDirection);
        } else {
            getIdDirectionUser(directionOfGroup);
            keyboardChooseWeek(chatId);
        }
    }

    private void getIdDirectionUser(ArrayList<String> directionOfGroup) {
        IIKFirstCourseID iikFirstCourseId = new IIKFirstCourseID(directionOfGroup);
        idDirection = iikFirstCourseId.getIdDirectionUser();
    }

    private List<String> getDirectionOfCourses(String directionOfGroup) {
        IIKCourse iikCourse = new IIKCourse(directionOfGroup);
        return iikCourse.getDirectionUser();
    }

    private List<String> getGroupOfDirection(String direction, String course) {
        if (course.equals("1")) {
            IIKDirectionOfGroupFirstCourse iikFirstCourse = new IIKDirectionOfGroupFirstCourse(direction);
            return iikFirstCourse.getGroupUser();
        } else if (course.equals("2")) {
            IIKDirectionOfGroupSecondCourse iikFirstCourse = new IIKDirectionOfGroupSecondCourse(direction);
            return iikFirstCourse.getGroupUser();
        } else if (course.equals("3")) {
            IIKDirectionOfGroupThirdCourse iikFirstCourse = new IIKDirectionOfGroupThirdCourse(direction);
            return iikFirstCourse.getGroupUser();
        } else if (course.equals("4")) {
            IIKDirectionOfGroupFourthCourse iikFirstCourse = new IIKDirectionOfGroupFourthCourse(direction);
            return iikFirstCourse.getGroupUser();
        } else {
            IIKDirectionOfGroupFifthCourse iikFirstCourse = new IIKDirectionOfGroupFifthCourse(direction);
            return iikFirstCourse.getGroupUser();
        }
    }

    private boolean checkAvailabilityDirection(String direction, String course) {
        if (course.equals("1")) {
            IIKDirectionOfGroupFirstCourse iikFirstCourse = new IIKDirectionOfGroupFirstCourse(direction);
            return iikFirstCourse.checkAvailabilityDirection();
        } else if (course.equals("2")) {
            IIKDirectionOfGroupSecondCourse iikFirstCourse = new IIKDirectionOfGroupSecondCourse(direction);
            return iikFirstCourse.checkAvailabilityDirection();
        } else if (course.equals("3")) {
            IIKDirectionOfGroupThirdCourse iikFirstCourse = new IIKDirectionOfGroupThirdCourse(direction);
            return iikFirstCourse.checkAvailabilityDirection();
        } else if (course.equals("4")) {
            IIKDirectionOfGroupFourthCourse iikFirstCourse = new IIKDirectionOfGroupFourthCourse(direction);
            return iikFirstCourse.checkAvailabilityDirection();
        } else {
            IIKDirectionOfGroupFifthCourse iikFirstCourse = new IIKDirectionOfGroupFifthCourse(direction);
            return iikFirstCourse.checkAvailabilityDirection();
        }
    }

    private void callParser(long chatId, boolean criterion) {
        Parser parser = new Parser(idDirection, numberOfWeek, criterion);
        timeTable = parser.Print();
        SendMessage message = new SendMessage();
        for (Day s : timeTable) {
            message.setChatId(chatId);
            message.setText(String.valueOf(s));
            sendMessage(message);
        }
    }

    private void keyboardChooseWeek(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите учебную неделю");

        ReplyKeyboardUser replyKeyboard = new ReplyKeyboardUser();

        message.setReplyMarkup(replyKeyboard.keyboardChooseWeek());
        sendMessage(message);
    }

    private void choiceOfCourse(long chatId) {
        InLineKeyboardButtonOfCourses inLineKeyboardButtonOfCourses = new InLineKeyboardButtonOfCourses();
        SendMessage message = inLineKeyboardButtonOfCourses.choiceOfCourse(chatId);
        sendMessage(message);
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
