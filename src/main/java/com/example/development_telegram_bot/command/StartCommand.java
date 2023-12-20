package com.example.development_telegram_bot.command;

import com.example.development_telegram_bot.service.BotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс, представляющий команду для отправки приветственного сообщения при вызове команды /start.
 */
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final BotMessageService sendBotMessageService;

    /**
     * Приветствие после команды /start.
     */
    public final static String START_MESSAGE = "Привет, я бот для работы с категориями. " +
            "Чтобы узнать, что я умею, напишите /help";


    /**
     * @param update объект {@link Update}, содержит информацию о сообщении пользователя.
     */
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), START_MESSAGE);
    }
}