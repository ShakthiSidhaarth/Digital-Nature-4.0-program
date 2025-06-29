class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public void run() {
        api.initialize();
        api.execute();
        api.cleanup();
    }
}