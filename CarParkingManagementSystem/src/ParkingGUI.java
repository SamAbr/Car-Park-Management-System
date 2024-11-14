package com.parkingmanagement;

import com.parkingmanagement.model.ParkingLot;
import com.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.model.Vehicle;
import com.parkingmanagement.service.ParkingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingGUI {

    private final ParkingService parkingService;
    private JFrame frame;
    private JTable vehicleTable;
    private DefaultTableModel tableModel;

    public ParkingGUI() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addSpace(new ParkingSpace(1));
        parkingLot.addSpace(new ParkingSpace(2));
        parkingLot.addSpace(new ParkingSpace(3)); // Add more spaces if needed
        this.parkingService = new ParkingService(parkingLot);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Car Parking Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Menu panel with buttons
        frame.add(createMenuPanel(), BorderLayout.NORTH);

        // Vehicle Table to display parked vehicles
        frame.add(createVehicleTablePanel(), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton parkButton = new JButton("Park Vehicle");
        JButton checkoutButton = new JButton("Checkout Vehicle");
        JButton listButton = new JButton("List Parked Vehicles");
        JButton availableButton = new JButton("Show Available Spaces");

        // Add action listeners to buttons
        addListeners(parkButton, checkoutButton, listButton, availableButton);

        panel.add(parkButton);
        panel.add(checkoutButton);
        panel.add(listButton);
        panel.add(availableButton);

        return panel;
    }

    private JScrollPane createVehicleTablePanel() {
        String[] columnNames = {"Plate Number", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        vehicleTable = new JTable(tableModel);
        
        return new JScrollPane(vehicleTable);
    }

    private void addListeners(JButton parkButton, JButton checkoutButton, JButton listButton, JButton availableButton) {
        parkButton.addActionListener(e -> parkVehicleDialog());
        checkoutButton.addActionListener(e -> checkoutVehicleDialog());
        listButton.addActionListener(e -> listParkedVehicles());
        availableButton.addActionListener(e -> showAvailableSpaces());
    }

    private void parkVehicleDialog() {
        String plateNumber = JOptionPane.showInputDialog(frame, "Enter vehicle plate number:");
        String type = JOptionPane.showInputDialog(frame, "Enter vehicle type (Car/Bike):");
        if (plateNumber != null && type != null) {
            Vehicle vehicle = new Vehicle(plateNumber, type);
            if (parkingService.parkVehicle(vehicle)) {
                JOptionPane.showMessageDialog(frame, "Vehicle parked successfully.");
                updateVehicleTable();
            } else {
                JOptionPane.showMessageDialog(frame, "No available parking spaces.");
            }
        }
    }

    private void checkoutVehicleDialog() {
        String plateNumber = JOptionPane.showInputDialog(frame, "Enter vehicle plate number to checkout:");
        if (plateNumber != null) {
            if (parkingService.checkoutVehicle(plateNumber)) {
                JOptionPane.showMessageDialog(frame, "Vehicle checked out successfully.");
                updateVehicleTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Vehicle not found.");
            }
        }
    }

    private void listParkedVehicles() {
        updateVehicleTable();
    }

    private void showAvailableSpaces() {
        StringBuilder availableSpaces = new StringBuilder("Available Spaces:\n");
        for (int spaceId : parkingService.getAvailableSpaces()) {
            availableSpaces.append("Space ID: ").append(spaceId).append("\n");
        }
        JOptionPane.showMessageDialog(frame, availableSpaces.toString());
    }

    private void updateVehicleTable() {
        tableModel.setRowCount(0); // Clear existing rows
        for (String vehicleInfo : parkingService.getParkedVehicles()) {
            String[] parts = vehicleInfo.split(" - "); // Assuming vehicleInfo is formatted like "PlateNumber - Type"
            tableModel.addRow(parts);
        }
    }
}
