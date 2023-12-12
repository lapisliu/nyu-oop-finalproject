import javax.swing.*;
import java.awt.*;

public class CourseInformation {
    private JFrame frame;
    private JTable gradesTable;
    private JPanel courseInfoPanel;
    private JLabel courseNameLabel;
    private JLabel instructorLabel;
    private JLabel courseTimeLabel;
    private String[] columnNames = { "Assignment", "Score", "Weight (%)", "Weighted Score", "Letter Grade" };

    public CourseInformation(Student student, Course course) {
        // Set up the frame
        frame = new JFrame(course.getName() + " Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Course information panel
        courseInfoPanel = new JPanel();
        courseInfoPanel.setLayout(new BoxLayout(courseInfoPanel, BoxLayout.Y_AXIS));
        courseInfoPanel.setBorder(BorderFactory.createTitledBorder("Course Information"));

        courseNameLabel = new JLabel("Course Name: " + course.getName());
        instructorLabel = new JLabel("Instructor: " + course.getInstructor());
        courseTimeLabel = new JLabel("Course Time: " + course.getTime());

        courseInfoPanel.add(courseNameLabel);
        courseInfoPanel.add(instructorLabel);
        courseInfoPanel.add(courseTimeLabel);
        frame.add(courseInfoPanel, BorderLayout.NORTH);

        // Convert gradesData to include the percentage and letter grade
        Object[][] tableData = calculateGradesData(course,student.getGradeByCourse(course.getCourseId()));

        // Grades table
        gradesTable = new JTable(tableData, columnNames);
        gradesTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(gradesTable);

        // Add scrollPane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Pack and show the frame
        frame.pack();
        frame.setVisible(true);
    }

    private Object[][] calculateGradesData(Course course, Grade grade) {
        double totalWeightedScore = 0;
        int assigmentTypeCount = AssignmentType.values().length;
        // Initialize a new array with one additional row for the final grade
        Object[][] completeData = new Object[assigmentTypeCount + 1][columnNames.length];

        for (int i = 0; i < assigmentTypeCount; i++) {
            completeData[i] = new Object[columnNames.length];
            AssignmentType type = AssignmentType.values()[i];

            String assignment = type.toString();
            double score = grade.getGradeByType(type);
            double weight = course.getWeightByType(type);
            double weightedScore = score * weight;
            totalWeightedScore += weightedScore;

            // Store the values in the new row
            completeData[i][0] = assignment;
            completeData[i][1] = score;
            completeData[i][2] = weight;
            completeData[i][3] = weightedScore;
            completeData[i][4] = convertScoreToGrade(weightedScore,weight); // Here you need to convert the weighted score, not the raw score
        }

        // Calculate the final grade as the sum of all weighted scores
        String finalGrade = convertScoreToGrade(totalWeightedScore, 1.0);

        // Add the final grade to the last row
        completeData[assigmentTypeCount][0] = "Final Grade";
        completeData[assigmentTypeCount][1] = null;
        completeData[assigmentTypeCount][2] = null;
        completeData[assigmentTypeCount][3] = totalWeightedScore;
        completeData[assigmentTypeCount][4] = finalGrade;

        return completeData;
    }

    private String convertScoreToGrade(double weightedScore, double weight) {
        double score = weightedScore / weight;

        if (score >= 93) return "A";
        else if (score >= 90) return "A-";
        else if (score >= 87) return "B+";
        else if (score >= 83) return "B";
        else if (score >= 80) return "B-";
        else if (score >= 77) return "C+";
        else if (score >= 73) return "C";
        else if (score >= 70) return "C-";
        else if (score >= 67) return "D+";
        else if (score >= 63) return "D";
        else if (score >= 60) return "D-";
        else return "F";
    }

    // Main method for testing
//    public static void main(String[] args) {
//        Object[][] data = {
//            {"Homework 1", 92.0, 10.0},
//            {"Homework 2", 85.0, 10.0},
//            {"Quiz 1", 78.0, 10.0},
//            {"Quiz 2", 88.0, 10.0},
//            {"Midterm", 74.0, 20.0},
//            {"Final Exam", 91.0, 25.0},
//            {"Group Project", 97.0, 15.0}
//        };
//    }
}
