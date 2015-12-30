package by.roman.ventskus.service;

import by.roman.ventskus.converter.Converter;
import by.roman.ventskus.converter.OnlinerConverter;
import by.roman.ventskus.dto.onliner.Apartment;
import by.roman.ventskus.dto.onliner.OnlinerResponse;
import by.roman.ventskus.entity.Flat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Service
public class OnlinerLoader implements Loader {

    private static final String URL = "https://ak.api.onliner.by/search/apartments?bounds%5Blb%5D%5Blat%5D=53.78036940194486&bounds%5Blb%5D%5Blong%5D=27.321624755859375&bounds%5Brt%5D%5Blat%5D=54.01583847496656&bounds%5Brt%5D%5Blong%5D=27.802276611328125";

    @Autowired
    private OnlinerConverter onlinerConverter;

    @Override
    public List<Flat> load() {
        Connection con = HttpConnection.connect(URL);
        con.method(Connection.Method.GET).ignoreContentType(true);
        Connection.Response resp = null;
        try {
            resp = con.execute();
            String body = resp.body();
            OnlinerResponse response = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create().fromJson(body, OnlinerResponse.class);
            List<Apartment> apartments = response.getApartments();
            return onlinerConverter.convert(apartments);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
