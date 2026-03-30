package commands;

import managers.CollectionManager;

/**
 * Команда вывода справки.
 * <p>Отображает список всех доступных команд с описанием.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class HelpCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        System.out.println("Доступные команды:");
        System.out.println("  help - вывести справку");
        System.out.println("  info - информация о коллекции");
        System.out.println("  show - показать все элементы");
        System.out.println("  add - добавить элемент");
        System.out.println("  update id <id> - обновить элемент");
        System.out.println("  remove_by_id <id> - удалить по ID");
        System.out.println("  clear - очистить коллекцию");
        System.out.println("  save - сохранить в файл");
        System.out.println("  execute_script <file> - выполнить скрипт");
        System.out.println("  exit - завершить программу");
        System.out.println("  head - первый элемент");
        System.out.println("  add_if_min - добавить если меньше мин");
        System.out.println("  remove_lower - удалить меньшие");
        System.out.println("  max_by_population_density - макс плотность");
        System.out.println("  count_greater_than_government - подсчёт");
        System.out.println("  filter_contains_name - фильтр по имени");
    }

    @Override
    public String getName() { return "help"; }

    @Override
    public String getDescription() { return "Вывести справку"; }
}