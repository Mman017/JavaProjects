/**
 * @author <Molines, Emmanuel D.>
 * @version 1.0
 * @Date 01/13/2021
 */
import javafx.application.Application;
import javafx.collections.FXCollections;

import javafx.com.emman.Inventory.Inventory;
import javafx.com.emman.Inventory.list.productList;
import javafx.com.emman.Inventory.login;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;



/*
    Java Inventory project with implementation of Javafx.
    This project allow users to add, delete update, and
    search items in the invetory java applicaiton -(CRUD).
 */

public class app extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        login lg = new login();
        lg.Loginstart(primaryStage);
    }


}
