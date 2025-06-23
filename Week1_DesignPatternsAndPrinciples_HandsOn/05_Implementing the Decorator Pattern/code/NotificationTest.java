public class     {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier emailAndSMS = new SMSNotifierDecorator(emailNotifier);
        Notifier allChannels = new SlackNotifierDecorator(emailAndSMS);

        System.out.println("== Email Notification ==");
        emailNotifier.send("Server is up!");

        System.out.println("\n== Email + SMS Notification ==");
        emailAndSMS.send("Disk space low!");

        System.out.println("\n== Email + SMS + Slack Notification ==");
        allChannels.send("Database down!");
    }
}
