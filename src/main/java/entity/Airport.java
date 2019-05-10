package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Airport")
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
    @OneToMany(mappedBy = "initialAirport",fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "initialFlightId")
    private List<Flight> initialFlights;
    @OneToMany(mappedBy = "finalAirport",fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "finalFlightId")
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
