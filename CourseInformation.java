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

    public CourseInformation(String courseName, String instructor, String courseTime, Object[][] gradesData) {
        // Set up the frame
        frame = new JFrame(courseName + " Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Course information panel
        courseInfoPanel = new JPanel();
        courseInfoPanel.setLayout(new BoxLayout(courseInfoPanel, BoxLayout.Y_AXIS));
        courseInfoPanel.setBorder(BorderFactory.createTitledBorder("Course Information"));

        courseNameLabel = new JLabel("Course Name: " + courseName);
        instructorLabel = new JLabel("Instructor: " + instructor);
        courseTimeLabel = new JLabel("Course Time: " + courseTime);

        courseInfoPanel.add(courseNameLabel);
        courseInfoPanel.add(instructorLabel);
        courseInfoPanel.add(courseTimeLabel);

        // Add courseInfoPanel to the frame
        frame.add(courseInfoPanel, BorderLayout.NORTH);

        // Convert gradesData to include the percentage and letter grade
        Object[][] tableData = calculateGradesData(gradesData);

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

    private Object[][] calculateGradesData(Object[][] gradesData) {
        double totalWeightedScore = 0;
        double totalWeight = 0;
        // Initialize a new array with one additional row for the final grade
        Object[][] completeData = new Object[gradesData.length + 1][columnNames.length];

        for (int i = 0; i < gradesData.length; i++) {
            Object[] row = gradesData[i];
            // Initialize the row with the number of columns specified
            completeData[i] = new Object[columnNames.length];

            String assignment = (String) row[0];
            double score = (double) row[1];
            double weight = (double) row[2];
            double weightedScore = score * weight * 0.01; // Corrected weighted score calculation
            totalWeightedScore += weightedScore;
            totalWeight += weight;

            // Store the values in the new row
            completeData[i][0] = assignment;
            completeData[i][1] = score;
            completeData[i][2] = weight;
            completeData[i][3] = weightedScore;
            completeData[i][4] = convertScoreToGrade(weightedScore); // Here you need to convert the weighted score, not the raw score
        }

        // Calculate the final grade as the sum of all weighted scores
        double finalGradeValue = totalWeightedScore;
        String finalGrade = convertScoreToGrade(finalGradeValue);

        // Add the final grade to the last row
        completeData[gradesData.length][0] = "Final Grade";
        completeData[gradesData.length][1] = null; // No score for the final grade row
        completeData[gradesData.length][2] = null; // No weight for the final grade row
        completeData[gradesData.length][3] = finalGradeValue;
        completeData[gradesData.length][4] = finalGrade;

        return completeData;
    }

    private String convertScoreToGrade(double weightedScore) {
        // Here you should convert weightedScore to a percentage if your total weights don't add up to 100
        double score = weightedScore; // This assumes totalWeight adds up to 100

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
    public static void main(String[] args) {
        Object[][] data = {
            {"Homework 1", 92.0, 10.0},
            {"Homework 2", 85.0, 10.0},
            {"Quiz 1", 78.0, 10.0},
            {"Quiz 2", 88.0, 10.0},
            {"Midterm", 74.0, 20.0},
            {"Final Exam", 91.0, 25.0},
            {"Group Project", 97.0, 15.0}
        };

        new CourseInformation("Calculus I", "Dr. Smith", "MWF 10-11 AM", data);
    }
}
