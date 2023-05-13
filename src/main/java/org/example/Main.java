package org.example;

import dao.OrderDAO;
import model.Client;
import model.Comanda;
import model.Product;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Client C = new Client("balint","asdad","3414@gmail.com",21);
        Product p = new Product("apsfpa",214,23);
        OrderDAO o1 = new OrderDAO();
        Comanda o = new Comanda("Balint","asaf",3);
        o1.insert(o);
//    }
    }
}