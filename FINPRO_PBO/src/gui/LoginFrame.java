package gui;

import model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private service.AuthService authService;

    // Premium UI Colors
    private final Color BG_COLOR = new Color(248, 250, 252);
    private final Color PANEL_BG = Color.WHITE;
    private final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private final Color TEXT_COLOR = new Color(30, 41, 59);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public LoginFrame() {
        authService = new service.AuthService();
        setTitle("Pop Culture Rental - Login");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new GridBagLayout());

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(PANEL_BG);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240)),
            new EmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Title
        JLabel lblTitle = new JLabel("Welcome Back", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(TEXT_COLOR);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        cardPanel.add(lblTitle, gbc);

        // Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(BOLD_FONT);
        lblUsername.setForeground(TEXT_COLOR);
        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 5, 0);
        cardPanel.add(lblUsername, gbc);

        txtUsername = new JTextField();
        txtUsername.setFont(MAIN_FONT);
        txtUsername.setPreferredSize(new Dimension(250, 35));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 15, 0);
        cardPanel.add(txtUsername, gbc);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(BOLD_FONT);
        lblPassword.setForeground(TEXT_COLOR);
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 5, 0);
        cardPanel.add(lblPassword, gbc);

        txtPassword = new JPasswordField();
        txtPassword.setFont(MAIN_FONT);
        txtPassword.setPreferredSize(new Dimension(250, 35));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            new EmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 4; gbc.insets = new Insets(0, 0, 25, 0);
        cardPanel.add(txtPassword, gbc);

        // Login Button
        btnLogin = new JButton("Sign In");
        btnLogin.setFont(BOLD_FONT);
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new Dimension(250, 40));
        
        gbc.gridy = 5; gbc.insets = new Insets(0, 0, 0, 0);
        cardPanel.add(btnLogin, gbc);

        // Add card to frame
        add(cardPanel);

        btnLogin.addActionListener(this::handleLogin);
    }

    private void handleLogin(ActionEvent e) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            User user = authService.login(username, password);
            new MainFrame(user).setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}