package entity;

public class Ticket {
    private Long id;
    private Long flightNumber;
    private Long luggageId;
    private Long passangerId;

    public Ticket(Long id, Long flightNumber, Long luggageId, Long passangerId){
        this.id = id;
        this.flightNumber = flightNumber;
        this.luggageId = luggageId;
        this.passangerId = passangerId;

    }

    public Ticket(Long flightNumber, Long luggageId, Long passangerId) {
        this.flightNumber = flightNumber;
        this.luggageId = luggageId;
        this.passangerId = passangerId;
    }

    public Long getId(){
        return id;
    }
    public Long getFlightNumber(){
        return flightNumber;
    }
    public Long getLuggageId(){
        return luggageId;
    }
    public Long getPassangerId(){
        return passangerId;
    }

    @Override
    public String toString() {
        return "TicketDao{" +
                "id=" + id +
                ", flightNumber=" + flightNumber +
                ", luggageId=" + luggageId +
                ", passangerId=" + passangerId +
                '}' ;
    }
}

