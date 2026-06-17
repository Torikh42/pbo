package service;

import repository.RentalRepository;
import model.Rental;
import java.sql.Date;
import java.util.List;

public class RentalService {
    private RentalRepository repository;

    public RentalService() {
        this.repository = new RentalRepository();
    }

    public List<Rental> getAllRental() {
        return repository.getAllRental();
    }

    public void sewaBarang(String namaPenyewa, String idKoleksi) throws Exception {
        if (namaPenyewa == null || namaPenyewa.trim().isEmpty()) {
            throw new Exception("Nama Penyewa harus diisi.");
        }
        if (idKoleksi == null || idKoleksi.trim().isEmpty()) {
            throw new Exception("ID Koleksi harus diisi.");
        }

        Date today = new Date(System.currentTimeMillis());
        boolean success = repository.sewa(namaPenyewa.trim(), idKoleksi.trim(), today);
        if (!success) {
            throw new Exception("Sewa gagal. Cek ketersediaan stok atau pastikan ID Koleksi valid.");
        }
    }

    public void kembalikanBarang(String idRentalStr) throws Exception {
        if (idRentalStr == null || idRentalStr.trim().isEmpty()) {
            throw new Exception("Pilih data rental di tabel dahulu.");
        }

        int idRental;
        try {
            idRental = Integer.parseInt(idRentalStr.trim());
        } catch (NumberFormatException e) {
            throw new Exception("ID Rental tidak valid.");
        }

        Date today = new Date(System.currentTimeMillis());
        boolean success = repository.kembalikan(idRental, today);
        if (!success) {
            throw new Exception("Pengembalian gagal. Pastikan transaksi valid dan statusnya masih 'Dipinjam'.");
        }
    }
}
