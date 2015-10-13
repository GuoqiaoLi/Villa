package com.example.guoqiao.villa;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;


import adapter.slideAdapter;
import beans.Apartment;
import helper.SharePreferenceHelper;


public class MainActivity extends Activity {
    private String[] imageUrl;
    private Apartment apartment;
    private SharePreferenceHelper helper;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBasicInformation();
        setViewPager();
        setLayout();
    }

    //-------- Set up house information from intent --------//
    public void setBasicInformation(){
        helper = new SharePreferenceHelper(this);
        apartment = helper.fetchApt();
    }

    public void setLayout(){
        TextView address = (TextView) findViewById(R.id.address);
        TextView price = (TextView) findViewById(R.id.price);
        TextView bedroom = (TextView) findViewById(R.id.bedroom);
        TextView bathroom = (TextView) findViewById(R.id.bathroom);
        TextView kitchen = (TextView) findViewById(R.id.kitchen);

        address.setText(apartment.getLocality());
        price.setText(apartment.getPrice());
        bedroom.setText(apartment.getBedroom());
        kitchen.setText(apartment.getKitchen());
        bathroom.setText(apartment.getBathroom());
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
