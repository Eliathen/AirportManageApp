package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Luggage")
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "height")
    private Integer height;
    @ManyToMany
    @JoinTable(
            name = "ticket_luggage",
            joinColumns=@JoinColumn(name="luggagecode"),
            inverseJoinColumns = @JoinColumn(name = "ticketid")
    )
    private List<Ticket> tickets;
    public Luggage() {
    }

    public Luggage(Long id, String code, Float weight, Integer height, List<Ticket> tickets) {
        this.id = id;
        this.code = code;
        this.weight = weight;
        this.height = height;
        this.tickets = tickets;
    }

    public Luggage(String code, Float weight, Integer height, List<Ticket> tickets) {
        this.code = code;
        this.weight = weight;
        this.height = height;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Luggage{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", tickets=" + tickets +
                '}';
    }
}
