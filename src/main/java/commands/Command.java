package commands;

import managers.CollectionManager;

/**
 * Интерфейс команды.
 * <p>Реализует паттерн Command для инкапсуляции запросов.
 * Все команды приложения должны реализовывать этот интерфейс.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public interface Command {

    /**
     * Выполняет команду.
     *
     * @param manager менеджер коллекции для работы с данными
     * @param args аргументы команды (строки после имени команды)
     */
    void execute(CollectionManager manager, String[] args);

    /**
     * Возвращает имя команды.
     * @return имя команды в нижнем регистре
     */
    String getName();

    /**
     * Возвращает краткое описание команды.
     * @return описание назначения команды
     */
    String getDescription();
}