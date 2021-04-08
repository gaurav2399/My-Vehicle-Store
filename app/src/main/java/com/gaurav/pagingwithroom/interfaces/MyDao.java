package com.gaurav.pagingwithroom.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gaurav.pagingwithroom.modals.Bike;
import com.gaurav.pagingwithroom.modals.Car;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface MyDao {

    @Query("Select * from car")
    Flowable<List<Car>> getCars();

    @Insert
    Completable insertCar(Car car);

    @Query("select * from bike")
    Flowable<List<Bike>> getBikes();

    @Query("select car.owner from car inner join bike on car.owner = bike.owner")
    Flowable<List<String>> getOwnerSame();

    @Insert
    Completable insertBike(Bike bike);

    @Insert
    Completable insertCarList(List<Car> carList);

    @Insert
    Completable insertBikeList(List<Bike> bikeList);

}
