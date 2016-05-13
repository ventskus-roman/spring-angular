package by.roman.ventskus.service;

import by.roman.ventskus.converter.OnlinerConverter;
import by.roman.ventskus.dto.onliner.Apartment;
import by.roman.ventskus.dto.onliner.OnlinerResponse;
import by.roman.ventskus.entity.Flat;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Service
@Qualifier("onlinerLoader")
public class OnlinerLoader implements Loader {

    private static final String URL = "https://ak.api.onliner.by/search/apartments?rent_type[]=1_room&rent_type[]=2_rooms&rent_type[]=3_rooms&rent_type[]=4_rooms&rent_type[]=5_rooms&rent_type[]=6_rooms#bounds%5Blb%5D%5Blat%5D=53.78036940194486&bounds%5Blb%5D%5Blong%5D=27.321624755859375&bounds%5Brt%5D%5Blat%5D=54.01583847496656&bounds%5Brt%5D%5Blong%5D=27.802276611328125";
    private static final String URL_METRO = "https://ak.api.onliner.by/search/apartments?rent_type[]=1_room&rent_type[]=2_rooms&rent_type[]=3_rooms&rent_type[]=4_rooms&rent_type[]=5_rooms&rent_type[]=6_rooms&metro%5B%5D=red_line&metro%5B%5D=blue_line&bounds%5Blb%5D%5Blat%5D=53.78036940194486&bounds%5Blb%5D%5Blong%5D=27.321624755859375&bounds%5Brt%5D%5Blat%5D=54.01583847496656&bounds%5Brt%5D%5Blong%5D=27.802276611328125";

    @Autowired
    private OnlinerConverter onlinerConverter;

    @Override
    public List<Flat> load() {
        Set<Flat> result = new HashSet<Flat>();
        result.addAll(load(true));
        result.addAll(load(false));
        return new ArrayList<Flat>(result);
    }

    private List<Flat> load(Boolean nearForMetro) {
        try {
            String url = nearForMetro ? URL_METRO : URL;
            Connection con = HttpConnection.connect(url);
            con.method(Connection.Method.GET).ignoreContentType(true);
            Connection.Response resp = con.execute();
            String body = resp.body();
            OnlinerResponse response = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create().fromJson(body, OnlinerResponse.class);
            List<Apartment> apartments = response.getApartments();
            List<Flat> result = onlinerConverter.convert(apartments);
            if (nearForMetro) {
                for (Flat flat : result) {
                    flat.setNearForMetro(true);
                }
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
