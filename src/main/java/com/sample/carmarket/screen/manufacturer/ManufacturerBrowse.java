package com.sample.carmarket.screen.manufacturer;


import com.sample.carmarket.app.CarService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Autowired
    private CarService carService;
    @Autowired
    private Notifications notifications;

    @Subscribe("tableCalculateCarsBtn")
    public void onTableCalculateCarsBtnClick(Button.ClickEvent event) {
        Manufacturer manufacturer = getTable().getSingleSelected();
        notifications.create()
                .withCaption(manufacturer != null
                        ? carService.calculateEnginesCars(manufacturer)
                        : "No selection")
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }
}