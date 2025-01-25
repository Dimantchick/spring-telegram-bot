package com.github.dimantchick.telegrambot.handlers.commands;

import com.github.dimantchick.telegrambot.config.TelegramParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.CommandRegistry;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
public class CustomHelpCommand extends HelpCommand {

    private static final String COMMAND_IDENTIFIER = "help";

    private final CommandRegistry commandRegistry;


    public CustomHelpCommand(CommandRegistry commandRegistry, TelegramParams telegramParams) {
        super(COMMAND_IDENTIFIER, telegramParams.getHelpDescription(), telegramParams.getHelpExtendedDescription());
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void execute(TelegramClient telegramClient, User user, Chat chat, String[] arguments) {
        if (arguments.length > 0) {
            IBotCommand command = commandRegistry.getRegisteredCommand(arguments[0]);
            String reply = getManText(command);
            try {
                telegramClient.execute(SendMessage.builder().chatId(chat.getId()).text(reply).parseMode("HTML").build());
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        } else {
            String reply = getHelpText(commandRegistry);
            try {
                telegramClient.execute(SendMessage.builder().chatId(chat.getId()).text(reply).parseMode("HTML").build());
            } catch (TelegramApiException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }
}
