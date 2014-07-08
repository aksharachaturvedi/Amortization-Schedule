package refactored;
import java.util.List;

//This interface should be implemented by the client to display the results.
public interface RenderResults {

    public void renderMonthlyPayment(Double payment);
    public void renderResults(List<AmortizeDetail> paymentSchedule);
    public void renderDetailsForMonth(int paymentCount, List<AmortizeDetail> paymentSchedule);
}
