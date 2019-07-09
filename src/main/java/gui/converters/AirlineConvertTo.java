package gui.converters;

import entity.Airline;
import gui.modelsFX.AirlineFX;

public class AirlineConvertTo {

    public static AirlineFX convertToAirlineFX(Airline airline){
        AirlineFX airlineFX = new AirlineFX();
        airlineFX.setId(airline.getId());
        airlineFX.setName(airline.getName());
        airlineFX.setCountry(airline.getCountry());
        airlineFX.setOtherDetails(airline.getDetails());
        return airlineFX;
    }

    public static Airline convertToAirline(AirlineFX airlineFX){
        Airline airline = new Airline();
        airline.setName(airlineFX.getName());
        airline.setId(airlineFX.getId());
        airline.setCountry(airlineFX.getCountry());
        airline.setDetails(airlineFX.getOtherDetails());
        return airline;
    }
}
