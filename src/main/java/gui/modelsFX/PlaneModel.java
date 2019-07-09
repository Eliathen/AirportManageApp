package gui.modelsFX;

import entity.Plane;
import exceptions.ApplicationException;
import gui.converters.AirlineConvertTo;
import gui.converters.PlaneConvertTo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.PlaneService;

public class PlaneModel {

    private ObjectProperty<PlaneFX> planeFXObjectProperty = new SimpleObjectProperty<>(new PlaneFX());

    public PlaneFX getPlaneFXObjectProperty() {
        return planeFXObjectProperty.get();
    }

    public ObjectProperty<PlaneFX> planeFXObjectPropertyProperty() {
        return planeFXObjectProperty;
    }

    public void setPlaneFXObjectProperty(PlaneFX planeFXObjectProperty) {
        this.planeFXObjectProperty.set(planeFXObjectProperty);
    }

    public void savePlane() throws ApplicationException {
        Plane plane = PlaneConvertTo.convertToPlane(planeFXObjectProperty.getValue());
        plane.setAirline(AirlineConvertTo.convertToAirline(planeFXObjectProperty.getValue().getAirlineFX()));
        PlaneService planeService = new PlaneService();
        planeService.savePlane(plane);
    }

    public void updatePlane() throws ApplicationException {
        Plane plane = PlaneConvertTo.convertToPlane(planeFXObjectProperty.getValue());
        plane.setAirline(AirlineConvertTo.convertToAirline(planeFXObjectProperty.getValue().getAirlineFX()));
        PlaneService planeService = new PlaneService();
        planeService.updatePlane(plane);
    }
}
