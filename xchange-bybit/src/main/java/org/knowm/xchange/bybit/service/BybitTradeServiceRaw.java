package org.knowm.xchange.bybit.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bybit.dto.BybitResult;
import org.knowm.xchange.bybit.dto.trade.BybitOrderDetails;
import org.knowm.xchange.bybit.dto.trade.BybitOrderRequest;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.Map;

import static org.knowm.xchange.bybit.BybitAdapters.createBybitExceptionFromResult;

public class BybitTradeServiceRaw extends BybitBaseService {

  public BybitTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public BybitResult<BybitOrderDetails> getBybitOrder(String orderId) throws IOException {
    BybitResult<BybitOrderDetails> order = bybitAuthenticated.getOrder(apiKey, orderId, nonceFactory, signatureCreator);
    if (!order.isSuccess()) {
      throw createBybitExceptionFromResult(order);
    }
    return order;
  }

  public BybitResult<BybitOrderRequest> placeOrder(String symbol, long qty, String side, String type) throws IOException {
    BybitResult<BybitOrderRequest> placeOrder = bybitAuthenticated.placeOrder(
            apiKey,
            symbol,
            qty,
            side,
            type,
            nonceFactory,
            signatureCreator
    );
    if (!placeOrder.isSuccess()) {
      throw createBybitExceptionFromResult(placeOrder);
    }
    return placeOrder;
  }


  public BybitResult<Map<String,Object>> placeLimitOrder(String var2, Double var3,
                                                 String var4, String var5, Double var6) throws IOException, BybitException{
    BybitResult<Map<String, Object>> r = bybitAuthenticated.placeLimitOrder(apiKey, var2, var3, var4, var5, var6, nonceFactory, signatureCreator);
    if(!r.isSuccess()){
      throw createBybitExceptionFromResult(r);
    }
    return r;
  }



}
