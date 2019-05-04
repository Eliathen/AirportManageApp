package entity;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "flightNumber")
    private Long flightNumber;
    @ManyToMany
    @JoinTable(
            name = "ticket_luggage",
            joinColumns=@JoinColumn(name="ticketid"),
            inverseJoinColumns = @JoinColumn(name = "luggagecode")
    )
    private List<Luggage> luggages;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "passengerid")
    private Passenger passenger;

    public Ticket(){

    }
    public Ticket(Long id, Long flightNumber, List<Luggage> luggages, Passenger passanger){
        this.id = id;
        this.flightNumber = flightNumber;
        this.luggages = luggages;
        this.passenger = passanger;

    }

    public Ticket(Long flightNumber, List<Luggage> luggage, Passenger passanger) {
        this.flightNumber = flightNumber;
        this.luggages = luggages;
        this.passenger = passanger;

    }

    public Long getId(){
        return id;
    }
    public Long getFlightNumber(){
        return flightNumber;
    }
    public List<Luggage> getLuggage(){
        return luggages;
    }
    public Passenger getPassanger(){
        return passenger;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightNumber=" + flightNumber +
                ", luggage=" + luggages +
                ", passenger=" + passenger +
                '}';
    }
}

