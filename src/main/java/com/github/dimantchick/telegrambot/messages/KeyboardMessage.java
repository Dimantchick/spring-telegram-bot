package com.github.dimantchick.telegrambot.messages;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface KeyboardMessage {
    InlineKeyboardMarkup getKeyboard();

    @NonNull
    String getMessageText();

    SendMessage getMessage(long chatId);
}
