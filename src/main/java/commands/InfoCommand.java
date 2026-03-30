package commands;

import managers.CollectionManager;

/**
 * Команда вывода информации о коллекции.
 * <p>Показывает тип коллекции, дату инициализации и количество элементов.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class InfoCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        System.out.println(manager.getInfo());
    }

    @Override
    public String getName() { return "info"; }

    @Override
    public String getDescription() { return "Информация о коллекции"; }
}