public class MobileApp implements Observer {
    private String user;

    public MobileApp(String user) {
        this.user = user;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("[MobileApp] Hello " + user + ", " + stockName + " is now Rs " + stockPrice);
    }
}
