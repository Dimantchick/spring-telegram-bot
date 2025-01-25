package com.github.dimantchick.telegrambot.services;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;

import java.io.Serializable;

public interface TelegramExecutorService {
    <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method);
}
