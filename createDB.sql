DROP TABLE USER CASCADE CONSTRAINTS;
DROP TABLE AIRPORT CASCADE CONSTRAINTS;
DROP TABLE AIRLINE CASCADE CONSTRAINTS;
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE LUGGAGE CASCADE CONSTRAINTS;
DROP TABLE PASSENGER CASCADE CONSTRAINTS;
DROP TABLE PLANE CASCADE CONSTRAINTS;
DROP TABLE TICKET CASCADE CONSTRAINTS;
DROP TABLE FLIGHT CASCADE CONSTRAINTS;
DROP TABLE FLIGHT_EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE TICKET_LUGGAGE CASCADE CONSTRAINTS;

CREATE TABLE IF NOT EXISTS USER(
                                id number auto_increment primary key ,
                                login varchar2(50) unique not null,
                                password varchar2(30) not null
);
CREATE TABLE IF NOT EXISTS AIRPORT(
                                    id number PRIMARY KEY AUTO_INCREMENT,
                                    name varchar2(30) not null,
                                    address varchar2(50) not null,
                                    city varchar(30) not null
);
CREATE TABLE IF NOT EXISTS AIRLINE(
                                    id number PRIMARY KEY AUTO_INCREMENT,
                                    name varchar2(50) not null,
                                    country varchar2(30) not null,
                                    otherDetails varchar2(150)
);
CREATE TABLE IF NOT EXISTS EMPLOYEE(
                                     id number PRIMARY KEY AUTO_INCREMENT,
                                     name varchar2(30) not null,
                                     surname varchar2(50) not null,
                                     pesel varchar2(11) unique not null,
                                     occupation varchar2(30) not null,
                                     salary number(7,2),
                                     airlineId number,
                                     FOREIGN KEY (id) REFERENCES AIRLINE(id)
);
CREATE TABLE IF NOT EXISTS LUGGAGE(
                                    code varchar2(10) PRIMARY KEY not null,
                                    weight number(4,2) not null,
                                    height number(3) not null
);
CREATE TABLE IF NOT EXISTS PASSENGER(
                                      id number primary key AUTO_INCREMENT,
                                      name varchar2(30) not null,
                                      surname varchar2(50) not null,
                                      pesel varchar2(11) unique not null,
);
CREATE TABLE IF NOT EXISTS PLANE(
                                  id number primary key AUTO_INCREMENT,
                                  registrationNumber varchar2(10) not null,
                                  modelNumber number not null,
                                  name varchar2(30) not null,
                                  capacity number not null ,
                                  weight number,
                                  airlineId number,
                                  FOREIGN KEY (airlineId) REFERENCES AIRLINE(id)
);
CREATE TABLE IF NOT EXISTS FLIGHT(
                                   id number PRIMARY KEY AUTO_INCREMENT,
                                   initialAirportId number not null,
                                   finalAirportId number not null,
                                   initialDate Date not null,
                                   planeId number,
                                   FOREIGN KEY (planeId) REFERENCES PLANE(id),
                                   FOREIGN KEY (initialAirportId) REFERENCES AIRPORT(id),
                                   FOREIGN KEY (finalAirportId) REFERENCES AIRPORT(id)
);
CREATE TABLE IF NOT EXISTS TICKET(
                                   id number primary key AUTO_INCREMENT,
                                   flightNumber number not null,
                                   passengerId number not null,
                                   FOREIGN KEY (flightNumber) REFERENCES FLIGHT(id),
                                   FOREIGN KEY (passengerId) REFERENCES PASSENGER(id)
);
CREATE TABLE IF NOT EXISTS TICKET_LUGGAGE(
                                           ticketId number not null,
                                           luggageCode varchar2(10) not null,
                                           FOREIGN KEY (ticketId) REFERENCES TICKET(id),
                                           FOREIGN KEY (luggageCode) REFERENCES LUGGAGE(code)
);

CREATE TABLE IF NOT EXISTS FLIGHT_EMPLOYEE(
				              flightId number not null,
				              employeeId number not null,
				              FOREIGN KEY (flightId) REFERENCES FLIGHT(id),
				              FOREIGN KEY (employeeId) REFERENCES EMPLOYEE(id)
);
