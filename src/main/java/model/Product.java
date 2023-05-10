package model;
public class Product {

    private int productID,productQuantity,productPrice;
    private String productName;

    public Product(int productID, String productName, int productQuantity, int productPrice)
    {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
//----------------------------------------------ID

    public int getproductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
//----------------------------------------------Name

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

//----------------------------------------------Price

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    //------------------------------------------Quantity

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }




}