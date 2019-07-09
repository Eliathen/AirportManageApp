package gui.modelsFX;

import entity.Ticket;
import javafx.beans.property.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LuggageFX {

    private StringProperty code = new SimpleStringProperty();

    private FloatProperty weight = new SimpleFloatProperty();

    private IntegerProperty height = new SimpleIntegerProperty();

    private ObjectProperty<TicketFX> ticket = new SimpleObjectProperty<>();

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public float getWeight() {
        return weight.get();
    }

    public FloatProperty weightProperty() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight.set(weight);
    }

    public int getHeight() {
        return height.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public TicketFX getTicket() {
        return ticket.get();
    }

    public ObjectProperty<TicketFX> ticketProperty() {
        return ticket;
    }

    public void setTicket(TicketFX ticket) {
        this.ticket.set(ticket);
    }

    @Override
    public String toString() {
        return getCode();
    }
}
