package com.cyqhl.netlib.client;

import okhttp3.OkHttpClient;

public class HttpClient {
    private static final long DEFAULT_TIME_OUT = 1000L * 10;
    private static final long DEFAULT_DOWN_TIME_OUT = 1000L * 60 * 3;
    private static OkHttpClient httpClient;

    public static OkHttpClient getHttpClient() {
        if(httpClient == null){
            //需要重新实现
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }
}
