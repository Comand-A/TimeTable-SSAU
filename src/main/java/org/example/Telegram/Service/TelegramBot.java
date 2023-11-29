package org.example.Telegram.Service;

import org.example.DirectionSSAU.IIK.IIKCourse;
import org.example.DirectionSSAU.IIK.IIKDirectionOfGroups.*;
import org.example.DirectionSSAU.IIK.IIKCorseID.IIKCoursesID;
import org.example.Parser.Parser;
import org.example.Telegram.KeyBoard.Keyboards;
import org.example.Telegram.Model.Emoji;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


public class TelegramBot extends TelegramLongPollingBot {
    private Map<String, Person> users = new HashMap<>();
    private SendMessage message;
    private String chatId;

    public String getBotUsername() {
        return "@TimeTableSSAUBot";
    }

    public String getBotToken() {
        return "5600469375:AAGiD2pqJ78SYQ2NvuyGPg8CBQ3dkEst2js";
    }


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = String.valueOf(update.getMessage().getChatId());
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    sendMessage(Emoji.WELCOME.get(), Keyboards.keyboardStart());
                    sendMessage("Привет, " + update.getMessage().getChat().getFirstName() + ", рад тебя видеть", Keyboards.keyboardStart());
                    break;
                case "↩Назад↩":
                    sendMessage("Вернулись...", Keyboards.keyboardStart());
                    break;
                case "\uD83D\uDE80К расписанию\uD83D\uDE80":
                    sendMessage("Выберите ваш курс", Keyboards.numberOfCourse());
                    if (users.containsKey(chatId)) {
                        users.replace(chatId, new Person());
                    } else {
                        users.put(chatId, new Person());
                    }
                    break;
                case "\uD83D\uDC68\u200D\uD83D\uDCBBАвторы\uD83D\uDC68\u200D\uD83D\uDCBB":
                    keyboardAuthorsProject(chatId);
                    break;
                case "моё расписание":
                    if (users.containsKey(chatId) && users.get(chatId).weekMemory != null) {
                        users.get(chatId).week = users.get(chatId).weekMemory;
                        sendMessage(String.valueOf(users.get(chatId).week.get(0)), Keyboards.week());
                    } else
                        sendMessage("Ты еще не сохранил рассписание");
                    break;
                case "Посхалка":
                    sendMessage("Комплимент дня: ты самый - самый", Keyboards.keyboardStart());
                    break;
                default:
                    sendMessage("sorry was not recognized", Keyboards.keyboardStart());
                    break;
            }

        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            if (users.containsKey(chatId)) {
                if (callbackData.contains("COURSE_BTN")) {
                    String s = String.valueOf(callbackData.charAt(0));
                    editMessage("Выберете направление:", messageId, Keyboards.directionIIK(IIKCourse.coursesOfDirection.get(s)));
                    users.get(chatId).course = Integer.parseInt(s);
                } else if (callbackData.contains("D_BTN") && users.get(chatId).course != 0) {
                    users.get(chatId).idDirection[0] = callbackData.substring(0, callbackData.length() - 5);
                    editMessage("Выберете группу:", messageId, Keyboards.groupIIK(
                            IIKDirectionOfGroupCourses.choiceCourse(
                                    users.get(chatId).idDirection[0] + users.get(chatId).idDirection[1], users.get(chatId).course)));
                } else if (callbackData.contains("G_BTN") && users.get(chatId).idDirection[0] != "") {
                    users.get(chatId).idDirection[1] = callbackData.substring(0, callbackData.length() - 5);
                    deletingMessage((int) messageId);
                    callParser(users.get(chatId).idDirection[0] + users.get(chatId).idDirection[1], users.get(chatId).numberOfWeek);
                } else if (callbackData.contains("DAY") && users.get(chatId).week != null) {
                    editMessage(String.valueOf(users.get(chatId).week.get(Integer.parseInt(String.valueOf(callbackData.charAt(0))) - 1)), messageId, Keyboards.week());
                } else if (callbackData.contains("MEMORY") && users.get(chatId).week != null) {
                    users.get(chatId).weekMemory = users.get(chatId).week;
                } else if (callbackData.contains("NEXT_WEEK") && users.get(chatId).week != null) {
                    deletingMessage((int) messageId);
                    users.get(chatId).numberOfWeek++;
                    callParser(users.get(chatId).idDirection[0] + users.get(chatId).idDirection[1], users.get(chatId).numberOfWeek);
                } else if (callbackData.contains("PAST_WEEK") && users.get(chatId).week != null) {
                    deletingMessage((int) messageId);
                    users.get(chatId).numberOfWeek--;
                    callParser(users.get(chatId).idDirection[0] + users.get(chatId).idDirection[1], users.get(chatId).numberOfWeek);
                }
            } else {
                sendMessage("Нажми на кнопку \"\uD83D\uDE80К расписанию\uD83D\uDE80\"");
            }
        }

    }

    private void callParser(String idDirection, long numberOfWeek) {
        Parser parser = new Parser(IIKCoursesID.map.get(idDirection), numberOfWeek);
        users.get(chatId).week = parser.Print();
        sendMessage(String.valueOf(users.get(chatId).week.get(0)), Keyboards.week());
    }


    private void keyboardAuthorsProject(String chatId) {
        SendMessage message = new SendMessage();
        message.enableHtml(true);
        message.setChatId(chatId);
        message.setText("<a href='https://vk.com/etsukanov0'><i>Егор Цуканов</i></a>" +
                "\n\n<a href='https://vk.com/arsenk1ng'><i>Арсений Замулин</i></a>");
        sendMessage(message);
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void sendMessage(String text, ReplyKeyboardMarkup keyboardMarkup) {
        message = new SendMessage();
        message.setReplyMarkup(keyboardMarkup);
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException ignored) {
        }
    }

    private void sendMessage(String text, InlineKeyboardMarkup keyboardMarkup) {
        message = new SendMessage();
        message.setReplyMarkup(keyboardMarkup);
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException ignored) {
        }
    }

    private void sendMessage(String text) {
        message = new SendMessage();
        //message.setReplyMarkup(keyboard.menuKeyboardWithSubscription());
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException ignored) {
        }
    }

    private void sendMessage(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void editMessage(String message, long messageId) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(message);
        messageText.setMessageId((int) messageId);
        try {
            execute(messageText);
        } catch (TelegramApiException e) {
        }
    }

    private void editMessage(String message, long messageId, InlineKeyboardMarkup keyboardMarkup) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(message);
        messageText.setReplyMarkup(keyboardMarkup);
        messageText.setMessageId((int) messageId);
        try {
            execute(messageText);
        } catch (TelegramApiException e) {
        }
    }

    private void deletingMessage(int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        try {
            execute(deleteMessage);
        } catch (TelegramApiException ignored) {
        }
    }
}
