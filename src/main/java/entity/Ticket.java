package entity;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "FlightNumber")
    private Flight flight;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ticket_luggage",
            joinColumns=@JoinColumn(name="ticketid"),
            inverseJoinColumns = @JoinColumn(name = "luggagecode")
    )
    private List<Luggage> luggages;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "passengerid")
    private Passenger passenger;

    public Ticket(){

    }
    public Ticket(Long id, Flight flight, List<Luggage> luggages, Passenger passanger){
        this.id = id;
        this.flight = flight;
        this.luggages = luggages;
        this.passenger = passanger;

    }

    public Ticket(Flight flight, List<Luggage> luggage, Passenger passanger) {
        this.flight = flight;
        this.luggages = luggages;
        this.passenger = passanger;

    }

    public Long getId(){
        return id;
    }
    public Flight getFlightNumber(){
        return flight;
    }
    public List<Luggage> getLuggage(){
        return luggages;
    }
    public Passenger getPassanger(){
        return passenger;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlightNumber(Flight flight) {
        this.flight = flight;
    }

    public List<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(List<Luggage> luggages) {
        this.luggages = luggages;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void addLuggage(Luggage luggage){
        if(luggages == null){
            luggages = new LinkedList<Luggage>();
            luggages.add(luggage);
        }else{
            luggages.add(luggage);
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flight=" + flight +
                ", luggage=" + luggages +
                ", passenger=" + passenger +
                '}';
    }
}

