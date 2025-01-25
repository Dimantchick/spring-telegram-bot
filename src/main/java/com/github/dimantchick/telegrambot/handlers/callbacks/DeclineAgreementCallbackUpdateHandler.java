package com.github.dimantchick.telegrambot.handlers.callbacks;

import com.github.dimantchick.telegrambot.handlers.CallbackUpdateHandler;
import com.github.dimantchick.telegrambot.services.impl.AgreementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@Order(0)
public class DeclineAgreementCallbackUpdateHandler implements CallbackUpdateHandler {

    public static final String DECLINE_AGREEMENT = "decline_agreement";
    private final AgreementServiceImpl agreementServiceImpl;

    public DeclineAgreementCallbackUpdateHandler(AgreementServiceImpl agreementServiceImpl) {
        this.agreementServiceImpl = agreementServiceImpl;
    }

    @Override
    public boolean isApplicable(Update update) {
        return update.hasCallbackQuery() && DECLINE_AGREEMENT.equals(update.getCallbackQuery().getData());
    }

    @Override
    public void handle(Update update) {
        agreementServiceImpl.declineAgreement(update);
    }
}
