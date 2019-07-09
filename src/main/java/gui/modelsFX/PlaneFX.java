package gui.modelsFX;


import javafx.beans.property.*;

public class PlaneFX extends BaseFX{

    private StringProperty registrationNumber = new SimpleStringProperty();

    private IntegerProperty modelNumber = new SimpleIntegerProperty();

    private StringProperty name = new SimpleStringProperty();

    private IntegerProperty capacity = new SimpleIntegerProperty();

    private IntegerProperty weight = new SimpleIntegerProperty();

    private AirlineFX airlineFX;

    public PlaneFX() {
    }

    public PlaneFX(StringProperty name) {
        super(name);
    }

    public String getRegistrationNumber() {
        return registrationNumber.get();
    }

    public StringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber.set(registrationNumber);
    }

    public int getModelNumber() {
        return modelNumber.get();
    }

    public IntegerProperty modelNumberProperty() {
        return modelNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber.set(modelNumber);
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public Integer getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public AirlineFX getAirlineFX() {
        return airlineFX;
    }

    public void setAirlineFX(AirlineFX airlineFX) {
        this.airlineFX = airlineFX;
    }

    @Override
    public String toString() {
        return getRegistrationNumber();
    }
}