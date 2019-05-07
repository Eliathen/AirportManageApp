package api;

import entity.Ticket;

import java.util.List;

public interface TicketDaoInterface {

        void saveTicket(Ticket ticket);

        void removeTicketById(Long id);

        List<Ticket> getAllTicket();
        List<Ticket> getAllTicketByPassangerId(Long id);

        void updateTicket(Ticket ticket);
}
