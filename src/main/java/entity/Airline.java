package entity;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
    @Column(name = "otherDetails")
    private String otherDetails;
    @OneToMany(mappedBy = "airline", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Employee> employees;
    @OneToMany(mappedBy = "airline", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Plane> planes;

    public Airline() {
    }

    public Airline(Long id, String name, String country, String otherDetails){
        this.id = id;
        this.name = name;
        this.country = country;
        this.otherDetails = otherDetails;
    }

    public Airline(String name, String country, String otherDetails) {
        this.name = name;
        this.country = country;
        this.otherDetails = otherDetails;
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCountry() {
        return country;
    }
    public String getDetails(){
        return otherDetails;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public void addEmployee(Employee employee){
        if(employees == null){
            employees = new LinkedList<Employee>();
            employees.add(employee);
        }else{
            employees.add(employee);
        }
    }
    public void addPlane(Plane plane){
        if(planes == null){
            planes = new LinkedList<Plane>();
            planes.add(plane);
        }else{
            planes.add(plane);
        }
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", otherDetails='" + otherDetails + '\'' +
                ", employees=" + employees +
                ", planes=" + planes +
                '}';
    }
}

