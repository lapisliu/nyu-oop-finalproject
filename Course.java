public class Course {
    private static int id=0;
    private final int courseId;
    private String name;
    private String instructor;
    private String time;

    //weights. sum of them should be 1
    private Double homeworkWeight=0.2;
    private Double midtermWeight=0.3;
    private Double finalWeight=0.5;


    public Course(String name, String instructor, String time) {
        this.courseId = id++;
        this.name = name;
        this.instructor = instructor;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTime() {
        return time;
    }

    public int getCourseId() {
        return courseId;
    }

    public Double getWeightByType(AssignmentType type){
        return switch (type) {
            case HOMEWORK -> homeworkWeight;
            case MIDTERM -> midtermWeight;
            case FINAL -> finalWeight;
        };
    }

    @Override
    public String toString() {
        return name + " - " + instructor + " - " + time;
    }
}
