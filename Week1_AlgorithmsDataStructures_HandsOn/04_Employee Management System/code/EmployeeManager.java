public class EmployeeManager {
    private Employee[] employees;
    private int size;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee employee) {
        if (size >= employees.length) {
            System.out.println("Cannot add employee. Array is full.");
            return;
        }
        employees[size++] = employee;
        System.out.println("Employee added: " + employee.getEmployeeId());
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        System.out.println("\n--- Employee List ---");
        for (int i = 0; i < size; i++) {
            employees[i].display();
        }
    }

    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                System.out.println("Employee deleted: " + employeeId);
                return;
            }
        }
        System.out.println("Employee not found: " + employeeId);
    }
}

