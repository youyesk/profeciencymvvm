package com.exercise.satheeshkumar_u.proficiencymvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.network.NetworkManager;

public class ListViewModel extends AndroidViewModel {

    private LiveData<ItemResponse> projectListObservable;

    public ListViewModel(@NonNull Application application) {
        super(application);
        callApi();
    }

    public void callApi(){
        this.projectListObservable = NetworkManager.getInstance().callInfoApi();
    }

    public LiveData<ItemResponse> getProjectListObservable() {
        return projectListObservable;
    }

}
