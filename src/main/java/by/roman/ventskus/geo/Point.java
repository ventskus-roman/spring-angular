package by.roman.ventskus.geo;

/**
 * Created by Roman Ventskus on 26.01.2016.
 */
public class Point {
    public double lat;
    public double lng;

    public Point(final double lng, final double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return lat + "," + lng;
    }
}