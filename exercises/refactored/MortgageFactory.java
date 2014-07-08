package refactored;

//Factory for creating Mortgage calculators
public class MortgageFactory {

    public enum MortgageType{FIXED_RATE, VARIABLE_RATE}

    public Mortgage getMortgageCalculator(MortgageType type,Double loanAmount, InterestRate rate, LoanDuration duration) throws IllegalArgumentException {
        Mortgage mortgage = null;

        switch (type) {
            case  FIXED_RATE:
                mortgage = new FixedRateMortgage(loanAmount, rate, duration);
                break;
            case VARIABLE_RATE: //future use
                // Fall through
            default:
                throw new IllegalArgumentException("Not supported yet");
        }

        return mortgage;
    }


}
