package refactored;

public interface Mortgage {

    public boolean calculateMortgage();
    public void printMonthlyPayment(RenderResults renderer);
    public void printPaymentDetails(RenderResults renderer);
    public Double getMonthlyPayment() throws Exception ;
}
