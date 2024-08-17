package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WarehouseTest {

    @Test
    public void testAddTire() {
        Warehouse warehouse = new Warehouse("Test Warehouse");
        ArrayList<List<String>> wh = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  wh, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        warehouse.addTire(tire);
        assertEquals(1, warehouse.getTires().size());
    }

    @Test
    public void testGetTires() {
        Warehouse warehouse = new Warehouse("Test Warehouse");
        ArrayList<List<String>> wh = new ArrayList<>();
        Tire tire1 = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  wh, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        Tire tire2 = new Tire("225/45R17", "Bridgestone", "car", 60, LocalDate.of(2024, 12, 13),  wh, LocalDate.of(2024, 10, 20), 8.0, false, 5, 120.0);
        warehouse.addTire(tire1);
        warehouse.addTire(tire2);

        assertEquals(2, warehouse.getTires().size());
        assertEquals(2, warehouse.getTires().size());
    }

    @Test
    public void testGetAllTires() {
        Warehouse warehouse = new Warehouse("Test Warehouse");
        ArrayList<List<String>> wh = new ArrayList<>();
        Tire tire1 = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  wh, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        Tire tire2 = new Tire("225/45R17", "Bridgestone", "car", 60, LocalDate.of(2024, 12, 13),  wh, LocalDate.of(2024, 10, 20), 8.0, false, 5, 120.0);
        warehouse.addTire(tire1);
        warehouse.addTire(tire2);

        assertEquals(2, warehouse.getAllTires().size());
        assertTrue(warehouse.getAllTires().containsKey("Petlas"));
        assertTrue(warehouse.getAllTires().containsKey("Bridgestone"));
    }
}
