package com.example.development_telegram_bot.service;

import com.example.development_telegram_bot.bot.TRRITtelegramBot;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Этот класс предоставляет реализацию методов для отправки сообщение в указанный чат.
 */
@Service
@RequiredArgsConstructor
public class BotMessageService {

    private final TRRITtelegramBot bot;

    /**
     * Логгер для записи событий и ошибок.
     */
    private Logger logger = LoggerFactory.getLogger(BotMessageService.class);

    /**
     * @param chatId  Уникальный идентификатор чата.
     * @param message Текст сообщения.<p/>
     * Выводит в консоль сообщения об отправленных сообщениях и ошибках при отправке сообщений.
     */
    public void sendMessage(Long chatId, String message)  {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        try {
            bot.execute(sendMessage);
            logger.info("Отправлено сообщение: {}", sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения: {}", e.getMessage(), e);
        }
    }
}
