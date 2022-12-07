package com.sample.carmarket.screen.manufacturer;


import com.sample.carmarket.app.CalculateCarsService;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.Collection;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Autowired
    private CalculateCarsService calculateCarsService;
    @Autowired
    private Notifications notifications;

    @Subscribe("tableCalculateCarsBtn")
    public void onTableCalculateCarsBtnClick(Button.ClickEvent event) {
        Manufacturer manufacturer = getTable().getSingleSelected();
        notifications.create()
                .withCaption(manufacturer != null
                        ? manufacturer.getName() + ": " + calculateCarsService.calculateEnginesCars(manufacturer)
                        : "No selection")
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }


    @Subscribe("table")
    public void onTableSelection(Table.SelectionEvent<Manufacturer> event) {

    }

//    @Named("table.calculateCars")
//    private
//    @Autowired
//    private DataManager dataManager;


}