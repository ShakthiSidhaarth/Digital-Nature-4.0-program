public class TaskLinkedList {
    private Node head;

    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
        System.out.println("Task added: " + task.getTaskId());
    }

    public Task searchTask(int taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId() == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    public void traverseTasks() {
        System.out.println("\n--- Task List ---");
        Node temp = head;
        while (temp != null) {
            temp.task.display();
            temp = temp.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            System.out.println("Task deleted: " + taskId);
            return;
        }

        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.task.getTaskId() == taskId) {
                prev.next = curr.next;
                System.out.println("Task deleted: " + taskId);
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        System.out.println("Task not found: " + taskId);
    }
}

