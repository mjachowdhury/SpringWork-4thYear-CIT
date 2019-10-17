CREATE TABLE customer ( 
	customerId int (11) NOT NULL AUTO_INCREMENT,
	firstName varchar (30) NOT NULL,
	lastName varchar (30) NOT NULL,
	address varchar (30) NOT NULL,
	city varchar (30) NOT NULL,
	contactNumber varchar (30) NOT NULL,
	email varchar (30) NOT NULL,
	PRIMARY KEY (customerId)
);


CREATE TABLE employee( 
	employeeId int (11) NOT NULL AUTO_INCREMENT,
	firstName varchar (30) NOT NULL,
	lastName varchar (30) NOT NULL,
	password varchar (30) NOT NULL,
	PRIMARY KEY (employeeId)
);


CREATE TABLE account( 
	customerId int (11) NOT NULL AUTO_INCREMENT,
	accountNumber int (30) NOT NULL,
	amount double (30) NOT NULL,
	PRIMARY KEY (accountId)
);

CREATE UNIQUE INDEX accountUniqueIndex ON account(accountNumber );
ALTER TABLE "account" ADD FOREIGN KEY (customerID)REFERENCE customer(customerID);