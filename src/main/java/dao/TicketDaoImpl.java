package dao;
import entity.Ticket;
import org.hibernate.query.Query;


import java.util.LinkedList;
import java.util.List;

public class TicketDaoImpl extends BaseDao implements api.TicketDao {

    public TicketDaoImpl() {
    }

    public void saveTicket(Ticket ticket){
        getCurrentSession().save(ticket);
    }

    public void removeTicketById(Long id){
        try{
            Ticket ticket = getTicketById(id);
            getCurrentSession().delete(ticket);
        }catch(NullPointerException e){

        }
    }
    public Ticket getTicketById(Long id){
        try{
            Query query = getCurrentSession().createQuery("FROM Ticket WHERE id =: id");
            query.setParameter("id", id);
            Ticket ticket = (Ticket) query.uniqueResult();
            return ticket;
        }catch(NullPointerException e){
            return new Ticket();
        }
    }
    public Ticket getAllTicketWithLuggages(Ticket ticket){
        try{
            Query query = getCurrentSession().createQuery("From Luggage WHERE ticketId =: ticketId");
            query.setParameter("ticketId", ticket.getId());
            ticket.setLuggages(query.getResultList());
            return ticket;
        }catch(NullPointerException e){
            return ticket;
        }
    }

    public List<Ticket> getAllTicket(){
        try{
            Query query = getCurrentSession().createQuery("From TICKET");
            List<Ticket> tickets = query.getResultList();
            return tickets;
        }catch(NullPointerException e){
            return new LinkedList<Ticket>();
        }
    }
    public List<Ticket> getAllTicketByPassangerId(Long id){
        try{
            Query query = getCurrentSession().createQuery("From TICKET WHERE PASSANGERID =: id");
            query.setParameter("id", id);
            List<Ticket> tickets = query.getResultList();
            return tickets;
        }catch(NullPointerException e){
            return new LinkedList<Ticket>();
        }
    }

    public void updateTicket(Ticket ticket){
        if(ticket!=null){
            getCurrentSession().update(ticket);
        }
    }
}
