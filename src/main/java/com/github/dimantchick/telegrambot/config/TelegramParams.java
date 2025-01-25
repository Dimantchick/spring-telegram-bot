package com.github.dimantchick.telegrambot.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramParams {
    private String token;
    private int poolSize;
    private String botName;

    private String helpDescription = "shows all commands. Use /help [command] for more info";
    private String helpExtendedDescription = "This command displays all commands the bot has to offer.\n /help [command] can display deeper information";

    private boolean enableLicenseAgreement;
    private int agreementVersion;
}
