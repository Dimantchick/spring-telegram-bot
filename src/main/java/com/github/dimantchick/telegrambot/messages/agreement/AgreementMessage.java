package com.github.dimantchick.telegrambot.messages.agreement;


import com.github.dimantchick.telegrambot.messages.KeyboardMessage;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

@Component
public class AgreementMessage implements KeyboardMessage {

    private static final String AGREE_TEXT = "✅ Accept";
    private static final String DISAGREE_TEXT = "❌ Decline";

    private static final InlineKeyboardButton AGREE_BUTTON = InlineKeyboardButton.builder().text(AGREE_TEXT).callbackData("accept_agreement").build();
    private static final InlineKeyboardButton DISAGREE_BUTTON = InlineKeyboardButton.builder().text(DISAGREE_TEXT).callbackData("decline_agreement").build();

    private static final InlineKeyboardRow AGREEMENT_BUTTONS_ROW = new InlineKeyboardRow(AGREE_BUTTON, DISAGREE_BUTTON);

    @Override
    public InlineKeyboardMarkup getKeyboard() {
        return InlineKeyboardMarkup.builder()
                .keyboardRow(AGREEMENT_BUTTONS_ROW)
                .build();
    }

    @Override
    public @NonNull String getMessageText() {
        return "<b>Before using this bot you must accept agreement.</b>\n" +
                "This is sample bot license text.";
    }

    @Override
    public SendMessage getMessage(long chatId) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(getMessageText())
                .parseMode("HTML")
                .replyMarkup(getKeyboard())
                .build();
    }
}
