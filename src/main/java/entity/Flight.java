package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name = "Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "initialairportid")
    private Airport initialAirport;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "finalairportid")
    private Airport finalAirport;

    @Column(name = "initialDate")
    private LocalDateTime initialDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "planeId")
    private Plane plane;

    @ManyToMany
    @JoinTable(
            name = "flight_employee",
            joinColumns=@JoinColumn(name="flightId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId"))
    private List<Employee> employees;

    public Flight()
    {

    }

    public Flight(Long id, Airport initialAirport, Airport finalAirport, LocalDateTime initialDate, Plane plane) {
        this.id = id;
        this.initialAirport = initialAirport;
        this.finalAirport = finalAirport;
        this.initialDate = initialDate;
        this.plane = plane;
    }

    public Flight(Airport initialAirport, Airport finalAirport, LocalDateTime initialDate, Plane plane) {
        this.initialAirport = initialAirport;
        this.finalAirport = finalAirport;
        this.initialDate = initialDate;
        this.plane = plane;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getInitialAirport() {
        return initialAirport;
    }

    public void setInitialAirport(Airport initialAirport) {
        this.initialAirport = initialAirport;
    }

    public Airport getFinalAirport() {
        return finalAirport;
    }

    public void setFinalAirport(Airport finalAirport) {
        this.finalAirport = finalAirport;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", initialAirport=" + initialAirport +
                ", finalAirport=" + finalAirport +
                ", initialDate=" + initialDate +
                ", plane=" + plane +
                '}';
    }
}