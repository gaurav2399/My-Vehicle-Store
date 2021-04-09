package com.gaurav.pagingwithroom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.gaurav.pagingwithroom.modals.Bike;
import com.gaurav.pagingwithroom.modals.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    MyDatabase myDatabase;
    Context ctx;
    List<Car> carList = new ArrayList<>();
    List<Bike> bikeList = new ArrayList<>();
    Button insertCar, insertBike, getCars, getBikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setTitle("My Vehicle Store");

        myDatabase = MyDatabase.getInstance(ctx);

        insertCar = findViewById(R.id.button1);
        insertBike = findViewById(R.id.button2);
        getCars = findViewById(R.id.button3);
        getBikes = findViewById(R.id.button4);

        initializeDB();

        insertCar.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, InsertVehicle.class);
            intent.putExtra("type",1);
            startActivity(intent);
        });

        insertBike.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, InsertVehicle.class);
            intent.putExtra("type",2);
            startActivity(intent);
        });

        getCars.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, VehicleList.class);
            intent.putExtra("type",1);
            startActivity(intent);
        });

        getBikes.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, VehicleList.class);
            intent.putExtra("type",2);
            startActivity(intent);
        });



//
//        myDatabase.myDao().getBikes()
//                .subscribeOn(Schedulers.io())
//                .subscribe(bikeList1 -> {
//                    Log.e(TAG,"bike list size is " + bikeList1.size());
//                    for(Bike bike1 : bikeList1){
//                        Log.e(TAG,"brand of bike is " + bike1.getBrand());
//                    }
//                }, e -> Log.e(TAG,"error is  " + e.getMessage()));

    }

    private void initializeDB() {

        String[] brands = new String[]{"bmw", "mercedes", "bentley", "ninja", "audi", "ford", "suzuki", "jaguar", "ferrari"};
        String[] names = new String[]{"gaurav", "aayush", "golu", "apoorv", "vaibhav", "mohit", "raina", "shubhangi", "priyanka"};
        String[] model = new String[]{"2001", "2003", "2006", "2020", "1987", "2014", "2005", "1999", "1990"};

        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            int x = r.nextInt(9);
            int y = r.nextInt(9);
            int z = r.nextInt(9);
            Car car = new Car(brands[x], model[y], 100000, names[z]);
            Bike bike = new Bike(brands[z], model[x], 200000, names[y]);

            carList.add(car);
            bikeList.add(bike);

        }

        myDatabase.myDao().insertBikeList(bikeList)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> Log.e(TAG, "list of bikes is inserted"));

        myDatabase.myDao().insertCarList(carList)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> Log.e(TAG, "list of cars is inserted"));

    }
}