package commands;

import managers.CollectionManager;

/**
 * Команда вывода первого элемента коллекции.
 * <p>Показывает город с минимальным ID (первый после сортировки).</p>
 *
 * @author Алина
 * @version 1.0
 */
public class HeadCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        var first = manager.getFirst();
        System.out.println(first != null ? first : "Коллекция пуста");
    }

    @Override
    public String getName() { return "head"; }

    @Override
    public String getDescription() { return "Первый элемент"; }
}