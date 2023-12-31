CREATE TABLE IF NOT EXISTS users(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    email VARCHAR NOT NULL UNIQUE,
    role INT NOT NULL ,
    status INT NOT NULL
);

CREATE TABLE IF NOT EXISTS book(
                      id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      person_id int REFERENCES users (id) on update cascade ,
                      author VARCHAR NOT NULL,
                      name VARCHAR NOT NULL,
                      year_of_production int,
                      date_of_appointment timestamp
);

ALTER TABLE book
    DROP CONSTRAINT book_person_id_fkey,
    ADD CONSTRAINT book_person_id_fkey FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE SET NULL ;

CREATE TABLE IF NOT EXISTS person(
                                   id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                   name VARCHAR NOT NULL,
                                   surname VARCHAR NOT NULL,
                                   email VARCHAR NOT NULL,
                                   birthday date,
                                   registration_time timestamp
);


INSERT INTO users (username, password, email, role, status) VALUES ('user','$2a$10$E0ysFiwgaUw/nw2Ax3YXveplNdV3sOOuRHGriORbcMrHr9.TnKJA2','user@mail.com',0,0);
INSERT INTO users (username, password, email, role, status) VALUES ('admin','$2a$10$vOEXwnijDt7Nr6qYe7kl1e3a35ZAiYSUdGHdVGEJApKBwbBAlL4UW','admin@mail.com',1,0);
