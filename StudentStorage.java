import java.util.*;

public class StudentStorage {
    private List<Student> studentList;

    private static Map<String,String> studentPasswordMap = new HashMap<>();

    private static StudentStorage instance;

    private StudentStorage(){
        studentList = new ArrayList<>();
    }

    public static StudentStorage getInstance(){
        if(instance==null){
            instance = new StudentStorage();
        }
        return instance;
    }

    public void addStudent(Student student, String password){
        studentList.add(student);
        studentPasswordMap.put(student.getName(),password);
    }


    public Student getStudentById(int id){
        for (Student student : studentList) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        throw new NoSuchElementException();
    }

    public Student getStudentByName(String name){
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new NoSuchElementException();
    }

    public List<Student> getStudentList(){
        return new ArrayList<>(studentList);
    }

    public static boolean isStudentRegistered(String name){
        return studentPasswordMap.containsKey(name);
    }

    public static boolean isPasswordCorrect(String name, String password){
        return studentPasswordMap.get(name).equals(password);
    }
}
