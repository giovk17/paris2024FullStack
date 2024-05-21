CREATE TABLE IF NOT EXISTS USERS (
    USER_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    FULL_NAME VARCHAR(255),
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    USER_ROLE VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS MATCHES (
    MATCH_ID INT AUTO_INCREMENT PRIMARY KEY,
    SPORT_NAME VARCHAR(255) NOT NULL,
    START_DATE DATE NOT NULL,
    START_HOUR INT NOT NULL,
    STADIUM_NAME VARCHAR(255) NOT NULL,
    DISCIPLINE VARCHAR(255),
    OLYMPIC_NR_ONE BIGINT NOT NULL,
    OLYMPIC_NR_TWO BIGINT,
    FREE_SEATS INT NOT NULL,
    TICKET_PRICE DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS TICKETS (
    TICKET_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    USER_ID BIGINT NOT NULL,
    MATCH_ID BIGINT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),
    FOREIGN KEY (MATCH_ID) REFERENCES MATCHES(MATCH_ID)
);