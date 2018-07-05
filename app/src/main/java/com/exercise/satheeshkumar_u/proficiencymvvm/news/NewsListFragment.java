package com.exercise.satheeshkumar_u.proficiencymvvm.news;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.satheeshkumar_u.proficiencymvvm.R;
import com.exercise.satheeshkumar_u.proficiencymvvm.adapter.NewsListAdapter;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.network.ApiClient;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.network.RetrofitApi;
import com.exercise.satheeshkumar_u.proficiencymvvm.util.Utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;
    RetrofitApi apiInterface;

    public NewsListFragment() {
        // Required empty public constructor
    }

    public static NewsListFragment newInstance(String param1, String param2) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        apiInterface = ApiClient.getClient().create(RetrofitApi.class);
        recyclerView = view.findViewById(R.id.rvList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        callNewsApi();
        return view;
    }

    private void callNewsApi() {

        Utils.showProgress(getActivity());

        /**
         GET List Resources
         **/
        Call<ItemResponse> call = apiInterface.getListResources();
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                ItemResponse resource = response.body();
                newsListAdapter = new NewsListAdapter(resource.getNews(), getActivity());
                recyclerView.setAdapter(newsListAdapter);
//                responseText.setText(displayResponse);

            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
