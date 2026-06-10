# Sistem Manajemen Koleksi & Rental Pop Culture

Aplikasi desktop Java berbasis GUI (Swing) dengan database MySQL untuk manajemen inventaris toko hobi (Manga dan Action Figure) serta transaksi penyewaan (rental).

Proyek ini dibangun sebagai tugas **Final Project Pemrograman Berorientasi Objek (PBO)**.

---

## 👥 Pembagian Tugas Kelompok

*   **Anggota 1 – Project Manager & Dokumentasi**: Menentukan kebutuhan sistem, membuat flowchart/use case, dan menyusun laporan akhir.
*   **Anggota 2 – Backend & Database (Anda/Saat Ini)**: Mendesain database, membuat tabel (`user`, `koleksi`, `rental`), membuat koneksi JDBC, dan mengelola CRUD database.
*   **Anggota 3 – GUI Developer**: Membuat tampilan antarmuka visual Swing (Login, Dashboard, Form Input, Tabel Data).
*   **Anggota 4 – OOP Developer**: Merancang class OOP (Encapsulation, Inheritance, Polymorphism) dan membantu integrasi.
*   **Anggota 5 – Testing & Demo**: Menguji aplikasi, memperbaiki bug, dan membuat video demo.

---

## 🛠️ Persyaratan Sistem & Lingkungan

1.  **Java Development Kit (JDK)**: Versi 8 atau yang lebih baru.
2.  **Docker & Docker Compose**: Untuk menjalankan instance database MySQL tanpa perlu XAMPP.
3.  **MySQL Connector/J**: Driver JDBC MySQL (file `.jar` diletakkan di folder `lib/`).

---

## 🗄️ Skema Database (3NF)

Database bernama `db_popculture` memiliki 3 tabel utama:

### 1. Tabel `user` (Autentikasi & Otorisasi)
Mengelola data pengguna yang dapat login ke sistem.
*   `id_user` (INT, Primary Key, Auto Increment)
*   `username` (VARCHAR(50), Unique, Not Null)
*   `password` (VARCHAR(255), Not Null)
*   `role` (VARCHAR(20), Not Null) — contoh: 'Admin', 'Staff'

### 2. Tabel `koleksi` (Inventaris Produk)
Menyimpan data barang koleksi. Menggunakan kolom `atribut_khusus` untuk mendukung konsep Polymorphism di Java (menyimpan "Volume" untuk Manga, dan "Brand" untuk Action Figure).
*   `id_koleksi` (VARCHAR(10), Primary Key) — contoh: `MGA001`, `FIG001`
*   `judul` (VARCHAR(150), Not Null)
*   `kategori` (ENUM('Manga', 'Action Figure'), Not Null)
*   `harga_sewa` (DOUBLE, Not Null)
*   `stok` (INT, Not Null)
*   `atribut_khusus` (VARCHAR(100)) — Menyimpan "Volume" untuk Manga, atau "Brand" untuk Action Figure.

### 3. Tabel `rental` (Transaksi Penyewaan)
Mencatat peminjaman barang. Biaya dihitung otomatis berdasarkan selisih tanggal kembali dan tanggal pinjam dikalikan harga sewa per hari.
*   `id_rental` (INT, Primary Key, Auto Increment)
*   `nama_penyewa` (VARCHAR(100), Not Null)
*   `id_koleksi` (VARCHAR(10), Foreign Key ke `koleksi.id_koleksi`)
*   `tanggal_pinjam` (DATE, Not Null)
*   `tanggal_kembali` (DATE, Nullable)
*   `total_biaya` (DOUBLE, Default 0)
*   `status` (ENUM('Dipinjam', 'Dikembalikan'), Default 'Dipinjam')

---

## 🚀 Panduan Memulai (Setup & Menjalankan)

### 1. Menjalankan Database MySQL via Docker Compose
Masuk ke direktori `FINPRO_PBO` dan jalankan perintah berikut untuk mengaktifkan kontainer MySQL:
```bash
docker compose up -d
```
Kontainer akan berjalan di latar belakang pada port `3306` dengan username `root` dan password `root`.

### 2. Struktur Direktori Proyek
```text
FINPRO_PBO/
├── docker-compose.yml
├── README.md
├── Format Laporan Project Kelas A.pdf
├── lib/
│   └── mysql-connector-j-x.x.x.jar  # Driver JDBC
└── src/
    ├── database/
    │   ├── Koneksi.java             # Class koneksi JDBC
    │   ├── SetupDatabase.java       # Pembuat skema & dummy data
    │   ├── DbCRUD.java              # Logika database raw query (CRUD)
    │   └── TestCRUD.java            # CLI Test untuk memverifikasi CRUD (Anggota 2)
```

### 3. Inisialisasi Database (Membuat Tabel & Dummy Data)
Compile dan jalankan `SetupDatabase.java` untuk membuat struktur tabel dan mengisi beberapa data awal:
```bash
# Compile
javac -cp ".;lib/*" -d bin src/database/Koneksi.java src/database/SetupDatabase.java

# Jalankan
java -cp "bin;lib/*" database.SetupDatabase
```

### 4. Menguji Backend & CRUD (Verifikasi Anggota 2)
Jalankan program pengujian otomatis CRUD untuk memastikan koneksi database dan query SQL berjalan dengan baik:
```bash
# Compile semua file src/database/
javac -cp ".;lib/*" -d bin src/database/*.java

# Jalankan CLI Test
java -cp "bin;lib/*" database.TestCRUD
```

---

## 🔒 Keamanan & Praktik Terbaik
*   **Prepared Statements**: Seluruh operasi CRUD di dalam `DbCRUD.java` menggunakan `PreparedStatement` untuk mencegah kerentanan SQL Injection.
*   **Koneksi Efisien**: Menutup resources (`Connection`, `PreparedStatement`, `ResultSet`) dengan menggunakan struktur *try-with-resources* di Java.
*   **Separation of Concerns**: Memisahkan logika database raw query (`DbCRUD`) agar tidak tercampur dengan urusan GUI (Anggota 3) atau arsitektur Class OOP (Anggota 4).
