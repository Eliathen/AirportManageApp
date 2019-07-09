package gui.modelsFX;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class TicketFX {

    private LongProperty id = new SimpleLongProperty();

    private ObjectProperty<FlightFX> flightFX = new SimpleObjectProperty<>();

    private ObjectProperty<PassengerFX> passangerFX = new SimpleObjectProperty<>();

    private ListProperty<LuggageFX> luggageFXES = new SimpleListProperty<>();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public FlightFX getFlightFX() {
        return flightFX.get();
    }

    public ObjectProperty<FlightFX> flightFXProperty() {
        return flightFX;
    }

    public void setFlightFX(FlightFX flightFX) {
        this.flightFX.set(flightFX);
    }

    public PassengerFX getPassangerFX() {
        return passangerFX.get();
    }

    public ObjectProperty<PassengerFX> passangerFXProperty() {
        return passangerFX;
    }

    public void setPassangerFX(PassengerFX passangerFX) {
        this.passangerFX.set(passangerFX);
    }

    public ObservableList<LuggageFX> getLuggageFXES() {
        return luggageFXES.get();
    }

    public ListProperty<LuggageFX> luggageFXESProperty() {
        return luggageFXES;
    }

    public void setLuggageFXES(ObservableList<LuggageFX> luggageFXES) {
        this.luggageFXES.set(luggageFXES);
    }
}
