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
public class AcceptAgreementCallbackUpdateHandler implements CallbackUpdateHandler {

    public static final String ACCEPT_AGREEMENT = "accept_agreement";
    private final AgreementServiceImpl agreementServiceImpl;

    public AcceptAgreementCallbackUpdateHandler(AgreementServiceImpl agreementServiceImpl) {
        this.agreementServiceImpl = agreementServiceImpl;
    }

    @Override
    public boolean isApplicable(Update update) {
        return update.hasCallbackQuery() && ACCEPT_AGREEMENT.equals(update.getCallbackQuery().getData());
    }

    @Override
    public void handle(Update update) {
        agreementServiceImpl.acceptAgreement(update);
    }
}
