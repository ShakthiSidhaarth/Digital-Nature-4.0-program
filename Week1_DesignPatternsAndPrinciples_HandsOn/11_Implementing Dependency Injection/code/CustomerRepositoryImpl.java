import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Map<String, Customer> customers = new HashMap<>();

    public CustomerRepositoryImpl() {
        customers.put("C101", new Customer("C101", "Shakthi Sidhaarth"));
        customers.put("C102", new Customer("C102", "Aarav Kumar"));
    }

    @Override
    public Customer findCustomerById(String customerId) {
        return customers.get(customerId);
    }
}
