package quiz_p7_praktikum;

public class BukuElektronik extends Buku {
    private String formatFile;

    public BukuElektronik(String judul, String penulis, String formatFile) {
        super(judul, penulis);
        this.formatFile = formatFile;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("Format File: " + formatFile);
    }
}
