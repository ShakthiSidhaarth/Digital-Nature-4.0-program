class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public void remove(String id) {
        api.deleteResource(id);
    }
}