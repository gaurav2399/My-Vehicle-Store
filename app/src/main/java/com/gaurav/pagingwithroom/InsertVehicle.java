package com.gaurav.pagingwithroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gaurav.pagingwithroom.modals.Bike;
import com.gaurav.pagingwithroom.modals.Car;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InsertVehicle extends AppCompatActivity {

    String actionBarTitle = "";
    EditText brand, name, price, model;
    Button save;
    Context ctx;

    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vehicle);

        ctx = this;
        myDatabase = MyDatabase.getInstance(ctx);

        int type = getIntent().getIntExtra("type", 1);
        if (type == 1) actionBarTitle = "Insert Car";
        else actionBarTitle = "Insert Bike";

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(actionBarTitle);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        brand = findViewById(R.id.brand);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        model = findViewById(R.id.model);
        save = findViewById(R.id.save);

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        save.setOnClickListener(v -> {
            String brandVal = brand.getText().toString();
            String nameVal = name.getText().toString();
            String modelVal = model.getText().toString();
            String priceVal = price.getText().toString();
            if (brandVal.isEmpty() || nameVal.isEmpty() || modelVal.isEmpty() || priceVal.isEmpty()) {
                Toast.makeText(ctx, "Please fill all fields.", Toast.LENGTH_LONG).show();
            } else {
                if (type == 1) {
                    Car car = new Car(brandVal, modelVal, Integer.parseInt(priceVal), nameVal);

                    myDatabase.myDao().insertCar(car)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(() -> {
                                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
                                brand.setText("");
                                model.setText("");
                                price.setText("");
                                name.setText("");
                                Toast.makeText(ctx, "Car inserted.", Toast.LENGTH_LONG).show();
                            },
                                    e -> {
                                        Toast.makeText(ctx, "Some error while insertion", Toast.LENGTH_LONG).show();
                                    });

                } else {
                    Bike bike = new Bike(brandVal, modelVal, Integer.parseInt(priceVal), nameVal);

                    myDatabase.myDao().insertBike(bike)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(() -> {
                                        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
                                        brand.setText("");
                                        model.setText("");
                                        price.setText("");
                                        name.setText("");
                                        Toast.makeText(ctx, "Bike inserted.", Toast.LENGTH_LONG).show();
                                    },
                                    e -> {
                                        Toast.makeText(ctx, "Some error while insertion", Toast.LENGTH_LONG).show();
                                    });

                }
            }
        });
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