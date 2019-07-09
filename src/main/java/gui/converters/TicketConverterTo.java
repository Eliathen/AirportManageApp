package gui.converters;

import entity.Luggage;
import entity.Ticket;
import gui.modelsFX.LuggageFX;
import gui.modelsFX.TicketFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TicketConverterTo {

    public static TicketFX convertToTicketFX(Ticket ticket){
        TicketFX ticketFX = new TicketFX();
        ticketFX.setId(ticket.getId());
        ticketFX.setPassengerFX(PassengerConverterTo.convertToPassengerFX(ticket.getPassenger()));
        ticketFX.setFlightFX(FlightConverterTo.convertToFlightFX(ticket.getFlight()));
        ObservableList<LuggageFX> luggageFXList = FXCollections.observableArrayList();
        ticket.getLuggages().forEach(luggage -> {
            LuggageFX luggageFX = new LuggageFX();
            luggageFX = LuggageConverterTo.converterToLuggageFX(luggage);
            luggageFXList.add(luggageFX);
        });
        ticketFX.setLuggageFXES(luggageFXList);
        return ticketFX;
    }

    public static Ticket convertToTicket(TicketFX ticketFX){
        Ticket ticket = new Ticket();
        ticket.setId(ticketFX.getId());
        ticket.setPassenger(PassengerConverterTo.convertToPassenger(ticketFX.getPassengerFX()));
        ticket.setFlight(FlightConverterTo.convertToFlight(ticketFX.getFlightFX()));
        List<Luggage> luggages = new ArrayList<>();
        ticketFX.getLuggageFXES().forEach(luggageFX -> {
            Luggage luggage = new Luggage();
            luggage = LuggageConverterTo.convertToLuggage(luggageFX);
            luggages.add(luggage);
        });
        ticket.setLuggages(luggages);
        return ticket;
    }
}
