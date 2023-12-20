package com.example.development_telegram_bot.appconfig;

import com.example.development_telegram_bot.bot.TRRITtelegramBot;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Инициализатор бота.
 *
 * Этот класс отвечает за инициализацию Telegram-бота.
 * Он использует бин `TRRITtelegramBot` для работы с ботом.
 */
@Component
public class InitializationBot {

    /**
     * Экземпляр бота.
     */
    private final TRRITtelegramBot bot;

    /**
     * Логгер для записи событий и ошибок.
     */
    private final Logger logger = LoggerFactory.getLogger(InitializationBot.class);

    /**
     * Конструктор класса BotInitializer.
     *
     * @param bot Экземпляр бота, переданный в качестве зависимости.
     */
    public InitializationBot(TRRITtelegramBot bot) {
        this.bot = bot;
    }

    /**
     * Метод инициализации бота.
     *
     * В этом методе происходит регистрация Telegram-бота с использованием API TelegramBotsApi.
     * Если регистрация прошла успешно, бот готов к работе.
     * В случае возникновения ошибки при регистрации, ошибки записываются в лог.
     *
     * @throws TelegramApiException Если произошла ошибка при регистрации бота.
     */
    @PostConstruct
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при регистрации бота: {}", e.getMessage(), e);
        }
    }
}
