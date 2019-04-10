CREATE TABLE IF NOT EXISTS USER
(
  id number auto_increment primary key ,
  login varchar2(50) unique ,
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
                                    otherDatails varchar2(150)
);
CREATE TABLE IF NOT EXISTS EMPLOYEE(
                                     id number PRIMARY KEY AUTO_INCREMENT,
                                     name varchar2(30) not null,
                                     surname varchar2(50) not null,
                                     pesel varchar2(11) not null,
                                     occupation varchar2(30) not null,
                                     salary number(7,2),
                                     airlineId number,
                                     FOREIGN KEY (id) REFERENCES AIRLINE(id)
);

CREATE TABLE IF NOT EXISTS LUGGAGE(
                                    id number primary key AUTO_INCREMENT,
                                    code varchar2(10) not null,
                                    weight number(4,2) not null,
                                    height number(3) not null
);
CREATE TABLE IF NOT EXISTS PASSANGER(
                                      id number primary key AUTO_INCREMENT,
                                      name varchar2(30) not null,
                                      surname varchar2(50) not null,
                                      pesel varchar2(11) not null,
);

CREATE TABLE IF NOT EXISTS PLANE(
                                  id number primary key AUTO_INCREMENT,
                                  registrationNumber varchar2(10) not null,
                                  modelNumber number not null,
                                  name varchar2(30) not null,
                                  capacity number not null ,
                                  weight number
);

CREATE TABLE IF NOT EXISTS TICKET(
                                   id number primary key AUTO_INCREMENT,
                                   flightNumber number not null,
                                   luggageId number not null ,
                                   passangerId number not null,
                                   FOREIGN KEY(luggageId) REFERENCES LUGGAGE(id),
                                   FOREIGN KEY (passangerId) REFERENCES PASSANGER(id)
);
CREATE TABLE IF NOT EXISTS FLIGHT(
                                   id number PRIMARY KEY AUTO_INCREMENT,
                                   initialAirlineId number not null,
                                   finalAirlineId number not null,
                                   initialDate Date not null,
                                   FOREIGN KEY (initialAirlineId) REFERENCES AIRPORT(id),
                                   FOREIGN KEY (finalAirlineId) REFERENCES AIRPORT(id)
);
