package p13;

import javax.swing.*;

public class FormMahasiswa extends JFrame {
    JTextField nama, nim;
    JButton simpan;

    public FormMahasiswa() {
        setTitle("Form Mahasiswa");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        JLabel lblNIM = new JLabel("NIM:");

        nama = new JTextField();
        nim = new JTextField();
        simpan = new JButton("Simpan");

        lblNama.setBounds(10, 10, 80, 20);
        nama.setBounds(100, 10, 150, 20);
        lblNIM.setBounds(10, 40, 80, 20);
        nim.setBounds(100, 40, 150, 20);
        simpan.setBounds(100, 70, 100, 25);

        simpan.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Data Disimpan:\nNama: " + nama.getText() + "\nNIM: " + nim.getText())
        );

        add(lblNama);
        add(nama);
        add(lblNIM);
        add(nim);
        add(simpan);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FormMahasiswa();
    }
}
