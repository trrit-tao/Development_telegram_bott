package com.example.development_telegram_bot.command;

import com.example.development_telegram_bot.service.BotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс, представляющий команду для отправки сообщения об ошибке при вводе команды
 *
 * @see Command
 */
@RequiredArgsConstructor
public class ErrorCommand implements Command {
    private final BotMessageService sendBotMessageService;

    /**
     * Сообщение, которое будет отправлено, когда пользователь неправильно ввёл команду.
     */
    public static final String ERROR_MESSAGE = "Неизвестная команда, напиши /help чтобы узнать все команды";



    /**
     * @param update объект {@link Update}, содержит информацию о сообщении пользователя.
     */
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), ERROR_MESSAGE);
    }
}
