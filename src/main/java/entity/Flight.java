package entity;

import sun.util.resources.LocaleData;

import java.util.Date;

public class Flight {
    private Long id;
    private Long airlineId;
    private Integer initialAirlineId;
    private Integer finalAirlineId;
    private LocaleData initialDate;

    public Flight(Long id, Long airlineId, Integer initialAirlineId, Integer finalAirlineId, LocaleData initialDate){
        this.id = id;
        this.airlineId = airlineId;
        this.initialAirlineId = initialAirlineId;
        this.finalAirlineId = finalAirlineId;
        this.initialDate = initialDate;
    }

    public Flight(Long airlineId, Integer initialAirlineId, Integer finalAirlineId, LocaleData initialDate) {
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

    public LocaleData getInitialDate() {
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
