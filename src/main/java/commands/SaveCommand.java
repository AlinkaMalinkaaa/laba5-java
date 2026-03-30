package commands;

import managers.CollectionManager;

/**
 * Команда сохранения коллекции в файл.
 * <p>Записывает текущее состояние коллекции в JSON-файл,
 * указанный в переменной окружения CITY_FILE.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class SaveCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        try {
            manager.saveToFile();
            System.out.println("Данные сохранены");
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    @Override
    public String getName() { return "save"; }

    @Override
    public String getDescription() { return "Сохранить в файл"; }
}