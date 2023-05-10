import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class Gui {
    private JFrame frame;
    private JTable clientsTable;
    private JTable productsTable;

    public Gui() {
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

        // Add action listeners for the buttons (implement your logic here)
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add client button click
            }
        });

        editClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit client button click
            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete client button click
            }
        });

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
            }
        });
    }
}

