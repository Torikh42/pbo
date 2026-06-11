package gui;

import model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContent;
    private User currentUser;

    // Colors
    private final Color SIDEBAR_BG = new Color(30, 41, 59); // slate-800
    private final Color SIDEBAR_HOVER = new Color(15, 23, 42); // slate-900
    private final Color TEXT_COLOR = Color.WHITE;
    private final Font MENU_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public MainFrame(User user) {
        this.currentUser = user;
        setTitle("Pop Culture Management System");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(220, 0));

        // User Info Panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(SIDEBAR_BG);
        userInfoPanel.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel lblApp = new JLabel("Pop Culture");
        lblApp.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblApp.setForeground(Color.WHITE);
        lblApp.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSystem = new JLabel("Management");
        lblSystem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSystem.setForeground(new Color(148, 163, 184));
        lblSystem.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("Hi, " + user.getUsername());
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setForeground(new Color(56, 189, 248));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUser.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel lblRole = new JLabel(user.getRole());
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRole.setForeground(new Color(148, 163, 184));
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);

        userInfoPanel.add(lblApp);
        userInfoPanel.add(lblSystem);
        userInfoPanel.add(lblUser);
        userInfoPanel.add(lblRole);

        sidebar.add(userInfoPanel, BorderLayout.NORTH);

        // Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 0, 5));
        menuPanel.setBackground(SIDEBAR_BG);
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnInventaris = createMenuButton("📦  Inventaris");
        JButton btnRental = createMenuButton("🔄  Rental / Sewa");
        
        menuPanel.add(btnInventaris);
        menuPanel.add(btnRental);

        sidebar.add(menuPanel, BorderLayout.CENTER);

        // Logout Panel
        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.setBackground(SIDEBAR_BG);
        logoutPanel.setBorder(new EmptyBorder(10, 10, 20, 10));
        JButton btnLogout = createMenuButton("🚪  Logout");
        btnLogout.setForeground(new Color(248, 113, 113));
        logoutPanel.add(btnLogout, BorderLayout.CENTER);
        
        sidebar.add(logoutPanel, BorderLayout.SOUTH);

        // Main Content Area
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);

        InventarisPanel inventarisPanel = new InventarisPanel();
        RentalPanel rentalPanel = new RentalPanel();

        mainContent.add(inventarisPanel, "Inventaris");
        mainContent.add(rentalPanel, "Rental");

        // Layout
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);

        // Actions
        btnInventaris.addActionListener(e -> cardLayout.show(mainContent, "Inventaris"));
        btnRental.addActionListener(e -> cardLayout.show(mainContent, "Rental"));
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginFrame().setVisible(true);
                this.dispose();
            }
        });

        // Show default
        cardLayout.show(mainContent, "Inventaris");
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(MENU_FONT);
        btn.setForeground(TEXT_COLOR);
        btn.setBackground(SIDEBAR_BG);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMargin(new Insets(10, 20, 10, 20));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(SIDEBAR_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(SIDEBAR_BG);
            }
        });

        return btn;
    }
}
