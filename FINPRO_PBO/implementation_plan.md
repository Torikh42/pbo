# Sistem Manajemen Koleksi & Rental Pop Culture

Membangun aplikasi desktop Java berbasis GUI (Swing) dengan database MySQL (Docker Compose) untuk tugas Final Project PBO. Aplikasi ini akan melacak inventaris toko hobi (Manga, Action Figure, dll.) dan memanajemen transaksi peminjaman/sewa.

## User Review Required

> [!IMPORTANT]
> **Keputusan Arsitektur & Alur Tim**
> Pembagian tugas tim dan tabel `user` untuk sistem Login telah ditambahkan. Silakan cek bagian **Pembagian Tugas & Alur Kerja** di bawah.
> Jika alur dependensi antar anggota sudah disetujui, kita bisa mulai tahap *coding* dan mencicil tugas per anggota!

## Pembagian Tugas & Alur Kerja (Workflow)

Untuk memastikan proyek berjalan lancar sesuai peran kelompok Anda, berikut adalah alur kerja dan dependensi (siapa menunggu siapa):

### 1. Anggota 1 – Project Manager & Dokumentasi
- **Tugas**: Menentukan kebutuhan sistem, membuat *flowchart/use case*, menyusun laporan, dan mengumpulkan semua *file* (*source code*, video, laporan).
- **Dependensi**: Bisa mulai mendesain *flowchart* di awal. Namun **harus menunggu** keseluruhan aplikasi selesai (Anggota 2, 3, 4, 5) untuk dapat membungkus laporan dan membuat _zip_ pengumpulan akhirnya.

### 2. Anggota 2 – Backend & Database
- **Tugas**: Mendesain database MySQL, membuat tabel (`user`, `koleksi`, `rental`), membuat koneksi JDBC, dan mengelola operasi CRUD dasar.
- **Dependensi**: Ini adalah ujung tombak. Bisa langsung *coding* MySQL dan class `Koneksi.java`. Merupakan pondasi mutlak bagi Anggota 3 dan 4.

### 3. Anggota 4 – OOP Developer
- **Tugas**: Mendesain class dan struktur program (Encapsulation, Inheritance, Polymorphism) dan membantu integrasi antar class.
- **Dependensi**: **Bekerja bersamaan** dengan Anggota 2. Anggota 4 merancang rancang bangun Objek-objek Java (seperti Class `Koleksi`), yang nantinya akan bertugas mengambil data SQL dari Anggota 2 dan meneruskannya ke Anggota 3.

### 4. Anggota 3 – GUI Developer
- **Tugas**: Membuat antarmuka visual (Tampilan Login, Dashboard, Form Input, Tabel Data).
- **Dependensi**: **Harus menunggu** Anggota 2 (untuk database) dan Anggota 4 (untuk fungsi objek Java). Anggota 3 hanya bertugas memanggil _method_ yang dibuat oleh Anggota 4 lalu menempelkannya ke tombol-tombol dan tabel antarmuka visual (`JFrame`).

### 5. Anggota 5 – Testing & Demo
- **Tugas**: Uji coba fitur, perbaikan *bug*, rekaman presentasi, *screenshot* untuk dokumentasi laporan.
- **Dependensi**: **Menunggu** Anggota 3 mengintegrasikan GUI dengan Backend. Jika aplikasi sudah jadi, bertugas mencari kerusakan (*bug*) dan melaporkannya agar direvisi, lalu merekam videonya.

---

## Proposed Changes

### Database Schema (MySQL - Porsi Anggota 2)

Sistem akan menggunakan MySQL via Docker Compose (URL: `jdbc:mysql://localhost:3306/db_popculture`).

#### [NEW] Tabel `user` (Untuk Sistem Login)
- `id_user` (INT, PK, Auto Increment)
- `username` (VARCHAR)
- `password` (VARCHAR)
- `role` (VARCHAR)

#### [NEW] Tabel `koleksi`
- `id_koleksi` (VARCHAR, PK)
- `judul` (VARCHAR)
- `kategori` (ENUM: 'Manga', 'Action Figure')
- `harga_sewa` (DOUBLE)
- `stok` (INT)
- `atribut_khusus` (VARCHAR) -> Menyimpan "Volume" (manga) atau "Brand" (figure).

#### [NEW] Tabel `rental`
- `id_rental` (INT, PK, Auto Increment)
- `nama_penyewa` (VARCHAR)
- `id_koleksi` (VARCHAR, FK ke koleksi)
- `tanggal_pinjam` (DATE)
- `tanggal_kembali` (DATE, Nullable saat baru pinjam)
- `total_biaya` (DOUBLE) -> Dihitung otomatis oleh sistem berdasarkan durasi (hari).
- `status` (ENUM: 'Dipinjam', 'Dikembalikan')

---

### Java OOP Architecture (Porsi Anggota 4)

#### [NEW] Package `database`
- `Koneksi.java`: Mengelola koneksi JDBC.

#### [NEW] Package `model`
- `ItemKoleksi.java` (Abstract Class): Properties dasar (id, judul, harga, stok). *(Encapsulation)*.
- `Manga.java` (Class): Extends `ItemKoleksi`, field `volume`. *(Inheritance & Polymorphism)*.
- `ActionFigure.java` (Class): Extends `ItemKoleksi`, field `brand`.
- `User.java`: Entitas untuk autentikasi login.
- `Rental.java`: Entitas transaksi.

#### [NEW] Package `dao` (Data Access Object)
- `UserDAO.java`, `KoleksiDAO.java`, `RentalDAO.java`: Logika pemanggilan SQL `INSERT, SELECT, UPDATE, DELETE`.

---

### GUI Development (Porsi Anggota 3)
#### [NEW] Package `gui`
- `LoginFrame.java`: Layar Login (Input username & password).
- `MainFrame.java`: Layar *Window* utama aplikasi (berisi menu navigasi di samping).
- `InventarisPanel.java`: Form Tambah/Ubah dan `JTable` koleksi hobi.
- `RentalPanel.java`: Form sewa dan pengembalian (termasuk tombol otomatis hitung harga).

## Verification Plan (Porsi Anggota 5)
1. Uji fitur Login dengan *username* dan *password* salah/benar.
2. Uji operasi tambah barang dan memastikan data tertampil di `JTable` dan tersimpan di database MySQL.
3. Simulasi penyewaan barang (Sistem harus mengecek jika `stok` 0 maka sewa ditolak, dan jika sukses stok berkurang 1).
4. Simulasi pengembalian barang (Sistem menghitung jeda hari dari tanggal pinjam ke kembali, lalu mengalikannya dengan harga sewa per hari, dan stok barang bertambah 1 kembali).
