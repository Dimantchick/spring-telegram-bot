package com.github.dimantchick.telegrambot.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

public interface DocumentWithKeyboardMessage extends KeyboardMessage {

    SendDocument getFileMessage(long chatId);
}
