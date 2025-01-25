package com.github.dimantchick.telegrambot.services;

import com.github.dimantchick.telegrambot.entities.BotUser;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Optional;

public interface BotUserService {

    void saveUserFromCallBack(CallbackQuery callbackQuery);

    void save(BotUser botUser);

    boolean isAgreeLicense(long chatId);

    Optional<BotUser> getBotUser(long chatId);

}
