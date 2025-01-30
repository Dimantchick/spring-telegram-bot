package com.github.dimantchick.telegrambot.services.telegram.impl;

import com.github.dimantchick.telegrambot.services.telegram.TelegramExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.message.Message;
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
            log.error("Error sending message", e);
            return null;
        }
    }

    @Override
    public <Media extends SendMediaBotMethod<Message>> Message execute(Media media) {
        try {
            switch (media) {
                case SendAnimation animation -> {return telegramClient.execute(animation);}
                case SendAudio audio -> {return telegramClient.execute(audio);}
                case SendDocument document -> {return telegramClient.execute(document);}
                case SendPhoto photo -> {return telegramClient.execute(photo);}
                case SendSticker sticker -> {return telegramClient.execute(sticker);}
                case SendVideo video -> {return telegramClient.execute(video);}
                case SendVideoNote videoNote -> {return telegramClient.execute(videoNote);}
                case SendVoice voice -> {return telegramClient.execute(voice);}
                default -> throw new IllegalStateException("Unexpected value: " + media);
            }
        } catch (TelegramApiException e) {
            log.error("Error sending document", e);
            return null;
        }
    }
}
