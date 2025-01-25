package com.github.dimantchick.telegrambot.repositories;

import com.github.dimantchick.telegrambot.entities.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotUserRepository extends JpaRepository<BotUser, Long> {

}
