package gui.modelsFX;

import entity.Flight;
import entity.Luggage;
import entity.Passenger;
import entity.Ticket;
import exceptions.ApplicationException;
import gui.converters.FlightConverterTo;
import gui.converters.LuggageConverterTo;
import gui.converters.PassengerConverterTo;
import gui.converters.TicketConverterTo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.FlightService;
import services.LuggageService;
import services.PassengerService;
import services.TicketService;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;


public class AddTicketModel {
    private ObservableList<FlightFX> flightFXObservableList = FXCollections.observableArrayList();

    private ObservableList<LuggageFX> luggageFXObservableList = FXCollections.observableArrayList();

    private ObjectProperty<TicketFX> ticketFXObjectProperty = new SimpleObjectProperty<>(new TicketFX());

    private String pesel;


    public void init() throws ApplicationException {
        initFlightList();
    }

    private void initFlightList() throws ApplicationException {
        FlightService flightService = new FlightService();
        List<Flight> flightList = flightService.getAllFlight();
        flightFXObservableList.clear();
        flightList.forEach(flight -> {
            FlightFX flightFX = FlightConverterTo.convertToFlightFX(flight);
            flightFXObservableList.add(flightFX);
        });
    }

    public void addToLuggageFXObservableList(LuggageFX luggageFX){
        this.luggageFXObservableList.add(luggageFX);

    }

    public ObservableList<FlightFX> getFlightFXObservableList() {
        return flightFXObservableList;
    }

    public void setFlightFXObservableList(ObservableList<FlightFX> flightFXObservableList) {
        this.flightFXObservableList = flightFXObservableList;
    }

    public TicketFX getTicketFXObjectProperty() {
        return ticketFXObjectProperty.get();
    }

    public ObjectProperty<TicketFX> ticketFXObjectPropertyProperty() {
        return ticketFXObjectProperty;
    }

    public void setTicketFXObjectProperty(TicketFX ticketFXObjectProperty) {
        this.ticketFXObjectProperty.set(ticketFXObjectProperty);
    }

    public ObservableList<LuggageFX> getLuggageFXObservableList() {
        return luggageFXObservableList;
    }

    public void setLuggageFXObservableList(ObservableList<LuggageFX> luggageFXObservableList) {
        this.luggageFXObservableList = luggageFXObservableList;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void saveTicket() throws ApplicationException {
        TicketService ticketService = new TicketService();
        PassengerService passengerService = new PassengerService();
        Passenger passenger = passengerService.getPassengerByPesel(pesel);
        LuggageService luggageService = new LuggageService();
        if (passenger.getId() != null) {
            ticketFXObjectProperty.getValue().setPassangerFX(PassengerConverterTo.convertToPassengerFX(passenger));
            ticketFXObjectProperty.getValue().setLuggageFXES(luggageFXObservableList);
            Ticket ticket = TicketConverterTo.convertToTicket(ticketFXObjectProperty.getValue());
            ticketService.saveTicket(ticket);
            for (Luggage luggage : ticket.getLuggages()) {
                luggage.setTicket(ticket);
                luggageService.saveLuggage(luggage);
            }
        }

    }

    public void updateTicket() throws ApplicationException {
        TicketService ticketService = new TicketService();
        System.out.println(this.getTicketFXObjectProperty().getId());
        PassengerService passengerService = new PassengerService();
        Passenger passenger = passengerService.getPassengerByPesel(pesel);
        LuggageService luggageService = new LuggageService();
        if (passenger.getId() != null) {
            ticketFXObjectProperty.getValue().setPassangerFX(PassengerConverterTo.convertToPassengerFX(passenger));
            ticketFXObjectProperty.getValue().setLuggageFXES(luggageFXObservableList);
            Ticket ticket = TicketConverterTo.convertToTicket(ticketFXObjectProperty.getValue());
            ticketService.updateTicket(ticket);
            for (Luggage luggage : ticket.getLuggages()) {
                luggage.setTicket(ticket);
                luggageService.updateLuggage(luggage);
            }
        }
    }
}
