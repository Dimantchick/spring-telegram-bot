package com.github.dimantchick.telegrambot.services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateUtilsService {

    Long getChatId(Update update);
}
