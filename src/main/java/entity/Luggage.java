package entity;

public class Luggage {
    private Long id;
    private String code;
    private Float weight;
    private Integer height;

    public Luggage(Long id, String code, Float weight, Integer height) {
        this.id = id;
        this.code = code;
        this.weight = weight;
        this.height = height;
    }

    public Long getId() {
        return id;
    }
    public String getCode(){return code;}
    public Float getWeight() {
        return weight;
    }
    public Integer getHeight(){
        return height;
    }

    @Override
    public String toString(){
        return "Luggage{" +
                "id:" + id + "\n"+
                "code:" + code + "\n"+
                "weight:" + weight + "\n"+
                "height:" + height + "\n";
    }
}
