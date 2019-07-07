package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "registrationNumber")
    private String registrationNumber;

    @Column(name = "modelNumber")
    private Integer modelNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "weight")
    private Long weight;

    @OneToMany(mappedBy = "plane",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Flight> flights;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "airlineId")
    private Airline airline;

    public Plane() {
    }

    public Plane(Long id, String registrationNumber, Integer modelNumber, Integer capacity, Long weight){
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.modelNumber = modelNumber;
        this.capacity = capacity;
        this.weight = weight;
    }

    public Plane(String registrationNumber, Integer modelNumber, String name, Integer capacity, Long weight) {
        this.registrationNumber = registrationNumber;
        this.modelNumber = modelNumber;
        this.name = name;
        this.capacity = capacity;
        this.weight = weight;
    }

    public Long getId(){
        return id;
    }
    public String getRegistrationNumber(){
        return registrationNumber;
    }
    public Integer getModelNumber(){
        return modelNumber;
    }
    public String getName(){
        return name;
    }
    public Integer getCapacity(){
        return capacity;
    }
    public Long getWeight(){
        return weight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setModelNumber(Integer modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", modelNumber=" + modelNumber +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(id, plane.id) &&
                Objects.equals(registrationNumber, plane.registrationNumber) &&
                Objects.equals(modelNumber, plane.modelNumber) &&
                Objects.equals(name, plane.name) &&
                Objects.equals(capacity, plane.capacity) &&
                Objects.equals(weight, plane.weight) &&
                Objects.equals(flights, plane.flights) &&
                Objects.equals(airline, plane.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, modelNumber, name, capacity, weight, flights, airline);
    }
}
