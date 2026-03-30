package commands;

import managers.CollectionManager;
import model.City;
import io.UserInput;

/**
 * Команда удаления элементов, меньших заданного.
 * <p>Удаляет все города из коллекции, которые меньше
 * введённого пользователем города (по ID).</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class RemoveLowerCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        City city = UserInput.readCity();
        int count = manager.removeLower(city);
        System.out.println("Удалено элементов: " + count);
    }

    @Override
    public String getName() { return "remove_lower"; }

    @Override
    public String getDescription() { return "Удалить меньшие элементы"; }
}