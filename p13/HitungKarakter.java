package p13;

import javax.swing.*;
import java.awt.BorderLayout;

public class HitungKarakter extends JFrame {
    JTextArea area;
    JLabel hasil;

    public HitungKarakter() {
        setTitle("Penghitung Karakter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        area = new JTextArea();
        hasil = new JLabel("Jumlah karakter: 0");
        JButton hitung = new JButton("Hitung");

        hitung.addActionListener(e -> {
            String text = area.getText();
            hasil.setText("Jumlah karakter: " + text.length());
        });

        add(new JScrollPane(area), BorderLayout.CENTER);
        add(hitung, BorderLayout.SOUTH);
        add(hasil, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HitungKarakter();
    }
}
