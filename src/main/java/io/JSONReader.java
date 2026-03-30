package io;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Чтение данных из JSON-файла.
 * <p>Преобразует JSON-строку в коллекцию объектов {@link City}.
 * Используется при загрузке коллекции из файла.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class JSONReader {

    /** Формат даты для LocalDate (ISO_LOCAL_DATE) */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    /** Формат даты для LocalDateTime (ISO_LOCAL_DATE_TIME) */
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Парсит JSON-строку в список городов.
     *
     * @param json JSON-строка с данными городов
     * @return LinkedList с объектами City
     */
    public static LinkedList<City> parseCities(String json) {
        LinkedList<City> cities = new LinkedList<>();
        JSONArray array = new JSONArray(json);

        for (int i = 0; i < array.length(); i++) {
            cities.add(parseCity(array.getJSONObject(i)));
        }
        return cities;
    }

    /**
     * Парсит один объект City из JSONObject.
     *
     * @param obj JSONObject с данными города
     * @return объект City
     */
    private static City parseCity(JSONObject obj) {
        City city = new City();
        city.setId(obj.getInt("id"));
        city.setName(obj.getString("name"));
        city.setCoordinates(parseCoordinates(obj.getJSONObject("coordinates")));

        if (obj.has("creationDate") && !obj.isNull("creationDate")) {
            city.setCreationDate(LocalDate.parse(obj.getString("creationDate"), DATE_FORMAT));
        }

        city.setArea((float) obj.getDouble("area"));
        city.setPopulation(obj.getInt("population"));

        if (obj.has("metersAboveSeaLevel") && !obj.isNull("metersAboveSeaLevel")) {
            city.setMetersAboveSeaLevel(obj.getDouble("metersAboveSeaLevel"));
        }
        if (obj.has("populationDensity") && !obj.isNull("populationDensity")) {
            city.setPopulationDensity(obj.getDouble("populationDensity"));
        }
        if (obj.has("government") && !obj.isNull("government")) {
            city.setGovernment(Government.valueOf(obj.getString("government")));
        }
        if (obj.has("standardOfLiving") && !obj.isNull("standardOfLiving")) {
            city.setStandardOfLiving(StandardOfLiving.valueOf(obj.getString("standardOfLiving")));
        }
        city.setGovernor(parseHuman(obj.getJSONObject("governor")));

        return city;
    }

    /**
     * Парсит координаты из JSONObject.
     *
     * @param obj JSONObject с координатами
     * @return объект Coordinates
     */
    private static Coordinates parseCoordinates(JSONObject obj) {
        return new Coordinates(obj.getDouble("x"), obj.getDouble("y"));
    }

    /**
     * Парсит информацию о правителе из JSONObject.
     *
     * @param obj JSONObject с данными правителя
     * @return объект Human
     */
    private static Human parseHuman(JSONObject obj) {
        String name = obj.getString("name");
        Long age = obj.getLong("age");
        LocalDateTime birthday = null;
        if (obj.has("birthday") && !obj.isNull("birthday")) {
            birthday = LocalDateTime.parse(obj.getString("birthday"), DATETIME_FORMAT);
        }
        return new Human(name, age, birthday);
    }
}