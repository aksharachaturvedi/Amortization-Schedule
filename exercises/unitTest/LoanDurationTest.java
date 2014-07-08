package unitTest;

import junit.framework.TestCase;
import org.junit.Test;
import refactored.LoanDuration;

public class LoanDurationTest extends TestCase {

    @Test
    public void testCreationCases() {
        System.out.println("Executing testCreationCases");

        // Invalid param - less than lower bound
        LoanDuration duration = null;
        try {
            duration = new LoanDuration(0);
            fail("Min bound check failed");
        } catch (Exception e) {}
        assertEquals(duration, null);


        // Invalid param - exceeds upper bound
        try {
            duration = new LoanDuration(100);
            fail("Max bound check failed");
        } catch (Exception e) {}
        assertEquals(duration, null);

        // Valid param
        try {
            duration = new LoanDuration(50);
        } catch (Exception e) {}
        assertNotNull("Failed creating for a valid case.",duration);
    }

    @Test
    public void testGetDurationYears() throws Exception {

        System.out.println("Executing GetDurationYears Test");
        LoanDuration duration = null;
        Integer years = 10;
        try {
            duration = new LoanDuration(years);
        } catch (Exception e) {}
        assertNotNull("Failed creating for a valid case.", duration);

        assertEquals(duration.getDurationYears(), years);
    }

    public void testGetDurationInMonths() throws Exception {

        System.out.println("Executing GetDurationInMonths Test");
        LoanDuration duration = null;
        Integer years = 10;
        try {
            duration = new LoanDuration(years);
        } catch (Exception e) {}
        assertNotNull("Failed creating for a valid case.",duration);

        assertEquals(duration.getDurationInMonths(), (Integer)(years*12));
    }
}