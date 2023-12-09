import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

class AvailableCourses {
    private JFrame frame;
    private JList<String> coursesList;
    private JButton enrollButton;
    private JButton backButton;
    private MainInterface mainInterface;

    public AvailableCourses(MainInterface mainInterface) {
        this.mainInterface = mainInterface;
        
        frame = new JFrame("Available Courses");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] coursesArray = {"Physics", "Chemistry", "Literature", "Calculus"};
        coursesList = new JList<>(coursesArray); // Directly create a JList from an array
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
                String selectedCourse = coursesList.getSelectedValue();
                if (selectedCourse != null) {
                    mainInterface.updateRegisteredCourses(selectedCourse);
                    JOptionPane.showMessageDialog(frame, "You have enrolled in " + selectedCourse);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the AvailableCourses window and show the main interface again
                frame.dispose();
                mainInterface.showMainInterface(); // Make sure this method correctly sets the main interface frame to visible
            }
        });
        

        frame.setVisible(true);
    }
}
