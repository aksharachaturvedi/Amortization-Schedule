package refactored;

//Abstract class for Payment detail for a specific payment
public abstract class AmortizeDetail {
    protected int paymentCount;
    protected Double principalPaid;
    protected Double interestPaid;
    protected Double paymentAmount;

    protected AmortizeDetail(int count, Double payment, Double principal, Double interest) {
        paymentCount = count;
        principalPaid = principal;
        interestPaid = interest;
        paymentAmount = payment;
    }

    public abstract Integer getPaymentCount();
    public abstract Double getPrincipalPaid();
    public abstract Double getInterestPaid();
    public abstract Double getPaymentAmount();
}
