package org.example;

import managers.CollectionManager;
import managers.CommandManager;
import commands.*;
import java.util.Scanner;

/**
 * Точка входа в приложение City Collection Manager.
 * <p>Инициализирует менеджеры коллекции и команд,
 * регистрирует все команды и запускает интерактивный цикл
 * обработки пользовательского ввода.</p>
 *
 * <p>При запуске загружает коллекцию из файла (если существует),
 * при завершении сохраняет изменения (по команде save).</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class Main {

    /**
     * Главная точка входа в приложение.
     * <p>Выполняет следующие шаги:</p>
     * <ol>
     * <li>Создаёт CollectionManager и CommandManager</li>
     * <li>Загружает коллекцию из файла</li>
     * <li>Регистрирует все команды</li>
     * <li>Запускает цикл обработки команд</li>
     * </ol>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();

        try {
            collectionManager.loadFromFile();
            System.out.println("✓ Коллекция загружена (" + collectionManager.size() + " элементов)");
        } catch (Exception e) {
            System.out.println("⚠ Ошибка загрузки: " + e.getMessage());
        }

        commandManager.register(new HelpCommand());
        commandManager.register(new InfoCommand());
        commandManager.register(new ShowCommand());
        commandManager.register(new AddCommand());
        commandManager.register(new UpdateCommand());
        commandManager.register(new RemoveByIdCommand());
        commandManager.register(new ClearCommand());
        commandManager.register(new SaveCommand());
        commandManager.register(new ExecuteScriptCommand(commandManager));
        commandManager.register(new ExitCommand());
        commandManager.register(new HeadCommand());
        commandManager.register(new AddIfMinCommand());
        commandManager.register(new RemoveLowerCommand());
        commandManager.register(new MaxByPopulationDensityCommand());
        commandManager.register(new CountGreaterThanGovernmentCommand());
        commandManager.register(new FilterContainsNameCommand());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 'help' для справки\n");

        while (commandManager.isRunning()) {
            System.out.print("> ");
            String line = scanner.nextLine();
            commandManager.execute(line, collectionManager);
        }

        scanner.close();
    }
}