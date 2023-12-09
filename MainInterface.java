import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainInterface {
    private JFrame frame;
    private JButton viewRegisteredCoursesButton;
    private JButton enrollInCoursesButton;
    private List<String> registeredCourses;

    public MainInterface() {
        registeredCourses = new ArrayList<>();
        frame = new JFrame("Student Main Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        viewRegisteredCoursesButton = new JButton("View Registered Courses");
        viewRegisteredCoursesButton.setBounds(50, 50, 300, 40);
        viewRegisteredCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisteredCoursesDialog registeredCoursesDialog = new RegisteredCoursesDialog(registeredCourses);
            }
        });
        frame.add(viewRegisteredCoursesButton);

        enrollInCoursesButton = new JButton("Enroll in New Course");
        enrollInCoursesButton.setBounds(50, 150, 300, 40);
        enrollInCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the available courses window
                AvailableCourses availableCourses = new AvailableCourses(MainInterface.this);
                frame.setVisible(false); // Optionally hide the main interface
            }
        });
        frame.add(enrollInCoursesButton);

        frame.setVisible(true);
    }

// Modify the updateRegisteredCourses method inside the MainInterface class
        public void updateRegisteredCourses(String course) {
    if (!registeredCourses.contains(course)) {
        registeredCourses.add(course);
        // Refresh the view to show the newly added course
        viewRegisteredCoursesButton.doClick(); // Simulate a click to refresh the view
    }
}

    // Add this method inside the MainInterface class
        public void showMainInterface() {
    frame.setVisible(true);
}


    public static void main(String[] args) {
        new MainInterface();
    }
}

