class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public void performAction() {
        api.logEvent("Action performed");
    }
}