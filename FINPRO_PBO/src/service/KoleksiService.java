package service;

import repository.KoleksiRepository;
import model.ActionFigure;
import model.ItemKoleksi;
import model.Manga;
import java.util.List;

public class KoleksiService {
    private KoleksiRepository repository;

    public KoleksiService() {
        this.repository = new KoleksiRepository();
    }

    public List<ItemKoleksi> getAllKoleksi() {
        return repository.getAllKoleksi();
    }

    public void simpanData(String id, String judul, String kategori, String hargaStr, String stokStr, String atribut, boolean isEditMode) throws Exception {
        id = id.trim();
        judul = judul.trim();
        hargaStr = hargaStr.trim();
        stokStr = stokStr.trim();
        atribut = atribut.trim();

        if (id.isEmpty() || judul.isEmpty() || hargaStr.isEmpty() || stokStr.isEmpty()) {
            throw new Exception("ID, Judul, Harga, dan Stok tidak boleh kosong.");
        }

        double harga;
        int stok;
        try {
            harga = Double.parseDouble(hargaStr);
            stok = Integer.parseInt(stokStr);
        } catch (NumberFormatException e) {
            throw new Exception("Harga dan Stok harus berupa angka yang valid.");
        }

        ItemKoleksi item;
        if ("Manga".equalsIgnoreCase(kategori)) {
            item = new Manga(id, judul, harga, stok, atribut);
        } else {
            item = new ActionFigure(id, judul, harga, stok, atribut);
        }

        if (isEditMode) {
            boolean success = repository.update(item);
            if (!success) {
                throw new Exception("Gagal mengupdate data ke database.");
            }
        } else {
            if (repository.getById(id) != null) {
                throw new Exception("Data dengan ID ini sudah ada! Gunakan ID lain atau klik data di tabel untuk Update.");
            }
            boolean success = repository.insert(item);
            if (!success) {
                throw new Exception("Gagal menyimpan data baru ke database.");
            }
        }
    }

    public void hapusData(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("Pilih data yang ingin dihapus.");
        }
        boolean success = repository.delete(id);
        if (!success) {
            throw new Exception("Gagal menghapus data dari database.");
        }
    }
}
