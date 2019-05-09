package dao;
import api.TicketDaoInterface;
import entity.Ticket;
import org.hibernate.query.Query;
;

import java.util.LinkedList;
import java.util.List;

public class TicketDao extends BaseDao implements TicketDaoInterface {
    public TicketDao() {
    }

    public void saveTicket(Ticket ticket){
        if(!isTicketAlreadyExist(ticket.getId())){
            getCurrentSession().save(ticket);
        }else{
            //jezeli bilet istnieje
        }
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
            Query query = getCurrentSession().createQuery("FROM TICKET WHERE id =: id");
            query.setParameter("id", id);
            Ticket ticket = (Ticket) query.uniqueResult();
            return ticket;
        }catch(NullPointerException e){
            return new Ticket();
        }
    }
    public boolean isTicketAlreadyExist(Long id){
        if(getTicketById(id).getId()!=null){
            return true;
        }else{
            return false;
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
