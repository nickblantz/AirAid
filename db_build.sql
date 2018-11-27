# ----------------------------------------
# SQL script to create the tables for the 
# Beverage Questionnaire Database (BevqDB)
# Created by Osman Balci on June 9, 2018
# ----------------------------------------

/*
Tables to be dropped must be listed in a logical order based on dependency.
UserQuestionnaire and UserPhoto depend on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS UserPhoto, User, UserTicket;

/* The User table contains attributes of interest of a User. */
CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,  /* To store Salted and Hashed Password Parts */
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
	phone_number VARCHAR(11) NOT NULL, 
    address1 VARCHAR(128) NOT NULL,
    address2 VARCHAR(128),
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(10) NOT NULL,    /* e.g., 24060-1804 */
    security_question_number INT NOT NULL,  /* Refers to the number of the selected security question */
    security_answer VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,    
    is_dark BOOLEAN NOT NULL,	
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

/* The UserTicket table contains the user's questionnaire data. */
CREATE TABLE UserTicket
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    flight_datetime DATETIME NOT NULL,
    expected_travel_time LONG NOT NULL,	   
    flight_id VARCHAR(128) NOT NULL,
    src_name VARCHAR(128) NOT NULL,
	src_longitude DOUBLE NOT NULL,
	src_latitude DOUBLE NOT NULL,
    dest_name VARCHAR(128) NOT NULL,
    price FLOAT(4,2) NOT NULL,
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);