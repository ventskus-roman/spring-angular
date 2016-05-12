package by.roman.ventskus.geo;

import com.google.common.collect.Maps;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Roman Ventskus on 26.01.2016.
 */
public class GeoCoder {

    public Point getPoint(final String address) {
        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// указывает, исходит ли запрос на геокодирование от устройства с датчиком
        // местоположения
        params.put("address", address);// адрес, который нужно геокодировать
        final String url = baseUrl + '?' + JsonReader.encodeParams(params);// генерируем путь с параметрами
        System.out.println(url);// Можем проверить что вернет этот путь в браузере
        final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        // как правило наиболее подходящий ответ первый и данные о координатах можно получить по пути
        // //results[0]/geometry/location/lng и //results[0]/geometry/location/lat
        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        final double lng = location.getDouble("lng");// долгота
        final double lat = location.getDouble("lat");// широта
        final Point point = new Point(lng, lat);
        System.out.println(address + " " + point); // выводим адрес и точку для него
        return point;
    }

    public Integer distFrom(Point one, Point two) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(two.lat-one.lat);
        double dLng = Math.toRadians(two.lng-one.lng);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(one.lat)) * Math.cos(Math.toRadians(two.lat)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (int) (earthRadius * c);
    }
}
