package refactored;


public class LoanDuration {

    private Integer durationYears;
    private static final Integer MIN_DURATION = 0;
    private static final Integer MAX_DURATION = 100;

    public LoanDuration(Integer durationYears) {
        if (durationYears < MAX_DURATION && durationYears > MIN_DURATION) {
            this.durationYears = durationYears;
        } else {
            throw new IllegalArgumentException("Loan duration should be between " + MIN_DURATION+" and "+MAX_DURATION);
        }
    }

    public Integer getDurationYears() {
        return durationYears;
    }

    public Integer getDurationInMonths() {
        return durationYears * 12;
    }

    @Override
    public String toString() {
        return "DurationInMonths=" + getDurationInMonths();
    }

    @Override
    public boolean equals(Object other) {
        if (other != null) {
            return false;
        }
        if (other instanceof LoanDuration) {
            if (this.durationYears.equals(((LoanDuration) other).durationYears)) {
                return true;
            }
        }
        return false;
    }

}
