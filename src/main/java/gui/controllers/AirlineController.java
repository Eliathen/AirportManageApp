package gui.controllers;

import entity.Airline;
import entity.Employee;
import entity.Plane;
import exceptions.ApplicationException;
import gui.converters.AirlineConvertTo;
import gui.converters.EmployeeConvertTo;
import gui.converters.PlaneConvertTo;
import gui.modelsFX.*;
import gui.utils.DialogsUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import services.AirlineService;
import services.EmployeeService;
import services.PlaneService;

import java.io.IOException;

public class AirlineController {

    private AirlineModel airlineModel;

    @FXML
    private TreeView<BaseFX> airlinesTreeView;

    @FXML
    private ContextMenu airlineContextMenu;

    @FXML
    private ContextMenu planeContextMenu;

    @FXML
    private ContextMenu employeeContextMenu;

    private MainController controller;

    private TreeItem<BaseFX> treeItem;

    public void initialize(){
        try {
            controller = TopMenuButtonsController.mainController;
            this.airlineModel = new AirlineModel();
            airlineModel.init();
            this.airlinesTreeView.setRoot(airlineModel.getRoot());
            createAirlinesContextMenu();
            createEmployeesContextMenu();
            createPlanesContextMenu();
            airlinesTreeView.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.DELETE) {
                    treeItem = airlinesTreeView.getSelectionModel().getSelectedItem();
                    if (treeItem != null) {
                        if (treeItem.getParent().getValue() != null) {
                            if (treeItem.getParent().getValue().getName().equals("Airlines")) {
                                this.deleteObjectFromDB(Airline.class);
                            }
                            if (treeItem.getParent().getValue().getName().equals("Planes")) {
                                this.deleteObjectFromDB(Plane.class);
                            }
                            if (treeItem.getParent().getValue().getName().equals("Employees")) {
                                this.deleteObjectFromDB(Employee.class);
                            }
                        }
                    }
                }

            });
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void onAction(MouseEvent mouseEvent) {
        treeItem = airlinesTreeView.getSelectionModel().getSelectedItem();
            this.clearAllContextMenu();
            if(treeItem != null) {
                if (treeItem.getValue().getName().equals("Airlines")) {
                    this.airlinesTreeView.setContextMenu(airlineContextMenu);
                }
                if (treeItem.getValue().getName().equals("Planes")) {
                    this.airlinesTreeView.setContextMenu(planeContextMenu);

                }
                if (treeItem.getValue().getName().equals("Employees")) {
                    this.airlinesTreeView.setContextMenu(employeeContextMenu);
                }
                if (treeItem.getParent().getValue() != null) {
                    if (treeItem.getParent().getValue().getName().equals("Airlines")) {
                        this.loadAirlineUpdateForm();
                    }
                    if (treeItem.getParent().getValue().getName().equals("Planes")) {
                        this.loadPlaneUpdateForm();
                    }
                    if (treeItem.getParent().getValue().getName().equals("Employees")) {
                        this.loadEmployeeUpdateForm();
                    }
                }
            }

    }

    private void createAirlinesContextMenu() {
        airlineContextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Add Airline");
        menuItem.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addAirline.fxml"));
            Parent parent = null;
            try{
                parent = (loader.load());
            }catch (IOException e){
                e.printStackTrace();
            }
            controller.getBorderPaneMain().setCenter(parent);
            AddAirlineController addAirlineController = loader.getController();
            addAirlineController.init();
            addAirlineController.setAirlineController(this);
            addAirlineController.setToSave(true);
        });
        airlineContextMenu.getItems().setAll(menuItem);
    }

    public void createEmployeesContextMenu() {
        employeeContextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Add Employee");
        menuItem.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
            Parent parent = null;
            try {
                parent = (loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            controller.getBorderPaneMain().setCenter(parent);
            EmployeeController employeeController = loader.getController();
            employeeController.setAirlineController(this);
            employeeController.init();
            employeeController.getEmployeeModel().getEmployeeFXObjectProperty().setAirlineFX((AirlineFX) treeItem.getParent().getValue());
            employeeController.setToSave(true);
        });
        employeeContextMenu.getItems().setAll(menuItem);
    }

    public void createPlanesContextMenu() {
        planeContextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Add Plane");
        menuItem.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/plane.fxml"));
            Parent parent = null;
            try {
                parent = (loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            controller.getBorderPaneMain().setCenter(parent);
            PlaneController planeController = loader.getController();
            planeController.setAirlineController(this);
            planeController.init();
            planeController.getPlaneModel().getPlaneFXObjectProperty().setAirlineFX((AirlineFX) treeItem.getParent().getValue());
            planeController.setToSave(true);
        });
        planeContextMenu.getItems().setAll(menuItem);
    }

    public void loadAirlineUpdateForm(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addAirline.fxml"));
        Parent parent = null;
        try{
            parent = (loader.load());
        }catch (IOException e){
            e.printStackTrace();
        }
        controller.getBorderPaneMain().setCenter(parent);
        AddAirlineController addAirlineController = loader.getController();
        addAirlineController.init((AirlineFX) treeItem.getValue());
        addAirlineController.setAirlineController(this);
        addAirlineController.setToSave(false);

    }

    public void loadPlaneUpdateForm(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/plane.fxml"));
        Parent parent = null;
        try {
            parent = (loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.getBorderPaneMain().setCenter(parent);
        PlaneController planeController = loader.getController();
        planeController.setAirlineController(this);
        planeController.init((PlaneFX) treeItem.getValue());
        planeController.getPlaneModel().getPlaneFXObjectProperty().setAirlineFX((AirlineFX)treeItem.getParent().getParent().getValue());
        planeController.setToSave(false);
    }

    public void loadEmployeeUpdateForm(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
        Parent parent = null;
        try {
            parent = (loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.getBorderPaneMain().setCenter(parent);
        EmployeeController employeeController = loader.getController();
        employeeController.setAirlineController(this);
        employeeController.init((EmployeeFX) treeItem.getValue());
        employeeController.getEmployeeModel().getEmployeeFXObjectProperty().setAirlineFX((AirlineFX)treeItem.getParent().getParent().getValue());
        employeeController.setToSave(false);
    }

    public <T> void deleteObjectFromDB(Class<T> clazz){
        try {
            if (clazz == Airline.class) {
                AirlineService airlineService = new AirlineService();
                Airline airline = AirlineConvertTo.convertToAirline((AirlineFX) treeItem.getValue());
                airlineService.removeAirlineById(airline.getId());
            }
            if (clazz == Plane.class) {
                PlaneService planeService = new PlaneService();
                Plane plane = PlaneConvertTo.convertToPlane((PlaneFX) treeItem.getValue());
                planeService.removePlaneById(plane.getId());
            }
            if (clazz == Employee.class) {
                EmployeeService employeeService = new EmployeeService();
                Employee employee = EmployeeConvertTo.convertToEmployee((EmployeeFX) treeItem.getValue());
                employeeService.removeEmployee(employee);
            }
            this.airlineModel.init();
        }catch (ApplicationException e){
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public void clearAllContextMenu(){
        airlinesTreeView.setContextMenu(null);
    }
}
