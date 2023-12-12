import java.util.*;

public class Student {
    private final int studentId;

    private String name;

    private Set<Course> registeredCourses;

    private Map<Integer,Grade> grades;

    private static int id=0;

    public Student(String name) {
        this.studentId = id++;
        this.name = name;
        this.registeredCourses = new HashSet<>();
        this.grades = new HashMap<>();
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

    public void setGrade(int courseId, AssignmentType type, Double score){
        Grade grade = grades.getOrDefault(courseId+"",new Grade());
        grade.setGradeByType(type,score);
        grades.put(courseId,grade);
    }

    public Grade getGradeByCourse(int courseId){
        return grades.getOrDefault(courseId,new Grade());
    }
}
