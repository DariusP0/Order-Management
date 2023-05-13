package presentation;
import dao.*;
import model.*;

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
    public ProductDAO productDAO;
    public OrderDAO orderDAO;
    public Gui() {
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
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
        model.addColumn("Price");
        model.addColumn("Stock Quantity");
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
        JTextField idTextField = new JTextField();
        JTextField nameTextField = new JTextField();
        JTextField priceTextField = new JTextField();
        JTextField quantityTextField = new JTextField();
        panel.add(new JScrollPane(productsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        List<Product> productList = productDAO.findAll();
        for (Product product : productList) {
            if (product != null) {
                Object[] rowData = {
                        product.getId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getProductQuantity()
                };
                model.addRow(rowData);
            }
        }
        // Add action listeners for the buttons (implement your logic here)
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add product button click
                model.setRowCount(0);

                String name = nameTextField.getText();
                int price = Integer.parseInt(priceTextField.getText());
                int quantity = Integer.parseInt(quantityTextField.getText());
                if(!idTextField.getText().isEmpty()){
                    try{
                        int id = Integer.parseInt(idTextField.getText());
                        Product p = new Product(id,name,price,quantity);productDAO.insert(p);
                    }
                    catch (NumberFormatException ex) {
                        // Handle invalid ID format
                        JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else{ Product p = new Product(name,price,quantity);productDAO.insert(p);}

                // Add the data to the table
                List<Product> productList = productDAO.findAll();
                for (Product product : productList) {
                    if (product != null) {
                        Object[] rowData = {
                                product.getId(),
                                product.getProductName(),
                                product.getProductPrice(),
                                product.getProductQuantity()
                        };
                        model.addRow(rowData);
                    }
                }

                // Clear the text fields
                idTextField.setText("");
                nameTextField.setText("");
                priceTextField.setText("");
                quantityTextField.setText("");
            }
        });

        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit product button click
                model.setRowCount(0);

                String name = nameTextField.getText();
                int price = Integer.parseInt(priceTextField.getText());
                int quantity = Integer.parseInt(quantityTextField.getText());
                if(!idTextField.getText().isEmpty()){
                    try{
                        int id = Integer.parseInt(idTextField.getText());
                        Product p = new Product(id,name,price,quantity);
                        productDAO.update(p);
                    }
                    catch (NumberFormatException ex) {
                        // Handle invalid ID format
                        JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Add the data to the table
                List<Product> productList = productDAO.findAll();
                for (Product product : productList) {
                    if (product != null) {
                        Object[] rowData = {
                                product.getId(),
                                product.getProductName(),
                                product.getProductPrice(),
                                product.getProductQuantity()
                        };
                        model.addRow(rowData);
                    }
                }

                // Clear the text fields
                idTextField.setText("");
                nameTextField.setText("");
                priceTextField.setText("");
                quantityTextField.setText("");

            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete product button click
                model.setRowCount(0);

                if(!idTextField.getText().isEmpty()){
                    try{
                        int id = Integer.parseInt(idTextField.getText());
                        productDAO.deleteById(id);
                    }
                    catch (NumberFormatException ex) {
                        // Handle invalid ID format
                        JOptionPane.showMessageDialog(frame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Add the data to the table
                List<Product> productList = productDAO.findAll();
                for (Product product : productList) {
                    if (product != null) {
                        Object[] rowData = {
                                product.getId(),
                                product.getProductName(),
                                product.getProductPrice(),
                                product.getProductQuantity()
                        };
                        model.addRow(rowData);
                    }
                }

                // Clear the text fields
                idTextField.setText("");

            }
        });
        JPanel textFieldPanel = new JPanel(new GridLayout(5, 2));
        textFieldPanel.add(new JLabel("Product ID:"));
        textFieldPanel.add(idTextField);
        textFieldPanel.add(new JLabel("Product Name:"));
        textFieldPanel.add(nameTextField);
        textFieldPanel.add(new JLabel("Price:"));
        textFieldPanel.add(priceTextField);
        textFieldPanel.add(new JLabel("Quantity:"));
        textFieldPanel.add(quantityTextField);

        // Add the text field panel to the main panel
        panel.add(textFieldPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createProductOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create dropdowns for selecting client and product
        JComboBox<String> clientDropdown = new JComboBox<>();
        JComboBox<String> productDropdown = new JComboBox<>();

        // Populate the client dropdown with client names
        List<Client> clientList = clientDAO.findAll();
        for (Client client : clientList) {
            clientDropdown.addItem(client.getName());
        }
        List<Product> productList = productDAO.findAll();
        for (Product product : productList) {
            productDropdown.addItem(String.valueOf(product.getId()));
        }

        // Create text field for quantity input
        JTextField quantityTextField = new JTextField();

        Dimension textFieldSize = new Dimension(120, quantityTextField.getPreferredSize().height);
        quantityTextField.setPreferredSize(textFieldSize);
        JTextField idDelTextField = new JTextField();

       idDelTextField.setPreferredSize(textFieldSize);
        // Add the quantity label and text field to the form panel
        // Create button for creating orders
        JButton createOrderButton = new JButton("Create Order");
        Dimension buttonSize = new Dimension(100, 30);
        // Set smaller button size
        createOrderButton.setPreferredSize(buttonSize);

        // Create the table for displaying orders
        DefaultTableModel orderModel = new DefaultTableModel();
        orderModel.addColumn("Order ID");
        orderModel.addColumn("Client Name");
        orderModel.addColumn("Product Name");
        orderModel.addColumn("Quantity");
        JTable ordersTable = new JTable(orderModel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);

        // Add dropdowns, text field, and button to the panel
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Select Client:"));
        formPanel.add(clientDropdown);
        formPanel.add(new JLabel("Select Product:"));
        formPanel.add(productDropdown);
        JLabel quantityLabel = new JLabel("Quantity:");
        Dimension labelSize = new Dimension(80, quantityLabel.getPreferredSize().height);
        quantityLabel.setPreferredSize(labelSize);
        JLabel idDeleteLabel = new JLabel("Delete(id):");
        Dimension lbSize2 = new Dimension(80, idDeleteLabel.getPreferredSize().height);
        idDeleteLabel.setPreferredSize(labelSize);
        // Add the quantity label and text field to the form panel
        formPanel.add(quantityLabel);
        formPanel.add(quantityTextField);
        formPanel.add(idDeleteLabel);
        formPanel.add(idDelTextField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createOrderButton);

        JButton deleteOrderButton = new JButton("Delete Order");
        deleteOrderButton.setPreferredSize(buttonSize);
        buttonPanel.add(deleteOrderButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        List<Comanda> orderList = orderDAO.findAll();
        for (Comanda order1 : orderList) {
            if (order1 != null) {
                Object[] rowData = {
                        order1.getId(),
                        order1.getClient(),
                        order1.getProdus(),
                        order1.getQuantity(),
                };
                orderModel.addRow(rowData);
            }
        }
        // Add action listener for the create order button
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderModel.setRowCount(0);
                // Retrieve the selected client, product, and quantity
                String selectedClientName = clientDropdown.getSelectedItem().toString();
                String selectedProductName = productDropdown.getSelectedItem().toString();
                int quantity = Integer.parseInt(quantityTextField.getText());

                // Find the selected client object using the selected name
                Client selectedClient = null;
                for (Client client : clientList) {
                    if (client.getName().equals(selectedClientName)) {
                        selectedClient = client;
                        break;
                    }
                }

                // Find the selected product object using the selected product ID
                Product selectedProduct = null;
                for (Product product : productList) {
                    if (Integer.parseInt(selectedProductName) == product.getId()) {
                        selectedProduct = product;
                        break;
                    }
                }

                selectedProduct.setProductQuantity(selectedProduct.getProductQuantity() - quantity);
                productDAO.update(selectedProduct);

                // Create an order with the selected client, product, and quantity
                Comanda order = new Comanda(selectedClient.getName(), selectedProduct.getProductName(), quantity);
                orderDAO.insert(order);

                // Handle the created order as needed (e.g., add it to the table model)
                List<Comanda> orderList = orderDAO.findAll();
                for (Comanda order1 : orderList) {
                    if (order1 != null) {
                        Object[] rowData = {
                                order1.getId(),
                                order1.getClient(),
                                order1.getProdus(),
                                order1.getQuantity(),
                        };
                        orderModel.addRow(rowData);
                    }
                }

                // Clear the input fields
                clientDropdown.setSelectedIndex(0);
                productDropdown.setSelectedIndex(0);
                quantityTextField.setText("");
            }
        });
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderModel.setRowCount(0);

                        int idDelete = Integer.parseInt(idDelTextField.getText());
                        Comanda orderDel = orderDAO.findById(idDelete);

                        Product selectedProduct = new Product();
                        for (Product product : productList) {
                            if (orderDel.getProdus().equals(product.getProductName())) {
                                selectedProduct = product;
                                break;
                            }
                        }
                        selectedProduct.setProductQuantity(selectedProduct.getProductQuantity() + orderDel.getQuantity());
                        productDAO.update(selectedProduct);
                        orderDAO.deleteById(idDelete);

                List<Comanda> orderList = orderDAO.findAll();
                for (Comanda order1 : orderList) {
                    if (order1 != null) {
                        Object[] rowData = {
                                order1.getId(),
                                order1.getClient(),
                                order1.getProdus(),
                                order1.getQuantity(),
                        };
                        orderModel.addRow(rowData);
                    }
                }

                // Clear the input fields
                clientDropdown.setSelectedIndex(0);
                productDropdown.setSelectedIndex(0);
                quantityTextField.setText("");
            }
            });
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }
}

