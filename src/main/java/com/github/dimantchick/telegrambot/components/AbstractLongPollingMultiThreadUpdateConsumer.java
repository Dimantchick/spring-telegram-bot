package com.github.dimantchick.telegrambot.components;

import org.telegram.telegrambots.extensions.bots.commandbot.CommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.concurrent.Executor;

public abstract class AbstractLongPollingMultiThreadUpdateConsumer implements LongPollingMultiThreadUpdateConsumer, CommandBot {

    private final Executor executor;

    protected AbstractLongPollingMultiThreadUpdateConsumer(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void consume(List<Update> updates) {
        updates.forEach((update) -> {
            executor.execute(() -> {
                this.consume(update);
            });
        });
    }

}
