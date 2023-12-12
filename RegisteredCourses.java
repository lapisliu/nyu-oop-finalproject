import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisteredCourses {
    private JFrame frame;
    private JList<Course> coursesList;
    private JButton viewCourseButton;

    public RegisteredCourses(Student student) {
        List<Course> registeredCourses = student.getRegisteredCourses();
        frame = new JFrame("Registered Courses");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        if (registeredCourses.isEmpty()) {
            JLabel emptyLabel = new JLabel("No registered courses.");
            frame.add(emptyLabel);
        } else {
            coursesList = new JList<>();
            coursesList.setListData(registeredCourses.toArray(new Course[0]));
            JScrollPane scrollPane = new JScrollPane(coursesList);
            frame.add(scrollPane, BorderLayout.CENTER);
        }

        viewCourseButton = new JButton("View Course");
        viewCourseButton.addActionListener(e -> {
            Course selectedCourse = coursesList.getSelectedValue();
            if (selectedCourse != null) {
                CourseInformation courseInformation = new CourseInformation(student, selectedCourse);
            }
        });
        frame.add(viewCourseButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
