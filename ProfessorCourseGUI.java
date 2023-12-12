import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ProfessorCourseGUI {
    private JPanel courseInfoPanel;
    private JTable studentGradesTable;

    private JButton modifyGradesButton;

    private String[] columnNames;

    public ProfessorCourseGUI(Course course, Professor professor) {
        JFrame frame = new JFrame("Course Information");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        courseInfoPanel = new JPanel();
        courseInfoPanel.setLayout(new BoxLayout(courseInfoPanel, BoxLayout.Y_AXIS));
        courseInfoPanel.setBorder(BorderFactory.createTitledBorder("Course Information"));

        JLabel courseNameLabel = new JLabel("Course Name: " + course.getName());
        JLabel timeLabel = new JLabel("Time: " + course.getTime());
        JLabel studentCountLabel = new JLabel("Student Count: " + course.getStudentCount());

        courseInfoPanel.add(courseNameLabel);
        courseInfoPanel.add(timeLabel);
        courseInfoPanel.add(studentCountLabel);

        frame.add(courseInfoPanel, BorderLayout.NORTH);

        columnNames = new String[5];
        columnNames[0] = "Student Name";
        columnNames[1] = "homework (" + course.getWeightByType(AssignmentType.HOMEWORK)*100 + "%)";
        columnNames[2] = "midterm (" + course.getWeightByType(AssignmentType.MIDTERM)*100 + "%)";
        columnNames[3] = "final (" + course.getWeightByType(AssignmentType.FINAL)*100 + "%)";
        columnNames[4] = "Overall Score";

        Object[][] tableData = calculateGradesData(course);
        DefaultTableModel model = new DefaultTableModel(tableData, columnNames);
        studentGradesTable = new JTable(model);
        studentGradesTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(studentGradesTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        modifyGradesButton = new JButton("Modify Grades");
        modifyGradesButton.setBounds(100, 150, 80, 25);
        modifyGradesButton.addActionListener(e -> {
            int selectedRow = studentGradesTable.getSelectedRow();
            if(selectedRow != -1){
                String studentName = (String) studentGradesTable.getValueAt(selectedRow, 0);
                Student student = StudentStorage.getInstance().getStudentByName(studentName);
                new ModifyGradesGUI(student, course, professor);
                frame.dispose();
            }else {
                JOptionPane.showMessageDialog(frame, "Please select a student to modify grades.");
            }
        });
        frame.add(modifyGradesButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private Object[][] calculateGradesData(Course course){
        java.util.List<Student> studentInCourse = new ArrayList<>();
        for(Student student : StudentStorage.getInstance().getStudentList()){
            if(student.getRegisteredCourses().contains(course)){
                studentInCourse.add(student);
            }
        }
        Object[][] completeData = new Object[studentInCourse.size()][columnNames.length];
        for(int i = 0; i < studentInCourse.size(); i++){
            completeData[i] = new Object[columnNames.length];
            Student student = studentInCourse.get(i);
            Double overallWeightedScore = 0.0;
            completeData[i][0] = student.getName();
            Double homeworkScore = student.getGradeByCourse(course.getCourseId()).getGradeByType(AssignmentType.HOMEWORK);
            completeData[i][1] = homeworkScore;
            overallWeightedScore += homeworkScore * course.getWeightByType(AssignmentType.HOMEWORK);
            Double midtermScore = student.getGradeByCourse(course.getCourseId()).getGradeByType(AssignmentType.MIDTERM);
            completeData[i][2] = midtermScore;
            overallWeightedScore += midtermScore * course.getWeightByType(AssignmentType.MIDTERM);
            Double finalScore = student.getGradeByCourse(course.getCourseId()).getGradeByType(AssignmentType.FINAL);
            completeData[i][3] = finalScore;
            overallWeightedScore += finalScore * course.getWeightByType(AssignmentType.FINAL);
            completeData[i][4] = overallWeightedScore;
        }
        return completeData;
    }

}
