package com.parkingmanagement.model;

public class ParkingSpace {
    private int id;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpace(int id) {
        this.id = id;
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }
}