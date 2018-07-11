package com.exercise.satheeshkumar_u.proficiencymvvm.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManager {

    public static NetworkManager getInstance(){
        return new NetworkManager();
    }

    public LiveData<ItemResponse> callNewsApi() {

        final MutableLiveData<ItemResponse> liveDataResponse = new MutableLiveData<>();

        RetrofitApi apiInterface = ApiClient.getClient().create(RetrofitApi.class);
        Call<ItemResponse> call = apiInterface.getListResources();
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse> response) {

                ItemResponse resource = response.body();
                liveDataResponse.setValue(resource);
            }

            @Override
            public void onFailure(@NonNull Call<ItemResponse> call, @NonNull Throwable t) {
                call.cancel();
                liveDataResponse.setValue(null);
            }
        });

        return  liveDataResponse;
    }

}
