public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket tcsStock = new StockMarket("TCS");

        Observer mobileUser = new MobileApp("Shakthi");
        Observer webDashboard = new WebApp("FinCorp");

        tcsStock.registerObserver(mobileUser);
        tcsStock.registerObserver(webDashboard);

        tcsStock.setPrice(3650.75);

        tcsStock.setPrice(3700.00);

        tcsStock.removeObserver(webDashboard);

        tcsStock.setPrice(3725.50);
    }
}
