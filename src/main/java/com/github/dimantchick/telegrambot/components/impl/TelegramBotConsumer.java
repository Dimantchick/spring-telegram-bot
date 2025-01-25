package com.github.dimantchick.telegrambot.components.impl;

import com.github.dimantchick.telegrambot.components.AbstractLongPollingMultiThreadUpdateConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.CommandRegistry;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import java.util.concurrent.Executor;

@Slf4j
@Component
public class TelegramBotConsumer extends AbstractLongPollingMultiThreadUpdateConsumer {

    private final CommandRegistry commandRegistry;

    protected TelegramBotConsumer(@Qualifier("botExecutor") Executor executor, CommandRegistry commandRegistry) {
        super(executor);
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && !update.hasCallbackQuery()) {
            Message message = update.getMessage();
            if (message.isCommand() && !filter(message)) {
                if (!commandRegistry.executeCommand(message)) {
                    //we have received a not registered command, handle it as invalid
                    processInvalidCommandUpdate(update);
                }
                return;
            }
        } else if (update.hasCallbackQuery()) {
            processCallbackUpdate(update);
        } else if (update.hasEditedMessage()) {
            // implement actions on user edit message, if you need
        }
        processNonCommandUpdate(update);
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        log.info("Processing non-command update {}", update);
    }

    public void processCallbackUpdate(Update update) {
        log.info("Processing callback update {}", update);
    }

    @Override
    public void processInvalidCommandUpdate(Update update) {
        super.processInvalidCommandUpdate(update);
    }

    @Override
    public boolean filter(Message message) {
        return super.filter(message);
    }
}
