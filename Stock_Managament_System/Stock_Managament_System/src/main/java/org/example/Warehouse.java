package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {

    private final String name;

    private final Map<String, List<Tire>> tires;

    public Warehouse(String name) {
        this.name = name;
        this.tires = new HashMap<>();
    }

    public void addTire(Tire tire) {
        tires.computeIfAbsent(tire.getBrand(), k -> new ArrayList<>()).add(tire);
    }

    public Map<String, List<Tire>> getAllTires() {
        return tires;
    }

    public Map<String, List<Tire>> getTires() {
        return tires;
    }

    public String getName() {
        return name;
    }
}
