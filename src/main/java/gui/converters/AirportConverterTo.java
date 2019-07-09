package gui.converters;

import entity.Airport;
import entity.Employee;
import gui.modelsFX.AirportFX;
import gui.modelsFX.EmployeeFX;

public class AirportConverterTo {

    public static AirportFX convertToAirportFX(Airport airport){
        AirportFX airportFX = new AirportFX();
        airportFX.setId(airport.getId());
        airportFX.setName(airport.getName());
        airportFX.setCity(airport.getCity());
        airportFX.setAddress(airport.getAddress());
        return airportFX;
    }

    public static Airport convertToAirport(AirportFX airportFX){
        Airport airport = new Airport();
        airport.setId(airportFX.getId());
        airport.setName(airportFX.getName());
        airport.setCity(airportFX.getCity());
        airport.setAddress(airportFX.getAddress());
        return  airport;
    }
}
