import java.util.*;

public class Student {
    private final int studentId;

    private String name;

    private Set<Course> registeredCourses;

    private static int id=0;

    public Student(String name) {
        this.studentId = id++;
        this.name = name;
        this.registeredCourses = new HashSet<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return new ArrayList<>(registeredCourses);
    }

    public void enrollInCourse(Course course) {
        registeredCourses.add(course);
    }
}
