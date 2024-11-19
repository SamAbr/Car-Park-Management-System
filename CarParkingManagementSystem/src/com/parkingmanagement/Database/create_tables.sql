CREATE TABLE Parking_lot (
    parking_lotID INT PRIMARY KEY AUTO_INCREMENT,
    number_of_blocks INT,
    is_slot_available BOOLEAN,
    address VARCHAR(255),
    zip VARCHAR(10),
    is_reentry_allowed BOOLEAN,
    operating_company_name VARCHAR(100),
    is_valet_parking_available BOOLEAN
);

CREATE TABLE Floor (
    floorID INT PRIMARY KEY AUTO_INCREMENT,
    floor_number INT,
    max_height_in_inch DECIMAL(5, 2),
    number_of_wings INT,
    number_of_slots INT,
    is_covered BOOLEAN,
    is_accessible BOOLEAN,
    is_floor_full BOOLEAN,
    is_reserved_reg_cust BOOLEAN
);

CREATE TABLE Customer (
    customerID INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_number VARCHAR(20) NOT NULL,
    registration_date DATE,
    is_regular_customer BOOLEAN,
    contact_number VARCHAR(15)
);

CREATE TABLE Regular_pass (
    regular_passID INT PRIMARY KEY AUTO_INCREMENT,
    customerID INT,
    purchase_date DATE,
    start_date DATE,
    duration_in_days INT,
    cost DECIMAL(10, 2),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Parking_slot_reservation (
    parking_slot_reservationID INT PRIMARY KEY AUTO_INCREMENT,
    customerID INT,
    start_timestamp TIMESTAMP,
    duration_in_minutes INT,
    booking_date DATE,
    parking_slotID INT,
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (parking_slotID) REFERENCES Parking_slot(parking_slotID)
);

CREATE TABLE Parking_slip (
    parking_slipID INT PRIMARY KEY AUTO_INCREMENT,
    parking_slot_reservationID INT,
    actual_entry_time TIMESTAMP,
    actual_exit_time TIMESTAMP,
    basic_cost DECIMAL(10, 2),
    penalty DECIMAL(10, 2),
    total_cost DECIMAL(10, 2),
    is_paid BOOLEAN,
    FOREIGN KEY (parking_slot_reservationID) REFERENCES Parking_slot_reservation(parking_slot_reservationID)
);

CREATE TABLE Parking_slot (
    parking_slotID INT PRIMARY KEY AUTO_INCREMENT,
    slot_number INT,
    slot_type VARCHAR(20),
    floorID INT,
    is_occupied BOOLEAN,
    is_reserved BOOLEAN,
    FOREIGN KEY (floorID) REFERENCES Floor(floorID)
);
