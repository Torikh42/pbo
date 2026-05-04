# Analisis Polimorfisme pada Class Enemy

Berdasarkan implementasi kode pada folder `p9`, berikut adalah hasil analisisnya:

## 1. Mengapa hasil output bisa berbeda walaupun method yang dipanggil sama?

Hasil output berbeda karena adanya konsep **Dynamic Method Binding** (atau Late Binding). 

Meskipun dalam loop kita menggunakan referensi tipe induk (`Enemy e`), Java Virtual Machine (JVM) akan memeriksa **tipe objek sebenarnya** (actual object type) pada saat runtime (saat program berjalan). 
- Jika objek tersebut adalah instance dari `Zombie`, maka method `attack()` milik `Zombie` yang dijalankan.
- Jika objek tersebut adalah instance dari `Pocong`, maka method `attack()` milik `Pocong` yang dijalankan.

Hal ini dimungkinkan karena setiap subclass telah melakukan **Overriding** terhadap method `attack()` dari class induknya.

## 2. Ini termasuk jenis polimorfisme apa?

Ini termasuk dalam jenis **Runtime Polymorphism** (atau Dynamic Polymorphism).

Karakteristik utamanya adalah:
- Terjadi pada saat program sedang berjalan (Runtime).
- Dicapai melalui mekanisme **Method Overriding**.
- Memungkinkan sebuah referensi tipe induk untuk merujuk ke objek tipe anak dan menjalankan perilaku yang spesifik sesuai dengan objek tersebut.

---
