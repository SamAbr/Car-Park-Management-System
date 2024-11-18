package com.parkingmanagement.service;

import com.parkingmanagement.model.ParkingLot;
import com.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.model.Vehicle;

public class ParkingService {
    private ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        ParkingSpace availableSpace = parkingLot.findAvailableSpace();
        if (availableSpace != null) {
            availableSpace.parkVehicle(vehicle);
            return true;
        }
        return false;  // No available space
    }

    public boolean checkoutVehicle(String plateNumber) {
        return parkingLot.removeVehicle(plateNumber);
    }

    public int viewAvailableSpaces() {
        int count = 0;
        for (ParkingSpace space : parkingLot.spaces) {
            if (space.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public List<String> getParkedVehicles() {
        List<String> parkedVehicles = new ArrayList<>();
        for (ParkingSpace space : parkingLot.spaces) {
            if (space.isOccupied()) {
                parkedVehicles.add("Vehicle Plate: " + space.vehicle.getPlateNumber() + ", Space ID: " + space.getId());
            }
        }
        return parkedVehicles;
    }

    public List<Integer> getAvailableSpaces() {
        List<Integer> availableSpaces = new ArrayList<>();
        for (ParkingSpace space : parkingLot.spaces) {
            if (space.isAvailable()) {
                availableSpaces.add(space.getId());
            }
        }
        return availableSpaces;
    }
    
}
