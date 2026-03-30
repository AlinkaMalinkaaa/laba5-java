package managers;

import model.City;
import model.Government;
import io.JSONReader;
import io.JSONWriter;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Менеджер коллекции городов.
 * <p>Управляет хранением, загрузкой, сохранением и операциями
 * с коллекцией {@link City}. Реализует CRUD-операции,
 * фильтрацию, сортировку и специальные методы из задания.</p>
 *
 * <p>Использует LinkedList для хранения (требование задания).
 * Сортировка выполняется через Comparable по ID.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class CollectionManager {

    /** Коллекция городов (LinkedList для сортировки) */
    private LinkedList<City> collection = new LinkedList<>();

    /** Дата и время инициализации коллекции */
    private final LocalDateTime initializationDate = LocalDateTime.now();

    /** Путь к файлу данных (из переменной окружения CITY_FILE) */
    private String filePath;

    /**
     * Создаёт менеджер коллекции.
     * <p>Путь к файлу берётся из переменной окружения CITY_FILE.</p>
     */
    public CollectionManager() {
        this.filePath = System.getenv("CITY_FILE");
    }

    /**
     * Возвращает копию коллекции городов.
     * @return новая LinkedList с элементами коллекции
     */
    public LinkedList<City> getCollection() {
        return new LinkedList<>(collection);
    }

    /**
     * Возвращает дату инициализации коллекции.
     * @return дата и время создания менеджера
     */
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    /**
     * Возвращает количество элементов в коллекции.
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }

    /**
     * Проверяет, пуста ли коллекция.
     * @return true если коллекция пуста
     */
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * Возвращает максимальный ID в коллекции.
     * @return максимальный ID или 0 если коллекция пуста
     */
    public int getMaxId() {
        return collection.stream()
                .mapToInt(City::getId)
                .max()
                .orElse(0);
    }

    /**
     * Находит город по ID.
     *
     * @param id идентификатор для поиска
     * @return город или null если не найден
     */
    public City getById(int id) {
        for (City city : collection) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    /**
     * Проверяет наличие города с указанным ID.
     *
     * @param id идентификатор для проверки
     * @return true если город найден
     */
    public boolean containsId(int id) {
        return getById(id) != null;
    }

    /**
     * Добавляет город в коллекцию.
     * <p>Если ID не установлен, генерирует новый (max+1).
     * Автоматически устанавливает дату создания и сортирует коллекцию.</p>
     *
     * @param city город для добавления
     * @throws IllegalArgumentException если city null
     */
    public void add(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City не может быть null");
        }
        if (city.getId() <= 0) {
            city.setId(getMaxId() + 1);
        }
        if (city.getCreationDate() == null) {
            city.setCreationDate(LocalDate.now());
        }
        collection.add(city);
        collection.sort(null);
    }

    /**
     * Обновляет город по ID.
     * <p>Сохраняет оригинальный ID и дату создания.</p>
     *
     * @param id идентификатор города для обновления
     * @param newCity новые данные города
     * @return true если обновление успешно
     */
    public boolean update(int id, City newCity) {
        City existing = getById(id);
        if (existing == null) return false;
        newCity.setId(id);
        newCity.setCreationDate(existing.getCreationDate());
        collection.remove(existing);
        collection.add(newCity);
        collection.sort(null);
        return true;
    }

    /**
     * Удаляет город по ID.
     *
     * @param id идентификатор города для удаления
     * @return true если удаление успешно
     */
    public boolean removeById(int id) {
        return collection.removeIf(city -> city.getId() == id);
    }

    /**
     * Очищает всю коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает первый элемент коллекции (с минимальным ID).
     * @return первый город или null если коллекция пуста
     */
    public City getFirst() {
        return collection.isEmpty() ? null : collection.getFirst();
    }

    /**
     * Добавляет город, если он меньше первого элемента.
     *
     * @param city город для добавления
     * @return true если город добавлен
     */
    public boolean addIfMin(City city) {
        if (isEmpty() || city.compareTo(getFirst()) < 0) {
            add(city);
            return true;
        }
        return false;
    }

    /**
     * Удаляет все города, которые меньше заданного.
     *
     * @param city город для сравнения
     * @return количество удалённых элементов
     */
    public int removeLower(City city) {
        int count = 0;
        for (City c : new LinkedList<>(collection)) {
            if (c.compareTo(city) < 0) {
                collection.remove(c);
                count++;
            }
        }
        return count;
    }

    /**
     * Находит город с максимальной плотностью населения.
     *
     * @return город с макс. плотностью или null
     */
    public City maxByPopulationDensity() {
        return collection.stream()
                .filter(c -> c.getPopulationDensity() != null)
                .max((c1, c2) -> Double.compare(
                        c1.getPopulationDensity(),
                        c2.getPopulationDensity()))
                .orElse(null);
    }

    /**
     * Считает количество городов с типом правления больше заданного.
     *
     * @param government тип правления для сравнения
     * @return количество городов
     */
    public long countGreaterThanGovernment(Government government) {
        if (government == null) return 0;
        return collection.stream()
                .map(City::getGovernment)
                .filter(g -> g != null && g.compareTo(government) > 0)
                .count();
    }

    /**
     * Фильтрует города по подстроке в названии.
     *
     * @param name подстрока для поиска (регистр не важен)
     * @return список найденных городов
     */
    public LinkedList<City> filterContainsName(String name) {
        LinkedList<City> result = new LinkedList<>();
        if (name == null) return result;
        for (City city : collection) {
            if (city.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(city);
            }
        }
        return result;
    }

    /**
     * Возвращает информацию о коллекции.
     * @return строка с типом, датой и количеством элементов
     */
    public String getInfo() {
        return "Тип коллекции: " + collection.getClass().getName() + "\n" +
                "Дата инициализации: " + initializationDate + "\n" +
                "Количество элементов: " + size();
    }

    /**
     * Загружает коллекцию из JSON-файла.
     * <p>Использует InputStreamReader для чтения (требование задания).
     * Путь берётся из переменной окружения CITY_FILE.</p>
     *
     * @throws IOException если файл не найден или переменная не установлена
     */
    public void loadFromFile() throws IOException {
        String envFile = System.getenv("CITY_FILE");
        if (envFile != null && !envFile.isEmpty()) {
            this.filePath = envFile;
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new IOException("Переменная окружения CITY_FILE не установлена");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fis)) {

            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }

            if (json.length() > 0) {
                collection = JSONReader.parseCities(json.toString());
            }
        }
        collection.sort(null);
    }

    /**
     * Сохраняет коллекцию в JSON-файл.
     * <p>Использует PrintWriter для записи (требование задания).</p>
     *
     * @throws IOException если путь не задан или нет прав на запись
     */
    public void saveToFile() throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IOException("Путь к файлу не задан");
        }

        try (FileOutputStream fos = new FileOutputStream(filePath);
             PrintWriter writer = new PrintWriter(fos)) {

            writer.print(JSONWriter.citiesToJson(collection));
            writer.flush();
        }
    }
}