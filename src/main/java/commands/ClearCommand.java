package commands;

import managers.CollectionManager;

/**
 * Команда очистки коллекции.
 * <p>Удаляет все элементы из коллекции (без сохранения в файл).</p>
 *
 * @author Алина
 * @version 1.0
 */
public class ClearCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        manager.clear();
        System.out.println("✓ Коллекция очищена");
    }

    @Override
    public String getName() { return "clear"; }

    @Override
    public String getDescription() { return "Очистить коллекцию"; }
}