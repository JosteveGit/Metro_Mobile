package com.metro.metromobile.injector;

import com.metro.metromobile.rest.LoggingInterceptor;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.utils.app.ServerUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofitInstance;
    private static ServiceGenerator serviceGenerator;

    public static Retrofit provideRetrofit() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injector.provideOkHttpClient())
                    .baseUrl(ServerUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }

    private static OkHttpClient provideOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor()).build();
        }

        return okHttpClient;
    }

    public static ServiceGenerator getServiceGenerator() {
        if (serviceGenerator == null) {
            serviceGenerator = provideRetrofit().create(ServiceGenerator.class);
        }

        return serviceGenerator;
    }


}