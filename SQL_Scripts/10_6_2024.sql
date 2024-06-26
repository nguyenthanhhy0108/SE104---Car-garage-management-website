create table TRIGGER_VALUE(
  ID INT IDENTITY PRIMARY KEY,
    Name VARCHAR(MAX) not null,
	Value INT
)

drop trigger CHECK_FORM_1

    insert into Trigger_value(name, value) values ('c', 31)






CREATE TRIGGER CHECK_FORM_1
    ON MAINTENANCE_RECORDS
    AFTER INSERT
AS
BEGIN

	DECLARE @C INT;
	DECLARE @LIMIT INT;
SELECT @C = COUNT(*) FROM MAINTENANCE_RECORDS WHERE DateReceived = (SELECT DateReceived FROM inserted);
SELECT @LIMIT = (SELECT Value FROM TRIGGER_VALUE WHERE Name = 'c')
           IF(@C = @LIMIT)
BEGIN
ROLLBACK TRANSACTION
END
END

