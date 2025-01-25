package com.github.dimantchick.telegrambot.services.telegram.impl;

import com.github.dimantchick.telegrambot.services.telegram.TelegramExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.Serializable;

@Slf4j
@Service
public class TelegramExecutorServiceImpl implements TelegramExecutorService {

    private final TelegramClient telegramClient;

    public TelegramExecutorServiceImpl(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method) {
        try {
            return telegramClient.execute(method);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки согласия с соглашением", e);
            return null;
        }
    }
}
