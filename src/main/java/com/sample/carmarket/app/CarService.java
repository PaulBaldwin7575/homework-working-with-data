package com.sample.carmarket.app;

import com.sample.carmarket.entity.Car;
import com.sample.carmarket.entity.EngineType;
import com.sample.carmarket.entity.Manufacturer;
import com.sample.carmarket.entity.Status;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CarService {

    @Autowired
    private DataManager dataManager;

    public String calculateEnginesCars(Manufacturer manufacturer) {
        int countOfElectricCars = 0;
        int countOfGasCars = 0;
        List<Car> cars = dataManager.load(Car.class)
                .query("select c from Car c " +
                        "where c.model in (select m from Model m " +
                        "where m.manufacturer = :manufacturer1)")
                .parameter("manufacturer1", manufacturer)
                .list();

        for (Car car : cars) {
            if (car.getModel().getEngineType().equals(EngineType.GASOLINE)) {
                countOfGasCars++;
            } else {
                countOfElectricCars++;
            }
        }
        return "Electric cars: " + countOfElectricCars
                + ", gasoline cars: " + countOfGasCars;
    }

    public String markAsSold(Car car) {
        if (car.getStatus().equals(Status.SOLD)) {
            return "Already Sold";
        } else {
            car.setStatus(Status.SOLD);
            car.setDateOfSale(LocalDate.now());
            var value = dataManager.save(car);
            return "Done";
        }
    }
}