package com.github.dimantchick.telegrambot.services.telegram.impl;

import com.github.dimantchick.telegrambot.services.telegram.UpdateUtilsService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UpdateUtilsServiceImpl implements UpdateUtilsService {

    @Override
    public Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        } else {
            return 0L;
        }
    }
}
