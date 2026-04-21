# Dokumentasi Praktikum Pemrograman Berorientasi Objek (PBO) - Semester 4

Repositori ini berisi kumpulan tugas, latihan, dan kuis dari mata kuliah Praktikum Pemrograman Berorientasi Objek (PBO) selama Semester 4. Semua kode ditulis menggunakan bahasa pemrograman Java.

## Struktur Direktori

Kumpulan kode dibagi ke dalam beberapa folder berdasarkan pertemuan/modul praktikum:

*   **`java_tutor/src`**: Berisi kode-kode dasar pembelajaran Java dan pengenalan OOP.
*   **`p2/`**: Materi praktikum pertemuan 2 (contoh: Konversi Suhu, pengenalan class dan object).
*   **`p3/`**: Materi praktikum pertemuan 3 (contoh: *Inner Class*, *Static Nested Class*, *Anonymous Class*).
*   **`p4/`**: Materi praktikum pertemuan 4 (contoh: Implementasi Method, Return Type, dan parameter).
*   **`p5/`**: Materi praktikum pertemuan 5 (contoh: Interaksi antar objek, studi kasus Karyawan, Tiket, Restoran).
*   **`p6/`**: Materi praktikum pertemuan 6 (contoh: *Inheritance* / Pewarisan, penggunaan `super`, hierarki class).
*   **`quiz_p7/`** & **`quiz_p7_praktikum/`**: Berisi studi kasus kuis pertemuan 7 yang mencakup *Encapsulation*, *Inner Class*, *Inheritance*, dan *Polymorphism*.
    *   **Soal 1**: Sistem Manajemen Perpustakaan Digital (Class `Buku`, `BukuElektronik`, `Pengguna`).
    *   **Soal 2**: Sistem Manajemen Kendaraan Parkir (Class `Kendaraan`, `Mobil`, `Motor`).

## Konsep OOP yang Diterapkan

Selama praktikum ini, beberapa konsep utama Pemrograman Berorientasi Objek telah diimplementasikan:

1.  **Class & Object**: Pembuatan cetak biru (*blueprint*) dan instansiasi objek.
2.  **Encapsulation**: Penyembunyian data menggunakan *access modifier* `private` dan pengaksesan melalui *getter* & *setter*.
3.  **Inheritance**: Pewarisan sifat dan perilaku dari *superclass* ke *subclass* (menggunakan *keyword* `extends`).
4.  **Polymorphism**: Kemampuan sebuah method untuk memiliki bentuk implementasi yang berbeda-beda (*Method Overriding*).
5.  **Inner Class**: Pengelompokan class yang secara logis saling terkait di dalam class lain.
6.  **Delegation**: Penyerahan tugas dari satu objek ke objek lain yang lebih berhak menanganinya (contoh: Pengguna meminjam Buku).

## Cara Menjalankan

Setiap program utama (*main class*) dapat dijalankan melalui IDE (seperti Eclipse, IntelliJ IDEA, atau VS Code) atau langsung melalui terminal menggunakan perintah `javac` dan `java`.

Contoh menjalankan `MainSoal1` dari `quiz_p7_praktikum`:

```bash
# Compile
javac quiz_p7_praktikum/*.java

# Run
java quiz_p7_praktikum.MainSoal1
```
