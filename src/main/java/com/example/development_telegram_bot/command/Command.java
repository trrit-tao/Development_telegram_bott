package com.example.development_telegram_bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс для обработки команд из Telegram-бота.
 */
public interface Command {

    /**
     * @param update объект {@link Update}, содержит информацию о сообщении пользователя.
     */
    void execute(Update update);
}
