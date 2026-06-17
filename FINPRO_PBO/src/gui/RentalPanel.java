package gui;

import model.Rental;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class RentalPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNamaPenyewa, txtIdKoleksi, txtIdRental;
    private JButton btnSewa, btnKembalikan;
    private service.RentalService rentalService;

    // Premium UI Colors
    private final Color BG_COLOR = new Color(248, 250, 252);
    private final Color PANEL_BG = Color.WHITE;
    private final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private final Color SUCCESS_COLOR = new Color(22, 163, 74);
    private final Color TEXT_COLOR = new Color(30, 41, 59);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public RentalPanel() {
        rentalService = new service.RentalService();
        setLayout(new BorderLayout(20, 20));
        setBackground(BG_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Table Setup
        tableModel = new DefaultTableModel(new String[]{"ID Rental", "Penyewa", "ID Koleksi", "Tgl Pinjam", "Tgl Kembali", "Biaya", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(MAIN_FONT);
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(TEXT_COLOR);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(226, 232, 240));

        JTableHeader header = table.getTableHeader();
        header.setFont(BOLD_FONT);
        header.setBackground(Color.WHITE);
        header.setForeground(TEXT_COLOR);
        header.setPreferredSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Form Panel
        JPanel formContainer = new JPanel(new BorderLayout(10, 10));
        formContainer.setBackground(PANEL_BG);
        formContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240)),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitle = new JLabel("Transaksi Rental");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(TEXT_COLOR);
        formContainer.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.weightx = 1.0;

        // Row 0
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        formPanel.add(createLabel("Nama Penyewa:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtNamaPenyewa = createTextField();
        formPanel.add(txtNamaPenyewa, gbc);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.2;
        formPanel.add(createLabel("ID Koleksi:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtIdKoleksi = createTextField();
        formPanel.add(txtIdKoleksi, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.2;
        formPanel.add(createLabel("ID Rental (Read-Only):"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtIdRental = createTextField();
        txtIdRental.setEditable(false);
        txtIdRental.setBackground(new Color(241, 245, 249));
        formPanel.add(txtIdRental, gbc);

        formContainer.add(formPanel, BorderLayout.CENTER);

        // Buttons
        btnSewa = createButton("Sewa Barang", PRIMARY_COLOR, Color.WHITE);
        btnKembalikan = createButton("Kembalikan Barang", SUCCESS_COLOR, Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(PANEL_BG);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(btnSewa);
        buttonPanel.add(btnKembalikan);

        formContainer.add(buttonPanel, BorderLayout.SOUTH);
        
        add(formContainer, BorderLayout.SOUTH);

        // Events
        loadData();
        btnSewa.addActionListener(e -> sewaAction());
        btnKembalikan.addActionListener(e -> kembalikanAction());
        table.getSelectionModel().addListSelectionListener(e -> fillForm());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BOLD_FONT);
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setFont(MAIN_FONT);
        tf.setPreferredSize(new Dimension(0, 35));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            new EmptyBorder(5, 10, 5, 10)
        ));
        return tf;
    }

    private JButton createButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(BOLD_FONT);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(170, 35));
        return btn;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Rental> list = rentalService.getAllRental();
        for (Rental r : list) {
            tableModel.addRow(new Object[]{
                r.getIdRental(),
                r.getNamaPenyewa(),
                r.getIdKoleksi(),
                r.getTanggalPinjam(),
                r.getTanggalKembali(),
                r.getTotalBiaya(),
                r.getStatus()
            });
        }
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtIdRental.setText(table.getValueAt(row, 0).toString());
            txtNamaPenyewa.setText(table.getValueAt(row, 1).toString());
            txtIdKoleksi.setText(table.getValueAt(row, 2).toString());
        }
    }

    private void sewaAction() {
        String nama = txtNamaPenyewa.getText();
        String idKoleksi = txtIdKoleksi.getText();
        
        try {
            rentalService.sewaBarang(nama, idKoleksi);
            JOptionPane.showMessageDialog(this, "Sewa berhasil.");
            loadData();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kembalikanAction() {
        String idStr = txtIdRental.getText();
        
        try {
            rentalService.kembalikanBarang(idStr);
            JOptionPane.showMessageDialog(this, "Pengembalian berhasil.");
            loadData();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtNamaPenyewa.setText("");
        txtIdKoleksi.setText("");
        txtIdRental.setText("");
        table.clearSelection();
    }
}
