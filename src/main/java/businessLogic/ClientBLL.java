package businessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import businessLogic.EmailValidator;
import businessLogic.ClientAgeValidator;
import businessLogic.Validator;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO ClientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());

        ClientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client st = ClientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Client with id =" + id + " was not found!");
        }
        return st;
    }
}

