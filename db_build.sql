/* sql file to create the databases or a user, userphoto, airports, and userticket. Created by Phillip Blake Adams */
/*
Tables to be dropped must be listed in a logical order based on dependency.
UserQuestionnaire and UserPhoto depend on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS UserPhoto, UserTicket, User, Airport;

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


/* info from: https://en.wikipedia.org/wiki/List_of_the_busiest_airports_in_the_United_States and https://www.airnav.com/airports/ */
/*
                                        AIRPORT INFO
        NAME                                                    LONGITUDE                  LATITUDE              STREET
        Virginia Tech Montgomery Executive Airport              -80.4078333                37.2076389            1601 Tech Center Dr, Blacksburg, VA 24060
        Roanoke-Blacksburg Regional Airport                     -79.9754167                37.3254722            5202 Aviation Dr NW, Roanoke, VA 24012
        Hartsfield-Jackson Atlanta International Airport        -84.4278640                33.6366996            6000 N Terminal Pkwy, Atlanta, GA 30320
        Los Angeles International Airport                       -118.408050                33.9424944            1 World Way, Los Angeles, CA 90045
        O'Hare International Airport                            -87.9065972                41.9745219            10000 W O'Hare Ave, Chicago, IL 60666
        
        Dallas/Fort Worth International Airport                 -97.0376949                32.8972316            2400 Aviation Dr, DFW Airport, TX 75261
        Denver International Airport                            -104.6731667               39.8616667            8500 PeÃƒÂ±a Blvd, Denver, CO 80249
        John F. Kennedy International Airport                   -73.7786950                40.6399257            Queens, NY 11430
        San Francisco International Airport                     -122.3754167               37.6188056            San Francisco, CA 94128
        McCarran International Airport                          -115.1522500               36.0800556            5757 Wayne Newton Blvd, Las Vegas, NV 89119
        
        Seattle-Tacoma International Airport                    -122.3117778               47.4498889            17801 International Blvd, Seattle, WA 98158
        Charlotte Douglas International Airport                 -80.9490556                35.2137500            5501 Josh Birmingham Pkwy, Charlotte, NC 28208
        Newark Liberty International Airport                    -74.1686868                40.6924798            3 Brewster Rd, Newark, NJ 07114
        Orlando International Airport                           -81.3090000                28.4293889            1 Jeff Fuqua Blvd, Orlando, FL 32827
        Phoenix Sky Harbor International Airport                -112.0115833               33.4342778            3400 E Sky Harbor Blvd, Phoenix, AZ 85034
        
        Miami International Airport                             -80.2901111                25.7953611            2100 NW 42nd Ave, Miami, FL 33126
        George Bush Intercontinental Airport                    -95.3414425                29.9844353            2800 N Terminal Rd, Houston, TX 77032
        Logan International Airport                             -71.0063889                42.3629444            1 Harborside Dr, Boston, MA 02128
        Minneapolis-Saint Paul International Airport            -93.2217778                44.8819722            4300 Glumack Drive, St. Paul, MN 55111
        Detroit Metropolitan Airport                            -83.3533889                42.2124444            Detroit, MI 48242
        
        Fort Lauderdale-Hollywood International Airport         -80.1496944                26.0716667            100 Terminal Dr, Fort Lauderdale, FL 33315
        Philadelphia International Airport                      -75.2406611                39.8720833            8000 Essington Ave, Philadelphia, PA 19153
        LaGuardia Airport                                       -73.8726111                40.7772500            Queens, NY 11371
        Baltimore-Washington International Airport              -76.6689911                39.1757283            Baltimore, MD 21240
        Salt Lake City International Airport                    -111.9777731               40.7883878            776 N Terminal Dr, Salt Lake City, UT 84122
        
        Ronald Reagan Washington National Airport               -77.0377222                38.8514444            Arlington, VA 22202
        Washington Dulles International Airport                 -77.4599444                38.9474444            1 Saarinen Cir, Dulles, VA 20166
        San Diego International Airport                         -117.1896667               32.7335556            3225 N Harbor Dr, San Diego, CA 92101
        Midway International Airport                            -87.7524167                41.7859722            5700 S Cicero Ave, Chicago, IL 60638
        Tampa International Airport                             -82.5332500                27.9754722            4100 George J Bean Pkwy, Tampa, FL 33607
        
        Daniel K. Inouye International Airport                  -157.9202627               21.3178275            300 Rodgers Blvd, Honolulu, HI 96819
        Portland International Airport                          -122.5968694               45.5887089            7000 NE Airport Way, Portland, OR 97218
*/

/* Table containing airport information */
CREATE TABLE Airport
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(128) NOT NULL,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    address VARCHAR(128) NOT NULL
);

INSERT INTO Airport (name, longitude, latitude, address) VALUES 
('Virginia Tech Montgomery Executive Airport', '-80.4078333', '37.2076389', '1601 Tech Center Dr, Blacksburg, VA 24060'),
('Roanoke-Blacksburg Regional Airport', '-79.9754167', '37.3254722', '5202 Aviation Dr NW, Roanoke, VA 24012'),
('Hartsfield-Jackson Atlanta International Airport', '-84.4278640', '33.6366996', '6000 N Terminal Pkwy, Atlanta, GA 30320'),
('Los Angeles International Airport', '-118.408050', '33.9424944', '1 World Way, Los Angeles, CA 90045'),
('O\'Hare International Airport', '-87.9065972', '41.9745219', '10000 W O\'Hare Ave, Chicago, IL 60666'),
('Dallas/Fort Worth International Airport', '-97.0376949', '32.8972316', '2400 Aviation Dr, DFW Airport, TX 75261'),
('Denver International Airport', '-104.6731667', '39.8616667', '8500 PeÃƒÂ±a Blvd, Denver, CO 80249'),
('John F. Kennedy International Airport', '-73.7786950', '40.6399257', 'Queens, NY 11430'),
('San Francisco International Airport', '-122.3754167', '37.6188056', 'San Francisco, CA 94128'),
('McCarran International Airport', '-115.1522500', '36.0800556', '5757 Wayne Newton Blvd, Las Vegas, NV 89119'),
('Seattle-Tacoma International Airport', '-122.3117778', '47.4498889', '17801 International Blvd, Seattle, WA 98158'),
('Charlotte Douglas International Airport', '-80.9490556', '35.2137500', '5501 Josh Birmingham Pkwy, Charlotte, NC 28208'),
('Newark Liberty International Airport', '-74.1686868', '40.6924798', '3 Brewster Rd, Newark, NJ 07114'),
('Orlando International Airport', '-81.3090000', '28.4293889', '1 Jeff Fuqua Blvd, Orlando, FL 32827'),
('Phoenix Sky Harbor International Airport', '-112.0115833', '33.4342778', '3400 E Sky Harbor Blvd, Phoenix, AZ 85034'),
('Miami International Airport', '-80.2901111', '25.7953611', '2100 NW 42nd Ave, Miami, FL 33126'),
('George Bush Intercontinental Airport', '-95.3414425', '29.9844353', '2800 N Terminal Rd, Houston, TX 77032'),
('Logan International Airport', '-71.0063889', '42.3629444', '1 Harborside Dr, Boston, MA 02128'),
('Minneapolis-Saint Paul International Airport', '-93.2217778', '44.8819722', '4300 Glumack Drive, St. Paul, MN 55111'),
('Detroit Metropolitan Airport', '-83.3533889', '42.2124444', 'Detroit, MI 48242'),
('Fort Lauderdale-Hollywood International Airport', '-80.1496944', '26.0716667', '100 Terminal Dr, Fort Lauderdale, FL 33315'),
('Philadelphia International Airport', '-75.2406611', '39.8720833', '8000 Essington Ave, Philadelphia, PA 19153'),
('LaGuardia Airport', '-73.8726111', '40.7772500', 'Queens, NY 11371'),
('Baltimore Washington International Airport', '-76.6689911', '39.1757283', 'Baltimore, MD 21240'),
('Salt Lake City International Airport', '-111.9777731', '40.7883878', '776 N Terminal Dr, Salt Lake City, UT 84122'),
('Ronald Reagan Washington National Airport', '-77.0377222', '38.8514444', 'Arlington, VA 22202'),
('Washington Dulles International Airport', '-77.4599444', '38.9474444', '1 Saarinen Cir, Dulles, VA 20166'),
('San Diego International Airport', '-117.1896667', '32.7335556', '3225 N Harbor Dr, San Diego, CA 92101'),
('Midway International Airport', '-87.7524167', '41.7859722', '5700 S Cicero Ave, Chicago, IL 60638'),
('Tampa International Airport', '-82.5332500', '27.9754722', '4100 George J Bean Pkwy, Tampa, FL 33607'),
('Daniel K. Inouye International Airport', '-157.9202627', '21.3178275', '300 Rodgers Blvd, Honolulu, HI 96819'),
('Portland International Airport', '-122.5968694', '45.5887089', '7000 NE Airport Way, Portland, OR 97218');


/* The UserTicket table contains the ticket/flight info */
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
    airline VARCHAR(128) NOT NULL,
    price FLOAT(5,2) NOT NULL,
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* Hardcoded tickets to start the database off */
INSERT INTO UserTicket (departure_time, arrival_time, src_name, dest_name, src_longitude, src_latitude, src_street, airline, price, user_id) VALUES 
('2018-12-01 14:45:00', '2018-12-01 16:55:00', 'Virginia Tech Montgomery Executive Airport', 'San Diego International Airport', '-80.4078333', '37.2076389', '1601 Tech Center Dr, Blacksburg, VA 24060', 'AirAid', '320.00', null),
('2018-12-02 03:25:00', '2018-12-02 06:15:00', 'Tampa International Airport', 'Detroit Metropolitan Airport', '-82.5332500', '27.9754722', '4100 George J Bean Pkwy, Tampa, FL 33607', 'AirAid', '435.00', null),
('2018-12-03 21:30:00', '2018-12-03 23:55:00', 'Phoenix Sky Harbor International Airport', 'Hartsfield-Jackson Atlanta International Airport', '-112.0115833', '33.4342778', '3400 E Sky Harbor Blvd, Phoenix, AZ 85034', 'AirAid', '230.00', null),
('2018-12-04 12:00:00', '2018-12-04 14:25:00', 'Portland International Airport', 'San Francisco International Airport', '-122.5968694', '45.5887089', '7000 NE Airport Way, Portland, OR 97218', 'AirAid', '335.00', null),
('2018-12-05 07:15:00', '2018-12-05 09:35:00', 'Washington Dulles International Airport', 'John F. Kennedy International Airport', '-77.4599444', '38.9474444', '1 Saarinen Cir, Dulles, VA 20166', 'AirAid', '240.00', null),
('2018-12-06 14:45:00', '2018-12-06 16:55:00', 'John F. Kennedy International Airport', 'Salt Lake City International Airport', '-73.7786950', '40.6399257', 'Queens, NY 11430', 'AirAid', '410.00', null),
('2018-12-07 08:20:00', '2018-12-07 11:50:00', 'Miami International Airport', 'Seattle-Tacoma International Airport', '-80.2901111', '25.7953611', '2100 NW 42nd Ave, Miami, FL 33126', 'AirAid', '330.00', null),
('2018-12-08 05:40:00', '2018-12-08 08:35:00', 'Baltimore-Washington International Airport', 'Minneapolis-Saint Paul International Airport', '-76.6689911', '39.1757283', 'Baltimore, MD 21240', 'AirAid', '420.00', null),
('2018-12-09 11:00:00', '2018-12-09 12:35:00', 'LaGuardia Airport', 'Orlando International Airport', '-73.8726111', '40.7772500', 'Queens, NY 11371', 'AirAid', '245.00', null),
('2018-12-10 06:45:00', '2018-12-10 08:25:00', 'Miami International Airport', 'Midway International Airport', '-80.2901111', '25.7953611', '2100 NW 42nd Ave, Miami, FL 33126', 'AirAid', '320.00', null),
('2018-12-01 12:15:00', '2018-12-01 14:55:00', 'LaGuardia Airport', 'Seattle-Tacoma International Airport', '-73.8726111', '40.7772500', 'Queens, NY 11371', 'AirAid', '430.00', null),
('2018-12-02 07:55:00', '2018-12-02 10:15:00', 'Baltimore-Washington International Airport', 'Portland International Airport', '-76.6689911', '39.1757283', 'Baltimore, MD 21240', 'AirAid', '320.00', null),
('2018-12-03 09:45:00', '2018-12-03 11:25:00', 'Roanoke-Blacksburg Regional Airport', 'Daniel K. Inouye International Airport', '-79.9754167', '37.3254722', '5202 Aviation Dr NW, Roanoke, VA 24012', 'AirAid', '245.00', null),
('2018-12-04 06:45:00', '2018-12-04 09:05:00', 'Roanoke-Blacksburg Regional Airport', 'Ronald Reagan Washington National Airport', '-79.9754167', '37.3254722', '5202 Aviation Dr NW, Roanoke, VA 24012', 'AirAid', '425.00', null),
('2018-12-05 07:45:00', '2018-12-05 09:25:00', 'Baltimore-Washington International Airport', 'Virginia Tech Montgomery Executive Airport', '-76.6689911', '39.1757283', 'Baltimore, MD 21240', 'AirAid', '235.00', null),
('2018-12-06 12:45:00', '2018-12-06 14:00:00', 'Daniel K. Inouye International Airport', 'Minneapolis-Saint Paul International Airport', '-157.9202627', '21.3178275', '300 Rodgers Blvd, Honolulu, HI 96819', 'AirAid', '340.00', null),
('2018-12-07 10:45:00', '2018-12-07 12:35:00', 'San Francisco International Airport', 'Salt Lake City International Airport', '-122.3754167', '37.6188056', 'San Francisco, CA 94128', 'AirAid', '425.00', null),
('2018-12-08 15:45:00', '2018-12-08 17:15:00', 'Minneapolis-Saint Paul International Airport', 'Roanoke-Blacksburg Regional Airport', '-93.2217778', '44.8819722', '4300 Glumack Drive, St. Paul, MN 55111', 'AirAid', '255.00', null),
('2018-12-09 18:45:00', '2018-12-09 20:05:00', 'Ronald Reagan Washington National Airport', 'Miami International Airport', '-77.0377222', '38.8514444', 'Arlington, VA 22202', 'AirAid', '460.00', null),
('2018-12-10 11:45:00', '2018-12-10 13:20:00', 'Portland International Airport', 'Charlotte Douglas International Airport', '-122.5968694', '45.5887089', '7000 NE Airport Way, Portland, OR 97218', 'AirAid', '255.00', null),
('2018-12-11 11:30:00', '2018-12-11 13:25:00', 'Salt Lake City International Airport', 'Detroit Metropolitan Airport', '-111.9777731', '40.7883878', '776 N Terminal Dr, Salt Lake City, UT 84122', 'AirAid', '440.00', null),
('2018-12-12 15:25:00', '2018-12-12 17:15:00', 'San Diego International Airport', 'Minneapolis-Saint Paul International Airport', '-117.1896667', '32.7335556', '3225 N Harbor Dr, San Diego, CA 92101', 'AirAid', '355.00', null),
('2018-12-21 07:50:00', '2018-12-21 09:40:00', 'Los Angeles International Airport', 'Tampa International Airport', '-118.408050', '33.9424944', '1 World Way, Los Angeles, CA 90045', 'AirAid', '260.00', null),
('2018-12-15 09:45:00', '2018-12-15 11:05:00', 'Roanoke-Blacksburg Regional Airport', 'Ronald Reagan Washington National Airport', '-79.9754167', '37.3254722', '5202 Aviation Dr NW, Roanoke, VA 24012', 'AirAid', '375.00', null),
('2018-12-27 16:05:00', '2018-12-27 18:35:00', 'Minneapolis-Saint Paul International Airport', 'Tampa International Airport', '-93.2217778', '44.8819722', '4300 Glumack Drive, St. Paul, MN 55111', 'AirAid', '465.00', null),
('2018-12-31 09:55:00', '2018-12-31 12:05:00', 'Los Angeles International Airport', 'Washington Dulles International Airport', '-118.408050', '33.9424944', '1 World Way, Los Angeles, CA 90045', 'AirAid', '280.00', null),
('2018-12-18 08:05:00', '2018-12-18 10:00:00', 'San Diego International Airport', 'Newark Liberty International Airport', '-117.1896667', '32.7335556', '3225 N Harbor Dr, San Diego, CA 92101', 'AirAid', '485.00', null),
('2018-12-22 06:40:00', '2018-12-22 09:55:00', 'Fort Lauderdale-Hollywood International Airport', 'John F. Kennedy International Airport', '-80.1496944', '26.0716667', '100 Terminal Dr, Fort Lauderdale, FL 33315', 'AirAid', '370.00', null),
('2018-12-14 15:25:00', '2018-12-14 17:45:00', 'George Bush Intercontinental Airport', 'Portland International Airport', '-95.3414425', '29.9844353', '2800 N Terminal Rd, Houston, TX 77032', 'AirAid', '265.00', null),
('2018-12-17 17:30:00', '2018-12-17 19:50:00', 'San Diego International Airport', 'Dallas/Fort Worth International Airport', '-117.1896667', '32.7335556', '3225 N Harbor Dr, San Diego, CA 92101', 'AirAid', '310.00', null),
('2018-12-26 13:45:00', '2018-12-26 16:15:00', 'Phoenix Sky Harbor International Airport', 'Baltimore-Washington International Airport', '-112.0115833', '33.4342778', '3400 E Sky Harbor Blvd, Phoenix, AZ 85034', 'AirAid', '495.00', null),
('2018-12-23 19:50:00', '2018-12-23 22:35:00', 'San Francisco International Airport', 'Minneapolis-Saint Paul International Airport', '-122.3754167', '37.6188056', 'San Francisco, CA 94128', 'AirAid', '375.00', null),
('2018-12-26 11:25:00', '2018-12-26 14:05:00', 'Fort Lauderdale-Hollywood International Airport', 'Daniel K. Inouye International Airport', '-80.1496944', '26.0716667', '100 Terminal Dr, Fort Lauderdale, FL 33315', 'AirAid', '485.00', null),
('2018-12-22 12:00:00', '2018-12-22 15:25:00', 'Midway International Airport', 'Minneapolis-Saint Paul International Airport', '-87.7524167', '41.7859722', '5700 S Cicero Ave, Chicago, IL 60638', 'AirAid', '295.00', null),
('2018-12-16 05:45:00', '2018-12-16 07:10:00', 'Washington Dulles International Airport', 'Baltimore-Washington International Airport', '-77.4599444', '38.9474444', '1 Saarinen Cir, Dulles, VA 20166', 'AirAid', '270.00', null),
('2018-12-03 07:20:00', '2018-12-03 09:10:00', 'LaGuardia Airport', 'Detroit Metropolitan Airport', '-73.8726111', '40.7772500', 'Queens, NY 11371', 'AirAid', '415.00', null),
('2018-12-15 08:15:00', '2018-12-15 10:40:00', 'Minneapolis-Saint Paul International Airport', 'Virginia Tech Montgomery Executive Airport', '-93.2217778', '44.8819722', '4300 Glumack Drive, St. Paul, MN 55111', 'AirAid', '295.00', null),
('2018-12-21 04:20:00', '2018-12-21 06:35:00', 'San Francisco International Airport', 'Roanoke-Blacksburg Regional Airport', '-122.3754167', '37.6188056', 'San Francisco, CA 94128', 'AirAid', '440.00', null),
('2018-12-20 12:35:00', '2018-12-20 14:55:00', 'Orlando International Airport', 'Seattle-Tacoma International Airport', '-81.3090000', '28.4293889', '1 Jeff Fuqua Blvd, Orlando, FL 32827', 'AirAid', '335.00', null),
('2018-12-29 07:40:00', '2018-12-29 09:50:00', 'Roanoke-Blacksburg Regional Airport', 'O\'Hare International Airport', '-79.9754167', '37.3254722', '5202 Aviation Dr NW, Roanoke, VA 24012', 'AirAid', '260.00', null),
('2018-12-12 16:35:00', '2018-12-12 18:55:00', 'Daniel K. Inouye International Airport', 'Denver International Airport', '-157.9202627', '21.3178275', '300 Rodgers Blvd, Honolulu, HI 96819', 'AirAid', '345.00', null),
('2018-12-07 14:20:00', '2018-12-07 16:35:00', 'Fort Lauderdale-Hollywood International Airport', 'Washington Dulles International Airport', '-80.1496944', '26.0716667', '100 Terminal Dr, Fort Lauderdale, FL 33315', 'AirAid', '440.00', null),
('2018-12-06 12:15:00', '2018-12-06 14:45:00', 'Fort Lauderdale-Hollywood International Airport', 'Denver International Airport', '-80.1496944', '26.0716667', '100 Terminal Dr, Fort Lauderdale, FL 33315', 'AirAid', '350.00', null),
('2018-12-03 11:10:00', '2018-12-03 13:40:00', 'LaGuardia Airport', 'Portland International Airport', '-73.8726111', '40.7772500', 'Queens, NY 11371', 'AirAid', '435.00', null),
('2018-12-04 10:05:00', '2018-12-04 12:15:00', 'McCarran International Airport', 'George Bush Intercontinental Airport', '-115.1522500', '36.0800556', '5757 Wayne Newton Blvd, Las Vegas, NV 89119', 'AirAid', '345.00', null),
('2018-12-05 06:50:00', '2018-12-05 09:10:00', 'Logan International Airport', 'McCarran International Airport', '-71.0063889', '42.3629444', '1 Harborside Dr, Boston, MA 02128', 'AirAid', '215.00', null),
('2018-12-02 08:00:00', '2018-12-02 10:35:00', 'LaGuardia Airport', 'O\'Hare International Airport', '-73.8726111', '40.7772500', 'Queens, NY 11371', 'AirAid', '330.00', null),
('2018-12-03 09:05:00', '2018-12-03 11:30:00', 'Seattle-Tacoma International Airport', 'Hartsfield-Jackson Atlanta International Airport', '-122.3117778', '47.4498889', '17801 International Blvd, Seattle, WA 98158', 'AirAid', '415.00', null),
('2018-12-09 10:50:00', '2018-12-09 13:00:00', 'Salt Lake City International Airport', 'Washington Dulles International Airport', '-111.9777731', '40.7883878', '776 N Terminal Dr, Salt Lake City, UT 84122', 'AirAid', '330.00', null),
('2018-12-17 17:50:00', '2018-12-17 20:00:00', 'Denver International Airport', 'Salt Lake City International Airport', '-104.6731667', '39.8616667', '8500 PeÃ±a Blvd, Denver, CO 80249', 'AirAid', '320.00', null);