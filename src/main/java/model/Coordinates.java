package model;

import java.io.Serializable;
/**
 * Координаты города.
 * <p>Содержит географические координаты X и Y для местоположения города.
 * Координата X имеет ограничение максимум 201.0 (требование задания).</p>
 *
 * <p>Реализует {@link Comparable} для сортировки координат
 * и {@link Serializable} для сохранения в файл.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class Coordinates implements Serializable, Comparable<Coordinates> {

    /** Максимально допустимое значение координаты X */
    public static final double MAX_X = 201.0;

    private Double x;  // Может быть null
    private double y;  // Примитив, не может быть null
    /**
     * Создаёт пустой объект координат.
     * <p>Необходим для JSON-парсера.</p>
     */
    public Coordinates() {}
    /**
     * Создаёт координаты с заданными значениями.
     *
     * @param x координата X (должна быть &lt;= MAX_X)
     * @param y координата Y
     */
    public Coordinates(Double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает координату X.
     * @return координата X или null
     */
    public Double getX() { return x; }
    /**
     * Возвращает координату Y.
     * @return координата Y
     */
    public double getY() { return y; }
    /**
     * Устанавливает координату X с валидацией.
     *
     * @param x координата X (должна быть &lt;= MAX_X)
     * @throws IllegalArgumentException если x null или больше MAX_X
     */
    public void setX(Double x) {
        if (x == null || x > MAX_X) {
            throw new IllegalArgumentException("X должен быть <= " + MAX_X);
        }
        this.x = x;
    }
    /**
     * Устанавливает координату Y.
     *
     * @param y координата Y
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * Возвращает строковое представление координат.
     * @return строка в формате "{x, y}"
     */
    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + "}";
    }
    /**
     * Сравнивает координаты для сортировки.
     * <p>Сначала сравнивает по X, при равенстве — по Y.</p>
     *
     * @param o другие координаты для сравнения
     * @return отрицательное число, если эти координаты меньше,
     *         0 если равны,
     *         положительное число если больше
     */
    @Override
    public int compareTo(Coordinates o) {
        if (o == null) return 1;
        int xCompare = this.x.compareTo(o.x);
        if (xCompare != 0) {
            return xCompare;
        }
        return Double.compare(this.y, o.y);
    }
}