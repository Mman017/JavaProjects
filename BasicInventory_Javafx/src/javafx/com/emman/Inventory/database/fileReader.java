package javafx.com.emman.Inventory.database;

import javafx.collections.ObservableList;
import javafx.com.emman.Inventory.list.productList;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/*
     This class is used to read and or save the input items to the database csv file.
 */
public class fileReader {

    public static List<productList> readFile(ArrayList<productList> products) {

        List <productList> product = new ArrayList<>();

        boolean loop = true;
        do {
            try {
                try {
                    try {
                        Scanner textFileReader = new Scanner(new File(".\\src\\resources\\fileInventory\\Products.csv"));
                        while (textFileReader.hasNextLine()) {
                            String contents = textFileReader.nextLine();

                            String[] Separator = contents.split(",");
                            int productCode = Integer.parseInt(Separator[0]);
                            String productTitle = Separator[1];
                            String productQuantity = Separator[2];
                            String productPrice = Separator[3];
                            String date = Separator[4];

                            products.add(new productList(productCode, productTitle, productQuantity, productPrice,date));

                            loop = true;


                        }

                        textFileReader.close();
                    } catch (FileNotFoundException e) {
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }catch (InputMismatchException e){

            }
            return  product;

        }while(loop!=false);



    }

    public static void toFile(ObservableList<productList> product) {
        try {
            File file = new File(".\\src\\resources\\fileInventory\\Products.csv");
            FileWriter collection = new FileWriter(file, false);
            String info = "";

            for (int i = 0; i < product.size(); i++) {
                info = "";
                info += product.get(i).getProductCode();
                info += ",";
                info += product.get(i).getProductName();
                info += ",";
                info += product.get(i).getQuantity();
                info += ",";
                info += product.get(i).getProductPrice();
                info += ",";
                info += product.get(i).getDate();


                collection.write(info);
                collection.write("\r\n");
            }
            collection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
