import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentMainInterface {
    private JFrame frame;
    private JTextField messageTextField;
    private JButton viewRegisteredCoursesButton;
    private JButton enrollInCoursesButton;

    private JButton logoutButton;
    private Student student;

    public StudentMainInterface(Student student) {
        this.student = student;

        frame = new JFrame("Student Main Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        messageTextField = new JTextField();
        messageTextField.setBounds(50, 250, 300, 25);
        messageTextField.setEditable(false);
        messageTextField.setText("Welcome, " + student.getName() + "!");
        frame.add(messageTextField);

        viewRegisteredCoursesButton = new JButton("View Registered Courses");
        viewRegisteredCoursesButton.setBounds(50, 50, 300, 40);
        viewRegisteredCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisteredCourses registeredCourses = new RegisteredCourses(student);
            }
        });
        frame.add(viewRegisteredCoursesButton);

        enrollInCoursesButton = new JButton("Enroll in New Course");
        enrollInCoursesButton.setBounds(50, 100, 300, 40);
        enrollInCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the available courses window
                AvailableCourses availableCourses = new AvailableCourses(student);
            }
        });
        frame.add(enrollInCoursesButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 200, 100, 40);


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                StudentLogin.getInstance().setVisible();
            }
        });
        frame.add(logoutButton);

        frame.setVisible(true);
    }
}

