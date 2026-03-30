package model;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Город в коллекции.
 * <p>Основной класс модели, содержащий всю информацию о городе:
 * название, координаты, площадь, население, правителя и другие характеристики.</p>
 *
 * <p>Реализует {@link Comparable} для сортировки по ID (требование задания)
 * и {@link Serializable} для сохранения в файл.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class City implements Serializable, Comparable<City> {

    /** Уникальный идентификатор города (авто-генерация) */
    private int id;

    /** Название города (не может быть пустым) */
    private String name;

    /** Географические координаты города */
    private Coordinates coordinates;

    /** Дата создания города (авто-генерация при добавлении) */
    private LocalDate creationDate;

    /** Площадь города в квадратных километрах (должна быть > 0) */
    private Float area;

    /** Население города (должно быть > 0) */
    private Integer population;
    /** Высота над уровнем моря в метрах (может быть null) */
    private Double metersAboveSeaLevel;

    /** Плотность населения (может быть null, должна быть > 0 если установлена) */
    private Double populationDensity;

    /** Тип правления (может быть null) */
    private Government government;

    /** Уровень жизни (может быть null) */
    private StandardOfLiving standardOfLiving;

    /** Правитель города (не может быть null) */
    private Human governor;

    /**
     * Создаёт пустой объект города.
     * <p>Необходим для JSON-парсера.</p>
     */
    public City() {}

    /**
     * Возвращает уникальный идентификатор города.
     * @return ID города
     */
    public int getId() { return id; }

    /**
     * Возвращает название города.
     * @return название города
     */
    public String getName() { return name; }
    /**
     * Возвращает координаты города.
     * @return координаты города
     */
    public Coordinates getCoordinates() { return coordinates; }
    /**
     * Возвращает дату создания города.
     * @return дата создания
     */
    public LocalDate getCreationDate() { return creationDate; }
    /**
     * Возвращает площадь города.
     * @return площадь в кв. км
     */
    public Float getArea() { return area; }

    /**
     * Возвращает население города.
     * @return количество жителей
     */
    public Integer getPopulation() { return population; }

    /**
     * Возвращает высоту над уровнем моря.
     * @return высота в метрах или null
     */
    public Double getMetersAboveSeaLevel() { return metersAboveSeaLevel; }
    /**
     * Возвращает плотность населения.
     * @return плотность или null
     */
    public Double getPopulationDensity() { return populationDensity; }
    /**
     * Возвращает тип правления.
     * @return тип правления или null
     */
    public Government getGovernment() { return government; }
    /**
     * Возвращает уровень жизни.
     * @return уровень жизни или null
     */
    public StandardOfLiving getStandardOfLiving() { return standardOfLiving; }
    /**
     * Возвращает правителя города.
     * @return правитель города
     */
    public Human getGovernor() { return governor; }

    /**
     * Устанавливает ID города с валидацией.
     *
     * @param id уникальный идентификатор (должен быть > 0)
     @throws IllegalArgumentException если id &lt;= 0
     */
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID должен быть > 0");
        this.id = id;
    }
    /**
     * Устанавливает название города с валидацией.
     *
     * @param name название города (не может быть пустым)
     * @throws IllegalArgumentException если name null или пустой
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.name = name.trim();
    }
    /**
     * Устанавливает координаты города.
     *
     * @param coordinates координаты города
     * @throws IllegalArgumentException если coordinates null
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException("Coordinates не может быть null");
        this.coordinates = coordinates;
    }
    /**
     * Устанавливает дату создания города.
     *
     * @param creationDate дата создания
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * Устанавливает площадь города с валидацией.
     *
     * @param area площадь в кв. км (должна быть > 0)
     * @throws IllegalArgumentException если area null или &lt;= 0
     */
    public void setArea(Float area) {
        if (area == null || area <= 0) throw new IllegalArgumentException("Площадь должна быть > 0");
        this.area = area;
    }
    /**
     * Устанавливает население города с валидацией.
     *
     * @param population количество жителей (должно быть > 0)
     * @throws IllegalArgumentException если population null или &lt;= 0
     */
    public void setPopulation(Integer population) {
        if (population == null || population <= 0) throw new IllegalArgumentException("Население должно быть > 0");
        this.population = population;
    }
    /**
     * Устанавливает высоту над уровнем моря.
     *
     * @param metersAboveSeaLevel высота в метрах
     */
    public void setMetersAboveSeaLevel(Double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }
    /**
     * Устанавливает плотность населения с валидацией.
     *
     * @param populationDensity плотность населения (должна быть > 0 если не null)
     * @throws IllegalArgumentException если density &lt;= 0
     */
    public void setPopulationDensity(Double populationDensity) {
        if (populationDensity != null && populationDensity <= 0) {
            throw new IllegalArgumentException("Плотность должна быть > 0");
        }
        this.populationDensity = populationDensity;
    }
    /**
     * Устанавливает тип правления.
     *
     * @param government тип правления
     */
    public void setGovernment(Government government) {
        this.government = government;
    }
    /**
     * Устанавливает уровень жизни.
     *
     * @param standardOfLiving уровень жизни
     */
    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }
    /**
     * Устанавливает правителя города.
     *
     * @param governor правитель города
     * @throws IllegalArgumentException если governor null
     */

    public void setGovernor(Human governor) {
        if (governor == null) throw new IllegalArgumentException("Governor не может быть null");
        this.governor = governor;
    }
    /**
     * Возвращает строковое представление города.
     * @return информация о городе в формате строки
     */
    @Override
    public String toString() {
        return String.format("City{id=%d, name='%s', coordinates=%s, area=%.2f, population=%d, density=%s, government=%s}",
                id, name, coordinates, area, population, populationDensity, government);
    }
    /**
     * Сравнивает города по ID для сортировки коллекции.
     * <p>Сортировка по умолчанию (требование задания).</p>
     *
     * @param o другой город для сравнения
     * @return отрицательное число, если этот ID меньше,
     *         0 если равны,
     *         положительное число если этот ID больше
     */
    @Override
    public int compareTo(City o) {
        if (o == null) return 1;
        return Integer.compare(this.id, o.id);
    }
    /**
     * Проверяет равенство городов по ID.
     *
     * @param obj объект для сравнения
     * @return true если ID равны
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof City)) return false;
        return id == ((City) obj).id;
    }
    /**
     * Возвращает хэш-код города на основе ID.
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
