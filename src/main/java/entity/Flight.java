package entity;

import java.util.Date;

public class Flight {
    private Long id;
    private Long airlineId;
    private Integer initialAirlineId;
    private Integer finalAirlineId;
    private Date initialDate;

    public Flight(Long id, Long airlineId, Integer initialAirlineId, Integer finalAirlineId, Date initialDate){
        this.id = id;
        this.airlineId = airlineId;
        this.initialAirlineId = initialAirlineId;
        this.finalAirlineId = finalAirlineId;
        this.initialDate = initialDate;
    }

    public Long getId() {
        return id;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public Integer getInitialAirlineId() {
        return initialAirlineId;
    }

    public Integer getFinalAirlineId() {
        return finalAirlineId;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineId=" + airlineId +
                ", initialAirlineId=" + initialAirlineId +
                ", finalAirlineId=" + finalAirlineId +
                ", initialDate=" + initialDate +
                '}' ;
    }
}
