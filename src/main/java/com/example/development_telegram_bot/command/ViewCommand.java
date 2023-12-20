package com.example.development_telegram_bot.command;

import com.example.development_telegram_bot.service.CategoryService;
import com.example.development_telegram_bot.service.BotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс, представляющий команду для отображения дерева категорий при вызове команды /viewTree.
 */
@RequiredArgsConstructor
public class ViewCommand implements Command {

    private final BotMessageService sendBotMessageService;
    private final CategoryService categoryService;


    /**
     * @param update объект {@link Update}, содержит информацию о сообщении пользователя.
     */
    @Override
    public void execute(Update update) {
        long chatId = update.getMessage().getChatId();
        String message = categoryService.view(chatId);
        sendBotMessageService.sendMessage(chatId, message);
    }
}
