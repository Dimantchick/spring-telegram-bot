package com.github.dimantchick.telegrambot.services.impl;

import com.github.dimantchick.telegrambot.config.TelegramParams;
import com.github.dimantchick.telegrambot.messages.agreement.AgreementMessage;
import com.github.dimantchick.telegrambot.services.AgreementService;
import com.github.dimantchick.telegrambot.services.BotUserService;
import com.github.dimantchick.telegrambot.services.telegram.TelegramExecutorService;
import com.github.dimantchick.telegrambot.services.telegram.UpdateUtilsService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.dimantchick.telegrambot.handlers.callbacks.AcceptAgreementCallbackUpdateHandler.ACCEPT_AGREEMENT;
import static com.github.dimantchick.telegrambot.handlers.callbacks.DeclineAgreementCallbackUpdateHandler.DECLINE_AGREEMENT;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final BotUserService botUserService;

    private final TelegramExecutorService telegramExecutorService;

    private final AgreementMessage agreementMessage;

    private final UpdateUtilsService updateUtilsService;

    private final TelegramParams telegramParams;

    public AgreementServiceImpl(BotUserService botUserService,
                                TelegramExecutorService telegramExecutorService,
                                AgreementMessage agreementMessage,
                                UpdateUtilsService updateUtilsService,
                                TelegramParams telegramParams) {
        this.botUserService = botUserService;
        this.telegramExecutorService = telegramExecutorService;
        this.agreementMessage = agreementMessage;
        this.updateUtilsService = updateUtilsService;
        this.telegramParams = telegramParams;
    }

    @Override
    public boolean checkAgreement(Update update) {
        if (!telegramParams.isEnableLicenseAgreement()) {
            // License agreement disabled
            return true;
        }
        // Agreement accepted or accept|decline callback
        return botUserService.isAgreeLicense(updateUtilsService.getChatId(update))
                || update.hasCallbackQuery()
                && (DECLINE_AGREEMENT.equals(update.getCallbackQuery().getData())
                    || ACCEPT_AGREEMENT.equals(update.getCallbackQuery().getData()));
    }

    @Override
    public void acceptAgreement(Update update) {
        botUserService.saveUserFromCallBack(update.getCallbackQuery());
        Long chatId = updateUtilsService.getChatId(update);
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        executeAgreementAccept(chatId, messageId);
    }

    private void executeAgreementAccept(long chatId, int messageId) {
        EditMessageText new_message = EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text("You accepted agreement.")
                .replyMarkup(null)
                .build();
        telegramExecutorService.execute(new_message);
    }

    @Override
    public void declineAgreement(Update update) {
        Long chatId = updateUtilsService.getChatId(update);
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        executeAgreementDecline(chatId, messageId);
    }

    private void executeAgreementDecline(long chatId, int messageId) {
        EditMessageText new_message = EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text("You decline agreement and can not use this bot.")
                .replyMarkup(null)
                .build();
        telegramExecutorService.execute(new_message);
    }

    @Override
    public void sendAgreement(Update update) {
        telegramExecutorService.execute(agreementMessage.getMessage(updateUtilsService.getChatId(update)));
    }
}
