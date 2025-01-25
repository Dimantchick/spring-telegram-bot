package com.github.dimantchick.telegrambot.handlers.callbacks;

import com.github.dimantchick.telegrambot.handlers.CallbackUpdateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@Order(Integer.MAX_VALUE) // Last ordered
public class DefaultCallbackUpdateHandler implements CallbackUpdateHandler {
    @Override
    public boolean isApplicable(Update update) {
        return true;
    }

    @Override
    public void handle(Update update) {
        log.warn("Unhandled callback update {} on message {}", update.getCallbackQuery().getData(), update.getCallbackQuery().getData());
    }
}
