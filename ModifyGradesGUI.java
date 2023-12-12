import javax.swing.*;

public class ModifyGradesGUI {
    JLabel courseNameLabel;
    JLabel studentNameLabel;

    JLabel homeworkLabel;
    JLabel midtermLabel;
    JLabel finalLabel;

    JTextField homeworkTextField;
    JTextField midtermTextField;
    JTextField finalTextField;

    JButton saveButton;

    JButton cancelButton;

    public ModifyGradesGUI(Student student, Course course, Professor professor) {
        JFrame frame = new JFrame("Modify Grades");
        frame.setSize(300, 300);
        frame.setLayout(null);

        courseNameLabel = new JLabel("Course Name: " + course.getName());
        courseNameLabel.setBounds(10, 0, 200, 25);
        frame.add(courseNameLabel);

        studentNameLabel = new JLabel("Student Name: " + student.getName());
        studentNameLabel.setBounds(10, 30, 200, 25);
        frame.add(studentNameLabel);

        homeworkLabel = new JLabel("Homework:");
        homeworkLabel.setBounds(10, 60, 80, 25);
        frame.add(homeworkLabel);

        midtermLabel = new JLabel("Midterm:");
        midtermLabel.setBounds(10, 90, 80, 25);
        frame.add(midtermLabel);

        finalLabel = new JLabel("Final:");
        finalLabel.setBounds(10, 120, 80, 25);
        frame.add(finalLabel);

        homeworkTextField = new JTextField(20);
        homeworkTextField.setBounds(100, 60, 160, 25);
        frame.add(homeworkTextField);

        midtermTextField = new JTextField(20);
        midtermTextField.setBounds(100, 90, 160, 25);
        frame.add(midtermTextField);

        finalTextField = new JTextField(20);
        finalTextField.setBounds(100, 120, 160, 25);
        frame.add(finalTextField);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 150, 80, 25);
        saveButton.addActionListener(e -> {
            double homeworkScore = Double.parseDouble(homeworkTextField.getText());
            double midtermScore = Double.parseDouble(midtermTextField.getText());
            double finalScore = Double.parseDouble(finalTextField.getText());

            student.setGrade(course.getCourseId(), AssignmentType.HOMEWORK, homeworkScore);
            student.setGrade(course.getCourseId(), AssignmentType.MIDTERM, midtermScore);
            student.setGrade(course.getCourseId(), AssignmentType.FINAL, finalScore);

            frame.dispose();
            new ProfessorCourseGUI(course, professor);
        });
        frame.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(100, 180, 80, 25);
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new ProfessorCourseGUI(course, professor);
        });
        frame.add(cancelButton);

        frame.setVisible(true);
    }
}
