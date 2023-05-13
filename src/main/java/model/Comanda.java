package model;

public class Comanda {
    public int id;
    public String client;
    public String produs;
    public int quantity;
    public Comanda(){
    }
    public Comanda(int id, String client, String produs, int q) {
        this.id = id;
        this.client = client;
        this.produs = produs;
        this.quantity = q;
    }
    public Comanda(String client, String produs, int q) {

        this.client = client;
        this.produs = produs;
        this.quantity = q;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public String getClient() {
        return client;
    }

    public int getId() {
        return id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProdus() {
        return produs;
    }

    public int getQuantity() {
        return quantity;
    }

}
