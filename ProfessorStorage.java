import java.util.*;

public class ProfessorStorage {
    private List<Professor> professorList;

    private static Map<String,String> professorPasswordMap = new HashMap<>();

    private static ProfessorStorage instance;

    private ProfessorStorage(){
        professorList = new ArrayList<>();
        CourseStorage courseStorage = CourseStorage.getInstance();
        professorList.add(new Professor("John Doe",List.of(courseStorage.getCourseByName("CS 101"),courseStorage.getCourseByName("CS 102"))));
        professorPasswordMap.put("John Doe","1234");
        professorList.add(new Professor("David Smith",List.of(courseStorage.getCourseByName("CS 201"),courseStorage.getCourseByName("CS 202"))));
        professorPasswordMap.put("David Smith","abcd");
    }

    public static ProfessorStorage getInstance(){
        if(instance==null){
            instance = new ProfessorStorage();
        }
        return instance;
    }

    public Professor getProfessorByName(String name){
        for (Professor professor : professorList) {
            if (professor.getName().equals(name)) {
                return professor;
            }
        }
        throw new NoSuchElementException();
    }

    public static boolean isProfessorRegistered(String name){
        return professorPasswordMap.containsKey(name);
    }

    public static boolean isPasswordCorrect(String name, String password){
        return professorPasswordMap.get(name).equals(password);
    }
}
