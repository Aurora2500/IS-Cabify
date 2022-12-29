package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DriverRepository {

    private final List<Driver> driverList;

    public DriverRepository() {
        this.driverList = new ArrayList<>();
    }

    public void add(Driver driver) {
        this.driverList.add(driver);
    }


    public Driver getDriver(String driverId) {
        return driverList.stream()
                .filter(driver -> driver.id().equals(driverId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

	public Driver getAvailableDriver() {
	      Random random = new Random();
        return driverList.get(random.nextInt(driverList.size()));
    }
}
