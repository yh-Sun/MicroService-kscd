package com.kscd.builder.example.director;

import com.kscd.builder.example.builders.Builder;
import com.kscd.builder.example.cars.CarType;
import com.kscd.builder.example.components.Engine;
import com.kscd.builder.example.components.GPSNavigator;
import com.kscd.builder.example.components.Transmission;
import com.kscd.builder.example.components.TripComputer;

/**
 * EN: Director defines the order of building steps. It works with a builder
 * object through common Builder interface. Therefore it may not know what
 * product is being built.
 *
 * RU: Директор знает в какой последовательности заставлять работать строителя.
 * Он работает с ним через общий интерфейс Строителя. Из-за этого, он может не
 * знать какой конкретно продукт сейчас строится.
 */
public class Director {

    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }
}
