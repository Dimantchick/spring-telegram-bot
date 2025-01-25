package com.github.dimantchick.telegrambot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotUser {
    @Id
    private Long chatId;

    private String username;

    private int acceptedAggrementVersion = 0;
}
