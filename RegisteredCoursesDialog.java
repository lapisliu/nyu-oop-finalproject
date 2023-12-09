import javax.swing.*;
import java.util.List;
import java.util.Vector;

class RegisteredCoursesDialog {
    public RegisteredCoursesDialog(List<String> registeredCourses) {
        JList<String> list = new JList<>(new Vector<>(registeredCourses));
        JOptionPane.showMessageDialog(null, new JScrollPane(list), "Registered Courses", JOptionPane.PLAIN_MESSAGE);
    }
}
