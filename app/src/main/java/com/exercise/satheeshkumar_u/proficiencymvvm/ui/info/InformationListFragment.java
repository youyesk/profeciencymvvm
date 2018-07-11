package com.exercise.satheeshkumar_u.proficiencymvvm.ui.info;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.satheeshkumar_u.proficiencymvvm.R;
import com.exercise.satheeshkumar_u.proficiencymvvm.adapter.NewsListAdapter;
import com.exercise.satheeshkumar_u.proficiencymvvm.data.model.ItemResponse;
import com.exercise.satheeshkumar_u.proficiencymvvm.util.Utils;
import com.exercise.satheeshkumar_u.proficiencymvvm.viewmodel.ListViewModel;

public class InformationListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvInfoList;
    private NewsListAdapter newsListAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public InformationListFragment() {
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
        if(getActivity()!=null)
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
                if (response != null && getActivity()!=null) {
                    ActionBar actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();
                    if(actionBar!=null){
                        actionBar.setTitle(response.getTitle());
                    }
                    newsListAdapter = new NewsListAdapter(response.getNews(), getActivity());
                    rvInfoList.setAdapter(newsListAdapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_list, container, false);
        rvInfoList = view.findViewById(R.id.rvInfoList);
        // SwipeRefreshLayout
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        if(getActivity()!=null) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            rvInfoList.setLayoutManager(mLayoutManager);
            rvInfoList.setItemAnimator(new DefaultItemAnimator());
            rvInfoList.addItemDecoration(new DividerItemDecoration(rvInfoList.getContext(), DividerItemDecoration.VERTICAL));
        }
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
