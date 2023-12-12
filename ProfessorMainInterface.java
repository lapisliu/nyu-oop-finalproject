import javax.swing.*;
import java.awt.*;

public class ProfessorMainInterface{

    private JFrame frame;
    private JList<Course> courseList;

    private JButton viewSelectedCourseButton;
    private JButton logoutButton;

    public ProfessorMainInterface(Professor professor) {
        frame = new JFrame("Professor Main Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Get the available courses
        java.util.List<Course> coursesTeaching = professor.getCoursesTeaching();
        coursesTeaching.forEach(System.out::println);

        courseList = new JList<>(); // Directly create a JList from an array
        courseList.setListData(coursesTeaching.toArray(new Course[0])); // Set the data of the JList to the available courses
        JScrollPane scrollPane = new JScrollPane(courseList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        viewSelectedCourseButton = new JButton("View Selected Course");

        viewSelectedCourseButton.setBounds(100, 150, 80, 25);
        viewSelectedCourseButton.addActionListener(e -> {
            Course selectedCourse = (Course) courseList.getSelectedValue();
            if (selectedCourse != null) {
                new ProfessorCourseGUI(selectedCourse, professor);
            }
        });
        buttonPanel.add(viewSelectedCourseButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 150, 80, 25);
        logoutButton.addActionListener(e -> {
            ProfessorLogin.getInstance().setVisible();
            frame.dispose();
        });
        buttonPanel.add(logoutButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ProfessorMainInterface(new Professor("test", CourseStorage.getInstance().getCourseList()));
    }
}
