package com.kscd.builder.example.builders;

import com.kscd.builder.example.cars.CarType;
import com.kscd.builder.example.components.Engine;
import com.kscd.builder.example.components.GPSNavigator;
import com.kscd.builder.example.components.Transmission;
import com.kscd.builder.example.components.TripComputer;

/**
 * EN: Builder interface defines all possible ways to configure a product.
 *
 * RU: Интерфейс Строителя объявляет все возможные этапы и шаги конфигурации
 * продукта.
 */
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
