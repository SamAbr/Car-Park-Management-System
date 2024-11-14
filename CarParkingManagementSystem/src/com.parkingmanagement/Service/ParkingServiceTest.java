package com.parkingmanagement.service;

import com.parkingmanagement.model.ParkingLot;
import com.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.model.Vehicle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingServiceTest {

    @Test
    public void testParkVehicle() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addSpace(new ParkingSpace(1));
        ParkingService parkingService = new ParkingService(parkingLot);

        Vehicle vehicle = new Vehicle("ABC123", "Car");
        assertTrue(parkingService.parkVehicle(vehicle), "Vehicle should be parked successfully");
        assertFalse(parkingService.parkVehicle(new Vehicle("XYZ789", "Car")), "Parking should fail when no spaces are available");
    }

    @Test
    public void testCheckoutVehicle() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingSpace space = new ParkingSpace(1);
        parkingLot.addSpace(space);
        ParkingService parkingService = new ParkingService(parkingLot);

        Vehicle vehicle = new Vehicle("ABC123", "Car");
        parkingService.parkVehicle(vehicle);
        assertTrue(parkingService.checkoutVehicle("ABC123"), "Vehicle should be checked out successfully");
        assertFalse(parkingService.checkoutVehicle("XYZ789"), "Checkout should fail for a non-existent vehicle");
    }
}

@Test
public void testGetParkedVehicles() {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.addSpace(new ParkingSpace(1));
    parkingLot.addSpace(new ParkingSpace(2));
    ParkingService parkingService = new ParkingService(parkingLot);

    Vehicle vehicle1 = new Vehicle("ABC123", "Car");
    parkingService.parkVehicle(vehicle1);

    List<String> parkedVehicles = parkingService.getParkedVehicles();
    assertEquals(1, parkedVehicles.size(), "Should have 1 parked vehicle");
    assertTrue(parkedVehicles.get(0).contains("ABC123"), "List should contain vehicle ABC123");
}

@Test
public void testGetAvailableSpaces() {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.addSpace(new ParkingSpace(1));
    parkingLot.addSpace(new ParkingSpace(2));
    ParkingService parkingService = new ParkingService(parkingLot);

    Vehicle vehicle = new Vehicle("ABC123", "Car");
    parkingService.parkVehicle(vehicle);

    List<Integer> availableSpaces = parkingService.getAvailableSpaces();
    assertEquals(1, availableSpaces.size(), "Should have 1 available space");
    assertEquals(2, availableSpaces.get(0), "Available space should be ID 2");
}
