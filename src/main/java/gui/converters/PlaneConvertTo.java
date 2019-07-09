package gui.converters;

import entity.Plane;
import gui.modelsFX.PlaneFX;

public class PlaneConvertTo {

    public static PlaneFX convertToPlaneFX(Plane plane){
        PlaneFX planeFX  = new PlaneFX();
        planeFX.setId(plane.getId());
        planeFX.setName(plane.getName());
        planeFX.setModelNumber(plane.getModelNumber());
        planeFX.setCapacity(plane.getCapacity());
        planeFX.setRegistrationNumber(plane.getRegistrationNumber());
        planeFX.setWeight(plane.getWeight());
        planeFX.setAirlineFX(AirlineConvertTo.convertToAirlineFX(plane.getAirline()));
        return planeFX;
    }

    public static Plane convertToPlane(PlaneFX planeFX){
        Plane plane  = new Plane();
        plane.setId(planeFX.getId());
        plane.setName(planeFX.getName());
        plane.setModelNumber(planeFX.getModelNumber());
        plane.setCapacity(planeFX.getCapacity());
        plane.setRegistrationNumber(planeFX.getRegistrationNumber());
        plane.setWeight(planeFX.getWeight());
        plane.setAirline(AirlineConvertTo.convertToAirline(planeFX.getAirlineFX()));
        return plane;
    }
}
