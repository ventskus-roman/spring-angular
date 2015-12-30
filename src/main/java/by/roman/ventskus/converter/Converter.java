package by.roman.ventskus.converter;

import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
public interface Converter<T, K> {

    List<K> convert(List<T> objects);
}
