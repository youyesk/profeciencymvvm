package com.exercise.satheeshkumar_u.proficiencymvvm.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.exercise.satheeshkumar_u.proficiencymvvm.R;
import com.exercise.satheeshkumar_u.proficiencymvvm.ui.info.InformationListFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFragment();
    }


    private void setUpFragment() {
        InformationListFragment newFragment = new InformationListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, newFragment).commit();
    }

}
