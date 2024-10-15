-- database m2_final_project

--rollback
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS rental_agreement;
DROP TABLE IF EXISTS property;
DROP TABLE IF EXISTS renter;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE renter (
    renter_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    profile_details TEXT
);

CREATE TABLE property (
    property_id SERIAL PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    rent_price DECIMAL(10, 2) NOT NULL,
    bedrooms INT NOT NULL,
    bathrooms INT NOT NULL,
    square_feet INT NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    basement BOOLEAN DEFAULT FALSE,
    description TEXT,
    imageUrl VARCHAR (100)
);

CREATE TABLE rental_agreement (
    rental_agreement_id SERIAL PRIMARY KEY,
	renter_id INT NOT NULL,
    property_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    monthly_rent NUMERIC(10, 2) NOT NULL,
    deposit_amount NUMERIC(10, 2) NOT NULL,
    agreement TEXT,
	CONSTRAINT FK_rental_agreement_renter FOREIGN KEY (renter_id) REFERENCES renter(renter_id),
    CONSTRAINT FK_rental_agreement_property FOREIGN KEY (property_id) REFERENCES property(property_id)
);



-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');
	
INSERT INTO renter (first_name, last_name, email, phone, profile_details) VALUES
('John', 'Doe', 'john.doe@example.com', '123-456-7890', 'Likes to keep the house clean'),
('Jane', 'Smith', 'jane.smith@example.com', '234-567-8901', 'Has a small dog'),
('Alice', 'Johnson', 'alice.johnson@example.com', '345-678-9012', 'Works from home'),
('Bob', 'Brown', 'bob.brown@example.com', '456-789-0123', 'Enjoys gardening'),
('Carol', 'Davis', 'carol.davis@example.com', '567-890-1234', 'Loves to cook'),
('David', 'Miller', 'david.miller@example.com', '678-901-2345', 'Avid reader'),
('Emily', 'Wilson', 'emily.wilson@example.com', '789-012-3456', 'Has two cats'),
('Frank', 'Moore', 'frank.moore@example.com', '890-123-4567', 'Plays the guitar');

-- property
INSERT INTO property (address, city, state, zip_code, rent_price, bedrooms, bathrooms, square_feet, available, basement, description, imageUrl) VALUES
('123 Main St', 'Anytown', 'CA', '12345', 1500.00, 3, 2, 1500, TRUE, FALSE, 'Cozy family home with backyard', '/propertyImages/1602.png' ),
('456 Oak Ave', 'Sometown', 'NY', '67890', 1800.00, 4, 3, 2000, TRUE, TRUE, 'Spacious house with basement', '/propertyImages/2.png' ),
('789 Maple Blvd', 'Anothercity', 'TX', '23456', 1200.00, 2, 1, 1000, TRUE, FALSE, 'Modern apartment near downtown', '/propertyImages/3.png'),
('321 Pine Dr', 'Yourtown', 'FL', '78901', 2200.00, 3, 2, 1800, TRUE, TRUE, 'Renovated townhouse with garage', '/propertyImages/4.png'),
('654 Elm Ln', 'Somewhere', 'WA', '34567', 1700.00, 3, 2, 1600, TRUE, FALSE, 'Corner lot with garden space', '/propertyImages/5.png'),
('987 Cedar Rd', 'Nowhere', 'OR', '45678', 1900.00, 4, 3, 2200, TRUE, TRUE, 'Ranch style home with mountain view', '/propertyImages/6.png'),
('210 Oakwood Ave', 'Anywhere', 'IL', '56789', 1300.00, 2, 1, 1100, TRUE, FALSE, 'Apartment in a quiet neighborhood', '/propertyImages/7.png'),
('543 Birch St', 'Everytown', 'MA', '89012', 1600.00, 3, 2, 1700, TRUE, TRUE, 'Duplex with fenced backyard', '/propertyImages/8.png'),
('876 Spruce Ave', 'Noway', 'PA', '90123', 2000.00, 4, 3, 2000, TRUE, FALSE, 'New construction with modern amenities', '/propertyImages/9.png'),
('109 Willow Dr', 'Smalltown', 'GA', '34567', 1400.00, 2, 1, 1200, TRUE, FALSE, 'Single family home with front porch', '/propertyImages/10.png');

-- rental_agreement
INSERT INTO rental_agreement (renter_id, property_id, start_date, end_date, monthly_rent, deposit_amount, agreement) VALUES
(1, 1, '2024-08-01', '2025-07-31', 1500.00, 2000.00, 'Tenant agrees to pay rent on the 1st of each month.'),
(2, 2, '2024-09-01', '2025-08-31', 1800.00, 2500.00, 'Tenant agrees to maintain the property in good condition.'),
(3, 3, '2024-10-01', '2025-09-30', 2000.00, 3000.00, 'Tenant agrees to notify landlord of any maintenance issues promptly.'),
(4, 4, '2024-08-01', '2025-07-31', 1500.00, 2000.00, 'Tenant agrees to pay rent on the 1st of each month.'),
(5, 5, '2024-09-01', '2025-08-31', 1800.00, 2500.00, 'Tenant agrees to maintain the property in good condition.'),
(6, 6, '2024-10-01', '2025-09-30', 2000.00, 3000.00, 'Tenant agrees to notify landlord of any maintenance issues promptly.'),
(7, 7, '2024-08-01', '2025-07-31', 1500.00, 2000.00, 'Tenant agrees to pay rent on the 1st of each month.'),
(8, 8, '2024-09-01', '2025-08-31', 1800.00, 2500.00, 'Tenant agrees to maintain the property in good condition.'),
(1, 9, '2024-09-01', '2025-08-31', 1800.00, 2500.00, 'Tenant agrees to maintain the property in good condition.');

COMMIT TRANSACTION;
