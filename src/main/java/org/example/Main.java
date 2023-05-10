package org.example;

import dao.ClientDAO;
import model.Client;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findById(5);

        System.out.println(client);

//    }
    }
}