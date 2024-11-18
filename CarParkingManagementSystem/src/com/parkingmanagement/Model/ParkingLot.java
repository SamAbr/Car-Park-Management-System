package com.parkingmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSpace> spaces;

    public ParkingLot() {
        spaces = new ArrayList<>();
    }

    public void addSpace(ParkingSpace space) {
        spaces.add(space);
    }

    public ParkingSpace findAvailableSpace() {
        for (ParkingSpace space : spaces) {
            if (space.isAvailable()) {
                return space;
            }
        }
        return null;
    }

    public boolean removeVehicle(String plateNumber) {
        for (ParkingSpace space : spaces) {
            if (space.isOccupied() && space.vehicle.getPlateNumber().equals(plateNumber)) {
                space.removeVehicle();
                return true;
            }
        }
        return false;
    }
}