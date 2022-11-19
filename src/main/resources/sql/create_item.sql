CREATE TABLE postal_item
(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    postal_office_in INT,
    postal_office_out INT,
    person_address VARCHAR(150) NOT NULL,
    person_name VARCHAR(150) NOT NULL,
    FOREIGN KEY (postal_office_in) REFERENCES post_office (Id) ON DELETE SET NULL,
    FOREIGN KEY (postal_office_out) REFERENCES post_office (Id) ON DELETE SET NULL
);
