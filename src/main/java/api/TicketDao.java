package api;

import entity.Ticket;

import java.util.List;

public interface TicketDao {

        void saveEmplyee(Ticket ticket);

        void removeTicketById(Long id);

        List<Ticket> getAllTicket();
        List<Ticket> getAllTicketByPassangerId(Long id);

        void updateFlight(Ticket ticket);
}
