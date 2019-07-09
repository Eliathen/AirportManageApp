package gui.modelsFX;

import entity.Airport;
import exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.AirportService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AirportModel {

    private ObjectProperty<AirportFX> airportFXObjectProperty = new SimpleObjectProperty<>(new AirportFX());

    private ObservableList<AirportFX> airportFXObservableList = FXCollections.observableArrayList();

    private List<AirportFX> airportFXList = new ArrayList<>();

    private String name;

    private String address;

    private String city;

    public AirportFX getAirportFXObjectProperty() {
        return airportFXObjectProperty.get();
    }

    public ObjectProperty<AirportFX> airportFXObjectPropertyProperty() {
        return airportFXObjectProperty;
    }

    public void setAirportFXObjectProperty(AirportFX airportFXObjectProperty) {
        this.airportFXObjectProperty.set(airportFXObjectProperty);
    }

    public ObservableList<AirportFX> getAirportFXObservableList() {
        return airportFXObservableList;
    }

    public void setAirportFXObservableList(ObservableList<AirportFX> airportFXObservableList) {
        this.airportFXObservableList = airportFXObservableList;
    }

    public List<AirportFX> getAirportFXList() {
        return airportFXList;
    }

    public void setAirportFXList(List<AirportFX> airportFXList) {
        this.airportFXList = airportFXList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void init() throws ApplicationException {
        AirportService airportService = new AirportService();
        List<Airport> airports = airportService.getAllAirport();
        this.airportFXList.clear();
        airports.forEach(airport -> {
            AirportFX airportFX = new AirportFX();
            airportFX.setId((airport.getId()));
            airportFX.setName((airport.getName()));
            airportFX.setAddress((airport.getAddress()));
            airportFX.setCity(airport.getCity());
            this.airportFXList.add(airportFX);
        });
        this.airportFXObservableList.setAll(airportFXList);
    }

    public void removeAirport(AirportFX airportFX) throws ApplicationException {
        AirportService airportService = new AirportService();
        airportService.removeAirportById(airportFX.getId());
        this.init();
    }
    public void filterAirportList(){
        if(!getName().isEmpty() && !getAddress().isEmpty() && !getCity().isEmpty()){
            filterPredicate(predicateName().and(predicateAddress()).and(predicateCity()));
        }
        else if(!getName().isEmpty() && !getAddress().isEmpty()){
            filterPredicate(predicateName().and(predicateAddress()));
        }
        else if(!getName().isEmpty() && !getCity().isEmpty()){
            filterPredicate(predicateName().and(predicateCity()));
        }
        else if(!getAddress().isEmpty() && !getCity().isEmpty()){
            filterPredicate(predicateAddress().and(predicateCity()));
        }
        else if(!getName().isEmpty()){
            filterPredicate(predicateName());
        }
        else if(!getAddress().isEmpty()){
            filterPredicate(predicateAddress());
        }
        else if(!getCity().isEmpty()){
            filterPredicate(predicateCity());
        }
        else{
            this.airportFXObservableList.setAll(this.airportFXList);
        }
    }
    private Predicate<AirportFX> predicateName() {
        return airportFX -> airportFX.getName().contains(getName());
    }
    private Predicate<AirportFX> predicateAddress(){
        return airportFX ->airportFX.getAddress().contains(getAddress());
    }
    private Predicate<AirportFX> predicateCity(){
        return airportFX ->airportFX.getCity().contains(getCity());
    }
    private void filterPredicate(Predicate<AirportFX> predicate){
        List<AirportFX> newList = airportFXList.stream().filter(predicate).collect(Collectors.toList());
        this.airportFXObservableList.setAll(newList);
    }
}
