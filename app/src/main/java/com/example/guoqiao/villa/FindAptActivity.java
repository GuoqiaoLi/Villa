package com.example.guoqiao.villa;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fragment.SearchByFilterFragment;
import fragment.SearchByUserFragment;

public class FindAptActivity extends FragmentActivity{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextView filterBtn;
    private TextView userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_apt);

        init();
    }

    public void init(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        SearchByFilterFragment fragment = new SearchByFilterFragment();
        fragmentTransaction.add(R.id.search_fragment, fragment);
        fragmentTransaction.commit();

        filterBtn = (TextView) findViewById(R.id.filter_button);
        userBtn = (TextView) findViewById(R.id.user_button);

        filterBtn.setOnClickListener(filterBtnClick);
        userBtn.setOnClickListener(userBtnClick);
    }

    public View.OnClickListener filterBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("filter", "search by filter clicked");

            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            SearchByFilterFragment fragment = new SearchByFilterFragment();
            fragmentTransaction.replace(R.id.search_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    };

    public View.OnClickListener userBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("user", "search by user clicked");

            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            SearchByUserFragment fragment = new SearchByUserFragment();
            fragmentTransaction.replace(R.id.search_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    };



}
