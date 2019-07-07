package gui.converters;

import entity.Airline;
import entity.Plane;
import gui.modelsFX.AirlineFX;
import gui.modelsFX.PlaneFX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneConvertToTest {

    @Test
    void convertToPlaneFX() {
        Plane plane = new Plane();
        plane.setId(1L);
        plane.setCapacity(70);
        plane.setName("Name1");
        plane.setModelNumber(123);
        plane.setRegistrationNumber("reg1");
        plane.setWeight(12);
        Airline airline = new Airline();
        airline.setId(1L);
        plane.setAirline(airline);
        PlaneFX planeFX = PlaneConvertTo.convertToPlaneFX(plane);
        assertNotNull(planeFX);
        assertEquals(plane.getCapacity(), planeFX.getCapacity());
        assertEquals(plane.getAirline().getId(), planeFX.getAirlineFX().getId());
        assertEquals(plane.getModelNumber(), planeFX.getModelNumber());
        assertEquals(plane.getWeight(), planeFX.getWeight());
    }

    @Test
    void convertToPlane() {
        PlaneFX planeFX = new PlaneFX();
        planeFX.setId(1L);
        planeFX.setCapacity(70);
        planeFX.setName("Name1");
        planeFX.setRegistrationNumber("reg1");
        planeFX.setModelNumber(123);
        planeFX.setWeight(12);
        AirlineFX airlineFX = new AirlineFX();
        airlineFX.setId(1L);
        planeFX.setAirlineFX(airlineFX);
        Plane plane = PlaneConvertTo.convertToPlane(planeFX);
        assertNotNull(plane);
        assertEquals(planeFX.getCapacity(), plane.getCapacity());
        assertEquals(planeFX.getAirlineFX().getId(), plane.getAirline().getId());
        assertEquals(planeFX.getModelNumber(), plane.getModelNumber());
        assertEquals(planeFX.getWeight(), plane.getWeight());
    }
}