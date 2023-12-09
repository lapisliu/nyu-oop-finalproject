import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisteredCourses {
    private JFrame frame;
    private JList<String> coursesList;

    public RegisteredCourses(List<String> registeredCourses) {
        frame = new JFrame("Registered Courses");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        if (registeredCourses.isEmpty()) {
            JLabel emptyLabel = new JLabel("No registered courses.");
            frame.add(emptyLabel);
        } else {
            coursesList = new JList<>(registeredCourses.toArray(new String[0]));
            JScrollPane scrollPane = new JScrollPane(coursesList);
            frame.add(scrollPane, BorderLayout.CENTER);
        }

        frame.setVisible(true);
    }

    // Main method for testing
    public static void main(String[] args) {
        new RegisteredCourses(List.of("Calculus I", "History"));
    }
}
