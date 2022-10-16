package org.example.Telegram.Service;

import org.example.DirectionSSAU.IIKFirstCourse;
import org.example.Parser.Day;
import org.example.Parser.Parser;
import org.example.Telegram.KeyBoard.InlineKeyboardButtonUser;
import org.example.Telegram.KeyBoard.ReplyKeyboardUser;
import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.Direction;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


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
                switch (messageText) {
                case "/start":
                    keyboardStart(chatId, Emoji.WELCOME.get());
                    keyboardStart(chatId, "Привет, " + update.getMessage().getChat().getFirstName() + ", рад тебя видеть");
                    break;
                case "Назад":
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
                    callParser(chatId);
                    break;
                case "Текущая":
                    numberOfWeek = 0;
                    callParser(chatId);
                    break;
                case "Следующая⏩":
                    numberOfWeek = 1;
                    callParser(chatId);
                    break;
                case "Своя неделя":
                    numberOfWeek = Integer.parseInt(update.getMessage().getText());
                    callParser(chatId);
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

            if (callbackData.equals(Direction.FIIT.get())) {
                executeEditMessageText(chatId, messageId, Direction.FIIT.get(), 4);
            } else if (callbackData.equals(Direction.PMF.get())) {
                executeEditMessageText(chatId, messageId, Direction.PMF.get(), 2);
            } else if (callbackData.equals(Direction.PMI.get())) {
                executeEditMessageText(chatId, messageId, Direction.PMI.get(), 6);
            } else if (callbackData.equals(Direction.RADIO_TECH.get())) {
                executeEditMessageText(chatId, messageId, Direction.RADIO_TECH.get(), 1);
            } else if (callbackData.equals(Direction.ELECTRONICS.get())) {
                executeEditMessageText(chatId, messageId, Direction.ELECTRONICS.get(), 1);
            } else if (callbackData.equals(Direction.IVT.get())) {
                executeEditMessageText(chatId, messageId, Direction.IVT.get(), 6);
            } else if (callbackData.equals(Direction.BIOTECH.get())) {
                executeEditMessageText(chatId, messageId, Direction.BIOTECH.get(), 2);
            } else if (callbackData.equals("1") || callbackData.equals("2") || callbackData.equals("3") || callbackData.equals("4") || callbackData.equals("5") || callbackData.equals("6")) {
                directionOfGroup.add(callbackData);
                EditMessageText messageText = new EditMessageText();
                messageText.setChatId(String.valueOf(chatId));
                messageText.setText("Вы выбрали группу № " + callbackData);
                messageText.setMessageId((int) messageId);
                sendMessageEditText(messageText);

                initializeDirectionIIK(directionOfGroup.get(0),directionOfGroup.get(1));

                keyboardChooseWeek(chatId);
            }
        }
    }

    private void initializeDirectionIIK(String direction, String numberGroup) {
        IIKFirstCourse iikFirstCourse = new IIKFirstCourse(direction,numberGroup);
        idDirection = iikFirstCourse.initializeDirectionUser();
    }

    private void callParser(long chatId) {
        Parser parser = new Parser(idDirection, numberOfWeek);
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
        message.setChatId(chatId);
        message.setText("Цуканов Егор(Владелец проекта)\n\n\nАрсений Замулин(Вспомогательные руки)");

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

    private void sendMessageEditText(EditMessageText message) {
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

    private void executeEditMessageText(long chatId, long messageId, String nameDirection, int amountGroup) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(String.valueOf(chatId));
        messageText.setText("Вы выбрали направление " + nameDirection);
        messageText.setMessageId((int) messageId);

        sendMessageEditText(messageText);

        InlineKeyboardButtonUser lineKeyboard = new InlineKeyboardButtonUser();
        sendMessage(lineKeyboard.choiceOfGroupNumber(chatId, amountGroup));

        directionOfGroup = new ArrayList<>();
        directionOfGroup.add(nameDirection);

    }
}
