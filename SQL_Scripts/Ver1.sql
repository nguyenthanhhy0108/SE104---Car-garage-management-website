create database SE
GO

use SE
go




create table USERS(
                      Username varchar(50) primary key,
                      Password varchar(500) not null,
                      Enabled int not null
);

create table AUTHORITIES(
                            Username varchar(50) not null,
                            Authority varchar(50) not null,
                            Primary key (Username, Authority),
                            Foreign key (Username) references USERS(Username)
);

insert into USERS(Username, Password, Enabled) values ('0941609091', '$2a$10$baoxjTClM2adP1Mdc9hDiexesFoY5miTDSAloR82FDtOH4l98XoJC', 1);

insert into AUTHORITIES(Username, Authority) values ('0941609091', 'ROLE_ADMIN');
insert into AUTHORITIES(Username, Authority) values ('0941609091', 'ROLE_USER');

create table USERDETAILS(
                            Username varchar(50) not null,
                            Email varchar(100) not null,
                            Name varchar(100),
                            Nationality varchar(50),
                            primary key (Username),
                            foreign key (Username) references USERS(Username)
);


insert into USERDETAILS(Username, Email, Name, Nationality) values ('0941609091', '22520593@gm.uit.edu.vn', 'Nguyen Thanh Hy','Viet Nam');


-- Owners Table
CREATE TABLE OWNERS (
                        OwnerID INT IDENTITY PRIMARY KEY,
                        OwnerName VARCHAR(255),
                        OwnerAddress VARCHAR(255),
                        OwnerPhoneNumber VARCHAR(20),
                        OwnerEmail VARCHAR(255)
);



-- Brands Table
CREATE TABLE BRANDS (
                        BrandID INT IDENTITY PRIMARY KEY,
                        BrandName VARCHAR(255)
);


-- Cars Table
CREATE TABLE CARS (
                      CarID INT IDENTITY PRIMARY KEY,
                      LicensePlate VARCHAR(20) NOT NULL,
                      OwnerID INT,
                      BrandID INT,
                      FOREIGN KEY (OwnerID) REFERENCES OWNERS(OwnerID),
                      FOREIGN KEY (BrandID) REFERENCES BRANDS(BrandID)
);


-- Repair Orders Table
CREATE TABLE REPAIR_ORDERS (
                               OrderNumber INT IDENTITY PRIMARY KEY,
                               CarID INT,
                               Date DATE,
                               AmountOwed DECIMAL(10, 2),
                               PaymentDate DATE,
                               AmountPaid DECIMAL(10, 2),
                               FOREIGN KEY (CarID) REFERENCES CARS(CarID)
);


-- Maintenance Records Table
CREATE TABLE MAINTENANCE_RECORDS (
                                     RecordID INT IDENTITY PRIMARY KEY,
                                     CarID INT,
                                     DateReceived DATE,
                                     FOREIGN KEY (CarID) REFERENCES CARS(CarID)
);

-- Parts Table
CREATE TABLE PARTS (
                       PartID INT IDENTITY PRIMARY KEY,
                       PartName VARCHAR(255),
                       PartPrice DECIMAL(10, 2)
);


-- Services Table
CREATE TABLE SERVICES (
                          ServiceID INT IDENTITY PRIMARY KEY,
                          ServiceName VARCHAR(255),
                          ServiceCost DECIMAL(10, 2)
);

-- RepairOrderParts Junction Table
CREATE TABLE REPAIR_ORDERS_PARTS (
    OrderNumber INT,
    PartID INT,
    Quantity INT,
    FOREIGN KEY (OrderNumber) REFERENCES REPAIR_ORDERS(OrderNumber),
    FOREIGN KEY (PartID) REFERENCES PARTS(PartID),
    PRIMARY KEY (OrderNumber, PartID)
);

-- RepairOrderServices Junction Table
CREATE TABLE REPAIR_ORDER_SERVICES (
    OrderNumber INT,
    ServiceID INT,
    FOREIGN KEY (OrderNumber) REFERENCES REPAIR_ORDERS(OrderNumber),
    FOREIGN KEY (ServiceID) REFERENCES SERVICES(ServiceID),
    PRIMARY KEY (OrderNumber, ServiceID)
);


ALTER TABLE OWNERS
    ADD Username VARCHAR(50);

ALTER TABLE OWNERS
    ADD CONSTRAINT FK_Owners_Users
        FOREIGN KEY (Username) REFERENCES USERS(Username);
