package entity;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "plane")
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
    @OneToMany(mappedBy = "plane", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
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

    @Override
    public String toString() {
        return "PlaneDao{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", modelNumber=" + modelNumber +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", weight=" + weight +
                '}' ;
    }
}
