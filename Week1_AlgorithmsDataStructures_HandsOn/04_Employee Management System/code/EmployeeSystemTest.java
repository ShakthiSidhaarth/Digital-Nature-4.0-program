public class EmployeeSystemTest {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(5);

        manager.addEmployee(new Employee(101, "Shakthi", "Developer", 65000));
        manager.addEmployee(new Employee(102, "Aarav", "Manager", 80000));
        manager.addEmployee(new Employee(103, "Meena", "HR", 50000));

        manager.traverseEmployees();

        Employee found = manager.searchEmployee(102);
        if (found != null) {
            System.out.println("\nEmployee found:");
            found.display();
        } else {
            System.out.println("Employee not found.");
        }

        manager.deleteEmployee(102);
        manager.traverseEmployees();
    }
}
