public class TaskManagerTest {
    public static void main(String[] args) {
        TaskLinkedList manager = new TaskLinkedList();

        manager.addTask(new Task(1, "Create UI", "Pending"));
        manager.addTask(new Task(2, "Write Backend", "Pending"));
        manager.addTask(new Task(3, "Deploy App", "Completed"));

        manager.traverseTasks();

        Task found = manager.searchTask(2);
        if (found != null) {
            System.out.println("\nTask found:");
            found.display();
        } else {
            System.out.println("Task not found.");
        }

        manager.deleteTask(2);
        manager.traverseTasks();
    }
}
