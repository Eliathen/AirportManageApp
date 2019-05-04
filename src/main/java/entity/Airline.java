package entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "conutry")
    private String country;
    @Column(name = "otherdetails")
    private String details;
    @OneToMany(mappedBy = "airline", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Employee> employees;
    @OneToMany(mappedBy = "airline", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Plane> planes;

    public Airline() {
    }

    public Airline(Long id, String name, String country, String details){
        this.id = id;
        this.name = name;
        this.country = country;
        this.details = details;
    }

    public Airline(String name, String country, String details) {
        this.name = name;
        this.country = country;
        this.details = details;
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
        return details;
    }
    @Override
    public String toString(){
        return "Airline{" +
                "id:" + id + "\n"+
                "name:" + name + "\n"+
                "country:" + country + "\n"+
                "otherDatails:" + details + "\n";
    }
}

