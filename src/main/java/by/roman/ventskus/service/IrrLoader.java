package by.roman.ventskus.service;

import by.roman.ventskus.entity.Flat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Roman Ventskus on 01.01.2016.
 */
@Service
@Qualifier("irrLoader")
public class IrrLoader implements Loader {

    private static final String URL = "http://minsk.irr.by/realestate/longtime/search/metro=1100295328_2719640638,1664329745_375969183,412379607_375969183,770979643_375969183,548641762_375969183,2174292858_375969183,1883522896_2719640638,289191268_2719640638,2015139194_2719640638,4043454774_375969183,3738419110_2719640638,2735620920_2719640638,2145321592_375969183,936437775_375969183,2572441954_2719640638,2949184446_375969183,758211451_2719640638,362657066_2719640638,2307816454_375969183,469519413_375969183,3721681265_375969183,929117731_375969183,2058913372_2719640638,1630634814_2719640638,210250501_2719640638,3179351486_2719640638,1539674705_375969183,3240371451_2719640638,1312743805_375969183/currency=USD/furniture=1/date_create=yesterday/hasimages=1/";

    @Override
    public List<Flat> load() {
        List<Flat> result = new ArrayList<Flat>();
        try {
            Document document = Jsoup.connect(URL).get();
            Elements adsBlockList = document.select(".adds_cont > .add_list, .adds_cont > .add_head");
            if (adsBlockList.size() > 0) {
                for (Element flatBlock : adsBlockList) {
                    if (flatBlock.className().contains("add_head")) {
                        return result;
                    } else {
                        result.add(convertToFlat(flatBlock));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Flat convertToFlat(Element flatBlock) {
        Flat flat = new Flat();
        Element titleLinkBlock = flatBlock.getElementsByClass("add_title").get(0);
        String link = titleLinkBlock.attr("href");
        String description = titleLinkBlock.text();
        String price = findPrice(flatBlock);
        String photoLink = findPhotoLink(flatBlock);
        flat.setLink(link);
        flat.setDescription(description);
        flat.setPrice(Integer.parseInt(price.trim()));
        flat.setPhotoLink(photoLink);
        flat.setSource("irr.by");
        flat.setAddress(description);
        flat.setCity("Минск");
        flat.setNearForMetro(true);
        flat.setParsedDate(new Date());
        flat.setCreationDate(new Date());
        return flat;
    }

    private String findPhotoLink(Element titleLinkBlock) {
        Elements elements = titleLinkBlock.select(".add_pic img");
        if (elements.size() > 0) {
            String srcSmall = elements.get(0).attr("src");
            return srcSmall.replaceAll("listing-realtycrop", "orig");
        }
        return null;
    }

    private String findPrice(Element titleLinkBlock) {
        Elements elements = titleLinkBlock.getElementsByClass("add_cost_alt");
        for (Element element : elements) {
            String text = element.text();
            if (text.contains("$")) {
                return text.replaceAll("[^0-9]", "");
            }
        }
        return null;
    }
}
