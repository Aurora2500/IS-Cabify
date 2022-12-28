package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DriverRepository {

    private List<Driver> driverList;

    public DriverRepository() {
        this.driverList = new ArrayList<>();
    }

    public Stream<Driver> stream() {
        return driverList.stream();
    }

    public DriverRepository addAll(List<Driver> drivers) {
        this.driverList.addAll(drivers);
        return this;
    }

    public DriverRepository add(Driver driver) {
        this.driverList.add(driver);
        return this;
    }


}
