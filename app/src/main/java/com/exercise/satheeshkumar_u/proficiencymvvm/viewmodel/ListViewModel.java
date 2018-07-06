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
        this.projectListObservable = NetworkManager.getInstance().callNewsApi(application.getApplicationContext());
    }

    public LiveData<ItemResponse> getProjectListObservable() {
        return projectListObservable;
    }

    public void setProjectListObservable(LiveData<ItemResponse> projectListObservable) {
        this.projectListObservable = projectListObservable;
    }

}
