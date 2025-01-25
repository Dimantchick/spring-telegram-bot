package com.github.dimantchick.telegrambot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.CommandRegistry;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.concurrent.Executor;

@Configuration
public class TelegramConfig {
    private static final String BOT_PARAMS = "botParams";

    /**
     * Параметры бота
     */
    @Bean(BOT_PARAMS)
    @ConfigurationProperties(prefix = "com.github.dimantchick.telegrambot")
    public TelegramParams pollerConfig() {
        return new TelegramParams();
    }

    @Bean(name = "botExecutor")
    public Executor botExecutor(@Qualifier(BOT_PARAMS) TelegramParams telegramParams) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(telegramParams.getPoolSize());
        executor.setMaxPoolSize(telegramParams.getPoolSize());
        executor.setThreadNamePrefix("BotThread-");
        executor.initialize();
        return executor;
    }

    @Bean
    public TelegramClient getTelegramClient(@Qualifier(BOT_PARAMS) TelegramParams telegramParams) {
        return new OkHttpTelegramClient(telegramParams.getToken());
    }

    @Bean
    public CommandRegistry getCommandRegistry(@Qualifier(BOT_PARAMS) TelegramParams telegramParams, TelegramClient telegramClient) {
        return new CommandRegistry(telegramClient, true, telegramParams::getBotName);
    }


}
