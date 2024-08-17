package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final ArrayList<Tire> tires = new ArrayList<>();
    private static Storage storage;
    private static final JFrame frame = new JFrame("Tire Inventory Management");

    public static void main(String[] args) {
        // Warehouse list
        Warehouse newCarWarehouse = new Warehouse("New Car Warehouse");
        Warehouse oldCarWarehouse = new Warehouse("Old Car Warehouse");
        Warehouse newExcavationWarehouse = new Warehouse("New Excavation Warehouse");
        Warehouse oldExcavationWarehouse = new Warehouse("Old Excavation Warehouse");
        Warehouse newAgricultureWarehouse = new Warehouse("New Agriculture Warehouse");
        Warehouse oldAgricultureWarehouse = new Warehouse("Old Agriculture Warehouse");
        storage = new Storage(newCarWarehouse, oldCarWarehouse, newExcavationWarehouse, oldExcavationWarehouse, newAgricultureWarehouse, oldAgricultureWarehouse);

        addInitialData();
        createMainMenu();
    }

    private static void createMainMenu() {
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JButton addButton = new JButton("Add New Tire");
        JButton displayButton = new JButton("View Warehouses");
        JButton rankButton = new JButton("Sort and Show Tires");
        JButton orderCheckButton = new JButton("Check Order Requirements");
        JButton discountButton = new JButton("Apply and Show Discount");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTire();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayWarehouses();
            }
        });

        rankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rankAndDisplayTires();
            }
        });

        orderCheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkOrderRequirements();
            }
        });

        discountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyAndDisplayDiscounts();
            }
        });

        panel.add(addButton);
        panel.add(displayButton);
        panel.add(rankButton);
        panel.add(orderCheckButton);
        panel.add(discountButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void addTire() {
        JFrame addFrame = new JFrame("Add Tire");
        addFrame.setSize(600, 600);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel sizeIdLabel = new JLabel("Size ID:");
        JTextField sizeIdText = new JTextField(20);

        JLabel nameLabel = new JLabel("Brand:");
        JTextField nameText = new JTextField(20);

        JLabel seasonTypeLabel = new JLabel("Category (car/excavation/agriculture):");
        JTextField seasonTypeText = new JTextField(20);

        JLabel salesRateLabel = new JLabel("Sales Rate:");
        JTextField salesRateText = new JTextField(20);

        JLabel expiringDateLabel = new JLabel("Expiring Date (YYYY-MM-DD):");
        JTextField expiringDateText = new JTextField(20);

        JLabel manufactureDateLabel = new JLabel("Manufacture Date (YYYY-MM-DD):");
        JTextField manufactureDateText = new JTextField(20);

        JLabel treadDepthLabel = new JLabel("Tread Depth:");
        JTextField treadDepthText = new JTextField(20);

        JLabel isFromFactoryLabel = new JLabel("Is From Factory:");
        JCheckBox isFromFactoryCheck = new JCheckBox();

        JLabel numberOfTiresLabel = new JLabel("Number of Tires:");
        JTextField numberOfTiresText = new JTextField(20);

        JLabel originalPriceLabel = new JLabel("Original Price:");
        JTextField originalPriceText = new JTextField(20);

        JButton addButton = new JButton("Add Tire");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sizeId = sizeIdText.getText();
                String name = nameText.getText();
                String seasonType = seasonTypeText.getText();
                int salesRate = Integer.parseInt(salesRateText.getText());
                LocalDate expiringDate = readDate(expiringDateText.getText());
                ArrayList<List<String>> warehouse = new ArrayList<>();
                LocalDate manufactureDate = readDate(manufactureDateText.getText());
                double treadDepth = Double.parseDouble(treadDepthText.getText());
                boolean isFromFactory = isFromFactoryCheck.isSelected();
                int numberOfTires = Integer.parseInt(numberOfTiresText.getText());
                double originalPrice = Double.parseDouble(originalPriceText.getText());

                Tire tire = new Tire(sizeId, name, seasonType, salesRate, expiringDate, warehouse, manufactureDate, treadDepth, isFromFactory, numberOfTires, originalPrice);
                tires.add(tire);
                storage.storeTire(tire);
                JOptionPane.showMessageDialog(addFrame, "The tire has been successfully added and stored.");
                addFrame.dispose();
            }
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(sizeIdLabel)
                                .addComponent(nameLabel)
                                .addComponent(seasonTypeLabel)
                                .addComponent(salesRateLabel)
                                .addComponent(expiringDateLabel)
                                .addComponent(manufactureDateLabel)
                                .addComponent(treadDepthLabel)
                                .addComponent(isFromFactoryLabel)
                                .addComponent(numberOfTiresLabel)
                                .addComponent(originalPriceLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(sizeIdText)
                                .addComponent(nameText)
                                .addComponent(seasonTypeText)
                                .addComponent(salesRateText)
                                .addComponent(expiringDateText)
                                .addComponent(manufactureDateText)
                                .addComponent(treadDepthText)
                                .addComponent(isFromFactoryCheck)
                                .addComponent(numberOfTiresText)
                                .addComponent(originalPriceText)
                                .addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(sizeIdLabel)
                                .addComponent(sizeIdText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(seasonTypeLabel)
                                .addComponent(seasonTypeText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salesRateLabel)
                                .addComponent(salesRateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(expiringDateLabel)
                                .addComponent(expiringDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(manufactureDateLabel)
                                .addComponent(manufactureDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(treadDepthLabel)
                                .addComponent(treadDepthText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(isFromFactoryLabel)
                                .addComponent(isFromFactoryCheck))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(numberOfTiresLabel)
                                .addComponent(numberOfTiresText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(originalPriceLabel)
                                .addComponent(originalPriceText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton))
        );

        addFrame.add(panel);
        addFrame.setVisible(true);
    }



    private static LocalDate readDate(String dateString) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(frame, "Invalid date format. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }

    private static void rankAndDisplayTires() {
        TireDSS tireDSS = new TireDSS();
        tires.sort((t1, t2) -> tireDSS.calculateScore(t2).getTotalScore() - tireDSS.calculateScore(t1).getTotalScore());

        StringBuilder ranking = new StringBuilder();
        for (Tire tire : tires) {
            DetailedScore score = tireDSS.calculateScore(tire);
            if (score.getTotalScore() > 0) {
                ranking.append("Tire: ").append(tire.getBrand()).append(" | Total Score: ").append(score.getTotalScore()).append("\n")
                        .append("  Sales Rate Contribution: ").append(score.getSalesRateContribution()).append("\n")
                        .append("  Sales Rate and Low Tire Count Contribution: ").append(score.getSalesRateAndLowTireCountContribution()).append("\n")
                        .append("  Sales Rate, Low Tire Count and Expiry Contribution: ").append(score.getSalesRateLowTireCountExpiryContribution()).append("\n")
                        .append("  Low Tire Count Contribution: ").append(score.getLowTireCountContribution()).append("\n")
                        .append("  Low Tire Count and Expiry Contribution: ").append(score.getLowTireCountAndExpiryContribution()).append("\n")
                        .append("  Expiry Soon Contribution: ").append(score.getExpirySoonContribution()).append("\n");

                if (score.getTotalScore() > 0) {
                    ranking.append("  Size ID: ").append(tire.getSizeId()).append("\n")
                            .append("  Brand: ").append(tire.getBrand()).append("\n")
                            .append("  Category: ").append(tire.getCategory()).append("\n")
                            .append("  Sales Rate: ").append(tire.getSalesRate()).append("\n")
                            .append("  Expiring Date: ").append(tire.getExpiringDate()).append("\n")
                            .append("  Manufacturing Date: ").append(tire.getManufactureDate()).append("\n")
                            .append("  Tread Depth: ").append(tire.getTreadDepth()).append("\n")
                            .append("  Is From Factory: ").append(tire.isFromFactory()).append("\n")
                            .append("  Number of Tires: ").append(tire.getNumberOfTires()).append("\n");
                }
                ranking.append("\n");
            }
        }

        JTextArea textArea = new JTextArea(ranking.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        JOptionPane.showMessageDialog(frame, scrollPane, "Ranked Tires", JOptionPane.INFORMATION_MESSAGE);
    }


    private static void checkOrderRequirements() {
        StringBuilder orderRequirements = new StringBuilder();
        for (Tire tire : tires) {
            if (tire.getNumberOfTires() < 12) {
                orderRequirements.append("Order required for ").append(tire.getSizeId() + " " + tire.getBrand()).append(" tires.\n");
            }
        }
        if (orderRequirements.length() == 0) {
            orderRequirements.append("No orders required.");
        }
        JOptionPane.showMessageDialog(frame, orderRequirements.toString(), "Order Requirements", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void applyAndDisplayDiscounts() {
        StringBuilder discountedTires = new StringBuilder("Discounted Tires:\n");
        for (Tire tire : tires) {
            if (tire.getSalesRate() <= 60) {
                double discountedPrice = tire.getOriginalPrice() * 0.9; // Applying 10% discount for low sales rate
                tire.setDiscountedPrice(discountedPrice);
                discountedTires.append("Tire: ").append(tire.getBrand()).append(" | Original Price: ").append(tire.getOriginalPrice()).append(" | Suggested Discounted Price: ").append(tire.getDiscountedPrice()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(frame, discountedTires.toString());
    }

    private static void displayWarehouses() {
        StringBuilder warehouseContents = new StringBuilder();
        warehouseContents.append("New Car Warehouse:\n");
        storage.getCarNewWarehouse().getAllTires().forEach((brand, tires) -> {
            warehouseContents.append("Brand: ").append(brand).append("\n");
            tires.forEach(tire -> warehouseContents.append(" - The size Id: ").append(tire.getSizeId()).append(" & Manufacturing Date: ").append(tire.getManufactureDate()).append(" & Number of Tires: ").append(tire.getNumberOfTires()).append("\n"));
        });

        warehouseContents.append("\nOld Car Warehouse:\n");
        storage.getCarOldWarehouse().getAllTires().forEach((brand, tires) -> {
            warehouseContents.append("Brand: ").append(brand).append("\n");
            tires.forEach(tire -> warehouseContents.append(" - The size Id: ").append(tire.getSizeId()).append(" & Manufacturing Date: ").append(tire.getManufactureDate()).append(" & Number of Tires: ").append(tire.getNumberOfTires()).append("\n"));
        });

        warehouseContents.append("\nNew Excavation Warehouse:\n");
        storage.getExcavationNewWarehouse().getAllTires().forEach((brand, tires) -> {
            warehouseContents.append("Brand: ").append(brand).append("\n");
            tires.forEach(tire -> warehouseContents.append(" - The size Id: ").append(tire.getSizeId()).append(" & Manufacturing Date: ").append(tire.getManufactureDate()).append(" & Number of Tires: ").append(tire.getNumberOfTires()).append("\n"));
        });

        warehouseContents.append("\nOld Excavation Warehouse:\n");
        storage.getExcavationOldWarehouse().getAllTires().forEach((brand, tires) -> {
            warehouseContents.append("Brand: ").append(brand).append("\n");
            tires.forEach(tire -> warehouseContents.append(" - The size Id: ").append(tire.getSizeId()).append(" & Manufacturing Date: ").append(tire.getManufactureDate()).append(" & Number of Tires: ").append(tire.getNumberOfTires()).append("\n"));
        });

        warehouseContents.append("\nNew Agriculture Warehouse:\n");
        storage.getAgriculturalNewWarehouse().getAllTires().forEach((brand, tires) -> {
            warehouseContents.append("Brand: ").append(brand).append("\n");
            tires.forEach(tire -> warehouseContents.append(" - The size Id: ").append(tire.getSizeId()).append(" & Manufacturing Date: ").append(tire.getManufactureDate()).append(" & Number of Tires: ").append(tire.getNumberOfTires()).append("\n"));
        });

        warehouseContents.append("\nOld Agriculture Warehouse:\n");
        storage.getAgriculturalOldWarehouse().getAllTires().forEach((brand, tires) -> {
            warehouseContents.append("Brand: ").append(brand).append("\n");
            tires.forEach(tire -> warehouseContents.append(" - The size Id: ").append(tire.getSizeId()).append(" & Manufacturing Date: ").append(tire.getManufactureDate()).append(" & Number of Tires: ").append(tire.getNumberOfTires()).append("\n"));
        });

        JTextArea textArea = new JTextArea(warehouseContents.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        JOptionPane.showMessageDialog(frame, scrollPane, "Warehouse Contents", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void addInitialData() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        // Car tires
        tires.add(new Tire("195/65Radsdas15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024,10,13), 7.5 ,true,4, 100.0));
        tires.add(new Tire("195/wrwe", "Starmaxx", "car", 60, LocalDate.of(2024, 11, 1),  warehouse, LocalDate.of(2024,10,13), 7.5,true,4, 120.0));
        tires.add(new Tire("3535", "Starmaxx", "car", 60, LocalDate.of(2024, 11, 1),  warehouse, LocalDate.of(2024,10,13), 7.5,true,4, 120.0));
        tires.add(new Tire("195/adnsnjda", "Dunlop", "car", 70, LocalDate.of(2024, 10, 5),  warehouse, LocalDate.of(2024,8,13), 6,false, 20, 150.0));
        tires.add(new Tire("195/asdjasjdsa", "Falken", "car", 80, LocalDate.of(2024, 9, 12),  warehouse, LocalDate.of(2024,10,13), 7.5, false, 20, 180.0));
        tires.add(new Tire("195/65fdfdfdfdfdfR15", "Harvester", "car", 90, LocalDate.of(2024, 8, 15),  warehouse, LocalDate.of(2024,10,13), 7.5, false, 20, 200.0));

        // Excavation tires
        tires.add(new Tire("235/75R17.5", "Michelin", "excavation", 80, LocalDate.of(2024, 8, 20),  warehouse, LocalDate.of(2024, 6, 15), 8.0, false, 15, 250.0)); // Truck
        tires.add(new Tire("215/75R17.5", "Bridgestone", "excavation", 70, LocalDate.of(2024, 8, 25),  warehouse, LocalDate.of(2024, 5, 10), 7.5, false, 10, 220.0)); // Ladle
        tires.add(new Tire("225/75R17.5", "Goodyear", "excavation", 60, LocalDate.of(2024, 7, 30),  warehouse, LocalDate.of(2024, 5, 15), 7.0, false, 12, 200.0)); // Forklift
        tires.add(new Tire("245/75R17.5", "Pirelli", "excavation", 50, LocalDate.of(2024, 6, 20),  warehouse, LocalDate.of(2024, 4, 20), 6.5, false, 20, 180.0)); // Crane

        // Agriculture tires
        tires.add(new Tire("480/70R34", "Firestone", "agriculture", 85, LocalDate.of(2025, 10, 1),  warehouse, LocalDate.of(2024, 9, 15), 8.5, false, 25, 300.0)); // Harvester
        tires.add(new Tire("320/85R28", "Trelleborg", "agriculture", 75, LocalDate.of(2025, 9, 20),  warehouse, LocalDate.of(2024, 8, 20), 8.0, false, 18, 280.0)); // Tractor
        tires.add(new Tire("400/70R24", "Continental", "agriculture", 65, LocalDate.of(2025, 8, 15),  warehouse, LocalDate.of(2024, 7, 10), 7.5, false, 22, 260.0)); // Trailer

        // Adding tires to warehouses
        for (Tire tire : tires) {
            storage.storeTire(tire);
        }
    }
}



