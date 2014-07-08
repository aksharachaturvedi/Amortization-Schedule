package refactored;

//Implements AmortizeDetail class
public class SimpleAmortizeDetail extends AmortizeDetail {

    public SimpleAmortizeDetail(int count, Double payment, Double principal, Double interest) {
        super(count, payment,  principal, interest);
    }

    @Override
    public Integer getPaymentCount() {
        return paymentCount;
    }

    @Override
    public Double getPrincipalPaid() {
        return principalPaid;
    }

    @Override
    public Double getInterestPaid() {
        return interestPaid;
    }

    @Override
    public Double getPaymentAmount() {
        return paymentAmount;
    }
}
