--Client table
CREATE TABLE Client {
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL
};

--Planet table
CREATE TABLE Planet {
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(500) NOT NULL
};

--Ticket table
CREATE TABLE Ticket {
    id INT PRIMARY KEY AUTO_INCREMENT,
    created_at TIMESTAMP,
    client_id INT,
    from_planet_id VARCHAR(10),
    to_planet_id VARCHAR(10),
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (from_planet_id) REFERENCES Planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES Planet(id)
};