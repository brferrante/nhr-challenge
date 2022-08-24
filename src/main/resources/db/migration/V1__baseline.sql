CREATE TABLE property (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  rent_price FLOAT NOT NULL,
  create_time TIMESTAMP,
  property_type VARCHAR(255),
  address VARCHAR(255),
  email VARCHAR(255),
  code VARCHAR(10) NOT NULL
);
