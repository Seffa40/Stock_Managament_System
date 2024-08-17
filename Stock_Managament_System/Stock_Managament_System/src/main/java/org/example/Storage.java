package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Storage {

    private final Warehouse carNewWarehouse;
    private final Warehouse carOldWarehouse;
    private final Warehouse excavationNewWarehouse;
    private final Warehouse excavationOldWarehouse;
    private final Warehouse agriculturalNewWarehouse;
    private final Warehouse agriculturalOldWarehouse;

    public Storage(Warehouse carNewWarehouse, Warehouse carOldWarehouse, Warehouse excavationNewWarehouse, Warehouse excavationOldWarehouse, Warehouse agriculturalNewWarehouse, Warehouse agriculturalOldWarehouse) {
        this.carNewWarehouse = carNewWarehouse;
        this.carOldWarehouse = carOldWarehouse;
        this.excavationNewWarehouse = excavationNewWarehouse;
        this.excavationOldWarehouse = excavationOldWarehouse;
        this.agriculturalNewWarehouse = agriculturalNewWarehouse;
        this.agriculturalOldWarehouse = agriculturalOldWarehouse;
    }

    public void storeTire(Tire tire) {
        long daysSinceManufacture = ChronoUnit.DAYS.between(tire.getManufactureDate(), LocalDate.now());
        boolean isNew = tire.isFromFactory() || (tire.getTreadDepth() >= 7 && daysSinceManufacture <= 30);

        if (tire.getCategory().equals("car")) {
            if (isNew) {
                carNewWarehouse.addTire(tire);
            } else {
                carOldWarehouse.addTire(tire);
            }
        } else if (tire.getCategory().equals("excavation")) {
            if (isNew) {
                excavationNewWarehouse.addTire(tire);
            } else {
                excavationOldWarehouse.addTire(tire);
            }
        } else if (tire.getCategory().equals("agriculture")) {
            if (isNew) {
                agriculturalNewWarehouse.addTire(tire);
            } else {
                agriculturalOldWarehouse.addTire(tire);
            }
        }
    }

    public Warehouse getCarNewWarehouse() {
        return carNewWarehouse;
    }

    public Warehouse getCarOldWarehouse() {
        return carOldWarehouse;
    }

    public Warehouse getExcavationNewWarehouse() {
        return excavationNewWarehouse;
    }

    public Warehouse getExcavationOldWarehouse() {
        return excavationOldWarehouse;
    }

    public Warehouse getAgriculturalNewWarehouse() {
        return agriculturalNewWarehouse;
    }

    public Warehouse getAgriculturalOldWarehouse() {
        return agriculturalOldWarehouse;
    }
}
