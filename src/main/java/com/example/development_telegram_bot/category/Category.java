package com.example.development_telegram_bot.category;

import jakarta.persistence.*;
import lombok.*;


/**
 * Класс, представляющий категорию.
 *
 * Описывает сущность Категория, которая может использоваться для
 * организации иерархии категорий. Категории могут иметь родительские категории и названия. Каждый чат имеет свои катерории.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    /**
     * Уникальный идентификатор категории.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Идентификатор родительской категории.
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * Название категории.
     *
     * Название категории является обязательным полем и имеет ограничение по длине
     * в 25 символов.
     */
    @Column(nullable = false, length = 25)
    private String name;

    /**
     * Идентификатор чата.
     */
    @Column(name = "chat_id")
    private long chatId;

    /**
     * Конструктор класса Category.
     *
     * @param parentId Идентификатор родительской категории.
     * @param name      Название категории.
     * @param chatId    Идентификатор чата.
     *
     */
    public Category(Long parentId, String name, long chatId) {
        this.parentId = parentId;
        this.name = name;
        this.chatId = chatId;
    }
}
