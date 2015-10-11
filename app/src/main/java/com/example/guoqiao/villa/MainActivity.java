package com.example.guoqiao.villa;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.guoqiao.villa.MapsActivity;
import com.example.guoqiao.villa.R;

import adapter.slideAdapter;


public class MainActivity extends Activity {
    private String address;
    private String[] imageUrl;
    private String name;
    private String landlord;
    private int bedroom;
    private int bathroom;
    private int kitchen;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBasicInformation();
        setViewPager();
    }

    //-------- Set up house information from intent --------//
    public void setBasicInformation(){

    }

    //-------- Set up viewPager --------//
    public void setViewPager(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new slideAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    public void findInMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
