package io;

import model.*;
import java.util.Scanner;

/**
 * Интерактивный ввод данных от пользователя.
 * <p>Предоставляет методы для чтения и валидации ввода
 * для всех полей объекта {@link City}.</p>
 *
 * @author Алина
 * @version 1.0
 */
public class UserInput {

    /** Сканер для чтения ввода из консоли */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Читает строку с подсказкой.
     *
     * @param prompt текст подсказки
     * @return введённая строка (с обрезанными пробелами)
     */
    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    /**
     * Читает строку, которая не может быть пустой.
     * <p>Повторяет запрос пока пользователь не введёт значение.</p>
     *
     * @param prompt текст подсказки
     * @return непустая строка
     */
    public static String readRequired(String prompt) {
        while (true) {
            String value = readString(prompt);
            if (!value.isEmpty()) return value;
            System.out.println("Поле не может быть пустым");
        }
    }

    /**
     * Читает целое число с обработкой ошибок.
     *
     * @param prompt текст подсказки
     * @return введённое число или null если пустая строка
     */
    public static Integer readInteger(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                if (input.isEmpty()) return null;
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    /**
     * Читает число с плавающей точкой с обработкой ошибок.
     *
     * @param prompt текст подсказки
     * @return введённое число или null если пустая строка
     */
    public static Double readDouble(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                if (input.isEmpty()) return null;
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }

    /**
     * Читает значение enum с показом доступных вариантов.
     *
     * @param <E> тип перечисления
     * @param prompt текст подсказки
     * @param enumClass класс перечисления
     * @return выбранное значение enum или null
     */
    public static <E extends Enum<E>> E readEnum(String prompt, Class<E> enumClass) {
        System.out.println(prompt + ":");
        for (E constant : enumClass.getEnumConstants()) {
            System.out.println("  - " + constant.name());
        }
        while (true) {
            String input = readString("Введите значение");
            if (input.isEmpty()) return null;
            try {
                return Enum.valueOf(enumClass, input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректное значение");
            }
        }
    }

    /**
     * Читает координаты с валидацией X &lt;= MAX_X.
     *
     * @return объект Coordinates
     */
    public static Coordinates readCoordinates() {
        System.out.println("--- Координаты ---");
        Double x = readDouble("  X (макс. 201)");
        while (x == null || x > 201) {
            System.out.println("X должен быть <= 201");
            x = readDouble("  X");
        }
        Double y = readDouble("  Y");
        return new Coordinates(x, y != null ? y : 0);
    }

    /**
     * Читает информацию о правителе с валидацией.
     *
     * @return объект Human
     */
    public static Human readHuman() {
        System.out.println("--- Правитель ---");
        String name = readRequired("  Имя");
        Long age = null;
        while (age == null || age <= 0) {
            Integer a = readInteger("  Возраст");
            if (a != null && a > 0) age = (long) a;
            else System.out.println("Возраст должен быть > 0");
        }
        return new Human(name, age, null);
    }

    /**
     * Читает все данные для нового города интерактивно.
     *
     * @return объект City с заполненными полями
     */
    public static City readCity() {
        System.out.println("=== Новый город ===");
        City city = new City();
        city.setName(readRequired("Название"));
        city.setCoordinates(readCoordinates());

        Float area = null;
        while (area == null || area <= 0) {
            Double d = readDouble("Площадь");
            if (d != null && d > 0) area = d.floatValue();
            else System.out.println("Площадь > 0");
        }
        city.setArea(area);

        Integer population = null;
        while (population == null || population <= 0) {
            population = readInteger("Население");
            if (population != null && population <= 0) {
                System.out.println("Население > 0");
            }
        }
        city.setPopulation(population);

        city.setMetersAboveSeaLevel(readDouble("Высота над уровнем моря"));
        city.setPopulationDensity(readDouble("Плотность населения"));
        city.setGovernment(readEnum("Тип правления", Government.class));
        city.setStandardOfLiving(readEnum("Уровень жизни", StandardOfLiving.class));
        city.setGovernor(readHuman());

        return city;
    }
}