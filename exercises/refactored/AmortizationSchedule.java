package refactored;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;


public class AmortizationSchedule {

    private static Console console = System.console();
    private static final double MIN_LOAN_AMOUNT = 1d;
    private static final double MAX_LOAN_AMOUNT = 50000001d;

    private static final String[] userPrompts = {
            "Please enter the amount you would like to borrow, e.g. 500000 ",
            "Please enter the annual percentage rate used to repay the loan, e.g. 4.55 ",
            "Please enter the term, in years, over which the loan is repaid, e.g. 30 "
    };

    private static LoanDuration duration = null;
    private static InterestRate rate = null;
    private static Double loanAmount = 0d;


    private static String readLine(String userPrompt) throws
            IOException {
        String line = "";

        if (console != null) {
            line = console.readLine(userPrompt);
        } else {
            BufferedReader bufferedReader = new
                    BufferedReader(new InputStreamReader(System.in));

            System.out.println(userPrompt);
            line = bufferedReader.readLine();
        }
        line.trim();
        return line;
    }

    private static boolean validateAmount(double duration) {

        if (duration > MIN_LOAN_AMOUNT && duration < MAX_LOAN_AMOUNT) {
            return true;
        } else {
            throw new IllegalArgumentException("Loan amount should be withing " + MIN_LOAN_AMOUNT + " and "+ MAX_LOAN_AMOUNT);
        }
    }

    private static void processRequest() {

        try {
            MortgageFactory mFactory = new MortgageFactory();

            Mortgage mortgage = mFactory.getMortgageCalculator(MortgageFactory.MortgageType.FIXED_RATE,
                    loanAmount, rate, duration);
            if (mortgage != null) {

                if (mortgage.calculateMortgage()) {
                    RenderResults renderer = PaymentDetailFactory.makePaymentRenderer(PaymentDetailFactory.rendererType.CONSOLE);

                    if (renderer != null) {
                        mortgage.printMonthlyPayment(renderer);
                        mortgage.printPaymentDetails(renderer);
                    } else {
                        System.out.println("Sorry, there was an error in rendering this request.");
                    }
                } else {
                    System.out.println("Sorry, there was an error in processing this request.");
                }
            } else {
                System.out.println("Sorry, there was an error in processing this request.");
            }

        } catch (Exception e) {
            System.out.println("Unable to process the values entered. Terminating program.\n");
            System.out.println(e.getMessage());
        }

    }

    private static boolean getUserInput() {
        boolean retValue = true;
        String line = "";

        for (int input = 0; input < userPrompts.length ; ) {
            String userPrompt = userPrompts[input];
            try {
                line = readLine(userPrompt);
            } catch (IOException e) {
                System.out.println("An IOException was encountered. Terminating program.\n");
                return false;
            }

            // Assume a user input mistake
            boolean isValidValue = false;

            try {
                switch (input) {
                    case 0:
                        loanAmount = Double.parseDouble(line);
                        isValidValue = validateAmount(loanAmount);
                        break;

                    case 1:
                        rate = new InterestRate(Double.parseDouble(line));
                        isValidValue = true;
                        break;

                    case 2:
                        duration = new LoanDuration(Integer.parseInt(line));
                        isValidValue = true;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            if (isValidValue) {
                input++;
            } else {
                System.out.println("An invalid value was entered.\n");
            }
        }

        return retValue;
    }

    public static void main(String[] args) {

        if (getUserInput()) {
            processRequest();
        }
    }

}
