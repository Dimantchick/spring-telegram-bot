package com.github.dimantchick.telegrambot.services.impl;

import com.github.dimantchick.telegrambot.config.TelegramParams;
import com.github.dimantchick.telegrambot.entities.BotUser;
import com.github.dimantchick.telegrambot.repositories.BotUserRepository;
import com.github.dimantchick.telegrambot.services.BotUserService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Optional;

@Service
public class BotUserServiceImpl implements BotUserService {

    private final BotUserRepository botUserRepository;

    private final TelegramParams telegramParams;

    public BotUserServiceImpl(BotUserRepository botUserRepository, TelegramParams telegramParams) {
        this.botUserRepository = botUserRepository;
        this.telegramParams = telegramParams;
    }

    @Override
    public void saveUserFromCallBack(CallbackQuery callbackQuery) {
        String userName = callbackQuery.getFrom().getUserName();
        BotUser botUser = BotUser.builder()
                .chatId(callbackQuery.getMessage().getChatId())
                .username(userName)
                .acceptedAggrementVersion(telegramParams.getAgreementVersion())
                .build();
        save(botUser);
    }

    @Override
    public void save(BotUser botUser) {
        botUserRepository.save(botUser);
    }

    @Override
    public boolean isAgreeLicense(long chatId) {
        Optional<BotUser> botUser = getBotUser(chatId);
        return botUser.isPresent() && botUser.get().getAcceptedAggrementVersion() >= telegramParams.getAgreementVersion();
    }

    @Override
    public Optional<BotUser> getBotUser(long chatId) {
        return botUserRepository.findById(chatId);
    }
}
