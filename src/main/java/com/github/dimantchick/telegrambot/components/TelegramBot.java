package com.github.dimantchick.telegrambot.components;

import com.github.dimantchick.telegrambot.config.TelegramParams;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;

@Component
public class TelegramBot implements SpringLongPollingBot {

    private final LongPollingMultiThreadUpdateConsumer consumer;
    private final TelegramParams telegramParams;
    private final ICommandRegistry commandRegistry;
    private final IBotCommand[] botCommands;

    public TelegramBot(LongPollingMultiThreadUpdateConsumer consumer, TelegramParams telegramParams, ICommandRegistry commandRegistry, IBotCommand[] botCommands) {
        this.consumer = consumer;
        this.telegramParams = telegramParams;
        this.commandRegistry = commandRegistry;
        this.botCommands = botCommands;
    }

    /**
     * Register all command beans
     */
    @PostConstruct
    public void init() {
        commandRegistry.registerAll(botCommands);
    }

    @Override
    public String getBotToken() {
        return telegramParams.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return consumer;
    }
}