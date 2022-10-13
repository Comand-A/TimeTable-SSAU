package org.example.Telegram.Service;

import org.example.Telegram.KeyBoard.InlineKeyboardButtonUser;
import org.example.Telegram.KeyBoard.ReplyKeyboardUser;
import org.example.Telegram.Model.Emoji;
import org.example.Telegram.Model.ListDirection;
import org.example.Telegram.Model.NameButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {

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
                    getAuthorsProject(chatId, "Цуканов Егор(Владелец проекта)");
                    break;
                default:
                    keyboardStart(chatId, "sorry was not recognized");
                    break;
            }
        }else if(update.hasCallbackQuery()){
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if(callbackData.equals(ListDirection.FIIT.get())){

            }
            else if (callbackData.equals(ListDirection.PMF.get())){

            }
        }
    }

    private void choiceOfDirection(long chatId) {
        InlineKeyboardButtonUser inlineKeyboardButtonUser = new InlineKeyboardButtonUser();
        SendMessage message = inlineKeyboardButtonUser.choiceOfDirection(chatId);
        sendMessage(message);
    }

    private void getAuthorsProject(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        ReplyKeyboardUser replyKeyboard = new ReplyKeyboardUser();

        message.setReplyMarkup(replyKeyboard.getAuthorsProject());
        sendMessage(message);
    }

    public void sendMessage(SendMessage message) {
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
