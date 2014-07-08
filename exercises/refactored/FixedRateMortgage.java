package refactored;

import java.util.ArrayList;
import java.util.List;

public class FixedRateMortgage implements Mortgage {

    private final InterestRate interestRate;
    private final LoanDuration loanDuration;
    private Double loanAmount;
    private Double fixedMonthlyPayment;
    private List<AmortizeDetail> paymentDetailList;
    private static final Double INITIAL_PAYMENT_AMT = -1000d;
    private static final Double INVALID_PAYMENT_AMT = -2000d;

    @Override
    public String toString() {
        return "Loanamount=" + loanAmount + " " +
                interestRate.toString() + " " +
                loanDuration.toString() +
                " FixedMonthlyPayment=" + (fixedMonthlyPayment==INITIAL_PAYMENT_AMT ? "Not calculated":fixedMonthlyPayment);
    }

    @Override
    public boolean equals(Object other) {
        boolean equals = false;

        if (other == null) {
            return false;
        }
        if (other instanceof FixedRateMortgage) {
            if (this.loanAmount == ((FixedRateMortgage)other).loanAmount &&
                this.interestRate.equals(((FixedRateMortgage)other).interestRate)  &&
                this.loanDuration.equals(((FixedRateMortgage)other).loanDuration) )    {
                equals = true;
            }
        }

        return equals;
    }

    public FixedRateMortgage(Double loan, InterestRate interest, LoanDuration duration) {

        if (interest != null) {
            if (duration != null) {
                if (loan > 0) {
                    interestRate = interest;
                    loanDuration = duration;
                    loanAmount   = loan;
                    fixedMonthlyPayment = INITIAL_PAYMENT_AMT;
                    paymentDetailList = new ArrayList<AmortizeDetail>();

                } else {
                    throw new IllegalArgumentException("Invalid loan amount, it should be > 0.");
                }
            } else {
                throw new IllegalArgumentException("Invalid duration, it is Null.");
            }
        } else {
            throw new IllegalArgumentException("Invalid interest rate, it is Null.");
        }
    }

    @Override
    public boolean calculateMortgage() {

        // Assume false return value, only set to true when we have the right value.
        boolean retValue = false;

        if (fixedMonthlyPayment.equals(INITIAL_PAYMENT_AMT)) {
            //P = L[c(1 + c)^n]/[(1 + c)^n - 1]
            // P = Fixed monthly payment
            // n = months
            // c = monthly interest rate of (for 6% it is .06/12)
            // L = Original loan amount
            // source http://homeguides.sfgate.com/calculate-principal-interest-mortgage-2409.html

            Double onePlusCRaisedN = Math.pow(1 + interestRate.getMonthlyInterest(), loanDuration.getDurationInMonths());
            Double numerator = (interestRate.getMonthlyInterest() * onePlusCRaisedN) * loanAmount;
            Double denominator = onePlusCRaisedN - 1;

            if (denominator != 0) {

                // Round to 2 decimal places.
                fixedMonthlyPayment = numerator / denominator;

                retValue = true;
            } else {
                fixedMonthlyPayment = INVALID_PAYMENT_AMT;
                System.out.println("Invalid denominator, will get divide by 0 error.");
                throw new RuntimeException("Cant calculate payment with current input.");
            }
        }
        return retValue;
    }


    private void monthlyPaymentSplit() {

        if (fixedMonthlyPayment.equals(INITIAL_PAYMENT_AMT)) {
            calculateMortgage();
        }

        if (fixedMonthlyPayment.equals(INVALID_PAYMENT_AMT)) {
            throw new RuntimeException("Could not calculate monthly payment.");
        }

        // Multiply the loan value by the period interest rate to determine the first month's interest,
        // and then subtract that amount from the monthly payment to get the first month's principal.

        int paymentCount = 0;
        Double totalPrincipalPaid = 0d;
        paymentDetailList.clear();

        while (paymentCount++ < loanDuration.getDurationInMonths()) {

            Double principalLeft = loanAmount - totalPrincipalPaid;

            Double nthMonthInterest = principalLeft * interestRate.getMonthlyInterest();

            Double nthMonthPrincipal = fixedMonthlyPayment - nthMonthInterest;

            totalPrincipalPaid += nthMonthPrincipal;

            AmortizeDetail paymentDetail = PaymentDetailFactory.makePaymentDetail(PaymentDetailFactory.detailType.SIMPLE,
                    paymentCount, fixedMonthlyPayment, nthMonthPrincipal, nthMonthInterest);

            paymentDetailList.add(paymentDetail);
        }
    }


    @Override
    public void printPaymentDetails(RenderResults renderer) {

        if (renderer != null) {
            if (paymentDetailList.size() != loanDuration.getDurationInMonths()) {
                monthlyPaymentSplit();
            }
            renderer.renderResults(paymentDetailList);
        } else {
            throw new IllegalArgumentException("Null renderer");
        }
    }

    @Override
    public void printMonthlyPayment(RenderResults renderer) {
        if (renderer != null) {
            if (fixedMonthlyPayment.equals(INITIAL_PAYMENT_AMT)) {
                calculateMortgage();
            }

            if (fixedMonthlyPayment.equals(INVALID_PAYMENT_AMT)) {
                throw new RuntimeException("Could not calculate monthly payment.");
            }

            renderer.renderMonthlyPayment(fixedMonthlyPayment);
        } else {
            throw new IllegalArgumentException("Null renderer");
        }
    }

    @Override
    public Double getMonthlyPayment() throws Exception {

        if (fixedMonthlyPayment.equals(INITIAL_PAYMENT_AMT)) {
            calculateMortgage();
        }

        if (fixedMonthlyPayment.equals(INVALID_PAYMENT_AMT)) {
            throw new RuntimeException("Could not calculate monthly payment.");
        }

        return fixedMonthlyPayment;
    }
}
