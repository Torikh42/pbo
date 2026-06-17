package model;

import java.sql.Date;

public class Rental {
    private int idRental;
    private String namaPenyewa;
    private String idKoleksi;
    private Date tanggalPinjam;
    private Date tanggalKembali;
    private double totalBiaya;
    private String status;

    public Rental(int idRental, String namaPenyewa, String idKoleksi, Date tanggalPinjam, Date tanggalKembali, double totalBiaya, String status) {
        this.idRental = idRental;
        this.namaPenyewa = namaPenyewa;
        this.idKoleksi = idKoleksi;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.totalBiaya = totalBiaya;
        this.status = status;
    }

    public int getIdRental() { return idRental; }
    public void setIdRental(int idRental) { this.idRental = idRental; }

    public String getNamaPenyewa() { return namaPenyewa; }
    public void setNamaPenyewa(String namaPenyewa) { this.namaPenyewa = namaPenyewa; }

    public String getIdKoleksi() { return idKoleksi; }
    public void setIdKoleksi(String idKoleksi) { this.idKoleksi = idKoleksi; }

    public Date getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(Date tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }

    public Date getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(Date tanggalKembali) { this.tanggalKembali = tanggalKembali; }

    public double getTotalBiaya() { return totalBiaya; }
    public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
