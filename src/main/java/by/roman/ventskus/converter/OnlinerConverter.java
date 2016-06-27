package by.roman.ventskus.converter;

import by.roman.ventskus.dto.onliner.Apartment;
import by.roman.ventskus.entity.Flat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
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
        flat.setPrice(apartment.getPrice().getAmount());
        flat.setParsedDate(new Date());
        loadInfoFromUrl(flat);
        return flat;
    }

    private void enableSSLSocket(){
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadInfoFromUrl(Flat flat) {
        try {
            enableSSLSocket();
            Document document = Jsoup.connect(flat.getLink()).get();
            Elements descriptionElements = document.getElementsByClass("apartment-info__sub-line_extended-bottom");
            if (!descriptionElements.isEmpty()) {
                String description = document.getElementsByClass("apartment-info__sub-line_extended-bottom").get(0).text();
                flat.setDescription(description);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
