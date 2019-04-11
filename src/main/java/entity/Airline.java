package entity;

public class Airline {
    private Long id;
    private String name;
    private String country;
    private String details;

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

