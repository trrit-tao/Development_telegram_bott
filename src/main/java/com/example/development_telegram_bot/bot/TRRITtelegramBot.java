package com.example.development_telegram_bot.bot;

import com.example.development_telegram_bot.command.ContainerCommand;
import com.example.development_telegram_bot.service.CategoryService;
import com.example.development_telegram_bot.service.BotMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.development_telegram_bot.command.NameCommand.ERROR;

/**
 * Класс, отвечающий за обработку сообщений пользователя
 */
@Service
public class TRRITtelegramBot extends TelegramLongPollingBot {
    private final ContainerCommand commandContainer;

    /**
     * Конструктор класса.
     *
     * @param categoryService экземпляр класса {@link ContainerCommand}
     */
    public TRRITtelegramBot(CategoryService categoryService) {
        super("6949293708:AAG2wfVaxU_I1xrrNaxE-ZWWwQVptkbzaE8");
        this.commandContainer = new ContainerCommand(new BotMessageService(this), categoryService);
    }


    /**
     * @return имя бота.
     */
    @Override
    public String getBotUsername() {
        return "TRRITtelegramBot";
    }


    /**
     * Метод для обработки сообщений пользователя.
     * Отвечает на команды пользователя или выводит сообщение об ошибке, если команда не верна.
     *
     * @param update объект {@link Update}, содержит информацию о сообщении пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        // Создаем регулярное выражение для проверки команды
        Pattern commandPattern = Pattern.compile("^/([A-Za-z]+)(\\s+<[^>]+>)*(\\s+<[^>]+>)?$");

        // Проверяем, есть ли сообщение и содержит ли оно текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            Matcher matcher = commandPattern.matcher(message);

            // Проверяем, соответствует ли сообщение формату команды
            if (matcher.matches()) {
                String commandIdentifier = message.split(" ")[0]; // Идентифицируем команду

                // Получаем и выполняем соответствующую команду из контейнера команд
                commandContainer.actionCommand(commandIdentifier).execute(update);
            } else {
                // Если сообщение не соответствует формату команды, отправляем сообщение об ошибке
                commandContainer.actionCommand(ERROR.getCommandName()).execute(update);
            }
        }
    }
}