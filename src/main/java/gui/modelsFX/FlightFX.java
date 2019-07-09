package gui.modelsFX;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightFX {

    private LongProperty id = new SimpleLongProperty();

    private ObjectProperty<AirportFX> initialAirport = new SimpleObjectProperty<>();

    private ObjectProperty<AirportFX> finalAirport = new SimpleObjectProperty<>();

    private ObjectProperty<LocalDateTime> dateTime = new SimpleObjectProperty<>();

    private IntegerProperty minutes = new SimpleIntegerProperty();

    private IntegerProperty hours = new SimpleIntegerProperty();

    private ObjectProperty<PlaneFX> planeFX = new SimpleObjectProperty<>();

    private ObjectProperty<AirlineFX> airlineFX = new SimpleObjectProperty<>();

    private ListProperty<EmployeeFX> employees;

    public FlightFX() {
        ObservableList<EmployeeFX> employeeFXES = FXCollections.observableArrayList();
        employees = new SimpleListProperty<>(employeeFXES);
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

    public AirportFX getInitialAirport() {
        return initialAirport.get();
    }

    public ObjectProperty<AirportFX> initialAirportProperty() {
        return initialAirport;
    }

    public void setInitialAirport(AirportFX initialAirport) {
        this.initialAirport.set(initialAirport);
    }

    public AirportFX getFinalAirport() {
        return finalAirport.get();
    }

    public ObjectProperty<AirportFX> finalAirportProperty() {
        return finalAirport;
    }

    public void setFinalAirport(AirportFX finalAirport) {
        this.finalAirport.set(finalAirport);
    }

    public LocalDateTime getDateTime() {
        return dateTime.get();
    }

    public ObjectProperty<LocalDateTime> dateTimeProperty() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime.set(dateTime);
    }

    public int getMinutes() {
        return minutes.get();
    }

    public IntegerProperty minutesProperty() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes.set(minutes);
    }

    public int getHours() {
        return hours.get();
    }

    public IntegerProperty hoursProperty() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours.set(hours);
    }

    public PlaneFX getPlaneFX() {
        return planeFX.get();
    }

    public ObjectProperty<PlaneFX> planeFXProperty() {
        return planeFX;
    }

    public void setPlaneFX(PlaneFX planeFX) {
        this.planeFX.set(planeFX);
    }

    public AirlineFX getAirlineFX() {
        return airlineFX.get();
    }

    public ObjectProperty<AirlineFX> airlineFXProperty() {
        return airlineFX;
    }

    public void setAirlineFX(AirlineFX airlineFX) {
        this.airlineFX.set(airlineFX);
    }

    public ObservableList<EmployeeFX> getEmployees() {
        return employees.get();
    }

    public void setEmployees(ObservableList<EmployeeFX> employees) {
        this.employees.set(employees);
    }

    public ListProperty<EmployeeFX> employeesProperty() {
        return employees;
    }

    public void setEmployees(ListProperty<EmployeeFX> employees) {
        this.employees.set(employees);
    }

    @Override
    public String toString() {
        return getInitialAirport()+ "<-> "+getFinalAirport() +" "+getDateTime();
    }
}
