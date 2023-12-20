package com.example.development_telegram_bot.command;

import com.example.development_telegram_bot.service.CategoryService;
import com.example.development_telegram_bot.service.BotMessageService;

import java.util.Map;
import static com.example.development_telegram_bot.command.NameCommand.*;

/**Контейнер команд {@link Command}, которые используются для обработки сообщений пользователя.
 * В нем будут храниться объекты наших команд, и по запросу мы можем получать необходимую команду.
 */
public class ContainerCommand {
    private final Map<String, Command> commandMap;
    private final Command errorCommand;

    /**
     *  В конструкторе заполняем мапу, в которой ключ  - это строка имени команды,
     *  а со значение -  это объект команды типа {@link Command}.
     */
    public ContainerCommand(BotMessageService sendBotMessageService, CategoryService categoryService) {
        commandMap = Map.ofEntries(
                Map.entry(START.getCommandName(), new StartCommand(sendBotMessageService)),
                Map.entry(ERROR.getCommandName(), new ErrorCommand(sendBotMessageService)),
                Map.entry(HELP.getCommandName(), new HelpCommand(sendBotMessageService)),
                Map.entry(ADD.getCommandName(), new AddCommand(sendBotMessageService, categoryService)),
                Map.entry(VIEW.getCommandName(), new ViewCommand(sendBotMessageService, categoryService)),
                Map.entry(REMOVE.getCommandName(), new RemoveCommand(sendBotMessageService, categoryService))
        );
        errorCommand = new ErrorCommand(sendBotMessageService);
    }

    /**
     * Извлекает конкретную команду {@link Command} по ее имени (например, /start).
     *
     * @return Экземпляр реализации интерфейса {@link Command}, связанной с указанным именем,
     *         или экземпляр {@link ErrorCommand}, если такой команды нет.
     */
    public Command actionCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, errorCommand);
    }
}