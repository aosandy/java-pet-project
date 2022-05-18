CREATE TABLE routes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE auto_personnel (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    pather_name VARCHAR(20) NOT NULL
);

CREATE TABLE auto (
    id SERIAL PRIMARY KEY,
    num VARCHAR(20) NOT NULL UNIQUE,
    color VARCHAR(20) NOT NULL,
    mark VARCHAR(20) NOT NULL,
    personnel_id INT NOT NULL,
    CONSTRAINT fk_auto_auto_personnel
        FOREIGN KEY (personnel_id) REFERENCES auto_personnel(id)
);

CREATE TABLE journal (
     id SERIAL PRIMARY KEY,
     time_out TIMESTAMP(3) NOT NULL,
     time_in TIMESTAMP(3) NOT NULL,
     auto_id INT NOT NULL,
     CONSTRAINT fk_journal_auto
         FOREIGN KEY (auto_id) REFERENCES auto(id),
     route_id INT NOT NULL,
     CONSTRAINT fk_journal_routes
         FOREIGN KEY (route_id) REFERENCES routes(id)
);
