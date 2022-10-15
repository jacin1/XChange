package org.knowm.xchange.bybit;

import org.knowm.xchange.bybit.dto.BybitResult;
import org.knowm.xchange.bybit.dto.account.BybitBalances;
import org.knowm.xchange.bybit.dto.trade.BybitOrderBookDetails;
import org.knowm.xchange.bybit.dto.trade.BybitOrderDetails;
import org.knowm.xchange.bybit.dto.trade.BybitOrderRequest;
import org.knowm.xchange.bybit.dto.trade.BybitTradesDetails;
import org.knowm.xchange.bybit.service.BybitException;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BybitAuthenticated {

    @GET
    @Path("/spot/v1/account")
    BybitResult<BybitBalances> getWalletBalances(
            @QueryParam("api_key") String apiKey,
            @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
            @QueryParam("sign") ParamsDigest signature
    ) throws IOException, BybitException;

    @GET
    @Path("/spot/v1/order")
    BybitResult<BybitOrderDetails> getOrder(
            @QueryParam("api_key") String apiKey,
            @QueryParam("orderId") String orderId,
            @QueryParam("timestamp") SynchronizedValueFactory<Long> timestamp,
            @QueryParam("sign") ParamsDigest signature
    ) throws IOException, BybitException;

    @POST
    @Path("/spot/v1/order")
    BybitResult<BybitOrderRequest> placeOrder(
            @FormParam("api_key") String apiKey,
            @FormParam("symbol") String symbol,
            @FormParam("qty") long qty,
            @FormParam("side") String side,
            @FormParam("type") String type,
            @FormParam("timestamp") SynchronizedValueFactory<Long> timestamp,
            @FormParam("sign") ParamsDigest signature
    ) throws IOException, BybitException;

    @GET
    @Path("/spot/quote/v1/depth")
    BybitResult<BybitOrderBookDetails> orderBook(@QueryParam("api_key") String var1, @QueryParam("symbol") String var2, @QueryParam("timestamp") SynchronizedValueFactory<Long> var3, @QueryParam("sign") ParamsDigest var4) throws IOException, BybitException;



    @GET
    @Path("/spot/quote/v1/trades")
    BybitResult<List<BybitTradesDetails>> trades(@QueryParam("api_key") String var1, @QueryParam("symbol") String var2, @QueryParam("timestamp") SynchronizedValueFactory<Long> var3, @QueryParam("sign") ParamsDigest var4) throws IOException, BybitException;


    @POST
    @Path("/spot/v1/order")
    BybitResult<Map<String,Object>> placeLimitOrder(@QueryParam("api_key") String var1, @QueryParam("symbol") String var2, @QueryParam("qty") Double var3,
                                                    @QueryParam("side") String var4, @QueryParam("type") String var5, @QueryParam("price") Double var6,
                                                    @QueryParam("timestamp") SynchronizedValueFactory<Long> var7, @QueryParam("sign") ParamsDigest var8) throws IOException, BybitException;


    @DELETE
    @Path("/spot/v1/order")
    BybitResult<Map<String, Object>> cancelOrder(String apiKey, String orderId, SynchronizedValueFactory<Long> nonceFactory, ParamsDigest signatureCreator);
}
