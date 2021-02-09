/**
 * @author <Molines, Emmanuel D.>
 * @version 1.0
 * @Date 01/13/2021
 */

package javafx.com.emman.Inventory.list;

public class productList {
    public int ProductCode;
    public String ProductName;
    public String Quantity;
    public String ProductPrice;
    public String Date;

    private productList() {}

    public productList(int id, String lastName, String firstName, String course, String date) {
        this.ProductCode = id;
        this.ProductName = lastName;
        this.Quantity = firstName;
        this.ProductPrice = course;
        this.Date = date;
    }


    public static productList parseStudentCSV(String csv) {
        String[] temp = csv.split(",");

        productList s = new productList();
        s.ProductCode = Integer.parseInt(temp[0].trim());
        s.ProductName = temp[1].trim();
        s.Quantity = temp[2].trim();
        s.ProductPrice = temp[3].trim();
        s.Date = temp[4].trim();
        return s;
    }

    public int getProductCode() {
        return ProductCode;
    }

    public String getProductName() {
        return ProductName.toUpperCase();
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public String getDate() {
        return Date;
    }

    @Override
    public String toString() {
        return ProductCode + "," + ProductName + "," + Quantity + "," + ProductPrice  + "," + Date  ;
    }

    @Override
    public boolean equals(Object obj) {
        productList other = (productList)obj;

        return this.ProductCode == other.ProductCode;
    }
}