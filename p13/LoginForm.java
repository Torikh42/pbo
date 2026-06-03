package p13;

import javax.swing.*;

public class LoginForm extends JFrame {
    JTextField username;
    JPasswordField password;
    JButton login;

    public LoginForm() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");
        username = new JTextField();
        password = new JPasswordField();
        login = new JButton("Login");

        lblUser.setBounds(10, 10, 80, 20);
        username.setBounds(100, 10, 150, 20);
        lblPass.setBounds(10, 40, 80, 20);
        password.setBounds(100, 40, 150, 20);
        login.setBounds(100, 70, 80, 25);

        login.addActionListener(e -> {
            String user = username.getText();
            String pass = new String(password.getPassword());
            if (user.equals("admin") && pass.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login Berhasil");
            } else {
                JOptionPane.showMessageDialog(this, "Login Gagal");
            }
        });

        add(lblUser);
        add(username);
        add(lblPass);
        add(password);
        add(login);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
