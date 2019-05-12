package entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "salary")
    private Float salary;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "airlineId")
    private Airline airline;

    @ManyToMany
    @JoinTable(
            name = "flight_employee",
            joinColumns=@JoinColumn(name="employeeId"),
            inverseJoinColumns = @JoinColumn(name = "flightId"))
    private List<Flight> flights;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, String pesel, String occupation, Float salary, Airline airline) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.occupation = occupation;
        this.salary = salary;
        this.airline = airline;
    }
    public Employee(String name, String surname, String pesel, String occupation, Float salary) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.occupation = occupation;
        this.salary = salary;
//        this.airline = airline;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getOccupation(){
        return occupation;
    }
    public Float getSalary(){
        return salary;
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

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", occupation='" + occupation + '\'' +
                ", salary=" + salary +
                '}';
    }
}
