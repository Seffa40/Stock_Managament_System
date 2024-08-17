package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TireDSS {

    public DetailedScore calculateScore(Tire tire) {
        int totalScore = 0;
        int salesRate = tire.getSalesRate();
        int numberOfTires = tire.getNumberOfTires();
        long daysToExpiry = ChronoUnit.DAYS.between(LocalDate.now(), tire.getExpiringDate());

        boolean highSalesRate = salesRate >= 60;
        boolean lowTireCount = numberOfTires < 12;
        boolean expirySoon = daysToExpiry < 30;

        int salesRateContribution = 0;
        int salesRateAndLowTireCountContribution = 0;
        int salesRateLowTireCountExpiryContribution = 0;
        int lowTireCountContribution = 0;
        int lowTireCountAndExpiryContribution = 0;
        int expirySoonContribution = 0;

        // Scoring system
        if (highSalesRate && lowTireCount) {
            salesRateAndLowTireCountContribution = 5 * salesRate;
            totalScore += salesRateAndLowTireCountContribution;
        }
        if (highSalesRate && lowTireCount && expirySoon) {
            salesRateLowTireCountExpiryContribution = 4 * salesRate;
            totalScore += salesRateLowTireCountExpiryContribution;
        }
        if (lowTireCount && expirySoon) {
            lowTireCountAndExpiryContribution = 3 * salesRate;
            totalScore += lowTireCountAndExpiryContribution;
        }
        if (lowTireCount) {
            lowTireCountContribution = 2 * salesRate;
            totalScore += lowTireCountContribution;
        }
        if (expirySoon) {
            expirySoonContribution = 1 * salesRate;
            totalScore += expirySoonContribution;
        }

        return new DetailedScore(
                totalScore,
                salesRateContribution,
                salesRateAndLowTireCountContribution,
                salesRateLowTireCountExpiryContribution,
                lowTireCountContribution,
                lowTireCountAndExpiryContribution,
                expirySoonContribution
        );
    }
}

class DetailedScore {
    private final int totalScore;
    private final int salesRateContribution;
    private final int salesRateAndLowTireCountContribution;
    private final int salesRateLowTireCountExpiryContribution;
    private final int lowTireCountContribution;
    private final int lowTireCountAndExpiryContribution;
    private final int expirySoonContribution;

    public DetailedScore(
            int totalScore,
            int salesRateContribution,
            int salesRateAndLowTireCountContribution,
            int salesRateLowTireCountExpiryContribution,
            int lowTireCountContribution,
            int lowTireCountAndExpiryContribution,
            int expirySoonContribution) {
        this.totalScore = totalScore;
        this.salesRateContribution = salesRateContribution;
        this.salesRateAndLowTireCountContribution = salesRateAndLowTireCountContribution;
        this.salesRateLowTireCountExpiryContribution = salesRateLowTireCountExpiryContribution;
        this.lowTireCountContribution = lowTireCountContribution;
        this.lowTireCountAndExpiryContribution = lowTireCountAndExpiryContribution;
        this.expirySoonContribution = expirySoonContribution;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getSalesRateContribution() {
        return salesRateContribution;
    }

    public int getSalesRateAndLowTireCountContribution() {
        return salesRateAndLowTireCountContribution;
    }

    public int getSalesRateLowTireCountExpiryContribution() {
        return salesRateLowTireCountExpiryContribution;
    }

    public int getLowTireCountContribution() {
        return lowTireCountContribution;
    }

    public int getLowTireCountAndExpiryContribution() {
        return lowTireCountAndExpiryContribution;
    }

    public int getExpirySoonContribution() {
        return expirySoonContribution;
    }
}
