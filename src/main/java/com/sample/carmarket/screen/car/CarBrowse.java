package com.sample.carmarket.screen.car;

import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("table")
public class CarBrowse extends MasterDetailScreen<Car> {
}