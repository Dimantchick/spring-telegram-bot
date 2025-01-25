package com.github.dimantchick.telegrambot.services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface AgreementService {

    boolean checkAgreement(Update update);

    void acceptAgreement(Update update);

    void declineAgreement(Update update);

    void sendAgreement(Update update);
}
