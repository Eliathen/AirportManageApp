package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "surname")
    protected String surname;
    @Column(name = "pesel")
    protected String pesel;
    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY,
                cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    protected List<Ticket> tickets;

    public Passenger() {
    }

    public Passenger(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public Passenger(Long id, String name, String surname, String pesel, List<Ticket> tickets){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.tickets = tickets;
    }

    public Passenger(String name, String surname, String pesel, List<Ticket> tickets) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString(){
        return "Person{ " +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "surname = " + surname + '\n' +
                "pesel = " + pesel;
    }
}
