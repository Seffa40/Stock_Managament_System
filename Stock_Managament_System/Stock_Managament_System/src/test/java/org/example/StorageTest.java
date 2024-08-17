package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StorageTest {

    @Test
    public void testStoreTire() {
        Warehouse newCarWarehouse = new Warehouse("New Car Warehouse");
        Warehouse oldCarWarehouse = new Warehouse("Old Car Warehouse");
        Warehouse newExcavationWarehouse = new Warehouse("New Excavation Warehouse");
        Warehouse oldExcavationWarehouse = new Warehouse("Old Excavation Warehouse");
        Warehouse newAgricultureWarehouse = new Warehouse("New Agriculture Warehouse");
        Warehouse oldAgricultureWarehouse = new Warehouse("Old Agriculture Warehouse");

        Storage storage = new Storage(newCarWarehouse, oldCarWarehouse, newExcavationWarehouse, oldExcavationWarehouse, newAgricultureWarehouse, oldAgricultureWarehouse);

        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 4, 100.0);
        storage.storeTire(tire);

        assertEquals(1, storage.getCarNewWarehouse().getTires().size());
    }

    @Test
    public void testStoreTireToOldWarehouse() {
        Warehouse newCarWarehouse = new Warehouse("New Car Warehouse");
        Warehouse oldCarWarehouse = new Warehouse("Old Car Warehouse");
        Warehouse newExcavationWarehouse = new Warehouse("New Excavation Warehouse");
        Warehouse oldExcavationWarehouse = new Warehouse("Old Excavation Warehouse");
        Warehouse newAgricultureWarehouse = new Warehouse("New Agriculture Warehouse");
        Warehouse oldAgricultureWarehouse = new Warehouse("Old Agriculture Warehouse");

        Storage storage = new Storage(newCarWarehouse, oldCarWarehouse, newExcavationWarehouse, oldExcavationWarehouse, newAgricultureWarehouse, oldAgricultureWarehouse);

        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2023, 10, 13), 6.5, false, 4, 100.0);
        storage.storeTire(tire);

        assertEquals(1, storage.getCarOldWarehouse().getTires().size());
    }
}
