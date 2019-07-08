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

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "FlightNumber")
    private Flight flight;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "ticket", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Luggage> luggages;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "passengerId")
    private Passenger passenger;

    public Ticket(){

    }

    public Ticket(Long id, Flight flight, List<Luggage> luggages, Passenger passenger){
        this.id = id;
        this.flight = flight;
        this.luggages = luggages;
        this.passenger = passenger;

    }

    public Ticket(Flight flight, List<Luggage> luggages, Passenger passenger) {
        this.flight = flight;
        this.luggages = luggages;
        this.passenger = passenger;

    }

    public Long getId(){
        return id;
    }

    public List<Luggage> getLuggage(){
        return luggages;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setId(Long id) {
        this.id = id;
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
            luggage.setTicket(this);
        }else{
            luggages.add(luggage);
            luggage.setTicket(this);
        }
    }
}