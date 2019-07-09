package gui.modelsFX;

import entity.Ticket;
import exceptions.ApplicationException;
import gui.converters.TicketConverterTo;
import gui.utils.DialogsUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.TicketService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TicketModel {
    private ObservableList<TicketFX> ticketObservableList = FXCollections.observableArrayList();

    private String pesel;

    public void init() throws ApplicationException {
        initTicketList();

    }

    private void initTicketList() throws ApplicationException {
        TicketService ticketService = new TicketService();
        List<Ticket> ticketList = ticketService.getAllTicket();
        ticketObservableList.clear();
        ticketList.forEach(ticket -> {
            try {
                ticket = ticketService.getTicketByIdWithLuggages(ticket.getId());
            } catch (ApplicationException e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
            TicketFX ticketFX = TicketConverterTo.convertToTicketFX(ticket);
            this.ticketObservableList.add(ticketFX);
        });
    }

    public ObservableList<TicketFX> getTicketObservableList() {
        return ticketObservableList;
    }

    public void setTicketObservableList(ObservableList<TicketFX> ticketObservableList) {
        this.ticketObservableList = ticketObservableList;
    }

    public void removeTicket(TicketFX ticketFX) throws ApplicationException {
        TicketService ticketService = new TicketService();
        ticketService.removeTicketById(ticketFX.getId());
        this.init();
    }

    public void filterTicketList() {
        List<TicketFX> list = ticketObservableList.stream().filter(predicatePesel()).collect(Collectors.toList());
        this.ticketObservableList.setAll(list);

    }
    private Predicate<TicketFX> predicatePesel(){
        return ticketFX -> ticketFX.getPassangerFX().getPesel().equals(getPesel());
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
