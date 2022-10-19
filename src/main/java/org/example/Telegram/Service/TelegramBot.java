package org.example.Telegram.Service;

import org.example.DirectionSSAU.IIKDirectionOfGroupFirstCourse;
import org.example.DirectionSSAU.IIKFirstCourseId;
import org.example.Parser.Day;
import org.example.Parser.Parser;
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
                        choiceOfDirection(chatId);
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

            if (checkAvailabilityDirection(callbackData))
                executeEditMessageText(chatId, messageId, callbackData, getDirectionOfGroup(callbackData), "Вы выбрали направление ", true);
            else {
                directionOfGroup.add(callbackData);
                executeEditMessageText(chatId, messageId, callbackData, null, "Вы выбрали группу № ", false);
            }
        }
    }

    public int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    private void executeEditMessageText(long chatId, long messageId, String nameDirection, List<String> amountGroup, String message, boolean answer) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(String.valueOf(chatId));
        messageText.setText(message + nameDirection);
        messageText.setMessageId((int) messageId);

        sendMessage(messageText);
        if (answer) {
            InlineKeyboardButtonUser lineKeyboard = new InlineKeyboardButtonUser();
            sendMessage(lineKeyboard.setOfGroupNumber(chatId, amountGroup));
            directionOfGroup = new ArrayList<>(Collections.singletonList(nameDirection));
        } else {
            getIdDirectionUser(directionOfGroup.get(0), directionOfGroup.get(1));
            keyboardChooseWeek(chatId);
        }
    }

    private void getIdDirectionUser(String direction, String numberGroup) {
        IIKFirstCourseId iikFirstCourseId = new IIKFirstCourseId(direction, numberGroup);
        idDirection = iikFirstCourseId.getIdDirectionUser();
    }

    private List<String> getDirectionOfGroup(String direction) {
        IIKDirectionOfGroupFirstCourse iikFirstCourse = new IIKDirectionOfGroupFirstCourse(direction);
        return iikFirstCourse.getDirectionUser();
    }

    private boolean checkAvailabilityDirection(String direction) {
        IIKDirectionOfGroupFirstCourse iikFirstCourse = new IIKDirectionOfGroupFirstCourse(direction);
        return iikFirstCourse.checkAvailabilityDirection();
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

    private void choiceOfDirection(long chatId) {
        InlineKeyboardButtonUser inlineKeyboardButtonUser = new InlineKeyboardButtonUser();
        SendMessage message = inlineKeyboardButtonUser.choiceOfDirection(chatId);
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
