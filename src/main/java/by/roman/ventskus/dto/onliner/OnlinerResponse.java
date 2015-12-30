package by.roman.ventskus.dto.onliner;

import java.util.List;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
public class OnlinerResponse {

    private List<Apartment> apartments;

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}
