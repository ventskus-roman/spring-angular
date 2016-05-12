package by.roman.ventskus.geo.address;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Roman Ventskus on 26.01.2016.
 */
public class Parser {

    private static List<String> streets = Arrays.asList(AllMinskStreets.STREETS.split(","));
    private static Pattern HOUSE_NUMBER_PATTERN = Pattern.compile("\\d+");

    public String parseAddress(String text) {
        try {
            for (String street : streets) {
                if (text.contains(street)) {
                    Integer offset = 0;
                    Integer indexOfStreetInText;
                    while ((indexOfStreetInText = text.toLowerCase().indexOf(street.toLowerCase(), offset + 1)) != -1) {
                        offset = indexOfStreetInText;
                        String regionForNumberOfHouseSearching = text.substring(indexOfStreetInText - 6, indexOfStreetInText + street.length() + 6);
                        Matcher m = HOUSE_NUMBER_PATTERN.matcher(regionForNumberOfHouseSearching);
                        while (m.find()) {
                            String number = m.group(0);
                            return street + " " + number;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}
