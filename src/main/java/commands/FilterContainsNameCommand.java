package commands;

import managers.CollectionManager;

/**
 * Команда фильтрации элементов по подстроке в названии.
 * <p>Находит все города, название которых содержит
 * указанную подстроку (регистр не важен).</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class FilterContainsNameCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите подстроку");
            return;
        }
        var result = manager.filterContainsName(args[0]);
        if (result.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (var city : result) {
                System.out.println(city);
            }
        }
    }

    @Override
    public String getName() { return "filter_contains_name"; }

    @Override
    public String getDescription() { return "Фильтр по имени"; }
}