package gui.modelsFX;

import javafx.beans.property.*;


public class AirportFX {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();

    public AirportFX() {
    }

    public AirportFX(LongProperty id, StringProperty name, StringProperty address, StringProperty city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    @Override
    public String toString() {
        return getName();
    }
}