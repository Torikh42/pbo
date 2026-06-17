package gui;

import model.ItemKoleksi;
import model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class InventarisPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtJudul, txtHarga, txtStok, txtAtribut;
    private JComboBox<String> cbKategori;
    private JButton btnSimpan, btnHapus, btnReset;
    private service.KoleksiService koleksiService;
    private boolean isAdmin;
    
    private boolean isEditMode = false;

    // Premium UI Colors
    private final Color BG_COLOR = new Color(248, 250, 252);
    private final Color PANEL_BG = Color.WHITE;
    private final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private final Color DANGER_COLOR = new Color(220, 38, 38);
    private final Color SECONDARY_COLOR = new Color(148, 163, 184);
    private final Color TEXT_COLOR = new Color(30, 41, 59);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public InventarisPanel(User user) {
        koleksiService = new service.KoleksiService();
        isAdmin = "Admin".equalsIgnoreCase(user.getRole());
        setLayout(new BorderLayout(20, 20));
        setBackground(BG_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Table Setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Judul", "Kategori", "Harga", "Stok", "Atribut"}, 0) {
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

        JLabel lblTitle = new JLabel(isAdmin ? "Form Inventaris" : "Detail Inventaris (View Only)");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(isAdmin ? TEXT_COLOR : new Color(148, 163, 184));
        formContainer.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PANEL_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.weightx = 1.0;

        // Row 0
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.2;
        formPanel.add(createLabel("ID Koleksi:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtId = createTextField();
        formPanel.add(txtId, gbc);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.2;
        formPanel.add(createLabel("Judul:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtJudul = createTextField();
        formPanel.add(txtJudul, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.2;
        formPanel.add(createLabel("Kategori:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        cbKategori = new JComboBox<>(new String[]{"Manga", "Action Figure"});
        cbKategori.setFont(MAIN_FONT);
        cbKategori.setBackground(Color.WHITE);
        formPanel.add(cbKategori, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.2;
        formPanel.add(createLabel("Harga Sewa:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtHarga = createTextField();
        formPanel.add(txtHarga, gbc);

        // Row 4
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.2;
        formPanel.add(createLabel("Stok:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtStok = createTextField();
        formPanel.add(txtStok, gbc);

        // Row 5
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.2;
        formPanel.add(createLabel("Atribut (Vol/Brand):"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        txtAtribut = createTextField();
        formPanel.add(txtAtribut, gbc);

        formContainer.add(formPanel, BorderLayout.CENTER);

        // Buttons
        btnSimpan = createButton("Simpan Baru", PRIMARY_COLOR, Color.WHITE);
        btnHapus = createButton("Hapus", DANGER_COLOR, Color.WHITE);
        btnReset = createButton("Batal / Reset", SECONDARY_COLOR, Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(PANEL_BG);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(btnReset);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnSimpan);

        formContainer.add(buttonPanel, BorderLayout.SOUTH);
        
        add(formContainer, BorderLayout.SOUTH);

        // Events
        loadData();
        btnSimpan.addActionListener(e -> saveAction());
        btnHapus.addActionListener(e -> deleteAction());
        btnReset.addActionListener(e -> resetForm());
        table.getSelectionModel().addListSelectionListener(e -> fillForm());

        // RBAC: disable form for Staff
        if (!isAdmin) {
            txtId.setEditable(false);
            txtJudul.setEditable(false);
            txtHarga.setEditable(false);
            txtStok.setEditable(false);
            txtAtribut.setEditable(false);
            cbKategori.setEnabled(false);
            btnSimpan.setEnabled(false);
            btnHapus.setEnabled(false);
            btnSimpan.setBackground(new Color(203, 213, 225));
            btnHapus.setBackground(new Color(203, 213, 225));
            btnSimpan.setToolTipText("Hanya Admin yang dapat menyimpan data.");
            btnHapus.setToolTipText("Hanya Admin yang dapat menghapus data.");
        }
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
        btn.setPreferredSize(new Dimension(140, 35));
        return btn;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<ItemKoleksi> list = koleksiService.getAllKoleksi();
        for (ItemKoleksi item : list) {
            tableModel.addRow(new Object[]{
                item.getIdKoleksi(),
                item.getJudul(),
                item.getKategori(),
                item.getHargaSewa(),
                item.getStok(),
                item.getAtributKhusus()
            });
        }
    }

    private void fillForm() {
        int row = table.getSelectedRow();
        if (row != -1) {
            isEditMode = true;
            txtId.setText(table.getValueAt(row, 0).toString());
            txtJudul.setText(table.getValueAt(row, 1).toString());
            cbKategori.setSelectedItem(table.getValueAt(row, 2).toString());
            txtHarga.setText(table.getValueAt(row, 3).toString());
            txtStok.setText(table.getValueAt(row, 4).toString());
            txtAtribut.setText(table.getValueAt(row, 5).toString());
            
            txtId.setEditable(false);
            txtId.setBackground(new Color(241, 245, 249)); // light gray to indicate locked
            btnSimpan.setText("Update Data");
        }
    }

    private void saveAction() {
        String id = txtId.getText();
        String judul = txtJudul.getText();
        String kategori = cbKategori.getSelectedItem().toString();
        String hargaStr = txtHarga.getText();
        String stokStr = txtStok.getText();
        String atribut = txtAtribut.getText();

        try {
            koleksiService.simpanData(id, judul, kategori, hargaStr, stokStr, atribut, isEditMode);
            JOptionPane.showMessageDialog(this, isEditMode ? "Data berhasil diupdate." : "Data berhasil ditambahkan.");
            loadData();
            resetForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAction() {
        String id = txtId.getText();
        if (id.isEmpty()) return;
        
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                koleksiService.hapusData(id);
                loadData();
                resetForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetForm() {
        isEditMode = false;
        txtId.setText("");
        txtJudul.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtAtribut.setText("");
        txtId.setEditable(true);
        txtId.setBackground(Color.WHITE);
        cbKategori.setSelectedIndex(0);
        btnSimpan.setText("Simpan Baru");
        table.clearSelection();
    }
}
