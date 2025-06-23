public class WebApp implements Observer {
    private String company;

    public WebApp(String company) {
        this.company = company;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("[WebApp] Notifying " + company + ": " + stockName + " is now Rs " + stockPrice);
    }
}
