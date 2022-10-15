package org.knowm.xchange.bybit.dto.trade;

import java.util.List;

public class BybitOrderBookDetails {

    private Long time;
    private List<List<String>> bids;
    private List<List<String>> asks;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }
}
