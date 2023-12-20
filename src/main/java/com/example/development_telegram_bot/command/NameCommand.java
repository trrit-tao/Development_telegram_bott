package com.example.development_telegram_bot.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Emum для хранения возможных команд.
 *
 * @see Command
 */
@RequiredArgsConstructor
@Getter
public enum NameCommand {
    /**
     * "/start", запускает бота.
     */
    START("/start"),

    /**
     * "/addElement", добавляет элемента в дерево.
     */
    ADD("/addElement"),

    /**
     * "/removeElement", удаляет элемент из дерева.
     */
    REMOVE("/removeElement"),

    /**
     * "/viewTree", просмотр структуры дерева.
     */
    VIEW("/viewTree"),

    /**
     * "/error", сообщает об ошибке при вводе команды.
     */
    ERROR("/error"),

    /**
     * "/help", получает информации о доступных командах.
     */
    HELP("/help");

    /**
     * Строковое представление команды.
     */
    private final String commandName;
}