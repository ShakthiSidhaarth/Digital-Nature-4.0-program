public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432", "Shakthi Sidhaarth"));
        context.executePayment(1500.00);

        context.setPaymentStrategy(new PayPalPayment("shakthi@example.com"));
        context.executePayment(2300.00);
    }
}

