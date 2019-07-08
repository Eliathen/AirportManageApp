package entity;

import javax.persistence.*;

@Entity
@Table(name = "Luggage")
public class Luggage {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Integer height;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    public Luggage() {
    }

    public Luggage(String code, Float weight, Integer height) {
        this.code = code;
        this.weight = weight;
        this.height = height;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

