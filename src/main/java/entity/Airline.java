package entity;

public class Airline {
    private Long id;
    private String name;
    private String country;
    private String otherDetails;

    public Airline(Long id, String name, String country, String otherDetails){
        this.id = id;
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
    public String getCountryOfOrigin() {
        return country;
    }
    public String getOtherDetails(){
        return otherDetails;
    }
    @Override
    public String toString(){
        return "Airline{" +
                "id:" + id + "\n"+
                "name:" + name + "\n"+
                "country:" + country + "\n"+
                "otherDatails:" + otherDetails + "\n";
    }
}

