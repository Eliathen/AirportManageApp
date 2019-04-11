package entity;

public class Airport {
    private Integer id;
    private String name;
    private String address;
    private String city;

    public Airport(int id, String name, String address, String city) {
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
    public int getId(){ return id; }
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
