CREATE TABLE movies (
    id BIGINT auto_increment,
    `year` INT,
    `title` VARCHAR(255),
    `studios` VARCHAR(255),
    `producers` VARCHAR(255),
    `winner` VARCHAR(255),
    PRIMARY KEY ( id )
);