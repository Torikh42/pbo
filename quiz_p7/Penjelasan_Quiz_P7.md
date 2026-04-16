# Dokumen Laporan Tugas PBO - Pertemuan 7

## Soal 1: Sistem Perpustakaan

### Bagian A: Analisis
**1. Apa yang dimaksud dengan class dan object dalam konteks kasus ini?**
*   **Class (Kelas)**: Cetak biru (blueprint) atau kerangka untuk membuat sebuah buku. Class `Buku` mendefinisikan sifat-sifat umum (atribut) dan perilaku (method) yang akan dimiliki oleh semua buku.
*   **Object (Objek)**: Realisasi nyata dari class. Contohnya `buku1` yang berisi data judul "Laskar Pelangi".

**2. Berikan contoh object nyata dari class yang akan dibuat**
*   `buku1` (Judul: Laskar Pelangi, Pengarang: Andrea Hirata)
*   `buku2` (Judul: Bumi Manusia, Pengarang: Pramoedya Ananta Toer)

**Identifikasi:**
*   **Atribut**: `judul` (String), `pengarang` (String), `tersedia` (boolean).
*   **Method**: `pinjamBuku()`, `kembalikanBuku()`, `tampilInfo()`.
*   **Kelemahan tanpa class**: Kode sulit dikelola (spaghetti code), variabel sulit dikelompokkan, dan redundansi tinggi jika data bertambah banyak.

### Bagian B: Implementasi

#### 1. Class Buku (`Buku.java`)
```java
package quiz_p7;

public class Buku {
    private String judul;
    private String pengarang;
    private boolean tersedia;

    public Buku(String judul, String pengarang) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tersedia = true;
    }

    public void pinjamBuku() {
        if (tersedia) {
            tersedia = false;
            System.out.println("Buku '" + judul + "' berhasil dipinjam.");
        } else {
            System.out.println("Maaf, buku '" + judul + "' sedang tidak tersedia.");
        }
    }

    public void kembalikanBuku() {
        if (!tersedia) {
            tersedia = true;
            System.out.println("Buku '" + judul + "' berhasil dikembalikan.");
        } else {
            System.out.println("Buku '" + judul + "' sudah tersedia (belum dipinjam).");
        }
    }

    public void tampilInfo() {
        System.out.println("--- Informasi Buku ---");
        System.out.println("Judul: " + judul);
        System.out.println("Pengarang: " + pengarang);
        System.out.println("Status: " + (tersedia ? "Tersedia" : "Dipinjam"));
        System.out.println("----------------------");
    }
}
```

#### 2. Main Method & Simulasi (`MainSoal1.java`)
```java
package quiz_p7;

public class MainSoal1 {
    public static void main(String[] args) {
        System.out.println("=== Simulasi Sistem Perpustakaan ===");
        Buku b1 = new Buku("Laskar Pelangi", "Andrea Hirata");
        Buku b2 = new Buku("Bumi Manusia", "Pramoedya Ananta Toer");

        b1.tampilInfo();
        System.out.println("\n>> Meminjam Laskar Pelangi:");
        b1.pinjamBuku();
        b1.tampilInfo();
        
        System.out.println("\n>> Mencoba pinjam lagi:");
        b1.pinjamBuku();

        System.out.println("\n>> Mengembalikan buku:");
        b1.kembalikanBuku();
        b1.tampilInfo();
    }
}
```

### Bagian C: Analisis Lanjutan
**1. Mengapa object lebih baik untuk 100 buku?**
Karena tidak perlu membuat 300 variabel terpisah. Cukup gunakan Array atau List of Object `Buku[]`.
**2. Skalabilitas & Maintainability:**
Perubahan logic (misal: tambah denda) cukup dilakukan di satu tempat (Class Buku) dan otomatis berlaku untuk semua buku.

---

## Soal 2: Game Character

### Bagian A: Analisis Konsep
**1. Mengapa method attack() di dalam class?**
Sesuai prinsip **Encapsulation**, perilaku karakter harus menyatu dengan datanya.
**2. Hubungan Object:**
Serangan adalah interaksi di mana object pengirim pesan (`attacker`) memodifikasi state object penerima (`target`).
**3. Jika parameter hanya integer?**
Karakter tidak tahu *musuh mana* yang diserang, sehingga tidak bisa mengubah HP musuh secara langsung.

### Bagian B: Implementasi

#### 1. Class Character (`Character.java`)
```java
package quiz_p7;

public class Character {
    private String nama;
    private int hp;
    private int attackPower;

    public Character(String nama, int hp, int attackPower) {
        this.nama = nama;
        this.hp = Math.max(hp, 0); 
        this.attackPower = attackPower;
    }

    private void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public void attack(Character target) {
        System.out.println(this.nama + " menyerang " + target.nama + "!");
        target.takeDamage(this.attackPower);
    }

    public void displayStatus() {
        System.out.println(nama + " [HP: " + hp + ", ATK: " + attackPower + "]");
    }
}
```

#### 2. Simulasi Pertarungan (`MainSoal2.java`)
```java
package quiz_p7;

public class MainSoal2 {
    public static void main(String[] args) {
        System.out.println("=== Simulasi Game ===");
        Character hero = new Character("Arthur", 100, 40);
        Character boss = new Character("Dragon", 100, 20);

        hero.displayStatus();
        boss.displayStatus();

        System.out.println("\n>> Arthur menyerang Dragon!");
        hero.attack(boss);
        boss.displayStatus();

        System.out.println("\n>> Dragon menyerang Arthur!");
        boss.attack(hero);
        hero.displayStatus();
    }
}
```

### Bagian C: Analisis Lanjutan
**1. Masalah HP Negatif:**
Dapat diperbaiki dengan pengecekan `if (hp < 0) hp = 0;` di dalam method `takeDamage`.
**2. Keunggulan PBO:**
Memudahkan penambahan fitur baru (misal: Character baru, Skill, Defense) tanpa merusak kode yang sudah ada.

---

## Bagian D: Hasil Output Program (Execution Logs)

**Output Simulasi 1:**
```text
=== Simulasi Sistem Perpustakaan ===
--- Informasi Buku ---
Judul: Laskar Pelangi
Pengarang: Andrea Hirata
Status: Tersedia
----------------------

>> Meminjam Laskar Pelangi:
Buku 'Laskar Pelangi' berhasil dipinjam.
--- Informasi Buku ---
Judul: Laskar Pelangi
Pengarang: Andrea Hirata
Status: Dipinjam
----------------------

>> Mencoba pinjam lagi:
Maaf, buku 'Laskar Pelangi' sedang tidak tersedia.

>> Mengembalikan buku:
Buku 'Laskar Pelangi' berhasil dikembalikan.
--- Informasi Buku ---
Judul: Laskar Pelangi
Pengarang: Andrea Hirata
Status: Tersedia
----------------------
```

**Output Simulasi 2:**
```text
=== Simulasi Game ===
Arthur [HP: 100, ATK: 40]
Dragon [HP: 100, ATK: 20]

>> Arthur menyerang Dragon!
Arthur menyerang Dragon!
Dragon [HP: 60, ATK: 20]

>> Dragon menyerang Arthur!
Dragon menyerang Arthur!
Arthur [HP: 80, ATK: 40]
```
