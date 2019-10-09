CREATE TABLE power ( 
	powerId int (11) NOT NULL AUTO_INCREMENT,
	powerName varchar (30) NOT NULL,
	powerDescription varchar (30) NOT NULL,
	PRIMARY KEY (powerId)
);

CREATE UNIQUE INDEX powerUniqueIndex ON power (powerName);