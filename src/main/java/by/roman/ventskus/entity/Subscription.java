package by.roman.ventskus.entity;

import javax.persistence.*;

/**
 * Created by Roman Ventskus on 31.12.2015.
 */
@Entity
@Table
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_start", nullable = false)
    private Integer priceStart;

    @Column(name = "price_end", nullable = false)
    private Integer priceEnd;

    @Column(name = "only_near_metro", nullable = false)
    private Boolean onlyNearMetro;

    @Column(name = "email", nullable = false)
    private String email;

    public Integer getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Integer priceStart) {
        this.priceStart = priceStart;
    }

    public Integer getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(Integer priceEnd) {
        this.priceEnd = priceEnd;
    }

    public Boolean getOnlyNearMetro() {
        return onlyNearMetro;
    }

    public void setOnlyNearMetro(Boolean onlyNearMetro) {
        this.onlyNearMetro = onlyNearMetro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
