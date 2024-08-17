package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Tire {
    private String sizeId;
    private String brand;
    private String category;
    private int salesRate;
    private LocalDate expiringDate;
    private ArrayList<List<String>> warehouse;
    private LocalDate manufactureDate;
    private double treadDepth;
    private boolean isFromFactory;
    private int numberOfTires;
    private double originalPrice;
    private double discountedPrice;

    public Tire(String sizeId, String brand, String category, int salesRate, LocalDate expiringDate, ArrayList<List<String>> warehouse, LocalDate manufactureDate, double treadDepth, boolean isFromFactory, int numberOfTires, double originalPrice) {
        this.sizeId = sizeId;
        this.brand = brand;
        this.category = category;
        this.salesRate = salesRate;
        this.expiringDate = expiringDate;
        this.warehouse = warehouse;
        this.manufactureDate = manufactureDate;
        this.treadDepth = treadDepth;
        this.isFromFactory = isFromFactory;
        this.numberOfTires = numberOfTires;
        this.originalPrice = originalPrice;
        this.discountedPrice = originalPrice; // Initial discounted price is the same as the original price

    }

    public String getSizeId() { return sizeId; }

    public String getBrand() { return brand; }

    public String getCategory() { return category; }

    public int getSalesRate() { return salesRate; }

    public LocalDate getExpiringDate() { return expiringDate; }

    public LocalDate getManufactureDate() { return manufactureDate; }

    public double getTreadDepth() { return treadDepth; }

    public boolean isFromFactory() { return isFromFactory; }

    public int getNumberOfTires() { return numberOfTires; }

    public double getOriginalPrice() { return originalPrice; }

    public double getDiscountedPrice() { return discountedPrice; }

    public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }

    public void setNumberOfTires(int numberOfTires) {
        this.numberOfTires = numberOfTires;
    }

    public void setFromFactory(boolean fromFactory) {
        isFromFactory = fromFactory;
    }


    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

}
