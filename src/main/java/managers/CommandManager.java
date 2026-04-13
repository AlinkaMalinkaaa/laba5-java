package managers;

import commands.Command;
import java.util.*;

/**
 * Менеджер команд.
 * <p>Регистрирует и выполняет команды приложения.
 * Реализует паттерн Command для инкапсуляции запросов.</p>
 *
 * @author Алина
 * @version 1.0
 */
public class CommandManager {

    /** Карта зарегистрированных команд (имя → команда) */
    private final Map<String, Command> commands = new HashMap<>();

    /** Флаг работы приложения */
    private boolean running = true;

    /**
     * Регистрирует команду в менеджере.
     *
     * @param command команда для регистрации
     * @throws IllegalArgumentException если command null
     */
    public void register(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Command не может быть null");
        }
        commands.put(command.getName(), command);
    }

    /**
     * Выполняет команду из строки ввода.
     * <p>Разбирает строку на имя команды и аргументы,
     * находит команду и вызывает её execute().</p>
     *
     * @param line строка ввода пользователя
     * @param manager менеджер коллекции для передачи в команду
     */
    public void execute(String line, CollectionManager manager) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? new String[]{parts[1]} : new String[0];

        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Неизвестная команда: " + commandName);
            System.out.println("💡 Введите 'help' для справки");
            return;
        }

        try {
            command.execute(manager, args);
            if (commandName.equals("exit")) {
                running = false;
            }
        } catch (Exception e) {
            System.out.println(" Ошибка: " + e.getMessage());
        }
    }

    /**
     * Возвращает незменяемую карту команд.
     * @return unmodifiable map команд
     */
    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }

    /**
     * Возвращает коллекцию всех команд.
     * @return коллекция команд
     */
    public Collection<Command> getAllCommands() {
        return commands.values();
    }

    /**
     * Проверяет, должна ли программа работать.
     * @return true если программа активна
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Проверяет наличие команды по имени.
     * @param name имя команды
     * @return true если команда зарегистрирована
     */
    public boolean hasCommand(String name) {
        return commands.containsKey(name.toLowerCase());
    }
}