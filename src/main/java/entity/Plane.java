package entity;

public class Plane {
    private Long id;
    private String registrationNumber;
    private Integer modelNumber;
    private String name;
    private Integer capacity;
    private Long weight;

    public Plane(Long id, String registrationNumber, Integer modelNumber, Integer capacity, Long weight){
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.modelNumber = modelNumber;
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
        return "Plane{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", modelNumber=" + modelNumber +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", weight=" + weight +
                '}' ;
    }
}
