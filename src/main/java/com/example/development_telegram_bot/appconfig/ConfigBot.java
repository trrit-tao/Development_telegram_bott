package com.example.development_telegram_bot.appconfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Класс конфигурации бота.
 *
 * Этот класс используется для настройки бота.
 * Он отмечен аннотацией {@code @Configuration}, что позволяет Spring использовать
 * его для конфигурации бинов в контексте приложения.
 *
 */
@Configuration
@Data
public class ConfigBot {
    /**
     * Имя бота.
     */
    @Value("${botName}")
    private String name;

    /**
     * Токен доступа бота.
     */
    @Value("${botToken}")
    private String token;
}