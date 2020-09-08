package com.smthweird.peretz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeretzApi {

    final static String PERETZ_KEY = "47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278";

    @GET("products")

    Call<List<Food>> getFoodsById(
            @Query("category") Integer category,
            @Query("key") String key
    );

}
