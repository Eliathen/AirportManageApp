package gui.modelsFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class AddLuggageModel {

    private ObjectProperty<LuggageFX> luggageFXObjectProperty;
    
    public AddLuggageModel(){
        luggageFXObjectProperty = new SimpleObjectProperty<>(new LuggageFX());
    }

    public LuggageFX getLuggageFXObjectProperty() {
        return luggageFXObjectProperty.get();
    }

    public ObjectProperty<LuggageFX> luggageFXObjectPropertyProperty() {
        return luggageFXObjectProperty;
    }

    public void setLuggageFXObjectProperty(LuggageFX luggageFXObjectProperty) {
        this.luggageFXObjectProperty.set(luggageFXObjectProperty);
    }
}
