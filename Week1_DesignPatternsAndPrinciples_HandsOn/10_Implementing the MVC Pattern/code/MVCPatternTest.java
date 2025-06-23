public class MVCPatternTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId("2025CSE103");
        student.setName("Shakthi Sidhaarth");
        student.setGrade("A");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        controller.updateView();

        controller.setStudentName("S. Sidhaarth");
        controller.setStudentGrade("A+");

        controller.updateView();
    }
}
