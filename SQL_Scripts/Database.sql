create database SE104
GO

use SE104
go

-- Owners Table
CREATE TABLE Owners (
    OwnerID INT IDENTITY PRIMARY KEY,
    OwnerName VARCHAR(255),
    OwnerAddress VARCHAR(255),
    OwnerPhoneNumber VARCHAR(20),
    OwnerEmail VARCHAR(255)
);

-- Brands Table
CREATE TABLE Brands (
    BrandID INT IDENTITY PRIMARY KEY,
    BrandName VARCHAR(255)
);

-- Cars Table
CREATE TABLE Cars (
    CarID INT IDENTITY PRIMARY KEY,
    LicensePlate VARCHAR(20) NOT NULL,
    OwnerID INT,
    BrandID INT,
    FOREIGN KEY (OwnerID) REFERENCES Owners(OwnerID),
    FOREIGN KEY (BrandID) REFERENCES Brands(BrandID)
);

-- Repair Orders Table
CREATE TABLE RepairOrders (
    OrderNumber INT IDENTITY PRIMARY KEY,
    CarID INT,
    Date DATE,
    AmountOwed DECIMAL(10, 2),
    PaymentDate DATE,
    AmountPaid DECIMAL(10, 2),
    FOREIGN KEY (CarID) REFERENCES Cars(CarID)
);

-- Maintenance Records Table
CREATE TABLE MaintenanceRecords (
    RecordID INT IDENTITY PRIMARY KEY,
    CarID INT,
    DateReceived DATE,
    FOREIGN KEY (CarID) REFERENCES Cars(CarID)
);

-- Parts Table
CREATE TABLE Parts (
    PartID INT IDENTITY PRIMARY KEY,
    PartName VARCHAR(255),
    PartPrice DECIMAL(10, 2)
);

-- Services Table
CREATE TABLE Services (
    ServiceID INT IDENTITY PRIMARY KEY,
    ServiceName VARCHAR(255),
    ServiceCost DECIMAL(10, 2)
);

-- RepairOrderParts Junction Table
CREATE TABLE RepairOrderParts (
    OrderNumber INT,
    PartID INT,
    Quantity INT,
    FOREIGN KEY (OrderNumber) REFERENCES RepairOrders(OrderNumber),
    FOREIGN KEY (PartID) REFERENCES Parts(PartID),
    PRIMARY KEY (OrderNumber, PartID)
);

-- RepairOrderServices Junction Table
CREATE TABLE RepairOrderServices (
    OrderNumber INT,
    ServiceID INT,
    FOREIGN KEY (OrderNumber) REFERENCES RepairOrders(OrderNumber),
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID),
    PRIMARY KEY (OrderNumber, ServiceID)
);

