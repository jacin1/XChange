package org.knowm.xchange.bybit.dto.trade;

public class BybitTradesDetails {

    private String price;
    private Long time;
    private Float qty;
    private Boolean isBuyerMaker;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Boolean getIsBuyerMaker() {
        return isBuyerMaker;
    }

    public void setIsBuyerMaker(Boolean buyerMaker) {
        isBuyerMaker = buyerMaker;
    }
}

