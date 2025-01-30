package com.github.dimantchick.telegrambot.services.telegram;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaBotMethod;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.io.Serializable;

public interface TelegramExecutorService {
    <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method);

    <Media extends SendMediaBotMethod<Message>> Message execute(Media media);
}
