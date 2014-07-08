package refactored;

import java.text.NumberFormat;
import java.util.IllegalFormatException;
import java.util.List;

//Implementation to display on the console
public class ConsoleRenderer implements RenderResults {

    //formating the display
    private static final String formatString = "%-10s%-20s%-20s%-20s%-20s%-20s";

    @Override
    public void renderMonthlyPayment(Double payment) {
        System.out.println("Monthly Payment is = "+ format(payment));
    }

    @Override
    public void renderResults(List<AmortizeDetail> paymentSchedule) {

        if (paymentSchedule.size() > 0) {
            Double totalInterestPaid = 0d;
            Double totalPrincipalPaid = 0d;

            printHeader();

            for (AmortizeDetail paymentDetail : paymentSchedule) {

                totalInterestPaid += paymentDetail.getInterestPaid();
                totalPrincipalPaid += paymentDetail.getPrincipalPaid();

                printRow(paymentDetail, totalInterestPaid, totalPrincipalPaid);
            }
        }
    }

    private void printHeader() {

        try {
            System.out.println(String.format(formatString,
                                        "Payment#", "Payment Made", "Principal",
                                        "Interest ", "Total Interest", "Total Principal"));
        } catch (IllegalFormatException e) {
            System.err.print("Error printing...\n");
        }
    }

    private void printRow(AmortizeDetail paymentDetail, Double totalInterestPaid, Double totalPrincipalPaid) {

        try {
            System.out.println(String.format(formatString,
                        paymentDetail.getPaymentCount(),
                        format(paymentDetail.getPaymentAmount()),
                        format(paymentDetail.getPrincipalPaid()),
                        format(paymentDetail.getInterestPaid()),
                        format(totalInterestPaid),
                        format(totalPrincipalPaid)));
        } catch (IllegalFormatException e) {
            System.err.print("Error printing...\n");
        }
    }

    @Override
    public void renderDetailsForMonth(int paymentCount, List<AmortizeDetail> paymentSchedule) {

        if (paymentSchedule.size() > paymentCount) {

            AmortizeDetail paymentDetail = paymentSchedule.get(paymentCount+1);

            System.out.println("#" + paymentDetail.getPaymentCount() +
                    " PrincipalPaid = " + format(paymentDetail.getPrincipalPaid()) +
                    " Interest = " + format(paymentDetail.getInterestPaid()));

        }

    }

    // Two Places Decimal, dollar format
    private String format(Double value) {
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance();
        return dollarFormat.format(value);
    }
}
