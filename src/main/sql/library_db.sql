CREATE TABLE Persons(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    full_name varchar(100) UNIQUE NOT NULL,
    yearOfTheBirth int CHECK ( yearOfTheBirth > 1899 ),
    email varchar(100) UNIQUE NOT NULL
);

CREATE TABLE Books(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    person_id int REFERENCES Persons(id) ON DELETE SET NULL,
    title varchar(100) NOT NULL UNIQUE,
    author varchar(100) NOT NULL,
    year int NOT NULL
);