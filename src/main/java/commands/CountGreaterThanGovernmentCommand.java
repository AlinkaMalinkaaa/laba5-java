package commands;

import managers.CollectionManager;
import model.Government;
import io.UserInput;

/**
 * Команда подсчёта элементов с типом правления больше заданного.
 * <p>Считает количество городов, у которых поле government
 * больше указанного (по порядку в enum).</p>
 *
 * @author Алина
 * @version 1.0
 */
public class CountGreaterThanGovernmentCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        Government gov = UserInput.readEnum("Тип правления", Government.class);
        long count = manager.countGreaterThanGovernment(gov);
        System.out.println("Количество: " + count);
    }

    @Override
    public String getName() { return "count_greater_than_government"; }

    @Override
    public String getDescription() { return "Подсчёт по правлению"; }
}