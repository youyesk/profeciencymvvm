package com.exercise.satheeshkumar_u.proficiencymvvm;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.exercise.satheeshkumar_u.proficiencymvvm.news.NewsListFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFragment();
    }


    private void setUpFragment() {
        NewsListFragment newFragment = new NewsListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_main, newFragment).commit();
    }

    public void setActionBarTitle(){
        ((AppCompatActivity)this).getSupportActionBar().setTitle("sub categories");
    }
}
