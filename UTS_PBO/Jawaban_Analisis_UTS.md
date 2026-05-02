# Jawaban Analisis UTS PBO
**Nama:** Torikh Abdullah Naser
**NIM:** 2410512031

---

## Analisis Soal 1

### 1. Mengapa Enkapsulasi penting pada sistem ini?
Enkapsulasi sangat krusial dalam sistem *e-learning* karena menyangkut **integritas data**. Jika properti seperti `assignmentScore` pada class `Student` dibuat `public`, maka siapa saja (bahkan dari luar sistem/class lain yang tidak berhak) dapat mengubah nilainya menjadi angka yang tidak masuk akal (contoh: diubah menjadi `-50` atau `9999`).
Dengan enkapsulasi (membuat field menjadi `private` dan menyediakan metode `setter`), sistem dapat menerapkan *validation rule*—seperti memblokir perubahan skor jika angkanya di luar jangkauan `0-100`—sehingga data tetap akurat, konsisten, dan aman.

### 2. Mengapa Pewarisan (Inheritance) lebih baik dibanding duplikasi class?
Pewarisan memastikan sistem memenuhi prinsip **DRY (Don't Repeat Yourself)**. Baik `Student` maupun `Instructor` pada dasarnya adalah entitas `User` yang memerlukan properti `id`, `name`, dan fungsionalitas seperti `login()`. Jika tidak menggunakan pewarisan, kita harus menulis ulang variabel dan metode tersebut di setiap class secara terpisah.
Dengan pewarisan, kita hanya perlu menempatkan kode umum tersebut di `User` (Superclass), lalu menurunkannya. Ini membuat pemeliharaan kode (maintenance) lebih mudah, struktur lebih rapi, dan menambah tipe user baru (seperti `Admin`) ke depannya menjadi sangat cepat.

---

## Analisis Soal 2

### 1. Tiga (3) Masalah Desain OOP pada Kode Awal & Dampaknya:
1. **Tidak Ada Enkapsulasi (Variabel Default/Package-Private):**
   Variabel `name` dan `score` di class `User` tidak memiliki modifier `private`.
   **Dampak:** Variabel bisa dimodifikasi dari luar class secara sewenang-wenang (contoh: `student.score = 999;`) sehingga logika atau batasan sistem hancur.
2. **Pelanggaran Batasan Superclass oleh Subclass:**
   Pada class `Student`, method `calculate()` langsung mengakses `score = score + 10;`.
   **Dampak:** Subclass menjadi terlalu bergantung pada implementasi internal superclass (*tight coupling*). Jika sewaktu-waktu cara penyimpanan data `score` di `User` berubah, class `Student` otomatis akan *error* (rusak).
3. **Tidak Memiliki Konstruktor dan Identifier Valid:**
   Class tidak memaksa adanya data krusial saat objek diciptakan (konstruktor *default* kosong), dan tidak memiliki `final ID`.
   **Dampak:** Objek bisa di-instansiasi tanpa nama dan nilai (menimbulkan `NullPointerException`). Ketiadaan ID yang unik dan statis (`final`) akan menyulitkan pelacakan spesifik saat data tersimpan di basis data nantinya.

### 2. Kapan Inner Class Lebih Tepat Digunakan Dibanding Class Terpisah?
*Inner class* (seperti `ActivityLog` atau `LoginActivity`) tepat digunakan jika class tersebut **hanya masuk akal atau hanya relevan jika berdiri di dalam konteks class utamanya (Outer Class)**.
Inner class juga bisa secara langsung mengakses variabel dan *method private* milik *Outer class* (seperti langsung membaca `name` dan `id` dari `User` tanpa perlu *getter* tambahan). Jika logika log aktivitas hanya relevan dan bergantung kuat pada eksistensi objek `User` tertentu, maka *Inner Class* jauh lebih *clean* secara arsitektur daripada membuat Class terpisah yang memaksa pertukaran data rumit.

### 3. Bandingkan: Desain Awal vs Desain Hasil Refactoring

| Aspek | Desain Awal (Before) | Desain Refactoring (After) |
|---|---|---|
| **Enkapsulasi** | Tidak ada. Field `public`/default, bisa diubah bebas dari mana saja. | Semua field `private`. Akses hanya lewat getter/setter bervalidasi. |
| **Keamanan Data** | Rawan manipulasi. `student.score = 999` valid secara sintaks. | Aman. `setScore()` menolak nilai di luar rentang 0-100. |
| **Identitas Objek** | Tidak ada ID. Sulit melacak objek spesifik. | Memiliki `final id` yang unik dan tidak bisa diubah setelah dibuat. |
| **Keterlacakan** | Tidak ada logging. Perubahan data tidak terekam. | Setiap perubahan data otomatis tercatat lewat *Inner Class* `ActivityLog`. |
| **Scalability** | Sangat rendah. Menambah fitur atau tipe user baru berisiko merusak kode lama (*fragile*). | Tinggi. Struktur *inheritance* yang benar dan akses via getter/setter memastikan subclass baru dapat ditambahkan tanpa merusak logika superclass. |

**Kesimpulan:** Desain hasil refactoring jauh lebih *scalable*. Ini karena setiap class memiliki tanggung jawab yang jelas dan terisolasi (*Single Responsibility*). Perubahan pada satu bagian tidak berdampak berantai ke bagian lain, sehingga sistem siap berkembang tanpa mengorbankan stabilitas kode yang sudah ada.
