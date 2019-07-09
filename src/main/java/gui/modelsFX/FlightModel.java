package gui.modelsFX;

import entity.*;
import exceptions.ApplicationException;
import gui.converters.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.AirlineService;
import services.AirportService;
import services.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightModel {

    private ObservableList<AirportFX> initialAiportFXObservableList = FXCollections.observableArrayList();

    private ObservableList<AirportFX> finalAiportFXObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AirportFX> initialAiportFXObjectProperty = new SimpleObjectProperty<>();

    private ObjectProperty<AirportFX> finalAiportFXObjectProperty = new SimpleObjectProperty<>();

    private ObservableList<FlightFX> flightFXObservableList = FXCollections.observableArrayList();

    private ObservableList<PlaneFX> planeFXObservableList = FXCollections.observableArrayList();

    private ObservableList<AirlineFX> airlineFXObservableList = FXCollections.observableArrayList();

    private ObjectProperty<PlaneFX> planeFXObjectProperty = new SimpleObjectProperty<>();

    private ObjectProperty<AirlineFX> airlineFXObjectProperty = new SimpleObjectProperty<>();

    private ObservableList<EmployeeFX> allEmployeeFXObservableList = FXCollections.observableArrayList();

    private ObservableList<EmployeeFX> choosenEmployeeFXObservableList = FXCollections.observableArrayList();

    private ObjectProperty<FlightFX> flightFXObjectProperty;

    private IntegerProperty hours = new SimpleIntegerProperty();

    private IntegerProperty minutes = new SimpleIntegerProperty();

    private ObjectProperty<LocalDate> localDate = new SimpleObjectProperty<>();

    private LocalDateTime localDateTime;

    public FlightModel() {
        this.flightFXObjectProperty = new SimpleObjectProperty<>();
    }

    public void init() throws ApplicationException {
        this.initInitialAiportList();
        this.initFinalAiportList();
        this.initFlightList();
        this.initAirlineList();

    }

    private void initInitialAiportList() throws ApplicationException {
        AirportService airportService = new AirportService();
        List<Airport> airports = airportService.getAllAirport();
        initialAiportFXObservableList.clear();
        airports.forEach(airport->{
            AirportFX airportFX = AirportConverterTo.convertToAirportFX(airport);
            this.initialAiportFXObservableList.add(airportFX);
        });
    }

    private void initFinalAiportList() throws ApplicationException {
        AirportService airportService = new AirportService();
        List<Airport> airports = airportService.getAllAirport();
        finalAiportFXObservableList.clear();
        airports.forEach(airport->{
            AirportFX airportFX = AirportConverterTo.convertToAirportFX(airport);
            this.finalAiportFXObservableList.add(airportFX);
        });
    }

    private void initFlightList() throws ApplicationException {
        FlightService flightService = new FlightService();
        List<Flight> flights = flightService.getAllFlight();
        flightFXObservableList.clear();
        flights.forEach(flight ->{
            FlightFX flightFX = FlightConverterTo.convertToFlightFX(flight);
            this.flightFXObservableList.add(flightFX);
        } );
    }

    private void initAirlineList() throws ApplicationException {
        AirlineService airlineService = new AirlineService();
        List<Airline> airlines = airlineService.getAllAirlines();
        airlineFXObservableList.clear();
        airlines.forEach(airline -> {
            AirlineFX airlineFX = AirlineConvertTo.convertToAirlineFX(airline);
            airlineFXObservableList.add(airlineFX);
        });
    }

    public void initPlaneList() throws ApplicationException {
        Airline airline = AirlineConvertTo.convertToAirline(airlineFXObjectProperty.getValue());
        AirlineService airlineService = new AirlineService();
        airline = airlineService.getPlanes(airline);
        List<Plane> planes = airline.getPlanes();
        planeFXObservableList.clear();
        planes.forEach(plane ->{
            PlaneFX planeFX = PlaneConvertTo.convertToPlaneFX(plane);
            planeFXObservableList.add(planeFX);
        });
    }

    public void initEmployeeList() throws ApplicationException {
        Airline airline = AirlineConvertTo.convertToAirline(airlineFXObjectProperty.getValue());
        AirlineService airlineService = new AirlineService();
        airline = airlineService.getEmployees(airline);
        List<Employee> employees = airline.getEmployees();
        allEmployeeFXObservableList.clear();
        choosenEmployeeFXObservableList.clear();
        employees.forEach(employee -> {
            EmployeeFX employeeFX = EmployeeConvertTo.convertToEmpolyeeFX(employee);
            allEmployeeFXObservableList.add(employeeFX);
        });
    }

    public void removeFromAllEmployeeObservableList(EmployeeFX employeeFX){
        this.allEmployeeFXObservableList.remove(employeeFX);

    }

    public void removeFromChoosenEmployeeObservableList(EmployeeFX employeeFX){
        this.choosenEmployeeFXObservableList.remove(employeeFX);
    }

    public void addToAllEmployeeObservableList(EmployeeFX employeeFX){
        this.allEmployeeFXObservableList.add(employeeFX);
    }

    public void addToChoosenEmployeeObservableList(EmployeeFX employeeFX){
        this.choosenEmployeeFXObservableList.add(employeeFX);
    }

    public void removeFlight(FlightFX flightFX) throws ApplicationException {
        FlightService flightService = new FlightService();
        flightService.removeFlight(FlightConverterTo.convertToFlight(flightFX));
        this.init();
    }

    public void updateFlight() throws ApplicationException {
        FlightService flightService = new FlightService();
        Flight flight = FlightConverterTo.convertToFlight(getFlightFXObjectProperty());
        List<Employee> employees = new ArrayList<>();
        choosenEmployeeFXObservableList.forEach(employeeFX -> {
            Employee employee = EmployeeConvertTo.convertToEmployee(employeeFX);
            employees.add(employee);
        });
        flight.setEmployees(employees);
        flightService.updateFlight(flight);

    }

    public void saveFlight() throws ApplicationException {
        FlightService flightService = new FlightService();
        Flight flight = new Flight();
        flight.setPlane(PlaneConvertTo.convertToPlane(planeFXObjectProperty.getValue()));
        flight.setInitialAirport(AirportConverterTo.convertToAirport(initialAiportFXObjectProperty.getValue()));
        flight.setFinalAirport(AirportConverterTo.convertToAirport(finalAiportFXObjectProperty.getValue()));
        List<Employee> employees = new ArrayList<>();
        choosenEmployeeFXObservableList.forEach(employeeFX -> {
            Employee employee = EmployeeConvertTo.convertToEmployee(employeeFX);
            employees.add(employee);
        });
        flight.setEmployees(employees);
        LocalTime time = LocalTime.of(this.getHours(), this.getMinutes());
        LocalDateTime date = LocalDateTime.of(localDate.getValue(), time);
        flight.setInitialDate(date);
        flightService.saveFlight(flight);
    }

    public ObservableList<AirportFX> getInitialAiportFXObservableList() {
        return initialAiportFXObservableList;
    }

    public void setInitialAiportFXObservableList(ObservableList<AirportFX> initialAiportFXObservableList) {
        this.initialAiportFXObservableList = initialAiportFXObservableList;
    }

    public ObservableList<AirportFX> getFinalAiportFXObservableList() {
        return finalAiportFXObservableList;
    }

    public void setFinalAiportFXObservableList(ObservableList<AirportFX> finalAiportFXObservableList) {
        this.finalAiportFXObservableList = finalAiportFXObservableList;
    }

    public AirportFX getInitialAiportFXObjectProperty() {
        return initialAiportFXObjectProperty.get();
    }

    public ObjectProperty<AirportFX> initialAiportObjectFXPropertyProperty() {
        return initialAiportFXObjectProperty;
    }

    public void setInitialAiportObjectProperty(AirportFX initialAiportFXObjectProperty) {
        this.initialAiportFXObjectProperty.set(initialAiportFXObjectProperty);
    }

    public AirportFX getFinalAiportFXObjectProperty() {
        return finalAiportFXObjectProperty.get();
    }

    public ObjectProperty<AirportFX> finalAiportObjectFXPropertyProperty() {
        return finalAiportFXObjectProperty;
    }

    public void setFinalAiportObjectProperty(AirportFX finalAiportFXObjectProperty) {
        this.finalAiportFXObjectProperty.set(finalAiportFXObjectProperty);
    }

    public ObservableList<FlightFX> getFlightFXObservableList() {
        return flightFXObservableList;
    }

    public void setFlightFXObservableList(ObservableList<FlightFX> flightFXObservableList) {
        this.flightFXObservableList = flightFXObservableList;
    }

    public ObservableList<PlaneFX> getPlaneFXObservableList() {
        return planeFXObservableList;
    }

    public void setPlaneFXObservableList(ObservableList<PlaneFX> planeFXObservableList) {
        this.planeFXObservableList = planeFXObservableList;
    }

    public ObservableList<AirlineFX> getAirlineFXObservableList() {
        return airlineFXObservableList;
    }

    public void setAirlineFXObservableList(ObservableList<AirlineFX> airlineFXObservableList) {
        this.airlineFXObservableList = airlineFXObservableList;
    }

    public PlaneFX getPlaneFXObjectProperty() {
        return planeFXObjectProperty.get();
    }

    public ObjectProperty<PlaneFX> planeFXObjectPropertyProperty() {
        return planeFXObjectProperty;
    }

    public void setPlaneFXObjectProperty(PlaneFX planeFXObjectProperty) {
        this.planeFXObjectProperty.set(planeFXObjectProperty);
    }

    public AirlineFX getAirlineFXObjectProperty() {
        return airlineFXObjectProperty.get();
    }

    public ObjectProperty<AirlineFX> airlineFXObjectPropertyProperty() {
        return airlineFXObjectProperty;
    }

    public void setAirlineFXObjectProperty(AirlineFX airlineFXObjectProperty) {
        this.airlineFXObjectProperty.set(airlineFXObjectProperty);
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

    public int getMinutes() {
        return minutes.get();
    }

    public IntegerProperty minutesProperty() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes.set(minutes);
    }

    public LocalDate getLocalDate() {
        return localDate.get();
    }

    public ObjectProperty<LocalDate> localDateProperty() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate.set(localDate);
    }

    public ObservableList<EmployeeFX> getAllEmployeeFXObservableList() {
        return allEmployeeFXObservableList;
    }

    public void setAllEmployeeFXObservableList(ObservableList<EmployeeFX> allEmployeeFXObservableList) {
        this.allEmployeeFXObservableList = allEmployeeFXObservableList;
    }

    public ObservableList<EmployeeFX> getChoosenEmployeeFXObservableList() {
        return choosenEmployeeFXObservableList;
    }

    public void setChoosenEmployeeFXObservableList(ObservableList<EmployeeFX> choosenEmployeeFXObservableList) {
        this.choosenEmployeeFXObservableList = choosenEmployeeFXObservableList;
    }

    public ObjectProperty<AirportFX> initialAiportFXObjectPropertyProperty() {
        return initialAiportFXObjectProperty;
    }

    public void setInitialAiportFXObjectProperty(AirportFX initialAiportFXObjectProperty) {
        this.initialAiportFXObjectProperty.set(initialAiportFXObjectProperty);
    }

    public ObjectProperty<AirportFX> finalAiportFXObjectPropertyProperty() {
        return finalAiportFXObjectProperty;
    }

    public void setFinalAiportFXObjectProperty(AirportFX finalAiportFXObjectProperty) {
        this.finalAiportFXObjectProperty.set(finalAiportFXObjectProperty);
    }

    public FlightFX getFlightFXObjectProperty() {
        return flightFXObjectProperty.get();
    }

    public ObjectProperty<FlightFX> flightFXObjectPropertyProperty() {
        return flightFXObjectProperty;
    }

    public void setFlightFXObjectProperty(FlightFX flightFXObjectProperty) {
        this.flightFXObjectProperty.set(flightFXObjectProperty);
    }

    public FlightFX loadAirline(FlightFX flightFX){
        flightFX.setAirlineFX(flightFX.getPlaneFX().getAirlineFX());
        return flightFX;
    }

    public FlightFX loadEmployees(FlightFX flightFX) throws ApplicationException {
        FlightService flightService = new FlightService();
        List<Employee> employees = flightService.getEmployees(FlightConverterTo.convertToFlight(flightFX)).getEmployees();
        employees.forEach(employee -> {
            EmployeeFX employeeFX = EmployeeConvertTo.convertToEmpolyeeFX(employee);
            flightFX.employeesProperty().addAll(employeeFX);
        });
        return flightFX;
    }

    public void setEmployeeObservableList(ObservableList<EmployeeFX> employeeFXObservableList) throws ApplicationException {
        this.choosenEmployeeFXObservableList.clear();
        this.choosenEmployeeFXObservableList = employeeFXObservableList;
        Airline airline = AirlineConvertTo.convertToAirline(getFlightFXObjectProperty().getAirlineFX());
        AirlineService airlineService = new AirlineService();
        airline = airlineService.getEmployees(airline);
        List<Employee> employees = airline.getEmployees();
        allEmployeeFXObservableList.clear();
        employees.forEach(employee -> {
            EmployeeFX employeeFX = EmployeeConvertTo.convertToEmpolyeeFX(employee);
            allEmployeeFXObservableList.add(employeeFX);
        });
        Iterator<EmployeeFX> all = allEmployeeFXObservableList.iterator();
        while(all.hasNext()){
            EmployeeFX currentAll = all.next();
            Iterator<EmployeeFX> choosen = this.choosenEmployeeFXObservableList.iterator();
            while(choosen.hasNext()){
                EmployeeFX currentChoosen = choosen.next();
                if(currentAll.getId() == currentChoosen.getId()){
                    all.remove();
                }
            }
        }
    }

    public void filterFlightList() throws ApplicationException {
        if(this.getInitialAiportFXObjectProperty()!=null && this.getFinalAiportFXObjectProperty()!=null && this.getLocalDate() != null){
            filterPredicate(predicateInitialAirport().and(predicateFinalAirport()).and(predicateDate()).and(predicateHours()).and(predicateMinutes()));
        }
        else if(this.getInitialAiportFXObjectProperty()!=null && this.getFinalAiportFXObjectProperty()!=null && this.getLocalDate() == null){
            filterPredicate(predicateInitialAirport().and(predicateFinalAirport()));
        }
        else if(this.getInitialAiportFXObjectProperty()!=null && this.getFinalAiportFXObjectProperty()==null && this.getLocalDate() != null){
            filterPredicate(predicateInitialAirport().and(predicateDate()).and(predicateHours()).and(predicateMinutes()));
        }
        else if(this.getInitialAiportFXObjectProperty()==null && this.getFinalAiportFXObjectProperty()!=null && this.getLocalDate() != null){
            filterPredicate(predicateFinalAirport().and(predicateDate()).and(predicateHours()).and(predicateMinutes()));
        }
        else if(this.getInitialAiportFXObjectProperty()!=null){
            filterPredicate(predicateInitialAirport());
        }
        else if(this.getFinalAiportFXObjectProperty()!=null){
            filterPredicate(predicateFinalAirport());
        }
        else if(this.getLocalDate() != null){
            filterPredicate(predicateDate().and(predicateHours()).and(predicateMinutes()));
        }
        else{
            this.initFlightList();
        }
    }

    private Predicate<FlightFX> predicateInitialAirport(){
        return flightFX -> flightFX.getInitialAirport().getId() == getInitialAiportFXObjectProperty().getId();
    }

    private Predicate<FlightFX> predicateFinalAirport(){
        return flightFX -> flightFX.getFinalAirport().getId() == getFinalAiportFXObjectProperty().getId();
    }

    private Predicate<FlightFX> predicateDate(){
        return flightFX -> flightFX.getDateTime().toLocalDate().equals(this.getLocalDate());
    }

    private Predicate<FlightFX> predicateHours(){
        return flightFX -> flightFX.getDateTime().getHour() == this.getHours();
    }

    private Predicate<FlightFX> predicateMinutes(){
        return flightFX -> flightFX.getDateTime().getMinute() ==this.getMinutes();
    }

    private void filterPredicate(Predicate<FlightFX> predicate){
        List<FlightFX> newList = flightFXObservableList.stream().filter(predicate).collect(Collectors.toList());
        this.flightFXObservableList.setAll(newList);
    }
}