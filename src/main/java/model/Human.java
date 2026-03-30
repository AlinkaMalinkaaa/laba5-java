package model;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * Информация о правителе города.
 * <p>Содержит имя, возраст и дату рождения правителя.
 * Используется как вложенный объект в классе {@link City}.</p>
 *
 * <p>Реализует {@link Comparable} для сортировки по имени
 * и {@link Serializable} для сохранения в файл.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
// Информация о правителе
public class Human implements Serializable, Comparable<Human>{
    public Human (String name, Long age, java.time.LocalDateTime birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
    /**
     * Создаёт пустой объект правителя.
     * <p>Необходим для JSON-парсера.</p>
     */
    public Human() {}
    /** Имя правителя (не может быть пустым) */
    private String name;
    /** Возраст правителя (должен быть > 0) */
    private Long age;
    /** Дата рождения правителя (может быть null) */
    private LocalDateTime birthday;
    public String getName() {
        return name;
    }
    public Long getAge() {
        return age;
    }
    public java.time.LocalDateTime getBirthday() {
        return birthday;

    }
    /**
     * Устанавливает имя правителя с валидацией.
     *
     * @param name имя правителя (не может быть пустым)
     * @throws IllegalArgumentException если name null или пустой
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name.trim();
    }

    /**
     * Устанавливает возраст правителя с валидацией.
     *
     * @param age возраст правителя (должен быть > 0)
     * @throws IllegalArgumentException если age null или &lt;= 0
     */
    public void setAge(Long age) {
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Возраст не может быть <= 0");
        }
        this.age = age;
    }
    /**
     * Устанавливает дату рождения правителя.
     *
     * @param birthday дата рождения
     */
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
    /**
     * Возвращает строковое представление правителя.
     * @return строка в формате "Имя (возраст: X)"
     */
    @Override
    public String toString() {
        return "Human{"+
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday + "}";
    }
    /**
     * Сравнивает правителей по имени для сортировки.
     *
     * @param o другой правитель для сравнения
     * @return результат сравнения имён
     */
    @Override
    public int compareTo(Human o) {
        if (o == null) return 1;
        return this.name.compareTo(o.name);
    }


}
