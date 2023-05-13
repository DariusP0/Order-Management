package model;
public class Product {

    private int id,productQuantity,productPrice;
    private String productName;
    public Product() {
    }
    public Product(int productId, String productName, int productQuantity, int productPrice)
    {
        this.id = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
    public Product(String productName, int productQuantity, int productPrice)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
//----------------------------------------------ID

    public int getId() {
        return id;
    }

    public void setId(int productId) {
        this.id = productId;
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