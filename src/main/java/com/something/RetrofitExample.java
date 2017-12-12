package com.something;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitExample {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        final ApiClient apiClient = retrofit.create(ApiClient.class);

        // using Single
        apiClient.getPost()
                .subscribe(System.out::println,
                        throwable -> System.out.println(throwable.getMessage()));

        // using Completable with the terminal operations of the Completable
        CompletableObserver completableObserver = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                System.out.println("PUT CALL DONE!");
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        apiClient.updatePost()
                .subscribe(() -> System.out.println("PUT CALL DONE"),
                        throwable -> System.out.println(throwable.getMessage()));


        // power of the completable with andThen()
        apiClient.updatePost()
                .andThen(apiClient.getPost())
                .subscribe(System.out::println,
                        throwable -> System.out.println(throwable.getMessage()));
    }
}
