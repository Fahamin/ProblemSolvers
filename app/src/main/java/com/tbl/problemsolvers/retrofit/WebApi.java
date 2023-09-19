package com.tbl.problemsolvers.retrofit;

import com.tbl.problemsolvers.retrofit.Model.SyncRespons;
import com.tbl.problemsolvers.retrofit.Model.TokenModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebApi {
    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpOrder/ApiSyncOrderNew")
    Call<SyncRespons> sendOrderSync(@Header("Authorization") String authorization, @Body String body);

    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpVisitDetail/ApiSyncVisitDetailNew")
    Call<SyncRespons> sendVisitSync(@Header("Authorization") String authorization, @Body String body);

    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpNewOutlet/ApiSyncNewOutletNew")
    Call<SyncRespons> sendNewOutletSync(@Header("Authorization") String authorization, @Body String body);

    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpLocation/AddRealtimePsrLocation")
    Call<SyncRespons> sendOfflineData(@Body String body);

    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpNewOutlet/ApiSyncEdsOutlet")
    Call<SyncRespons> sendEditOutletSync(@Header("Authorization") String authorization, @Body String body);

    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpVisicooler/ApiSyncSgainfo")
    Call<SyncRespons> sendVisicoolerSync(@Header("Authorization") String authorization, @Body String body);


    @Headers({"Content-Type: application/json; charset=utf-8"})
    @POST("/api/ApiSyncUpVisitDetail/ApiSyncDamage")
    Call<SyncRespons> sendDamageSync(@Header("Authorization") String authorization, @Body String body);


    @FormUrlEncoded
    @POST("/api/PSRtoken")
    Call<TokenModel> getToken(@Field("username") String username,
                              @Field("password") String password,
                              @Field("grant_type") String grant_type);


    @Headers({"Content-Type: application/json; charset=utf-8"})
    @FormUrlEncoded
    @POST("/api/ApiCheckUser/ApiCheckUser")
    Call<LoginModel> getLoginInfo(
            @FieldMap HashMap<String, String> param,
            @Header("Authorization") String authorization);


    @Headers({"Content-Type: application/json; charset=utf-8"})
    @GET("/api/ApiSyncPromotation/ApiGetTradePromotionNew?")
    Call<OfferModel> getOfferList(
            @Header("Authorization") String authorization,
            @Query("dbid") String id,
            @Query("psrid") String psrid,
            @Query("token") String token);


    @Headers({"Content-Type: application/json; charset=utf-8"})
    @GET("/api/ApiSyncPromotation/ApiGetTradePromotionNew?")
    Call<OutletModel> getOutletList(
            @Header("Authorization") String authorization,
            @Query("dbid") String id,
            @Query("psrid") String psrid,
            @Query("token") String token);

}
