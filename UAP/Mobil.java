package UAP;

public class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kode, String nama, double harga, int jumlahKursi) {
        super(kode, nama, harga);
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    @Override
    public void tampilInfo() {
        String status = isTersedia() ? "Tersedia" : "Disewa";
        System.out.printf("[MOBIL] Kode: %-6s | Nama: %-15s | Kursi: %d | Tarif: Rp%.0f/hari | Status: %s\n",
                getKodeKendaraan(), getNamaKendaraan(), jumlahKursi, getHargaSewaPerHari(), status);
    }
}
