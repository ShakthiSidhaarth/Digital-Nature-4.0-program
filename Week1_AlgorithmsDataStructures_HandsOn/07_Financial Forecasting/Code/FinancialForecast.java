import java.util.Scanner;

public class FinancialForecast {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your current amount (Rs): ");
        double currentAmount = scanner.nextDouble();

        System.out.print("Enter your monthly saving amount (Rs): ");
        double monthlySaving = scanner.nextDouble();

        System.out.print("Enter expected annual interest rate (in %): ");
        double annualRate = scanner.nextDouble();

        System.out.print("Enter forecast period (in years): ");
        int years = scanner.nextInt();

        double monthlyRate = annualRate / 12 / 100;

        double futureValue = currentAmount;

        System.out.println("\nFinancial Forecast Yearly Summary:");
        for (int year = 1; year <= years; year++) {
            for (int month = 1; month <= 12; month++) {
                futureValue += monthlySaving;                  
                futureValue += futureValue * monthlyRate;      
            }
            System.out.printf("Year %d: Rs %.2f\n", year, futureValue);
        }

        scanner.close();
    }
}