package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TireDSSTest {

    @Test
    public void testCalculateScore() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 60, LocalDate.of(2024, 11, 13), warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 8, 100.0);
        TireDSS tireDSS = new TireDSS();
        DetailedScore score = tireDSS.calculateScore(tire);

        assertEquals(300, score.getSalesRateAndLowTireCountContribution());
        assertEquals(0, score.getSalesRateLowTireCountExpiryContribution());
        assertEquals(120, score.getLowTireCountContribution());
        assertEquals(0, score.getLowTireCountAndExpiryContribution());
        assertEquals(0, score.getExpirySoonContribution());
    }

    @Test
    public void testLowTireCountScore() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 40, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 8, 100.0);
        TireDSS tireDSS = new TireDSS();
        DetailedScore score = tireDSS.calculateScore(tire);

        assertEquals(0, score.getSalesRateContribution());
        assertEquals(0, score.getSalesRateAndLowTireCountContribution());
        assertEquals(0, score.getSalesRateLowTireCountExpiryContribution());
        assertEquals(80, score.getLowTireCountContribution());
        assertEquals(0, score.getLowTireCountAndExpiryContribution());
        assertEquals(0, score.getExpirySoonContribution());
    }

    @Test
    public void testLowTireAndHighSalesScore() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 70, LocalDate.of(2024, 11, 13),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 10, 100.0);
        TireDSS tireDSS = new TireDSS();
        DetailedScore score = tireDSS.calculateScore(tire);

        assertEquals(0, score.getSalesRateContribution());
        assertEquals(350, score.getSalesRateAndLowTireCountContribution());
        assertEquals(0, score.getSalesRateLowTireCountExpiryContribution());
        assertEquals(140, score.getLowTireCountContribution());
        assertEquals(0, score.getLowTireCountAndExpiryContribution());
        assertEquals(0, score.getExpirySoonContribution());
    }

    @Test
    public void testCalculateScoreForEverySituation() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 70, LocalDate.now().plusDays(10),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 10, 100.0);
        TireDSS tireDSS = new TireDSS();
        DetailedScore score = tireDSS.calculateScore(tire);

        assertEquals(350, score.getSalesRateAndLowTireCountContribution());
        assertEquals(280, score.getSalesRateLowTireCountExpiryContribution());
        assertEquals(140, score.getLowTireCountContribution());
        assertEquals(210, score.getLowTireCountAndExpiryContribution());
        assertEquals(70, score.getExpirySoonContribution());
    }

    @Test
    public void testCalculateScoreForExpirySoon() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 70, LocalDate.now().plusDays(10),  warehouse, LocalDate.of(2024, 10, 13), 7.5, true, 40, 100.0);
        TireDSS tireDSS = new TireDSS();
        DetailedScore score = tireDSS.calculateScore(tire);

        assertEquals(0, score.getSalesRateContribution());
        assertEquals(0, score.getSalesRateAndLowTireCountContribution());
        assertEquals(0, score.getSalesRateLowTireCountExpiryContribution());
        assertEquals(0, score.getLowTireCountContribution());
        assertEquals(0, score.getLowTireCountAndExpiryContribution());
        assertEquals(70, score.getExpirySoonContribution());
    }

    @Test
    public void testCalculateScoreForLowTireCountAndExpirySoon() {
        ArrayList<List<String>> warehouse = new ArrayList<>();
        Tire tire = new Tire("195/65R15", "Petlas", "car", 50, LocalDate.now().plusDays(10),  warehouse, LocalDate.of(2024, 10, 13), 8.0, true, 5, 100.0);
        TireDSS tireDSS = new TireDSS();
        DetailedScore score = tireDSS.calculateScore(tire);

        assertEquals(0, score.getSalesRateContribution());
        assertEquals(0, score.getSalesRateAndLowTireCountContribution());
        assertEquals(0, score.getSalesRateLowTireCountExpiryContribution());
        assertEquals(100, score.getLowTireCountContribution());
        assertEquals(150, score.getLowTireCountAndExpiryContribution());
        assertEquals(50, score.getExpirySoonContribution());
    }
}
