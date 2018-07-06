package com.exercise.satheeshkumar_u.proficiencymvvm.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;
import com.exercise.satheeshkumar_u.proficiencymvvm.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkManager {

    RetrofitApi apiInterface;

    public static NetworkManager getInstance(){
        return new NetworkManager();
    }

    public LiveData<ItemResponse> callNewsApi(Context context) {

        Utils.showProgress(context);

        final MutableLiveData<ItemResponse> liveDataResponse = new MutableLiveData<>();

        apiInterface = ApiClient.getClient().create(RetrofitApi.class);
        /**
         GET List Resources
         **/
        Call<ItemResponse> call = apiInterface.getListResources();
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                ItemResponse resource = response.body();
                liveDataResponse.setValue(resource);

            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                call.cancel();
                liveDataResponse.setValue(null);
            }
        });

        return  liveDataResponse;
    }

}
