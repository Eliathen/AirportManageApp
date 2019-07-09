package gui.modelsFX;

import javafx.beans.property.*;

import java.util.Objects;

public class EmployeeFX extends BaseFX{

    private StringProperty surname = new SimpleStringProperty();

    private StringProperty pesel = new SimpleStringProperty();

    private StringProperty occupation = new SimpleStringProperty();

    private FloatProperty salary = new SimpleFloatProperty();

    private AirlineFX airlineFX;

    public EmployeeFX() {
    }

    public EmployeeFX(StringProperty name) {
        super(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getOccupation() {
        return occupation.get();
    }

    public StringProperty occupationProperty() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }

    public float getSalary() {
        return salary.get();
    }

    public FloatProperty salaryProperty() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary.set(salary);
    }

    public AirlineFX getAirlineFX() {
        return airlineFX;
    }

    public void setAirlineFX(AirlineFX airlineFX) {
        this.airlineFX = airlineFX;
    }

    @Override
    public String toString() {
        return getSurname() + " " + getName();
    }

}
