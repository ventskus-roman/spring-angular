package by.roman.ventskus.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman Ventskus on 30.12.2015.
 */
@Entity
@Table(name = "flat")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String address;

    @Column(nullable = false)
    private Integer price;

    @Column
    private String link;

    @Column
    private String description;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "parsed_date", nullable = false)
    private Date parsedDate;

    @Column(nullable = false)
    private String source;

    @Column(name = "near_for_metro", nullable = false)
    private Boolean nearForMetro = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getParsedDate() {
        return parsedDate;
    }

    public void setParsedDate(Date parsedDate) {
        this.parsedDate = parsedDate;
    }

    public Boolean getNearForMetro() {
        return nearForMetro;
    }

    public void setNearForMetro(Boolean nearForMetro) {
        this.nearForMetro = nearForMetro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        if (city != null ? !city.equals(flat.city) : flat.city != null) return false;
        if (address != null ? !address.equals(flat.address) : flat.address != null) return false;
        if (price != null ? !price.equals(flat.price) : flat.price != null) return false;
        if (link != null ? !link.equals(flat.link) : flat.link != null) return false;
        if (description != null ? !description.equals(flat.description) : flat.description != null) return false;
        if (photoLink != null ? !photoLink.equals(flat.photoLink) : flat.photoLink != null) return false;
        return !(source != null ? !source.equals(flat.source) : flat.source != null);

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoLink != null ? photoLink.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }
}
