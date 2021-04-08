package com.gaurav.pagingwithroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class VehicleList extends AppCompatActivity {

    String actionBarTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        int type = getIntent().getIntExtra("type",1);
        if(type == 1) actionBarTitle = "Cars List";
        else actionBarTitle = "Bikes List";

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(actionBarTitle);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}