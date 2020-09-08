package com.smthweird.peretz;

import android.app.Application;
import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static PeretzApi peretzApi;
    private static String BASE_URL = "https://peretz-group.ru/api/v2/";
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("CountProduct", MODE_PRIVATE);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        peretzApi = retrofit.create(PeretzApi.class);
    }

    public static void savePref(int id, int quantity) {
        sharedPreferences.edit()
                .putInt(String.valueOf(id), quantity)
                .apply();
    }

    public static Integer loadPref(int id) {
        return sharedPreferences.getInt(String.valueOf(id), 0);
    }

    public static void deletePref(int id){
        sharedPreferences.edit().remove(String.valueOf(id));

    }
    public static PeretzApi getPeretzApi() {
        return peretzApi;
    }

}
