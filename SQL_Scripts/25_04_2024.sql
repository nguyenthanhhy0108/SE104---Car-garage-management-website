
SET IDENTITY_INSERT OWNERS ON;

insert into OWNERS(OwnerID, OwnerName, OwnerAddress, OwnerPhoneNumber, OwnerEmail, Username)
values (1, 'Nguyen Thanh Hy', 'Chau Thanh Ben Tre', '0941609091', '22520593@gm.uit.edu.vn', '0941609091')

    SET IDENTITY_INSERT SE.dbo.OWNERS OFF;

SET IDENTITY_INSERT BRANDS ON;

insert into BRANDS(BrandID, BrandName) values (1, 'Mercedes-Benz')

    SET IDENTITY_INSERT BRANDS OFF;

SET IDENTITY_INSERT CARS ON;

insert into CARS(CarID, LicensePlate, OwnerID, BrandID)
values (1, '71-B3.27133', 1, 1)

    SET IDENTITY_INSERT CARS OFF;

SET IDENTITY_INSERT MAINTENANCE_RECORDS ON;

INSERT INTO MAINTENANCE_RECORDS(RecordID, CarID, DateReceived)
VALUES (1, 1, '2024-04-25');

SET IDENTITY_INSERT MAINTENANCE_RECORDS OFF;

