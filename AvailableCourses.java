import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AvailableCourses {
    private JFrame frame;
    private JList<Course> coursesList;
    private JButton enrollButton;
    private JButton backButton;

    public AvailableCourses(Student student) {
        frame = new JFrame("Available Courses");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Get the available courses
        java.util.List<Course> availableCourses = CourseStorage.getInstance().getCourseList();

        coursesList = new JList<>(); // Directly create a JList from an array
        coursesList.setListData(availableCourses.toArray(new Course[0])); // Set the data of the JList to the available courses
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
