package by.roman.ventskus.dto;

/**
 * Created by Roman Ventskus on 31.12.2015.
 */
public class Filter {

    private Integer priceStart;
    private Integer priceEnd;
    private Boolean onlyNearMetro;
    private Integer offset;
    private Integer limit;

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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
