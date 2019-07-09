package gui.modelsFX;

import entity.Airline;
import exceptions.ApplicationException;
import gui.converters.AirlineConvertTo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.AirlineService;


public class AddAirlineModel {

    private ObjectProperty<AirlineFX> airlineFXObjectProperty = new SimpleObjectProperty<>(new AirlineFX());


    public AirlineFX getAirlineFXObjectProperty() {
        return airlineFXObjectProperty.get();
    }


    public ObjectProperty<AirlineFX> airlineFXObjectPropertyProperty() {
        return airlineFXObjectProperty;
    }


    public void setAirlineFXObjectProperty(AirlineFX airlineFXObjectProperty) {
        this.airlineFXObjectProperty.set(airlineFXObjectProperty);
    }

    public void updateAirline() throws ApplicationException {
        AirlineService airlineService = new AirlineService();
        Airline airline = AirlineConvertTo.convertToAirline(airlineFXObjectProperty.getValue());
        airlineService.updateAirline(airline);
    }

    public void saveAirline() throws ApplicationException {
        AirlineService airlineService = new AirlineService();
        Airline airline = AirlineConvertTo.convertToAirline(airlineFXObjectProperty.getValue());
        airlineService.saveAirline(airline);
    }
}
