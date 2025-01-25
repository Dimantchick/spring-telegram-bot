package com.github.dimantchick.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackUpdateHandler {

    boolean isApplicable(Update update);

    void handle(Update update);
}
