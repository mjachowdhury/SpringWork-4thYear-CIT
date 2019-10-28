INSERT INTO customer(title, firstName, lastName, address, city, contactNumber, email, password) VALUES
('Mr', 'Mohammed', 'Alom', 'WesternRd', 'Cork', '0123456789', 'mohammed@mycit.ie', '123456'),
('Mrs', 'Opi', 'Alom', 'WesternRd', 'Cork', '0123456789', 'mohammed@mycit.ie', 'abcdef'),
('Miss', 'Maimoona', 'Alom', 'WesternRd', 'Cork', '0123456789', 'mohammed@mycit.ie', 'ghijkl'),
('Miss', 'Maimoona', 'Alom', 'WesternRd', 'Cork', '0123456789', 'mohammed@mycit.ie', 'mnopqr');



INSERT INTO employee(firstName, lastName, password) VALUES
('Mohammed', 'Alom', '123456'),
('Opi', 'Alom', '654321'),
('Yahia', 'Alom', '987654');



INSERT INTO account(customerId, accountNumber, amount, overDraft) VALUES
(1, 555555, '1000', '1000'),
(2, 666666, '20000', '1000'),
(3, 777777, '3000', '0'),
(4, 88888, '15000', '0');
