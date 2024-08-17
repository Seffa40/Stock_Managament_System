package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TireTest {

    @Test
    public void testTireCreation() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        assertEquals("195/65R15", tire.getSizeId());
        assertEquals("Petlas", tire.getBrand());
        assertEquals("car", tire.getCategory());
        assertEquals(50, tire.getSalesRate());
        assertEquals(LocalDate.of(2024, 11, 13), tire.getExpiringDate());
        assertEquals(LocalDate.of(2024, 10, 13), tire.getManufactureDate());
        assertEquals(7.5, tire.getTreadDepth());
        assertTrue(tire.isFromFactory());
        assertEquals(4, tire.getNumberOfTires());
        assertEquals(100.0, tire.getOriginalPrice());
    }

    @Test
    public void testOrderRequirement() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 11, 100.0);
        tire.setNumberOfTires(10);
        assertTrue(tire.getNumberOfTires() < 12);
    }

    @Test
    public void testTireExpirationSoon() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.now().plusDays(20), warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        assertTrue(tire.getExpiringDate().isBefore(LocalDate.now().plusDays(30)));
    }

    @Test
    public void testTireTreadDepth() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 6.5, true, 4, 100.0);
        assertTrue(tire.getTreadDepth() < 7);
    }

    @Test
    public void testTireFromFactory() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        assertTrue(tire.isFromFactory());
        tire.setFromFactory(false);
        assertFalse(tire.isFromFactory());
    }

    @Test
    public void testTirePriceUpdate() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        tire.setOriginalPrice(120.0);
        assertEquals(120.0, tire.getOriginalPrice());
        tire.setDiscountedPrice(100.0);
        assertEquals(100.0, tire.getDiscountedPrice());
    }
}
