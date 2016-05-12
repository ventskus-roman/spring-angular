package by.roman.ventskus.entity;

import javax.persistence.*;

/**
 * Created by Roman Ventskus on 31.12.2015.
 */
@Entity
@Table
public class Subscription extends BaseEntity {

    @Column(name = "price_start", nullable = false)
    private Integer priceStart;

    @Column(name = "price_end", nullable = false)
    private Integer priceEnd;

    @Column(name = "only_near_metro", nullable = false)
    private Boolean onlyNearMetro;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "accept_code", nullable = false)
    private String acceptCode;

    @Column(name = "user_id")
    private String userId;

    public Subscription() {
    }

    public Subscription(Integer priceStart, Integer priceEnd, Boolean onlyNearMetro, String email, Boolean active, String acceptCode, String userId) {
        this.priceStart = priceStart;
        this.priceEnd = priceEnd;
        this.onlyNearMetro = onlyNearMetro;
        this.email = email;
        this.active = active;
        this.acceptCode = acceptCode;
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAcceptCode() {
        return acceptCode;
    }

    public void setAcceptCode(String acceptCode) {
        this.acceptCode = acceptCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
