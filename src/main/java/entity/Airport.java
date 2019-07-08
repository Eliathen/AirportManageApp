package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AIRPORT")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy="initialAirport", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Flight> initialFlights;
   
    @OneToMany(mappedBy="finalAirport", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Flight> finalFlights;

    public Airport() {
    }

    public Airport(Long id, String name, String address, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Airport(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }
    public Long getId(){ return id; }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getCity(){
        return city;
    }

    public List<Flight> getInitialFlights() {
        return initialFlights;
    }

    public void setInitialFlights(List<Flight> initialFlights) {
        this.initialFlights = initialFlights;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public List<Flight> getFinalFlights() {
        return finalFlights;
    }
    public void setFinalFlights(List<Flight> finalFlights) {
        this.finalFlights = finalFlights;
    }
    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}' ;
    }
}
