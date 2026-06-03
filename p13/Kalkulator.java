package p13;

import javax.swing.*;
import java.awt.FlowLayout;

public class Kalkulator extends JFrame {
    JTextField angka1, angka2, hasil;
    JButton tambah;

    public Kalkulator() {
        setTitle("Kalkulator Sederhana");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        angka1 = new JTextField(5);
        angka2 = new JTextField(5);
        hasil = new JTextField(10);
        hasil.setEditable(false);
        tambah = new JButton("+");

        tambah.addActionListener(e -> {
            int a = Integer.parseInt(angka1.getText());
            int b = Integer.parseInt(angka2.getText());
            hasil.setText(String.valueOf(a + b));
        });

        add(new JLabel("Angka 1:"));
        add(angka1);
        add(new JLabel("Angka 2:"));
        add(angka2);
        add(tambah);
        add(new JLabel("Hasil:"));
        add(hasil);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Kalkulator();
    }
}
