# ----------------------------------------
# SQL script to create the tables for the 
# Beverage Questionnaire Database (BevqDB)
# Created by Osman Balci on June 9, 2018
# ----------------------------------------

/*
Tables to be dropped must be listed in a logical order based on dependency.
UserQuestionnaire and UserPhoto depend on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS UserPhoto, UserTicket, User;

/* The User table contains attributes of interest of a User. */
CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,  /* To store Salted and Hashed Password Parts */
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
    phone_number VARCHAR(32) NOT NULL, 
    address1 VARCHAR(128) NOT NULL,
    address2 VARCHAR(128),
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(10) NOT NULL,    /* e.g., 24060-1804 */
    security_question_number INT NOT NULL,  /* Refers to the number of the selected security question */
    security_answer VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    is_verified BOOLEAN NOT NULL,
    is_dark BOOLEAN NOT NULL,	
    mobile_carrier VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

/* The UserPhoto table contains attributes of interest of a user's photo. */
CREATE TABLE UserPhoto
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* The UserTicket table contains the ticket/flight info */
/* I don't think we need a flight id since they are made up */
/* A user ID of null means it was created here or by a admin and anyone should be able to view it */
CREATE TABLE UserTicket
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    src_name VARCHAR(128) NOT NULL,
    dest_name VARCHAR(128) NOT NULL,
    src_longitude DOUBLE NOT NULL,
    src_latitude DOUBLE NOT NULL,
    src_street VARCHAR(128) NOT NULL,
    price FLOAT(5,2) NOT NULL,
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* info from: https://en.wikipedia.org/wiki/List_of_the_busiest_airports_in_the_United_States and https://www.airnav.com/airports/ */
/*
                                        AIRPORT INFO
        NAME                                                    LONGITUDE                  LATITUDE              STREET
        Virginia Tech Montgomery Executive Airport              -80.4078333                37.2076389            1601 Tech Center Dr, Blacksburg, VA 24060
        Roanoke-Blacksburg Regional Airport                     -79.9754167                37.3254722            5202 Aviation Dr NW, Roanoke, VA 24012
        Hartsfield–Jackson Atlanta International Airport        -84.4278640                33.6366996            6000 N Terminal Pkwy, Atlanta, GA 30320


*/

/* Hardcoded tickets to start the database off */
INSERT INTO UserTicket (departure_time, arrival_time, src_name, dest_name, src_longitude, src_latitude, src_street, price, user_id) VALUES 
('2018-12-01 14:45:00', '2018-12-01 16:55:00', 'Virginia Tech Montgomery Executive Airport1', 'Hartsfield–Jackson Atlanta International Airport', '-80.4078333', '37.2076389', '1601 Tech Center Dr, Blacksburg, VA 24060', '325.00', null);


