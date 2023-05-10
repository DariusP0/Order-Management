package org.example;

import dao.ClientDAO;
import model.Client;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ClientDAO c = new ClientDAO();
        c.findByEmail("Andrei@gmail.com");
//    }
    }
}