package p13;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolumeBalok extends JFrame {
    private JTextField tfPanjang;
    private JTextField tfLebar;
    private JTextField tfTinggi;
    private JTextField tfVolume;
    private JButton btnHitung;
    private JButton btnReset;
    private JButton btnKeluar;

    public VolumeBalok() {
        setTitle("Aplikasi Penghitung Volume Balok");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Menggunakan null layout seperti di praktek drag-and-drop 

        JLabel lblRumus = new JLabel("Rumus V = p*l*t");
        lblRumus.setBounds(20, 10, 200, 20);
        add(lblRumus);

        JLabel lblPanjang = new JLabel("Panjang");
        lblPanjang.setBounds(20, 40, 80, 25);
        add(lblPanjang);
        tfPanjang = new JTextField();
        tfPanjang.setBounds(120, 40, 200, 25);
        add(tfPanjang);

        JLabel lblLebar = new JLabel("Lebar");
        lblLebar.setBounds(20, 70, 80, 25);
        add(lblLebar);
        tfLebar = new JTextField();
        tfLebar.setBounds(120, 70, 200, 25);
        add(tfLebar);

        JLabel lblTinggi = new JLabel("Tinggi");
        lblTinggi.setBounds(20, 100, 80, 25);
        add(lblTinggi);
        tfTinggi = new JTextField();
        tfTinggi.setBounds(120, 100, 200, 25);
        add(tfTinggi);

        JLabel lblVolume = new JLabel("Volume");
        lblVolume.setBounds(20, 130, 80, 25);
        add(lblVolume);
        tfVolume = new JTextField();
        tfVolume.setBounds(120, 130, 200, 25);
        tfVolume.setEditable(false);
        add(tfVolume);

        btnReset = new JButton("Reset");
        btnReset.setBounds(20, 170, 80, 25);
        add(btnReset);

        btnHitung = new JButton("Hitung");
        btnHitung.setBounds(120, 170, 200, 25);
        add(btnHitung);

        btnKeluar = new JButton("Keluar");
        btnKeluar.setBounds(20, 210, 80, 25);
        add(btnKeluar);

        // Action Listener dari modul (Langkah r-w)
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // deklarasi variabel
                double panjang, lebar, tinggi, volume;
                // setting input
                panjang = Double.parseDouble(tfPanjang.getText());
                lebar = Double.parseDouble(tfLebar.getText());
                tinggi = Double.parseDouble(tfTinggi.getText());
                // hitung volume
                volume = panjang * lebar * tinggi;
                // setting output volume
                tfVolume.setText(String.valueOf(volume));
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPanjang.setText("");
                tfLebar.setText("");
                tfTinggi.setText("");
                tfVolume.setText("");
                tfPanjang.requestFocus();
            }
        });

        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VolumeBalok();
    }
}
