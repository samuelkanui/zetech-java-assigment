import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Car {
    private String carId, make, model;
    private double dailyRate;
    private boolean isAvailable;

    public Car(String carId, String make, String model, double dailyRate) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.dailyRate = dailyRate;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public double getDailyRate() { return dailyRate; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String[] toTableRow() {
        return new String[]{carId, make, model, String.format("$%.2f", dailyRate), isAvailable ? "Available" : "Rented"};
    }
}

class Customer {
    private String customerId, name, phone;

    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public String[] toTableRow() {
        return new String[]{customerId, name, phone};
    }
}

class Rental {
    private String carId, customerId;
    private LocalDate startDate;
    private int rentalDays;
    private double totalPrice;

    public Rental(String carId, String customerId, LocalDate startDate, int rentalDays, double totalPrice) {
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.rentalDays = rentalDays;
        this.totalPrice = totalPrice;
    }

    public String[] toTableRow() {
        return new String[]{carId, customerId, startDate.toString(), String.valueOf(rentalDays), String.format("$%.2f", totalPrice)};
    }
}

class RentalAgency {
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Rental> activeRentals = new ArrayList<>();
    private int customerCounter = 1;

    public void addCar(String carId, String make, String model, double dailyRate) {
        cars.add(new Car(carId, make, model, dailyRate));
    }

    public String addCustomer(String name, String phone) {
        String customerId = "CU" + String.format("%03d", customerCounter++);
        customers.add(new Customer(customerId, name, phone));
        return customerId;
    }

    public boolean rentCar(String carId, String customerId, int days) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId) && car.isAvailable()) {
                car.setAvailable(false);
                double totalPrice = car.getDailyRate() * days;
                activeRentals.add(new Rental(carId, customerId, LocalDate.now(), days, totalPrice));
                return true;
            }
        }
        return false;
    }

    public List<String[]> getAvailableCars() {
        List<String[]> rows = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) rows.add(car.toTableRow());
        }
        return rows;
    }

    public List<String[]> getCustomers() {
        List<String[]> rows = new ArrayList<>();
        for (Customer customer : customers) rows.add(customer.toTableRow());
        return rows;
    }

    public List<String[]> getActiveRentals() {
        List<String[]> rows = new ArrayList<>();
        for (Rental rental : activeRentals) rows.add(rental.toTableRow());
        return rows;
    }
}

public class CarRentalSystem {
    private RentalAgency agency;
    private JTable table;
    private DefaultTableModel tableModel;
    private JFrame frame;

    public CarRentalSystem() {
        agency = new RentalAgency();
        initializeCars();
        createWelcomePage();
    }

    private void initializeCars() {
        agency.addCar("C001", "Toyota", "Corolla", 50.0);
        agency.addCar("C002", "Honda", "Civic", 55.0);
        agency.addCar("C003", "Ford", "Focus", 60.0);
        agency.addCar("C004", "Chevrolet", "Malibu", 65.0);
        agency.addCar("C005", "BMW", "3 Series", 120.0);
        agency.addCar("C006", "Audi", "A4", 110.0);
        agency.addCar("C007", "Mercedes", "C-Class", 130.0);
        agency.addCar("C008", "Volkswagen", "Passat", 70.0);
        agency.addCar("C009", "Nissan", "Altima", 75.0);
        agency.addCar("C010", "Hyundai", "Sonata", 80.0);
        agency.addCar("C011", "Subaru", "Impreza", 62.0);
        agency.addCar("C012", "Mazda", "Mazda3", 58.0);
        agency.addCar("C013", "Tesla", "Model 3", 140.0);
        agency.addCar("C014", "Kia", "Optima", 66.0);
        agency.addCar("C015", "Lexus", "ES 350", 125.0);
    }

    private void createWelcomePage() {
        JFrame welcomeFrame = new JFrame("Welcome to the Car Rental System");
        welcomeFrame.setSize(400, 200);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Car Rental System!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeFrame.add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            welcomeFrame.dispose();
            initializeGUI();
        });
        welcomeFrame.add(startButton, BorderLayout.SOUTH);

        welcomeFrame.setVisible(true);
    }

    private void initializeGUI() {
        frame = new JFrame("Car Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        String[] buttons = {"Show Available Cars", "Show Customers", "Add Customer", "Rent a Car", "Show Active Rentals"};
        for (String label : buttons) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Show Available Cars":
                    updateTable(new String[]{"Car ID", "Make", "Model", "Rate", "Status"}, agency.getAvailableCars());
                    break;
                case "Show Customers":
                    updateTable(new String[]{"ID", "Name", "Phone"}, agency.getCustomers());
                    break;
                case "Add Customer":
                    addCustomer();
                    break;
                case "Rent a Car":
                    rentCar();
                    break;
                case "Show Active Rentals":
                    updateTable(new String[]{"Car ID", "Customer ID", "Start Date", "Days", "Total Price"}, agency.getActiveRentals());
                    break;
            }
        }
    }

    private void updateTable(String[] headers, List<String[]> data) {
        tableModel.setDataVector(data.toArray(new String[0][]), headers);
    }

    private void addCustomer() {
        String name = JOptionPane.showInputDialog("Enter Customer Name:");
        String phone = JOptionPane.showInputDialog("Enter Customer Phone:");
        if (name != null && phone != null) {
            JOptionPane.showMessageDialog(null, "Customer added with ID: " + agency.addCustomer(name, phone));
        }
    }

    private void rentCar() {
        String carId = JOptionPane.showInputDialog("Enter Car ID:");
        String customerId = JOptionPane.showInputDialog("Enter Customer ID:");
        int days = Integer.parseInt(JOptionPane.showInputDialog("Enter Rental Days:"));
        JOptionPane.showMessageDialog(null, agency.rentCar(carId, customerId, days) ? "Car rented!" : "Invalid input");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarRentalSystem::new);
    }
}
