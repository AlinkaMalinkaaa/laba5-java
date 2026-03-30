package commands;

import managers.CollectionManager;

/**
 * Команда удаления элемента по ID.
 * <p>Удаляет город с указанным идентификатором из коллекции.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class RemoveByIdCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите ID");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            if (manager.removeById(id)) {
                System.out.println("✓ Элемент удалён");
            } else {
                System.out.println("Элемент не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ID");
        }
    }

    @Override
    public String getName() { return "remove_by_id"; }

    @Override
    public String getDescription() { return "Удалить элемент по ID"; }
}
