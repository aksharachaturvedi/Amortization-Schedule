package unitTest;

import org.junit.Test;
import refactored.FixedRateMortgage;
import refactored.InterestRate;
import refactored.LoanDuration;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class FixedRateMortgageTest {

    @Test
    public void testCalculateMortgage() throws Exception {
        Double loan = 20000d;
        InterestRate rate = new InterestRate(4d);
        LoanDuration duration = new LoanDuration(20);
        FixedRateMortgage mortgage = new FixedRateMortgage(loan, rate, duration);
        assertNotNull("FixedRateMortgage  should not be null", mortgage);


        FixedRateMortgage mortgageNull = null;
        try {
            mortgageNull = new FixedRateMortgage(loan, null, duration);
        } catch (Exception e) {}
        assertEquals(mortgageNull, null);
        try {
            mortgageNull = new FixedRateMortgage(loan, rate, null);
        } catch (Exception e) {}
        assertEquals(mortgageNull, null);
    }

    @Test
    public void testGetMonthlyPayment() throws Exception {
        Double loan = 20000d;
        InterestRate rate = new InterestRate(4d);
        LoanDuration duration = new LoanDuration(20);
        FixedRateMortgage mortgage = new FixedRateMortgage(loan, rate, duration);
        System.out.println(mortgage.toString());

        assertEquals(mortgage.getMonthlyPayment(), 121.1960658598819);

    }
}