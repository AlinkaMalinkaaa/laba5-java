package commands;

import managers.CollectionManager;
import model.City;
import io.UserInput;

/**
 * Команда обновления элемента коллекции по ID.
 * <p>Обновляет данные города с указанным идентификатором,
 * сохраняя оригинальную дату создания.</p>
 *
 * @author Алина
 * @version 1.0
 */
public class UpdateCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        if (args.length == 0) {
            System.out.println("Формат: update id <id>");
            return;
        }
        String[] parts = args[0].split("\\s+", 2);
        if (!parts[0].equals("id") || parts.length < 2) {
            System.out.println("Формат: update id <id>");
            return;
        }
        try {
            int id = Integer.parseInt(parts[1]);
            City newCity = UserInput.readCity();
            if (manager.update(id, newCity)) {
                System.out.println("Элемент обновлён");
            } else {
                System.out.println("Элемент не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ID");
        }
    }

    @Override
    public String getName() { return "update"; }

    @Override
    public String getDescription() { return "Обновить элемент по ID"; }
}