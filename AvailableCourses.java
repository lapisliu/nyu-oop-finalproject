import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AvailableCourses {
    private static final Course[] availableCourses;
    static {
        availableCourses = new Course[4];
        availableCourses[0] = new Course("CS 101", "John Doe", "MWF 10:00 AM");
        availableCourses[1] = new Course("CS 102", "John Doe", "MWF 11:00 AM");
        availableCourses[2] = new Course("CS 201", "David Smith", "TuTr 12:00 PM");
        availableCourses[3] = new Course("CS 202", "David Smith", "MWF 1:00 PM");
    }

    private JFrame frame;
    private JList<Course> coursesList;
    private JButton enrollButton;
    private JButton backButton;

    public AvailableCourses(Student student) {
        frame = new JFrame("Available Courses");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        coursesList = new JList<>(); // Directly create a JList from an array
        coursesList.setListData(availableCourses); // Set the data of the JList to the available courses
        JScrollPane scrollPane = new JScrollPane(coursesList);
        frame.add(scrollPane, BorderLayout.CENTER);

        enrollButton = new JButton("Enroll");
        backButton = new JButton("Back");

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(enrollButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        enrollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Enroll logic here
                Course selectedCourse = coursesList.getSelectedValue();
                if (selectedCourse != null) {
                    student.enrollInCourse(selectedCourse);
                    JOptionPane.showMessageDialog(frame, "You have enrolled in " + selectedCourse);
                }
            }
        });

        backButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
