package commands;

import managers.CollectionManager;
import model.City;
import io.UserInput;

/**
 * Команда добавления элемента, если он меньше минимального.
 * <p>Сравнивает новый город с первым элементом коллекции
 * и добавляет только если он меньше (по ID).</p>
 *
 * @author Алина
 * @version 1.0
 */
public class AddIfMinCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        City city = UserInput.readCity();
        if (manager.addIfMin(city)) {
            System.out.println("Элемент добавлен");
        } else {
            System.out.println("Элемент не меньше минимального");
        }
    }

    @Override
    public String getName() { return "add_if_min"; }

    @Override
    public String getDescription() { return "Добавить если меньше мин"; }
}