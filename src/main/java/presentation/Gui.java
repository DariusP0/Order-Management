package presentation;
import dao.*;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
public class Gui {
    private JFrame frame;
    private JTable clientsTable;
    private JTable productsTable;
    private JTextArea textArea;
    public ClientDAO clientDAO;
    public Gui() {
        clientDAO = new ClientDAO();
        frame = new JFrame("Order Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the tabs for different operations
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Client Operations", createClientOperationsPanel());
        tabbedPane.addTab("Product Operations", createProductOperationsPanel());
        tabbedPane.addTab("Create Product Orders", createProductOrdersPanel());

        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createClientOperationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create the table for displaying clients
        clientsTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Client ID");
        model.addColumn("Client Name");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Age");
        clientsTable.setModel(model);

        // Create text fields for client data entry
        JTextField idTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        JTextField addressTextField = new JTextField();
        JTextField emailTextField = new JTextField();
        JTextField ageTextField = new JTextField();

        // Create buttons for client operations
        JButton addClientButton = new JButton("Add Client");
        JButton editClientButton = new JButton("Edit Client");
        JButton deleteClientButton = new JButton("Delete Client");

        // Set smaller button size
        Dimension buttonSize = new Dimension(100, 30);
        addClientButton.setPreferredSize(buttonSize);
        editClientButton.setPreferredSize(buttonSize);
        deleteClientButton.setPreferredSize(buttonSize);

        // Add buttons and table to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addClientButton);
        buttonPanel.add(editClientButton);
        buttonPanel.add(deleteClientButton);

        panel.add(new JScrollPane(clientsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        List<Client> clientList = clientDAO.findAll();
        for (Client client : clientList) {
            if (client != null) {
                Object[] rowData = {
                        client.getId(),
                        client.getName(),
                        client.getAddress(),
                        client.getEmail(),
                        client.getAge()
                };
                model.addRow(rowData);
            }
        }

        // Add action listeners for the buttons (implement your logic here)
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add client button click

                model.setRowCount(0);

                String name = nameTextField.getText();
                String address = addressTextField.getText();
                String email = emailTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                if(!idTextField.getText().isEmpty()){
                    try{
                        int id = Integer.parseInt(idTextField.getText());
                        Client c = new Client(id,name,address,email,age);clientDAO.insert(c);
                    }
                    catch (NumberFormatException ex) {
                        // Handle invalid ID format
                        JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
              }
                else{ Client c = new Client(name,address,email,age);clientDAO.insert(c);}

                // Add the data to the table
                List<Client> clientList = clientDAO.findAll();
                for (Client client : clientList) {
                    if (client != null) {
                        Object[] rowData = {
                                client.getId(),
                                client.getName(),
                                client.getAddress(),
                                client.getEmail(),
                                client.getAge()
                        };
                        model.addRow(rowData);
                    }
                }

                // Clear the text fields
                idTextField.setText("");
                nameTextField.setText("");
                addressTextField.setText("");
                emailTextField.setText("");
                ageTextField.setText("");
            }
        });

        editClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                String name = nameTextField.getText();
                String address = addressTextField.getText();
                String email = emailTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                // Handle edit client button click
                if(!idTextField.getText().isEmpty()){
                    try{
                        int id = Integer.parseInt(idTextField.getText());
                        Client c = new Client(id,name,address,email,age);
                        clientDAO.update(c);;
                    }
                    catch (NumberFormatException ex) {
                        // Handle invalid ID format
                        JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                List<Client> clientList = clientDAO.findAll();
                for (Client client : clientList) {
                    if (client != null) {
                        Object[] rowData = {
                                client.getId(),
                                client.getName(),
                                client.getAddress(),
                                client.getEmail(),
                                client.getAge()
                        };
                        model.addRow(rowData);
                    }
                }

                // Clear the text fields
                idTextField.setText("");
                nameTextField.setText("");
                addressTextField.setText("");
                emailTextField.setText("");
                ageTextField.setText("");
            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                // Handle edit client button click
                if(!idTextField.getText().isEmpty()){
                    try{
                        int id = Integer.parseInt(idTextField.getText());
                        clientDAO.deleteById(id);
                    }
                    catch (NumberFormatException ex) {
                        // Handle invalid ID format
                        JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                List<Client> clientList = clientDAO.findAll();
                for (Client client : clientList) {
                    if (client != null) {
                        Object[] rowData = {
                                client.getId(),
                                client.getName(),
                                client.getAddress(),
                                client.getEmail(),
                                client.getAge()
                        };
                        model.addRow(rowData);
                    }
                }

                // Clear the text fields
                idTextField.setText("");
            }
        });

        // Create a panel for the text fields
        JPanel textFieldPanel = new JPanel(new GridLayout(5, 2));
        textFieldPanel.add(new JLabel("Client ID:"));
        textFieldPanel.add(idTextField);
        textFieldPanel.add(new JLabel("Client Name:"));
        textFieldPanel.add(nameTextField);
        textFieldPanel.add(new JLabel("Address:"));
        textFieldPanel.add(addressTextField);
        textFieldPanel.add(new JLabel("Email:"));
        textFieldPanel.add(emailTextField);
        textFieldPanel.add(new JLabel("Age:"));
        textFieldPanel.add(ageTextField);

        // Add the text field panel to the main panel
        panel.add(textFieldPanel, BorderLayout.NORTH);

        return panel;
    }


    private JPanel createProductOperationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create the table for displaying products
        productsTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Product Name");
        model.addColumn("Stock Quantity");
        model.addColumn("Price");
        productsTable.setModel(model);

        // Create buttons for product operations
        JButton addProductButton = new JButton("Add Product");
        JButton editProductButton = new JButton("Edit Product");
        JButton deleteProductButton = new JButton("Delete Product");
        Dimension buttonSize = new Dimension(100, 30);
        // Set smaller button size
        addProductButton.setPreferredSize(buttonSize);
        editProductButton.setPreferredSize(buttonSize);
        deleteProductButton.setPreferredSize(buttonSize);

        // Add buttons and table to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addProductButton);
        buttonPanel.add(editProductButton);
        buttonPanel.add(deleteProductButton);

        panel.add(new JScrollPane(productsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons (implement your logic here)
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add product button click
            }
        });

        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit product button click
            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete product button click
            }
        });

        return panel;
    }

    private JPanel createProductOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create dropdowns for selecting client and product
        JComboBox<String> clientDropdown = new JComboBox<>();
        JComboBox<String> productDropdown = new JComboBox<>();

        // Create text field for quantity input
        JTextField quantityTextField = new JTextField();

        // Create button for creating orders
        JButton createOrderButton = new JButton("Create Order");
        Dimension buttonSize = new Dimension(100, 30);
        // Set smaller button size
        createOrderButton.setPreferredSize(buttonSize);

        // Add dropdowns, text field, and button to the panel
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Select Client:"));
        formPanel.add(clientDropdown);
        formPanel.add(new JLabel("Select Product:"));
        formPanel.add(productDropdown);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantityTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createOrderButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the create order button (implement your logic here)
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create order button click
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
                OrderDAO O = new OrderDAO();
            }
        });
    }
}

