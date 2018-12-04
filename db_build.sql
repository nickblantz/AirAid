/* sql file to create the databases or a user, userphoto, airports, and userticket. Created by Phillip Blake Adams */
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
