package gui.modelsFX;

import entity.Airline;
import exceptions.ApplicationException;
import gui.converters.AirlineConvertTo;
import gui.converters.EmployeeConvertTo;
import gui.converters.PlaneConvertTo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import services.AirlineService;

import java.util.List;


public class AirlineModel {
    private ObservableList<AirlineFX> airlineFXObservableList = FXCollections.observableArrayList();

    private TreeItem<BaseFX> root = new TreeItem<BaseFX>();

    public void init() throws ApplicationException {
        AirlineService airlineService = new AirlineService();
        List<Airline> airlines = airlineService.getAllAirlines();
        for(Airline a: airlines){
            a = airlineService.getEmployees(a);
        }
        for(Airline a: airlines){
            a = airlineService.getPlanes(a);
        }
        initRoots(airlines);

    }
    private void initRoots(List<Airline> airlines) {
            StringProperty stringProperty = new SimpleStringProperty("Airlines");
            TreeItem<BaseFX> allAirlinesFXTreeItem = new TreeItem<>(new BaseFX(stringProperty));
            allAirlinesFXTreeItem.setExpanded(true);
            airlines.forEach(a -> {
                TreeItem<BaseFX> airlineFXTreeItem = new TreeItem<>(AirlineConvertTo.convertToAirlineFX(a));
                airlineFXTreeItem.setExpanded(true);
                    StringProperty stringProperty1 = new SimpleStringProperty("Planes");
                    TreeItem<BaseFX> planes = new TreeItem<>(new BaseFX(stringProperty1));
                    airlineFXTreeItem.getChildren().add(planes);
                    a.getPlanes().forEach(b -> {
                        planes.getChildren().add(new TreeItem<>(PlaneConvertTo.convertToPlaneFX(b)));
                    });
                    StringProperty stringProperty2 = new SimpleStringProperty("Employees");
                    TreeItem<BaseFX> employees = new TreeItem<>(new BaseFX(stringProperty2));
                    airlineFXTreeItem.getChildren().add(employees);
                    a.getEmployees().forEach(b -> {
                        employees.getChildren().add(new TreeItem<>(EmployeeConvertTo.convertToEmpolyeeFX(b)));
                    });

                allAirlinesFXTreeItem.getChildren().addAll(airlineFXTreeItem);

            });
            root.getChildren().setAll(allAirlinesFXTreeItem);
    }

    public ObservableList<AirlineFX> getAirlineFXObservableList() {
        return airlineFXObservableList;
    }

    public void setAirlineFXObservableList(ObservableList<AirlineFX> airlineFXObservableList) {
        this.airlineFXObservableList = airlineFXObservableList;
    }

    public TreeItem<BaseFX> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<BaseFX> root) {
        this.root = root;
    }
}