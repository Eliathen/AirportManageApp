package services;

import dao.TicketDaoImpl;
import entity.Ticket;
import exceptions.ApplicationException;

import java.util.List;

public class TicketService {

    private TicketDaoImpl ticketDao;

    public TicketService() {
        this.ticketDao = new TicketDaoImpl();
    }

    public void saveTicket(Ticket ticket) throws ApplicationException {
        ticketDao.openCurrentSessionWithTransaction();
        ticketDao.saveTicket(ticket);
        ticketDao.closeCurrentSessionWithTransaction();
    }

    public void removeTicketById(Long id) throws ApplicationException {
        ticketDao.openCurrentSessionWithTransaction();
        ticketDao.removeTicketById(id);
        ticketDao.closeCurrentSessionWithTransaction();
    }

    public List<Ticket> getAllTicket() throws ApplicationException {
        ticketDao.openCurrentSession();
        List<Ticket> tickets = ticketDao.getAllTicket();
        ticketDao.closeCurrentSession();
        return tickets;
    }

    public Ticket getTicketByIdWithLuggages(Long id) throws ApplicationException {
        ticketDao.openCurrentSession();
        Ticket ticket = ticketDao.getTicketById(id);
        ticket = ticketDao.getTicketWithLuggages(ticket);
        ticketDao.closeCurrentSession();
        return ticket;
    }
    public void updateTicket(Ticket ticket) throws ApplicationException {
        ticketDao.openCurrentSessionWithTransaction();
        ticketDao.updateTicket(ticket);
        ticketDao.closeCurrentSessionWithTransaction();
    }
}

