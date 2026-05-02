# Jawaban UTS Praktikum PBO

## Soal 1

### a. Perbedaan Paradigma Prosedural dan OOP dalam Sistem Informasi Manajemen Karyawan
*   **Paradigma Prosedural:** Pendekatan ini berfokus pada urutan langkah atau fungsi (prosedur) untuk memanipulasi data karyawan. Dalam sistem ini, struktur data (seperti array/record karyawan) dan fungsi (seperti hitungGaji) dibuat secara terpisah. Ketika sistem manajemen karyawan menjadi kompleks, pemisahan ini bisa membuat kode sulit dikelola dan rentan terhadap kesalahan (karena fungsi apa saja berpotensi mengubah data karyawan).
*   **Paradigma OOP (Object-Oriented Programming):** Pendekatan ini berfokus pada **objek** sebagai entitas utama yang menggabungkan data (atribut) dan perilaku (metode) dalam satu kesatuan (Enkapsulasi). Dalam sistem karyawan, kita dapat merancang *blueprint* berupa class `Karyawan`. Hal ini membuat kode lebih modular, data lebih terlindungi dari modifikasi sembarangan, dan sangat mendukung pengembangan lebih lanjut (misal penambahan Karyawan Tetap, Karyawan Kontrak) melalui konsep pewarisan (Inheritance) dan polimorfisme.

### b. Identifikasi Komponen OOP pada Sistem
*   **Class:** `Karyawan` (Berfungsi sebagai *blueprint* atau cetakan dasar yang mendefinisikan informasi dan kemampuan yang harus dimiliki oleh setiap pekerja).
*   **Object:** Instansiasi atau wujud nyata dari class `Karyawan` di dalam memori saat program berjalan. Contohnya, objek `karyawan1` (mewakili data Budi) dan objek `karyawan2` (mewakili data Siti).
*   **Attribute (State):** Variabel penyimpan data atau karakteristik yang dimiliki oleh karyawan. Contoh: `idKaryawan`, `nama`, `jabatan`, `gajiPokok`.
*   **Method (Behavior):** Fungsi yang mendefinisikan aksi atau operasi yang bisa dilakukan oleh atau pada objek karyawan tersebut. Contoh: `tampilkanInfo()`, `hitungGajiTotal()`, `setJabatan()`.

### c. Rancangan Struktur Sederhana Class Karyawan
(Kode implementasi Java telah dibuat pada file `Soal1_Karyawan.java`)

**Analisis Kode:** Pada implementasi class `Soal1_Karyawan`, kita menerapkan konsep dasar OOP dengan mendefinisikan *attribute* dengan hak akses `private` (seperti `idKaryawan`, `nama`, `jabatan`, dan `gajiPokok`) untuk melindungi data internal. Kita juga menyediakan *constructor* untuk menginisialisasi nilai awal secara terstruktur saat objek dibuat. Selain itu, terdapat *method* `tampilkanInfo()` untuk menampilkan data karyawan dan `hitungGajiTotal()` untuk merangkum logika perhitungan gaji beserta tunjangan. Hal ini menunjukkan penerapan *Enkapsulasi* di mana status (data) dan perilaku (fungsi) digabungkan dan dikelola di dalam satu entitas objek yang utuh.

---

## Soal 2

### 1. Analisis Kelemahan Kode dari Sisi Keamanan Data dan Kode Etik Penggunaan Data
Kode awal:
```java
public class Mahasiswa {
    public String nama;
    public int nim;
}
```
*   **Keamanan Data:** Menggunakan *access modifier* `public` pada atribut membuat data tersebut terbuka dan bisa diakses serta dimodifikasi secara langsung dari luar class tanpa ada validasi (kontrol). Hal ini sangat berbahaya karena program lain bisa mengubah nilai `nim` menjadi negatif, *null*, atau format yang salah, sehingga merusak integritas (konsistensi) data dalam sistem.
*   **Kode Etik Penggunaan Data:** Data seperti `nama` dan `nim` merupakan data pribadi (Personally Identifiable Information/PII). Membiarkan data sensitif ini terekspos secara publik melanggar prinsip hak akses minimal (*least privilege*). Dalam desain sistem yang baik, data pribadi harus dilindungi dan dijaga kerahasiaannya agar tidak mudah disalahgunakan atau dibaca oleh komponen/class lain yang tidak berhak.

### 2. Perbaikan Kode Menggunakan Konsep Encapsulation
(Kode implementasi Java telah dibuat pada file `Soal2_Mahasiswa.java`)

**Analisis Kode:** Pada perbaikan class `Soal2_Mahasiswa`, seluruh atribut (`nama` dan `nim`) diubah aksesnya menjadi `private` agar tidak bisa diakses dan diedit sembarangan secara langsung dari luar class (Data Hiding). Untuk berinteraksi dengan data tersebut, kita menyediakan *method getter* (seperti `getNama()` dan `getNim()`) yang berfungsi hanya untuk membaca nilai, serta *method setter* (seperti `setNama()` dan `setNim()`) untuk mengubah nilainya. Di dalam *method setter* tersebut, disisipkan logika validasi (misal, `nama` tidak boleh kosong dan `nim` harus bernilai lebih dari 0) sehingga sistem bisa menolak input yang tidak valid dan mencegah kerusakan integritas data.

### 3. Manfaat Penerapan Encapsulation dalam Sistem Enterprise
*   **Data Hiding & Security (Keamanan Data):** Menyembunyikan detail representasi data di dalam objek. Atribut dibuat `private` sehingga tidak bisa diakses langsung, melindungi data internal dari modifikasi yang tidak disengaja atau tidak sah.
*   **Validation & Control (Validasi dan Kontrol):** Dengan menggunakan *setter*, kita dapat menambahkan logika validasi aturan bisnis sebelum mengubah data. Misalnya, memastikan format NIM sudah benar atau memastikan `nama` tidak boleh kosong.
*   **Maintainability & Flexibility (Kemudahan Pemeliharaan):** Implementasi internal suatu class dapat diubah sewaktu-waktu (misal cara menyimpan data nim diubah dari `int` menjadi `String` di level *database*) tanpa merusak kode program lain yang memanggil *getter/setter*-nya, selama *interface* *method* tidak berubah.
*   **Read-Only / Write-Only Properties:** Encapsulation memungkinkan pengembang untuk menentukan kontrol akses yang lebih presisi (misalnya hanya membuat metode *getter* agar data bersifat *read-only* bagi komponen luar).

---

## Soal 3

### 1. Konsep Inheritance dan Overriding pada Kasus Person & Employee
*   **Inheritance (Pewarisan):** Dalam kasus ini, `Employee` adalah class turunan (*subclass/child class*) dari `Person` (*superclass/parent class*). Ini berarti `Employee` akan secara otomatis mewarisi atribut (`nama`, `umur`) dan *method* dari `Person`. Konsep ini mencegah duplikasi kode, karena kita tidak perlu mendeklarasikan ulang atribut `nama` dan `umur` pada class `Employee`, cukup menambahkan atribut spesifik seperti `idKaryawan`.
*   **Overriding:** Terjadi ketika *subclass* (`Employee`) mendeklarasikan ulang atau menimpa *method* yang sudah ada di *superclass* (`Person`) dengan *signature* (nama method dan parameter) yang sama persis. Contohnya, jika `Person` memiliki method `tampilkanInfo()` untuk mencetak nama dan umur, maka `Employee` dapat melakukan *override* pada method tersebut agar selain mencetak nama dan umur, ia juga turut mencetak `idKaryawan`.

### 2. Implementasi Kode Java (Inheritance & Overriding)
(Kode implementasi Java telah dibuat pada file `Soal3_SistemManajemen.java`)

**Analisis Kode:** Pada implementasi di `Soal3_SistemManajemen.java`, kita mendefinisikan class `Person` dengan atribut `nama` dan `umur` serta method `tampilkanInfo()`. Kemudian, class `Employee` menggunakan kata kunci `extends Person` untuk mewarisi sifat `Person`. `Employee` memiliki atribut tambahannya sendiri yaitu `idKaryawan`. Konstruktor `Employee` memanggil `super(nama, umur)` untuk menginisialisasi atribut induknya. Kita juga menggunakan anotasi `@Override` pada `tampilkanInfo()` di class `Employee` untuk menandakan bahwa kita memperluas perilaku method tersebut dari perilaku aslinya di `Person`.

### 3. Analisis Keuntungan Penggunaan Inheritance dalam Sistem Enterprise
*   **Reusability (Daur Ulang Kode):** Penulisan logika umum yang hanya dilakukan satu kali pada *superclass* (misal logika `Person`) membuat *subclass* (seperti `Employee`, `Pelanggan`, `Vendor`) dapat langsung memakainya. Ini sangat mengurangi redundansi baris kode pada program enterprise yang memiliki ratusan entitas.
*   **Maintainability (Kemudahan Pemeliharaan):** Jika terdapat perbaikan (*bug fix*) atau perubahan aturan pada level struktur data umum, pengembang hanya perlu mengubahnya di satu tempat (pada *superclass*). Perubahan tersebut secara otomatis akan diwariskan ke semua *subclass*, mencegah inkonsistensi.
*   **Extensibility (Skalabilitas Tinggi):** Sistem enterprise sering berkembang. Jika suatu hari perusahaan ingin menambah jabatan yang lebih spesifik (seperti class `Manager` atau `Director`), developer cukup membuat class baru yang mewarisi class `Employee` tanpa perlu membangun segalanya dari nol.
*   **Mendukung Polymorphism:** Inheritance adalah tulang punggung dari *Polymorphism*. Di sistem besar, hal ini memungkinkan array atau *collection* tipe data induk (`Person[]`) untuk menyimpan berbagai macam tipe data turunan (`Employee`, dll.) dan memanipulasinya secara seragam (misal dalam satu perulangan `for`), sehingga kode arsitektur bisnis menjadi lebih abstrak dan rapi.

---

## Soal 4

### 1. Analisis Kebutuhan User dan Tujuan Organisasi
*   **Kebutuhan User (Petugas/Pustakawan):** Membutuhkan antarmuka atau sistem yang mudah untuk mencatat data buku baru, melakukan pencatatan transaksi peminjaman buku, dan mencetak laporan sirkulasi dengan cepat tanpa harus menghitung secara manual.
*   **Tujuan Organisasi:** Melakukan digitalisasi inventaris untuk mencegah kehilangan aset buku, memonitor tingkat sirkulasi peminjaman sebagai tolak ukur minat baca, serta menyediakan laporan yang akurat secara *real-time* untuk keperluan audit dan evaluasi kinerja perpustakaan.

### 2. Rancang Struktur Class dan Method
Berdasarkan spesifikasi, berikut adalah rancangan desain berorientasi objeknya:
*   **Class `Buku`:** 
    *   *Attribute:* `judul`, `pengarang`, `statusDipinjam`
    *   *Method:* `getJudul()`, `isDipinjam()`, `setDipinjam()`
*   **Class `Perpustakaan` (Sebagai sistem utamanya):**
    *   *Attribute:* `daftarBuku` (List/Array), `jumlahPinjaman` (int)
    *   *Method:* 
        *   `tambahBuku(Buku b)` -> untuk menyimpan data buku.
        *   `pinjamBuku(Buku b)` -> untuk mengubah status buku dan merekap transaksi peminjaman.
        *   `hitungJumlahPinjaman()` -> mengembalikan (return) nilai integer total pinjaman.
        *   `tampilkanLaporan()` -> mencetak data rekap perpustakaan ke layar.

### 3. Implementasi Minimal 1 Class dengan Method (Void dan Non-Void)
(Kode implementasi Java telah dibuat pada file `Soal4_Perpustakaan.java`)

**Analisis Kode:** Pada implementasi `Soal4_Perpustakaan.java`, dibuat sebuah class `Perpustakaan` yang bertugas me-*manage* objek `Buku`. Di dalamnya terdapat implementasi *method* bertipe **void** (seperti `tambahBuku()` dan `tampilkanLaporan()`) yang berfungsi untuk melakukan suatu aksi perubahan *state* atau mencetak output tanpa mengembalikan suatu nilai ke pemanggilnya. Di sisi lain, terdapat juga *method* bertipe **non-void** yaitu `hitungJumlahPinjaman()` yang memiliki *return type* `int`, berfungsi mengembalikan nilai kalkulasi matematis (jumlah buku yang berstatus sedang dipinjam) agar dapat diolah lebih lanjut oleh sistem.

### 4. Evaluasi Efektivitas dan Efisiensi Desain
*   **Efektif:** Desain ini sudah sangat efektif karena secara langsung menjawab 3 *requirement* utama organisasi: fungsi penyimpanan terakomodasi di dalam `ArrayList<Buku>` dan `tambahBuku()`, fungsi kalkulasi terpenuhi melalui perulangan di `hitungJumlahPinjaman()`, dan fungsi rekapan tersedia di `tampilkanLaporan()`. Pemisahan entitas antara `Buku` dan `Perpustakaan` (prinsip *Single Responsibility*) membuat kode terstruktur dengan baik.
*   **Efisien:** Penggunaan tipe kembalian (void dan non-void) diterapkan pada konteks yang tepat (tidak memboroskan alokasi nilai *return* jika hanya untuk mencetak). Penggunaan *Collection* seperti `ArrayList` lebih efisien daripada *Array* statis tradisional, karena ukurannya bisa bertambah secara dinamis sesuai jumlah buku, menghemat pemakaian memori sejak awal program berjalan.
