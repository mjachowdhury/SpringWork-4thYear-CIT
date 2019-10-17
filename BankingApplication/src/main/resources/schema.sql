CREATE TABLE customer ( 
	customerId int (11) NOT NULL AUTO_INCREMENT,
	firstName varchar (30) NOT NULL,
	lastName varchar (30) NOT NULL,
	PRIMARY KEY (customerId)
);

CREATE UNIQUE INDEX customerUniqueIndex ON customer(firstName );