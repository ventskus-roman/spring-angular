package by.roman.ventskus.dto.onliner;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
public class Apartment {

    private Long id;

    private Price price;

    @SerializedName("rent_type")
    private String rentType;

    private Location location;

    private String photo;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("last_time_up")
    private Date lastTimeUp;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastTimeUp() {
        return lastTimeUp;
    }

    public void setLastTimeUp(Date lastTimeUp) {
        this.lastTimeUp = lastTimeUp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
