import javax.swing.*;
import java.awt.*;
    
    public class CourseInformation {
        private JFrame frame;
        private JLabel instructorLabel;
        private JLabel courseTimeLabel;
        private JLabel assignmentGradeLabel;
        private String courseName; // Assuming each course has a unique name
    
        public CourseInformation(String courseName, String instructor, String courseTime, String assignmentGrade) {
            this.courseName = courseName;
            frame = new JFrame(courseName + " Information");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new GridLayout(3, 1));
    
            instructorLabel = new JLabel("Instructor: " + instructor);
            courseTimeLabel = new JLabel("Course Time: " + courseTime);
            assignmentGradeLabel = new JLabel("Assignment Grade: " + assignmentGrade);
    
            frame.add(instructorLabel);
            frame.add(courseTimeLabel);
            frame.add(assignmentGradeLabel);
    
            frame.setVisible(true);
        }
    
        // Main method for testing
        public static void main(String[] args) {
            new CourseInformation("Calculus I", "Dr. Smith", "MWF 10-11 AM", "Grade: A");
        }
    }
    
    

