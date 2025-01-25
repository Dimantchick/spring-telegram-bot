package com.github.dimantchick.telegrambot.components.impl;


import com.github.dimantchick.telegrambot.handlers.CallbackUpdateHandler;
import com.github.dimantchick.telegrambot.components.CallbackUpdateRegistry;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CallbackUpdateRegistryImpl implements CallbackUpdateRegistry {

    private final CallbackUpdateHandler[] handlers;

    public CallbackUpdateRegistryImpl(CallbackUpdateHandler[] handlers) {
        this.handlers = handlers;
    }

    @Override
    public void processCallbackUpdate(Update update) {
        for (CallbackUpdateHandler handler : handlers) {
            if (handler.isApplicable(update)) {
                handler.handle(update);
                return;
            }
        }
    }
}
