package gui.converters;

import entity.Flight;
import gui.modelsFX.FlightFX;

public class FlightConverterTo {

    public static FlightFX convertToFlightFX(Flight flight){
        FlightFX flightFX = new FlightFX();
        flightFX.setId(flight.getId());
        flightFX.setInitialAirport(AirportConverterTo.convertToAirportFX(flight.getInitialAirport()));
        flightFX.setFinalAirport(AirportConverterTo.convertToAirportFX(flight.getFinalAirport()));
        flightFX.setDateTime(flight.getInitialDate());
        flightFX.setPlaneFX(PlaneConvertTo.convertToPlaneFX(flight.getPlane()));
        return flightFX;
    }

    public static Flight convertToFlight(FlightFX flightFX){
        Flight flight = new Flight();
        flight.setId(flightFX.getId());
        flight.setInitialAirport(AirportConverterTo.convertToAirport(flightFX.getInitialAirport()));
        flight.setFinalAirport(AirportConverterTo.convertToAirport(flightFX.getFinalAirport()));
        flight.setInitialDate(flightFX.getDateTime());
        flight.setPlane(PlaneConvertTo.convertToPlane(flightFX.getPlaneFX()));
        return flight;
    }
}
