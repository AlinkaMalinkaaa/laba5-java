package model;
/**
 * Уровни жизни населения города.
 * <p>Перечисление содержит все допустимые уровни жизни,
 * которые могут быть установлены для города в коллекции.</p>
 *
 * <ul>
 *   <li>VERY_HIGH - очень высокий</li>
 *   <li>MEDIUM - средний</li>
 *   <li>LOW - низкий</li>
 *   <li>VERY_LOW - очень низкий</li>
 * </ul>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public enum Government
{
    KRITARCHY,
    PLUTOCRACY,
    STRATOCRACY,
    THEOCRACY,
    TECHNOCRACY;
    /**
     * Выводит список всех доступных значений правления.
     * <p>Используется при интерактивном вводе для подсказки пользователю.</p>
     */
    public static void printValues() {
        System.out.println("Доступные значения:");
        for (Government g: values() ) {
            System.out.println(" -" + g.name());
        }

    }
}
