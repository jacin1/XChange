package org.knowm.xchange.bybit.service;

import org.knowm.xchange.bybit.BybitAdapters;
import org.knowm.xchange.bybit.BybitExchange;
import org.knowm.xchange.bybit.dto.BybitResult;
import org.knowm.xchange.bybit.dto.trade.BybitOrderBookDetails;
import org.knowm.xchange.bybit.dto.trade.BybitTradesDetails;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BybitMarketDataService extends BybitMarketDataServiceRaw implements MarketDataService {
    public BybitMarketDataService(BybitExchange exchange) {
        super(exchange);
    }


    @Override
    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        String symbol = BybitAdapters.convertToBybitSymbol(currencyPair .toString());
        BybitResult<BybitOrderBookDetails> orderBook = this.bybitAuthenticated.orderBook(this.apiKey, symbol, this.nonceFactory, this.signatureCreator);
        if (!orderBook.isSuccess()) {
            throw BybitAdapters.createBybitExceptionFromResult(orderBook);
        } else {
            List<LimitOrder> bids = orderBook.getResult().getBids().stream().map(e ->
                    new LimitOrder(Order.OrderType.BID, new BigDecimal(e.get(1)), currencyPair , null, null, new BigDecimal(e.get(0)))).collect(Collectors.toList());
            List<LimitOrder> asks = orderBook.getResult().getAsks().stream().map(e ->
                    new LimitOrder(Order.OrderType.ASK, new BigDecimal(e.get(1)),currencyPair , null, null, new BigDecimal(e.get(0)))).collect(Collectors.toList());
            return new OrderBook(new Date(orderBook.getResult().getTime()), asks, bids);
        }
    }

    @Override
    public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
        String symbol = BybitAdapters.convertToBybitSymbol(currencyPair.toString());
        BybitResult<List<BybitTradesDetails>> trades = this.bybitAuthenticated.trades(this.apiKey, symbol, this.nonceFactory, this.signatureCreator);
        if (!trades.isSuccess()) {
            throw BybitAdapters.createBybitExceptionFromResult(trades);
        } else {
            List<Trade> rs = trades.getResult().stream().map(at ->
                            new Trade.Builder()
                                    .type(at.getIsBuyerMaker() ? Order.OrderType.BID : Order.OrderType.ASK)
                                    .originalAmount(new BigDecimal(at.getQty()))
                                    .instrument(currencyPair)
                                    .price(new BigDecimal(at.getPrice()))
                                    .timestamp(new Date(at.getTime()))
                                    .id(Long.toString(at.getTime()))
                                    .build())
                    .collect(Collectors.toList());
            return new Trades(rs, Trades.TradeSortType.SortByTimestamp);
        }
    }
}
