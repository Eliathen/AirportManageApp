package services;

import dao.TicketDaoImpl;
import entity.Ticket;

import java.util.List;

public class TicketService {
    private TicketDaoImpl ticketDao;

    public TicketService() {
        this.ticketDao = new TicketDaoImpl();
    }
    public void saveTicket(Ticket ticket){
        ticketDao.openCurrentSessionWithTransaction();
        ticketDao.saveTicket(ticket);
        ticketDao.closeCurrentSessionWithTransaction();
    }
    public void removeTicketById(Long id){
        ticketDao.openCurrentSessionWithTransaction();
        ticketDao.removeTicketById(id);
        ticketDao.closeCurrentSessionWithTransaction();
    }
    public Ticket getTicketById(Long id){
        ticketDao.openCurrentSession();
        Ticket ticket = ticketDao.getTicketById(id);
        ticketDao.closeCurrentSession();
        return ticket;
    }
    public List<Ticket> getAllTicket(){
        ticketDao.openCurrentSession();
        List<Ticket> tickets = ticketDao.getAllTicket();
        ticketDao.closeCurrentSession();
        return tickets;
    }
    public List<Ticket> getAllTicketByPassangerId(Long id){
        ticketDao.openCurrentSession();
        List<Ticket> tickets = ticketDao.getAllTicketByPassangerId(id);
        ticketDao.closeCurrentSession();
        return tickets;
    }
}

