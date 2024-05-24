INSERT INTO USERS (FULL_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES
('John Doe', 'john_doe', 'password123', 'USER'),
('Jane Smith', 'jane_smith', 'password456', 'ADMIN'),
('Giovanny', 'giovk', 'sudosudo', 'ADMIN');


INSERT INTO MATCHES (SPORT_NAME, START_DATE, START_HOUR, STADIUM_NAME, OLYMPIC_NR_ONE, OLYMPIC_NR_TWO, FREE_SEATS, TICKET_PRICE) VALUES
('SOCCER', '2024-07-24', 10, 'Main Stadium', 15123, 16000, 30, 50.00),
('BASKETBALL', '2024-07-25', 14, 'Secondary Stadium', 34512, 35000, 20, 75.00);

INSERT INTO TICKETS (USER_ID, MATCH_ID) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(1, 1),
(2, 1),
(1, 2),
(2, 2);