package com.tbl.problemsolvers.retrofit;


public class ApiUtils {

    public static WebApi getAPIService() {

        return RetrofitClient.getClient("LinkUtil.domain").create(WebApi.class);
    }

}

