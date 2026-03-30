package commands;

import managers.CollectionManager;

/**
 * Команда вывода объекта с максимальной плотностью населения.
 * <p>Находит и показывает город с наибольшим значением
 * поля populationDensity.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class MaxByPopulationDensityCommand implements Command {

    @Override
    public void execute(CollectionManager manager, String[] args) {
        var max = manager.maxByPopulationDensity();
        System.out.println(max != null ? max : "Нет данных о плотности");
    }

    @Override
    public String getName() { return "max_by_population_density"; }

    @Override
    public String getDescription() { return "Максимальная плотность"; }
}