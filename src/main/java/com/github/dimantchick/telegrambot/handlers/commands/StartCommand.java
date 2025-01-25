package com.github.dimantchick.telegrambot.handlers.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
public class StartCommand extends BotCommand {

    private static final String COMMAND_IDENTIFIER = "start";
    private static final String COMMAND_DESCRIPTION = "Start bot";

    public StartCommand() {
        super(COMMAND_IDENTIFIER, COMMAND_DESCRIPTION);
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] arguments) {
        //TODO Implement command execution
        try {
            telegramClient.execute(
                    SendMessage.builder()
                            .chatId(chat.getId())
                            .text("Welcome to spring bot")
                            .parseMode("HTML")
                            .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
