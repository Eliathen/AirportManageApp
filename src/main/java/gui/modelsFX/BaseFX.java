package gui.modelsFX;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class BaseFX{

    protected LongProperty id = new SimpleLongProperty();

    protected StringProperty name = new SimpleStringProperty();

    public BaseFX() {
    }

    public BaseFX(StringProperty name) {
        this.name = name;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString(){
        return getName();
    }
}
