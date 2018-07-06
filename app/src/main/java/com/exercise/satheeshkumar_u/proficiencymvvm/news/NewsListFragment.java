package com.exercise.satheeshkumar_u.proficiencymvvm.news;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.satheeshkumar_u.proficiencymvvm.MainActivity;
import com.exercise.satheeshkumar_u.proficiencymvvm.R;
import com.exercise.satheeshkumar_u.proficiencymvvm.adapter.NewsListAdapter;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.network.ApiClient;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.network.RetrofitApi;
import com.exercise.satheeshkumar_u.proficiencymvvm.util.Utils;
import com.exercise.satheeshkumar_u.proficiencymvvm.viewmodel.ListViewModel;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshModel();
    }

    private void refreshModel() {
        if (Utils.isNetworkAvailable(getActivity())) {
            Utils.showProgress(getActivity());
            mSwipeRefreshLayout.setRefreshing(false);
            final ListViewModel viewModel =
                    ViewModelProviders.of(this).get(ListViewModel.class);

            observeViewModel(viewModel);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            Utils.showError(getResources().getString(R.string.no_internet), getActivity());
        }
    }

    private void observeViewModel(ListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, new Observer<ItemResponse>() {
            @Override
            public void onChanged(@Nullable ItemResponse response) {
                Utils.dismissProgress();
                if (response != null) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(response.getTitle());
                    newsListAdapter = new NewsListAdapter(response.getNews(), getActivity());
                    recyclerView.setAdapter(newsListAdapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        recyclerView = view.findViewById(R.id.rvList);
        // SwipeRefreshLayout
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        refreshModel();
    }
}
