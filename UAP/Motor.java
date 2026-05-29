package UAP;

public class Motor extends Kendaraan {
    private String jenisTransmisi;

    public Motor(String kode, String nama, double harga, String jenisTransmisi) {
        super(kode, nama, harga);
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisTransmisi() {
        return jenisTransmisi;
    }

    @Override
    public void tampilInfo() {
        String status = isTersedia() ? "Tersedia" : "Disewa";
        System.out.printf("[MOTOR] Kode: %-6s | Nama: %-15s | Transmisi: %-6s | Tarif: Rp%.0f/hari | Status: %s\n",
                getKodeKendaraan(), getNamaKendaraan(), jenisTransmisi, getHargaSewaPerHari(), status);
    }
}
