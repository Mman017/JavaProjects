/**
 * @author <Molines, Emmanuel D.>
 * @version 1.0, 3/25/2020
 */
package javafx.com.emman.Inventory;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.com.emman.Inventory.list.productList;

import static javafx.com.emman.Inventory.database.fileReader.readFile;
import static javafx.com.emman.Inventory.database.fileReader.toFile;


/*
     This class is used to create the GUI of the Inventory application.
 */

public class Inventory {
    ObservableList<productList> studentObservableList  = FXCollections.observableArrayList();;
    TableView<productList> studentTableView;

    public void load() {
        ArrayList<productList> tempStudList = new ArrayList();
        readFile(tempStudList);
        for (productList s : tempStudList) {
            studentObservableList.add(s);
        }
        for(int i = 0;i<studentObservableList.size();i++){
        }

    }

    public void add(int productCode, String productName, String quantity, String price,String date) {
        productList s = new productList(productCode, productName, quantity, price,date);
        studentObservableList.add(s);
        toFile(studentObservableList);

    }

    public String delete(int productCode) {
        productList s = productList.parseStudentCSV(productCode + ",productName,quantity,price,0");
        int index = studentObservableList.indexOf(s);
        String csv = null;

        if (index != -1) {
            productList deletedItem = studentObservableList.remove(index);
            toFile(studentObservableList);
            csv = deletedItem.toString();

        }

        return csv;
    }

    public TableView createStudentTableView() {
        // create the table columns
        TableColumn<productList, Integer> idColumn = new TableColumn("ProductCode");
        idColumn.setCellValueFactory(new PropertyValueFactory("ProductCode"));
        idColumn.setPrefWidth(100);

        TableColumn<productList, String> lastNameColumn = new TableColumn("Product Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory("ProductName"));
        lastNameColumn.setPrefWidth(200);


        TableColumn<productList, String> firstNameColumn = new TableColumn("Quantity");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory("Quantity"));
        firstNameColumn.setPrefWidth(100);

        TableColumn<productList, String> courseColumn = new TableColumn("Product Price");
        courseColumn.setCellValueFactory(new PropertyValueFactory("ProductPrice"));
        courseColumn.setPrefWidth(100);

        TableColumn<productList, String> dateColumn = new TableColumn("Date Edited");
        dateColumn.setCellValueFactory(new PropertyValueFactory("Date"));
        dateColumn.setPrefWidth(200);

        studentTableView = new TableView();
        studentTableView.getColumns().addAll(idColumn, lastNameColumn, firstNameColumn, courseColumn,dateColumn);
        studentTableView.setOnKeyPressed(
                keyEvent -> {
                    switch (keyEvent.getCode()) {
                        case DELETE:

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


                            alert.setHeaderText("Are you sure you want to delete this product?");
                            alert.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {
                                delete(studentTableView.getSelectionModel().getSelectedItem().getProductCode());
                                toFile(studentObservableList);
                            });
                            break;

                        case ALT:

                            Stage editProductStage = editProduct(studentTableView.getSelectionModel().getSelectedItem().getProductCode());
                            editProductStage.show();
                            break;

                    }
                }
        );

        return studentTableView;
    }

    public Stage createAddNewStudentStage() {
        Stage stage;
        Scene scene;

        GridPane pane = new GridPane();
        scene = new Scene(pane);
        stage = new Stage();

        TextField productCode = new TextField();
        TextField productName = new TextField();
        TextField  quantity= new TextField();
        TextField price = new TextField();

        Button okButton = new Button("Ok");
        okButton.setOnAction(
                actionEvent -> {
                    if (productCode.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error: Product code Missing");
                        alert.setContentText("Please enter enter a unqie Product Code");
                        alert.showAndWait();
                        return;
                    }

                    for (int i = 0;i <studentObservableList.size();i++) {
                        if (productCode.getText().equals(String.valueOf(studentObservableList.get(i).getProductCode()))) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Error: Product code already exist");
                            alert.setContentText("Please choose a unique product code");
                            alert.showAndWait();
                            return;
                        }
                    }
                    if (productName.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error: Product Name Missing");
                        alert.setContentText("Please input the  Product Name");
                        alert.showAndWait();
                        return;
                    }


                    if (price.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error: Product Price Missing");
                        alert.setContentText("Please input the Product Price");
                        alert.showAndWait();
                        return;

                    } else {
                        try {
                            int pricep = Integer.parseInt(price.getText());

                            if (pricep < 0) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText("Error: Product Price must be positive integer");
                                alert.setContentText("Please input only a number for Product price");
                                alert.showAndWait();
                                return;

                            }
                        } catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Error: Product Price must be positive integer");
                            alert.setContentText("Please input only a number for Product price");
                            alert.showAndWait();
                            return;
                        }

                        if (quantity.getText().isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Error: Product Quantity is Missing");
                            alert.setContentText("Please input the Product Quantity");
                            alert.showAndWait();
                            return;

                        } else {
                            try {
                                int quantityq = Integer.parseInt(quantity.getText());

                                if (quantityq < 0) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setHeaderText("Error: Product Quantity must be positive integer");
                                    alert.setContentText("Please input only a number for Product Quantity");
                                    alert.showAndWait();
                                    return;

                                }
                            } catch (Exception e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText("Error: Product Quantity must be positive integer");
                                alert.setContentText("Please input only a number for Product Quantity");
                                alert.showAndWait();
                                return;
                            }
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Are you sure you want to add this product?");

                        alert.setContentText("ProductCode: " +productCode.getText() +"\n"
                                + "Product Name: "+ productName.getText() +"\n"+
                                "Quantity: "+ quantity.getText() +"\n" +
                                "Price: " + price.getText());

                        alert.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date = new Date();

                            add(Integer.parseInt(productCode.getText()),
                                    productName.getText(),
                                    price.getText(),
                                    quantity.getText(),
                                    formatter.format(date)
                            );
                            toFile(studentObservableList);
                            Alert al = new Alert(Alert.AlertType.INFORMATION);
                            al.setHeaderText("Input accepted!");
                            al.setContentText("New product added!");
                            al.showAndWait();
                            stage.close();
                        });
                    }
                }
        );


        pane.add(new Label("Product Code"), 0, 0);
        pane.add(productCode, 1, 0);
        pane.add(new Label("Product Name"), 0, 1);
        pane.add(productName, 1, 1);
        pane.add(new Label("Price"), 0, 2);
        pane.add(price, 1, 2);
        pane.add(new Label("Quantity"), 0, 3);
        pane.add(quantity, 1, 3);
        pane.add(okButton, 2,3,4,5);

        pane.setPadding(new Insets(3, 5, 3, 5));
        pane.setVgap(3);
        pane.setHgap(5);

        stage.setTitle("Add New Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.close();

        return stage;
    }

    public Stage editProduct(int productCodeee) {
        Stage stage;
        Scene scene;

        TextField productCode = new TextField();
        TextField productName = new TextField();
        TextField  quantity= new TextField();
        TextField price = new TextField();

        productList s = productList.parseStudentCSV(productCodeee + ",productName,quantity,price,0");
        int index = studentObservableList.indexOf(s);
        productCode.setText(String.valueOf(studentObservableList.get(index).getProductCode()));
        productName.setText(studentObservableList.get(index).getProductName());
        price.setText(studentObservableList.get(index).getProductPrice());
        quantity.setText(studentObservableList.get(index).getQuantity());


        Button okButton = new Button("Ok");
        okButton.setOnAction(
                actionEvent -> {
                    if (productCode.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You forgot to enter a Code!");
                        alert.showAndWait();
                        return;
                    }

                    for (int i = 0;i <studentObservableList.size();i++) {
                        if (productCode.getText().equals(String.valueOf(studentObservableList.get(i).getProductCode()))) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Product code already exist. Please choose a unique product code");
                            alert.showAndWait();
                            return;
                        }
                    }

                    if (productName.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You forgot to enter a ProductName!");
                        alert.showAndWait();
                        return;
                    }


                    if (price.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("PRICE is empty!");
                        alert.showAndWait();
                        return;

                    } else {
                        try {
                            int pricep = Integer.parseInt(price.getText());
                            if (pricep < 0) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Price must be positive integer");
                                alert.showAndWait();
                                return;

                            }
                        } catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Price must be a number");
                            alert.showAndWait();
                            return;
                        }

                        if (quantity.getText().isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setGraphic(null);
                            alert.setHeaderText(null);
                            alert.setContentText("Quantity is empty!");
                            alert.showAndWait();
                            return;

                        } else {
                            try {
                                int quantityq = Integer.parseInt(quantity.getText());

                                if (quantityq < 0) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("Quantity must be positive");
                                    alert.showAndWait();
                                    return;

                                }
                            } catch (Exception e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("quantity must be a number");
                                alert.showAndWait();
                                return;
                            }
                        }

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Are you sure you want to Edit this product?");

                        alert.setContentText("ProductCode: " +productCode.getText() +"\n"
                                + "Product Name: "+ productName.getText() +"\n"+
                                "Quantity: "+ quantity.getText() +"\n" +
                                "Price: " + price.getText());

                        alert.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {

                            ArrayList<productList> prod = new ArrayList<>();
                            readFile(prod);
                            String csv = null; // comma separated values representing the student info deleted from the list

                            if (index != -1) {

                                productList deletedItem = studentObservableList.remove(index);
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date date = new Date();
                                add(Integer.parseInt(productCode.getText()),
                                        productName.getText(),
                                        price.getText(),
                                        quantity.getText(),
                                        formatter.format(date)
                                );
                                toFile(studentObservableList);
                                csv = deletedItem.toString();
                                toFile(studentObservableList);

                            }
                        });
                    }
                }
        );



        GridPane pane = new GridPane();
        pane.add(new Label("Product Code"), 0, 0);
        pane.add(productCode, 1, 0);
        pane.add(new Label("Product Name"), 0, 1);
        pane.add(productName, 1, 1);
        pane.add(new Label("Price"), 0, 2);
        pane.add(price, 1, 2);
        pane.add(new Label("Quantity"), 0, 3);
        pane.add(quantity, 1, 3);
        pane.add(okButton, 2,3,4,5);

        pane.setPadding(new Insets(3, 5, 3, 5));
        pane.setVgap(3);
        pane.setHgap(5);

        scene = new Scene(pane);
        stage = new Stage();

        stage.setTitle("Add New Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        return stage;
    }

    public void inventoryStart(Stage primaryStage) throws Exception {

        // create the GUI
        TableView<productList> studentTableView = createStudentTableView();

        Stage studentStage = createAddNewStudentStage();
        Button addButton = new Button("Add New Product");
        addButton.setOnAction(
                actionEvent -> {
                    studentStage.show();
                }
        );

        Button edit = new Button("Edit Product");
        edit.setOnAction(
                actionEvent -> {
                    studentStage.show();
                }
        );




        TextField textField = new TextField();

        textField.setPromptText("Search here!");
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("ProductCode", "ProductName", "Quantity","ProductPrice");
        choiceBox.setValue("ProductCode");


        FilteredList<productList> flPerson = new FilteredList<>(studentObservableList, p -> true);
        //  FilteredList<productList> flPerson = new FilteredList(studentObservableList, p -> true);//Pass the data to a filtered list
        studentTableView.setItems(flPerson);//Set the table's items using the filtered list


        textField.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "ProductCode":
                    flPerson.setPredicate(p -> Integer.toString(p.getProductCode()).toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "ProductName":
                    flPerson.setPredicate(p -> p.getProductName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                textField.setText("");
                flPerson.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(choiceBox, textField);

        BorderPane root = new BorderPane();
        root.setCenter(studentTableView);
        root.setBottom(addButton);
        root.setTop(hbox);
        primaryStage.setTitle("Product Information");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        load();

    }


}

