package commands;

import managers.CollectionManager;

/**
 * Команда завершения программы.
 * <p>Устанавливает флаг завершения работы приложения.</p>
 *
 * @author Алина
 * @version 1.0
 */
public class ExitCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        System.out.println("Программа завершена");
    }

    @Override
    public String getName() { return "exit"; }

    @Override
    public String getDescription() { return "Завершить программу"; }
}