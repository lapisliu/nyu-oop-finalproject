import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfessorLogin {
    private JFrame frame;
    private JLabel userLoginLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private JButton loginAsStudentButton;

    private static ProfessorLogin instance;

    private ProfessorLogin() {
        frame = new JFrame("Professor Account Log In");
        frame.setSize(300, 300);
        frame.setLayout(null);

        // User login label
        userLoginLabel = new JLabel("Professor Login");
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

        loginAsStudentButton = new JButton("Login as Student");
        loginAsStudentButton.setBounds(50, 200, 180, 25);
        loginAsStudentButton.addActionListener(e -> {
            StudentLogin.getInstance().setVisible();
            frame.dispose();
        });
        frame.add(loginAsStudentButton);

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
        ProfessorStorage professorStorage = ProfessorStorage.getInstance();
        if(ProfessorStorage.isProfessorRegistered(username)){
            if(ProfessorStorage.isPasswordCorrect(username,password)){
                frame.setVisible(false);
                ProfessorMainInterface professorMainInterface = new ProfessorMainInterface(professorStorage.getProfessorByName(username));
            }else{
                JOptionPane.showMessageDialog(frame, "Incorrect password");
            }
        }else{
            JOptionPane.showMessageDialog(frame, "professor does not exist");
        }
    }

    public void setVisible(){
        frame.setVisible(true);
    }

    public static ProfessorLogin getInstance(){
        if(instance==null){
            instance = new ProfessorLogin();
        }
        return instance;
    }
}
