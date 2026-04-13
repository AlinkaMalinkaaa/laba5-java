package commands;

import managers.CollectionManager;
import managers.CommandManager;
import java.io.*;

/**
 * Команда выполнения скрипта из файла.
 * Читает команды из файла и выполняет их последовательно.
 *
 * @author Алина
 * @version 1.0
 */
public class ExecuteScriptCommand implements Command {

    private final CommandManager commandManager;

    /**
     * Конструктор команды.
     * @param commandManager менеджер команд для выполнения
     */
    public ExecuteScriptCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(CollectionManager manager, String[] args) {
        // Проверка аргументов
        if (args.length == 0 || args[0].trim().isEmpty()) {

            System.out.println(" Ошибка: укажите имя файла");

            System.out.println("Использование: execute_script <file_name>");
            return;
        }

        File script = new File(args[0].trim());

        // Проверка существования и прав доступа
        if (!script.exists()) {
            System.out.println(" Файл не найден: " + args[0]);
            return;
        }
        if (!script.canRead()) {
            System.out.println(" Нет прав на чтение файла: " + args[0]);
            return;
        }
        if (script.isDirectory()) {
            System.out.println("Ожидается файл, а не директория: " + args[0]);
            return;
        }

        // Выполнение скрипта
        try (BufferedReader br = new BufferedReader(new FileReader(script))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null && commandManager.isRunning()) {
                lineNumber++;
                line = line.trim();

                // Пропускаем пустые строки и комментарии
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }

                // Выполняем команду
                commandManager.execute(line, manager);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + args[0]);
        } catch (IOException e) {
            System.out.println(" Ошибка чтения файла: " + e.getMessage());
        } catch (StackOverflowError e) {
            System.out.println(" Ошибка: возможна рекурсия в скриптах");
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "Считать и исполнить скрипт из указанного файла";
    }
}
