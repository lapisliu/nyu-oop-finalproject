import java.util.*;

public class Professor {
    private String name;

    private List<Course> coursesTeaching;

    public Professor(String name, List<Course> coursesTeaching) {
        this.name = name;
        this.coursesTeaching = coursesTeaching;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCoursesTeaching() {
        return new ArrayList<>(coursesTeaching);
    }

    @Override
    public String toString() {
        return name;
    }


}
