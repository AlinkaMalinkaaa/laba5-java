package commands;

import managers.CollectionManager;

/**
 * Команда показа всех элементов коллекции.
 * <p>Выводит все города в консоль или сообщение о пустой коллекции.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class ShowCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        if (manager.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (var city : manager.getCollection()) {
                System.out.println(city);
            }
        }
    }

    @Override
    public String getName() { return "show"; }

    @Override
    public String getDescription() { return "Показать все элементы"; }
}