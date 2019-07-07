package api;

import entity.Ticket;

import java.io.Serializable;
import java.util.List;

public interface TicketDao extends Serializable {
        
        void saveTicket(Ticket ticket);
        
        void removeTicketById(Long id);
        
        List<Ticket> getAllTicket();

        Ticket getTicketWithLuggages(Ticket ticket);
        
        void updateTicket(Ticket ticket);
}
