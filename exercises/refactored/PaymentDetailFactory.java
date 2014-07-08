package refactored;

public class PaymentDetailFactory {

    public enum detailType { SIMPLE, COMPLEX}
    public enum rendererType {CONSOLE, FILE}

    //Create a payment detail
    public static AmortizeDetail makePaymentDetail(detailType type, int count, Double payment, Double principal, Double interest) {

        switch (type) {
            case SIMPLE:
                return new SimpleAmortizeDetail(count, payment, principal, interest);

            case COMPLEX: //for future
            default:
                throw new UnsupportedOperationException("This PaymentDetail is not yet supported.");
        }
    }

    //Create a renders for displaying results
    public static RenderResults makePaymentRenderer(rendererType type) {

        switch (type) {
            case CONSOLE:
                return new ConsoleRenderer();

            case FILE:
            default:
                throw new UnsupportedOperationException("This Renderer is not yet supported.");
        }

    }
}
