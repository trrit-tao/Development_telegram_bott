package com.example.development_telegram_bot.service;


import com.example.development_telegram_bot.repository.CategoryRepository;
import com.example.development_telegram_bot.category.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


/**
 * Этот класс предоставляет реализацию методов для добавления, просмотра и удаления категорий.
 */
@Service
public class CategoryService {

    private final   CategoryRepository categoryRepository = new CategoryRepository() {
        @Override
        public List<Category> findByChatId(long chatId) {
            return null;
        }

        @Override
        public List<Category> findByChatIdAndParentId(long chatId, Long parentId) {
            return null;
        }

        @Override
        public Optional<Category> findByChatIdAndName(long chatId, String name) {
            return Optional.empty();
        }

        @Override
        public void deleteCategoryAndChildsByChatIdAndId(long chatId, Long id) {

        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Category> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Category> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Category getOne(Long aLong) {
            return null;
        }

        @Override
        public Category getById(Long aLong) {
            return null;
        }

        @Override
        public Category getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Category> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Category> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public List<Category> findAll() {
            return null;
        }

        @Override
        public List<Category> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public <S extends Category> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Category> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Category entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Category> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<Category> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Category> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Category> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Category> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Category> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };

    /**
     * Добавляет новую корневую категорию с указанным именем.
     *
     * @param root   Название новой корневой категории.
     * @param chatId Идентификатор чата.
     * @return Сообщение о результате операции.
     */
    public String addRoot(String root, long chatId) {
        if (!categoryRepository.findByChatIdAndParentId(chatId, 0L).isEmpty()) {
            return "Корневой элемент " + root + " уже существует";
        }
        Category category = new Category(0L, root, chatId);
        categoryRepository.save(category);
        return "Категория " + root + " успешно добавлена";
    }

    /**
     * Добавляет новую дочернюю категорию с указанным именем к существующей родительской категории.
     *
     * @param child  Название новой дочерней категории.
     * @param parent Название родительской категории, к которой следует добавить дочернюю.
     * @param chatId Идентификатор чата.
     * @return Сообщение о результате операции.
     */
    public String add(String child, String parent, long chatId) {
        if (!categoryRepository.findByChatIdAndName(chatId, child).isEmpty()) {
            return "Такая категория " + child + " уже существует";
        }
        Optional<Category> optionalCategory = categoryRepository.findByChatIdAndName(chatId, parent);
        if (optionalCategory.isEmpty()) {
            return "Такой категории-родителя " + parent + " не существует";
        }
        Category parentCategory = optionalCategory.get();
        Category category = new Category(parentCategory.getId(), child, chatId);
        categoryRepository.save(category);
        return "Категория " + child + " успешно добавлена";
    }

    /**
     * Возвращает структурированный вид дерева категорий.
     *
     * @param chatId Идентификатор чата.
     * @return Строка, представляющая дерево категорий.
     */
    public String view(long chatId) {
        List<Category> categories = categoryRepository.findByChatId(chatId);
        if (categories.isEmpty()) {
            return "Категорий пока нет";
        }
        StringBuilder treeBuilder = new StringBuilder();
        for (Category category : categories) {
            if (category.getParentId().equals(0L)) {
                buildCategoryTree(treeBuilder, categories, category, 0);
            }
        }
        return treeBuilder.toString();
    }

    /**
     * Рекурсивный метод для построения структурированного дерева категорий в виде строки.
     *
     * @param treeBuilder     StringBuilder для добавления категорий и их отступов.
     * @param categories      Список всех категорий.
     * @param currentCategory Текущая категория, для которой строится поддерево.
     * @param depth           Глубина вложенности текущей категории в дереве.
     */
    private static void buildCategoryTree(StringBuilder treeBuilder, List<Category> categories, Category currentCategory, int depth) {
        // Добавляем отступы в зависимости от глубины вложенности
        treeBuilder.append("---".repeat(Math.max(0, depth)));
        // Добавляем название текущей категории
        treeBuilder.append(currentCategory.getName()).append("\n");
        // Рекурсивно обходим дочерние категории этой категории
        for (Category childCategory : categories) {
            if (!childCategory.getParentId().equals(0L) && childCategory.getParentId().equals(currentCategory.getId())) {
                buildCategoryTree(treeBuilder, categories, childCategory, depth + 1);
            }
        }
    }

    /**
     * Удаляет указанную категорию и все её дочерние категории.
     *
     * @param category Название категории, которую следует удалить.
     * @param chatId   Идентификатор чата.
     * @return Сообщение о результате операции.
     */
    public String remove(String category, long chatId)
    //здесь можно было бы также использовать рекурсивный метод, по аналогии с buildCategoryTree
    {
        Optional<Category> optionalCategory = categoryRepository.findByChatIdAndName(chatId, category);
        if (optionalCategory.isEmpty()) {
            return "Такой категории " + category + " не существует";
        }
        Long id = optionalCategory.get().getId();
        categoryRepository.deleteCategoryAndChildsByChatIdAndId(chatId, id);

        return "Категория " + category + " и все её потомки успешно удалены";
    }

}