package gui.converters;

import entity.Passenger;
import gui.modelsFX.PassengerFX;

import java.util.List;

public class PassengerConverterTo {

    public static PassengerFX convertToPassengerFX(Passenger passenger){
        PassengerFX passengerFX = new PassengerFX();
        passengerFX.setId(passenger.getId());
        passengerFX.setName(passenger.getName());
        passengerFX.setSurname(passenger.getSurname());
        passengerFX.setPesel(passenger.getPesel());
        return passengerFX;
    }

    public static Passenger convertToPassenger(PassengerFX passengerFX){
        Passenger passenger = new Passenger();
        passenger.setId(passengerFX.getId());
        passenger.setName(passengerFX.getName());
        passenger.setSurname(passengerFX.getSurname());
        passenger.setPesel(passengerFX.getPesel());
        return passenger;
    }
}
