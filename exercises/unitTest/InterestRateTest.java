package unitTest;

import org.junit.Test;
import refactored.InterestRate;

import static junit.framework.Assert.*;

public class InterestRateTest {

    @Test
    public void testCreationCases() {
        System.out.println("Executing testCreationCases");

        // Invalid param - less than lower bound
        InterestRate interestRate = null;
        try {
            interestRate = new InterestRate(0d);
            fail("Min bound check failed");
        } catch (Exception e) {}
        assertEquals(interestRate, null);


        // Invalid param - exceeds upper bound
        try {
            interestRate = new InterestRate(100d);
            fail("Max bound check failed");
        } catch (Exception e) {}
        assertEquals(interestRate, null);

        // Valid param
        try {
            interestRate = new InterestRate(5d);
        } catch (Exception e) {}
        assertNotNull("Failed creating for a valid case.",interestRate);
    }


    @Test
    public void testGetRateOfInterest() throws Exception {
        System.out.println("Executing testGetRateOfInterest");
        InterestRate interestRate = null;
        Double rate = 2.221d;
        try {
            interestRate = new InterestRate(rate);
        } catch (Exception e) {}
        assertEquals(interestRate.getRateOfInterest(), rate);
    }

    @Test
    public void testGetMonthlyInterest() throws Exception {
        System.out.println("Executing testGetMonthlyInterest");
        InterestRate interestRate = null;
        Double rate = 2.221d;
        try {
            interestRate = new InterestRate(rate);
        } catch (Exception e) {}
        assertEquals(interestRate.getMonthlyInterest(), (rate/12)/100);
    }
}