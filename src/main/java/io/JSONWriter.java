package io;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * Запись данных в JSON-файл.
 * <p>Преобразует коллекцию объектов {@link City} в JSON-строку.
 * Используется при сохранении коллекции в файл.</p>
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class JSONWriter {

    /** Формат даты для LocalDate (ISO_LOCAL_DATE) */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    /** Формат даты для LocalDateTime (ISO_LOCAL_DATE_TIME) */
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Преобразует коллекцию городов в JSON-строку.
     *
     * @param cities коллекция городов для сериализации
     * @return JSON-строка с данными
     */
    public static String citiesToJson(Collection<City> cities) {
        JSONArray array = new JSONArray();
        for (City city : cities) {
            array.put(cityToJSON(city));
        }
        return array.toString(2);
    }

    /**
     * Преобразует объект City в JSONObject.
     *
     * @param city объект города для сериализации
     * @return JSONObject с данными города
     */
    private static JSONObject cityToJSON(City city) {
        JSONObject obj = new JSONObject();
        obj.put("id", city.getId());
        obj.put("name", city.getName());
        obj.put("coordinates", coordinatesToJSON(city.getCoordinates()));
        obj.put("creationDate", city.getCreationDate().format(DATE_FORMAT));
        obj.put("area", city.getArea());
        obj.put("population", city.getPopulation());

        if (city.getMetersAboveSeaLevel() != null) {
            obj.put("metersAboveSeaLevel", city.getMetersAboveSeaLevel());
        }
        if (city.getPopulationDensity() != null) {
            obj.put("populationDensity", city.getPopulationDensity());
        }
        if (city.getGovernment() != null) {
            obj.put("government", city.getGovernment().name());
        }
        if (city.getStandardOfLiving() != null) {
            obj.put("standardOfLiving", city.getStandardOfLiving().name());
        }
        obj.put("governor", humanToJSON(city.getGovernor()));

        return obj;
    }

    /**
     * Преобразует координаты в JSONObject.
     *
     * @param c объект координат
     * @return JSONObject с координатами
     */
    private static JSONObject coordinatesToJSON(Coordinates c) {
        JSONObject obj = new JSONObject();
        obj.put("x", c.getX());
        obj.put("y", c.getY());
        return obj;
    }

    /**
     * Преобразует правителя в JSONObject.
     *
     * @param h объект правителя
     * @return JSONObject с данными правителя
     */
    private static JSONObject humanToJSON(Human h) {
        JSONObject obj = new JSONObject();
        obj.put("name", h.getName());
        obj.put("age", h.getAge());
        if (h.getBirthday() != null) {
            obj.put("birthday", h.getBirthday().format(DATETIME_FORMAT));
        }
        return obj;
    }
}