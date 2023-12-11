import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLogin {
    private JFrame frame;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private static StudentLogin instance;

    private StudentLogin() {
        frame = new JFrame("Student Account Log In");
        frame.setSize(300, 200);
        frame.setLayout(null);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        frame.add(userLabel);

        userTextField = new JTextField(20);
        userTextField.setBounds(100, 10, 160, 25);
        frame.add(userTextField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        frame.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        frame.add(loginButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

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
            JOptionPane.showMessageDialog(frame, "New user created");
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
