DROP TABLE IF EXISTS url;

CREATE TABLE url (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(250) NOT NULL,
    resized_url VARCHAR(250) DEFAULT NULL,

);