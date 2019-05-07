package entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Luggage")
public class Luggage {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "height")
    private Integer height;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH
            }
    )
    @JoinTable(
            name = "ticket_luggage",
            joinColumns=@JoinColumn(name="luggagecode"),
            inverseJoinColumns = @JoinColumn(name = "ticketid")
    )
    private List<Ticket> tickets;
    public Luggage() {
    }

    public Luggage(String code, Float weight, Integer height) {
        this.code = code;
        this.weight = weight;
        this.height = height;
    }
    public String getCode(){return code;}
    public Float getWeight() {
        return weight;
    }
    public Integer getHeight(){
        return height;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket){
        if(tickets == null){
            tickets = new LinkedList<Ticket>();
        }
        else{
            tickets.add(ticket);
        }
    }

    @Override
    public String toString() {
        return "Luggage{" +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
