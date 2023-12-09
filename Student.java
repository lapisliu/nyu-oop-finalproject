import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private List<String> registeredCourses;

    // Constructor
    public Student(String studentId) {
        this.studentId = studentId;
        this.registeredCourses = new ArrayList<>();
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<String> getRegisteredCourses() {
        return new ArrayList<>(registeredCourses); // Provide a copy for encapsulation
    }

    // Method to enroll in a course
    public void enrollInCourse(String courseId) {
        if (!registeredCourses.contains(courseId)) {
            registeredCourses.add(courseId);
        }
    }

    // Additional methods as needed...
}
