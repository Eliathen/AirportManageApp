package gui.modelsFX;

import javafx.beans.property.*;
import javafx.collections.ObservableList;


public class AirlineFX extends BaseFX{

    private StringProperty country = new SimpleStringProperty();

    private StringProperty otherDetails = new SimpleStringProperty();

    public AirlineFX() {
    }

    public AirlineFX(StringProperty name) {
        super(name);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public String getOtherDetails() {
        return otherDetails.get();
    }

    public StringProperty otherDetailsProperty() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails.set(otherDetails);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    @Override
    public String toString(){
        return getName();
    }
}