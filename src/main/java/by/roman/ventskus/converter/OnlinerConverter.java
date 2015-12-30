package by.roman.ventskus.converter;

import by.roman.ventskus.dto.onliner.Apartment;
import by.roman.ventskus.entity.Flat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Component
public class OnlinerConverter implements Converter<Apartment, Flat> {

    @Override
    public List<Flat> convert(List<Apartment> objects) {
        List<Flat> result = new ArrayList<Flat>();
        if (objects != null) {
            for (Apartment apartment : objects) {
                result.add(convert(apartment));
            }
        }
        return result;
    }

    private Flat convert(Apartment apartment) {
        Flat flat = new Flat();
        flat.setAddress(apartment.getLocation().getAddress());
        flat.setLink(apartment.getUrl());
        flat.setPhotoLink(apartment.getPhoto());
        flat.setCreationDate(apartment.getCreatedAt());
        flat.setSource("http://r.onliner.by/");
        flat.setPrice(apartment.getPrice().getUsd());
        return flat;
    }
}
