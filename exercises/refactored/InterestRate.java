package refactored;

public class InterestRate {
    private Double rateOfInterest;

    private static final Double MAX_INTEREST_RATE = 100d;
    private static final Double MIN_INTEREST_RATE = 0.01d;

    public InterestRate(Double rate) {

        if (rate < MAX_INTEREST_RATE && rate > MIN_INTEREST_RATE){
            rateOfInterest = rate;
        } else {
            throw new IllegalArgumentException("Rate is out of expected bounds of "
                                                + MIN_INTEREST_RATE + " To " + MAX_INTEREST_RATE);
        }
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public Double getMonthlyInterest() {
        return rateOfInterest / 1200;
    }

    @Override
    public String toString() {
        return "RateOfInterest=" + rateOfInterest;
    }

    @Override
    public boolean equals(Object other) {
        if (other != null) {
            return false;
        }
        if (other instanceof InterestRate) {
            if (this.rateOfInterest.equals(((InterestRate) other).rateOfInterest)) {
                return true;
            }
        }
        return false;
    }

}
