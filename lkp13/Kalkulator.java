package lkp13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulator extends JFrame {
    private JTextField tfAngka1, tfAngka2, tfHasil;
    private JButton btnTambah, btnKurang, btnKali, btnBagi, btnModulus;

    public Kalkulator() {
        setTitle("Aplikasi Kalkulator");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Menggunakan GridLayout untuk struktur form 4 baris, 2 kolom
        setLayout(new GridLayout(4, 2, 10, 10));

        // Baris 1: Angka 1
        add(new JLabel("Angka 1:"));
        tfAngka1 = new JTextField();
        add(tfAngka1);

        // Baris 2: Angka 2
        add(new JLabel("Angka 2:"));
        tfAngka2 = new JTextField();
        add(tfAngka2);

        // Baris 3: Operator
        add(new JLabel("Operator:"));
        // Panel khusus untuk menjejerkan tombol-tombol operator secara horizontal
        JPanel panelOperator = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        btnTambah = new JButton("+");
        btnKurang = new JButton("-");
        btnKali = new JButton("*");
        btnBagi = new JButton("/");
        btnModulus = new JButton("%");

        panelOperator.add(btnTambah);
        panelOperator.add(btnKurang);
        panelOperator.add(btnKali);
        panelOperator.add(btnBagi);
        panelOperator.add(btnModulus);
        add(panelOperator);

        // Baris 4: Hasil
        add(new JLabel("Hasil:"));
        tfHasil = new JTextField();
        tfHasil.setEditable(false);
        add(tfHasil);

        // Action Listener terpusat untuk semua tombol operator
        ActionListener operatorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double a = Double.parseDouble(tfAngka1.getText());
                    double b = Double.parseDouble(tfAngka2.getText());
                    double hasil = 0;
                    String op = ((JButton) e.getSource()).getText();

                    switch (op) {
                        case "+": hasil = a + b; break;
                        case "-": hasil = a - b; break;
                        case "*": hasil = a * b; break;
                        case "/": 
                            if (b == 0) {
                                JOptionPane.showMessageDialog(null, "Pembagian dengan nol tidak diperbolehkan!");
                                return;
                            }
                            hasil = a / b; 
                            break;
                        case "%": hasil = a % b; break;
                    }
                    // Tampilkan hasil
                    tfHasil.setText(String.valueOf(hasil));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mohon masukkan angka yang valid!");
                }
            }
        };

        // Pasang listener ke masing-masing tombol
        btnTambah.addActionListener(operatorListener);
        btnKurang.addActionListener(operatorListener);
        btnKali.addActionListener(operatorListener);
        btnBagi.addActionListener(operatorListener);
        btnModulus.addActionListener(operatorListener);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Kalkulator();
    }
}
