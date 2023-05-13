package model;

public class Order {
    public int id;
    public Client client;
    public Product produs;

    public Order(int id, Client client, Product produs) {
        this.id = id;
        this.client = client;
        this.produs = produs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProdus(Product produs) {
        this.produs = produs;
    }

    public Client getClient() {
        return client;
    }

    public int getId() {
        return id;
    }

    public Product getProdus() {
        return produs;
    }
}
