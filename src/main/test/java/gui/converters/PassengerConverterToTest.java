package gui.converters;

import entity.Passenger;
import gui.modelsFX.PassengerFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerConverterToTest {

    @Test
    void convertToPassengerFX() {
        Passenger passenger = new Passenger(1L, "Name", "Surname", "42060327215");
        PassengerFX passengerFX = PassengerConverterTo.convertToPassengerFX(passenger);
        assertNotNull(passengerFX);
        assertEquals(passenger.getPesel(), passengerFX.getPesel());
        assertEquals(passenger.getId(), passengerFX.getId());
    }

    @Test
    void convertToPassenger() {
        PassengerFX passengerFX = new PassengerFX();
        passengerFX.setPesel("42060327215");
        passengerFX.setId(1L);
        passengerFX.setName("Name");
        passengerFX.setSurname("Surname");
        Passenger passenger = PassengerConverterTo.convertToPassenger(passengerFX);
        assertNotNull(passenger);
        assertEquals(passengerFX.getPesel(), passenger.getPesel());
        assertEquals(passengerFX.getName(), passenger.getName());
    }
}