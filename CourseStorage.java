import java.util.*;

public class CourseStorage {
    private  List<Course> courseList;

    private static CourseStorage instance;

    private CourseStorage(){
        courseList = new ArrayList<>();
        courseList.add(new Course("CS 101", "John Doe", "MWF 10:00 AM"));
        courseList.add(new Course("CS 102", "John Doe", "MWF 11:00 AM"));
        courseList.add(new Course("CS 201", "David Smith", "TuTr 12:00 PM"));
        courseList.add(new Course("CS 202", "David Smith", "MWF 1:00 PM"));
    }

    public static CourseStorage getInstance(){
        if(instance==null){
            instance = new CourseStorage();
        }
        return instance;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public boolean removeCourseById(int courseId){
        for (int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getCourseId()==courseId){
                courseList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Course getCourseById(int id){
        for (Course course : courseList) {
            if (course.getCourseId() == id) {
                return course;
            }
        }
        throw new NoSuchElementException();
    }

    public Course getCourseByName(String name){
        for (Course course : courseList) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        throw new NoSuchElementException();
    }

    public List<Course> getCourseList(){
        return new ArrayList<>(courseList);
    }

}
