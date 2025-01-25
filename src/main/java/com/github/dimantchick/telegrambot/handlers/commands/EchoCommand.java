package com.github.dimantchick.telegrambot.handlers.commands;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EchoCommand extends BotCommand {

    private static final String COMMAND_IDENTIFIER = "echo";
    private static final String COMMAND_DESCRIPTION = "Echo command arguments";

    public EchoCommand() {
        super(COMMAND_IDENTIFIER, COMMAND_DESCRIPTION);
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] arguments) {
        String args = String.join(" ", arguments);
        try {
            telegramClient.execute(
                    SendMessage.builder()
                            .chatId(chat.getId())
                            .text(String.format("Echo command arguments: %s", args))
                            .parseMode("HTML")
                            .build()
            );
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
