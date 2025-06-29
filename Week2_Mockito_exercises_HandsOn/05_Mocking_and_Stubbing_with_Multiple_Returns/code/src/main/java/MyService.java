class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String checkStatus() {
        return api.getStatus();
    }
}
