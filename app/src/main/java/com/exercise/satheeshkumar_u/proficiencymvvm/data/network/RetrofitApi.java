package com.exercise.satheeshkumar_u.proficiencymvvm.data.network;

import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

        @GET("/s/2iodh4vg0eortkl/facts.json")
        Call<ItemResponse> getListResources();
}
