package commands;

import managers.CollectionManager;
import model.City;
import io.UserInput;

/**
 * Команда добавления нового города в коллекцию.
 * <p>Запрашивает данные у пользователя через {@link UserInput},
 * создаёт новый объект {@link City} и добавляет его в коллекцию.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class AddCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        City city = UserInput.readCity();
        manager.add(city);
        System.out.println("Элемент добавлен с ID: " + city.getId());
    }

    @Override
    public String getName() { return "add"; }

    @Override
    public String getDescription() { return "Добавить новый элемент"; }
}