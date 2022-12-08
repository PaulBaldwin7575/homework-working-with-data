package com.sample.carmarket.screen.car;

import com.sample.carmarket.app.CarService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("table")
public class CarBrowse extends MasterDetailScreen<Car> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private CarService carService;

    @Subscribe("soldBtn")
    public void onSoldBtnClick(Button.ClickEvent event) {
        Car car = getTable().getSingleSelected();
        notifications.create()
                .withCaption(car != null
                        ? carService.markAsSold(car)
                        : "Not selected")
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }

}