package com.github.dimantchick.telegrambot.components;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackUpdateRegistry {

    void processCallbackUpdate(Update update);

}
