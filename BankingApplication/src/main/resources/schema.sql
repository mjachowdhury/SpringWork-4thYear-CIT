CREATE TABLE customer ( 
	customerId int (11) NOT NULL AUTO_INCREMENT,
	title varchar (10) NOT NULL,
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
	accountId int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	customerId int (11) NOT NULL,
	accountNumber int (11) NOT NULL,
	amount double (30) NOT NULL,
	overDraft double (30) NOT NULL,
	FOREIGN KEY (customerId) REFERENCES customer(customerId)
);

CREATE UNIQUE INDEX accountUniqueIndex ON account(accountNumber );
