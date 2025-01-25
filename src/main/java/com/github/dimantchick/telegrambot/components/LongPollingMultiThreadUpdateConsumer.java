package com.github.dimantchick.telegrambot.components;

import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface LongPollingMultiThreadUpdateConsumer extends LongPollingUpdateConsumer {
    void consume(Update var1);
}
