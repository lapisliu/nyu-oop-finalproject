import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLogin {
    private JFrame frame;
    private JLabel userLoginLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private JButton loginAsProfessorButton;

    private static StudentLogin instance;

    private StudentLogin() {
        frame = new JFrame("Student Account Log In");
        frame.setSize(300, 300);
        frame.setLayout(null);

        // User login label
        userLoginLabel = new JLabel("Student Login");
        userLoginLabel.setBounds(100, 0, 100, 25);
        frame.add(userLoginLabel);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 50, 80, 25);
        frame.add(userLabel);

        userTextField = new JTextField(20);
        userTextField.setBounds(100, 50, 160, 25);
        frame.add(userTextField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 100, 80, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 160, 25);
        frame.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 150, 80, 25);
        frame.add(loginButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        loginAsProfessorButton = new JButton("Login as Professor");
        loginAsProfessorButton.setBounds(50, 200, 180, 25);
        loginAsProfessorButton.addActionListener(e -> {
            ProfessorLogin.getInstance().setVisible();
            frame.dispose();
        });
        frame.add(loginAsProfessorButton);

        // Display the frame
        frame.setVisible(true);
    }

    // Method to authenticate user
    private void authenticateUser() {
        String username = userTextField.getText().trim();
        if(username.isEmpty()){
            JOptionPane.showMessageDialog(frame, "Please enter a username");
            return;
        }
        String password = new String(passwordField.getPassword());
        if(StudentStorage.isStudentRegistered(username)){
            if(StudentStorage.isPasswordCorrect(username,password)){
                StudentMainInterface studentMainInterface = new StudentMainInterface(StudentStorage.getInstance().getStudentByName(username));
                frame.dispose();
            }else{
                JOptionPane.showMessageDialog(frame, "Incorrect password");
            }
        }else{
            StudentStorage storage = StudentStorage.getInstance();
            Student student = new Student(username);
            storage.addStudent(student,password);
            StudentMainInterface studentMainInterface = new StudentMainInterface(student);
            JOptionPane.showMessageDialog(frame, "New student user created");
            frame.dispose();
        }
    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public static StudentLogin getInstance(){
        if(instance==null){
            instance = new StudentLogin();
        }
        return instance;
    }

    public static void main(String[] args) {
        StudentLogin.getInstance();
    }
}
