package UAP;

import java.util.ArrayList;

public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan;

    public GoDriveRentalSystem() {
        daftarKendaraan = new ArrayList<>();

        daftarKendaraan.add(new Mobil("MBL01", "Toyota Avanza", 350000, 7));
        daftarKendaraan.add(new Mobil("MBL02", "Daihatsu Sigra", 300000, 7));
        daftarKendaraan.add(new Mobil("MBL03", "Honda Brio", 280000, 5));
        daftarKendaraan.add(new Motor("MTR01", "Honda Vario", 80000, "Matik"));
        daftarKendaraan.add(new Motor("MTR02", "Yamaha NMAX", 100000, "Matik"));
        daftarKendaraan.add(new Motor("MTR03", "Kawasaki KLX", 90000, "Manual"));
    }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println(
                "[INFO] Kendaraan berhasil ditambahkan: " + k.getNamaKendaraan() + " (" + k.getKodeKendaraan() + ")");
    }

    public void tampilkanDaftarKendaraan() {
        System.out.println("\n=== DAFTAR ARMADA GODRIVE ===");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada armada yang terdaftar.");
            return;
        }
        int i = 1;
        for (Kendaraan k : daftarKendaraan) {
            System.out.print(i + ". ");
            k.tampilInfo();
            i++;
        }
    }

    public void sewaKendaraan(String kode, int lamaSewa, boolean isVIP) throws KendaraanException {
        Kendaraan kSewa = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                kSewa = k;
                break;
            }
        }

        if (kSewa == null || !kSewa.isTersedia()) {
            throw new KendaraanException("Kendaraan dengan kode " + kode
                    + " gagal disewa. Alasan: Kendaraan sedang disewa atau tidak ditemukan!");
        }

        kSewa.setTersedia(false);

        double biayaDasar = kSewa.hitungBiayaDasar(lamaSewa);
        double biayaTambahan = 0;
        String infoTambahan = "";

        if (kSewa instanceof Mobil) {
            Mobil m = (Mobil) kSewa;
            if (m.getJumlahKursi() > 5) {
                biayaTambahan = 50000;
                infoTambahan = String.format("Tambahan Kursi (>5): Rp %.0f", biayaTambahan);
            }
        } else if (kSewa instanceof Motor) {
            Motor m = (Motor) kSewa;
            if (m.getJenisTransmisi().equalsIgnoreCase("Matik")) {
                biayaTambahan = 10000 * lamaSewa;
                infoTambahan = String.format("Tambahan Asuransi Matik: Rp %.0f", biayaTambahan);
            }
        }

        double subtotal = biayaDasar + biayaTambahan;
        double diskonVIP = isVIP ? (subtotal * 0.10) : 0;
        double diskonLama = lamaSewa > 7 ? (subtotal * 0.05) : 0;

        double totalAkhir = subtotal - diskonVIP - diskonLama;

        System.out.println("\n=== TRANSAKSI SEWA GODRIVE ===");
        System.out.println("Kendaraan Berhasil Disewa!");
        System.out.println("Unit              : " + kSewa.getNamaKendaraan() + " (" + kSewa.getKodeKendaraan() + ")");
        System.out.println("Lama Sewa         : " + lamaSewa + " hari");
        System.out.printf("Biaya Dasar Harian: Rp %.0f\n", biayaDasar);

        if (biayaTambahan > 0) {
            System.out.println(infoTambahan);
        }
        if (diskonVIP > 0) {
            System.out.printf("Diskon Member VIP (10%%): -Rp %.0f\n", diskonVIP);
        }
        if (diskonLama > 0) {
            System.out.printf("Diskon > 7 hari (5%%): -Rp %.0f\n", diskonLama);
        }
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BIAYA AKHIR : Rp %.0f\n", totalAkhir);
    }

    public void kembalikanKendaraan(String kode) {
        boolean ditemukan = false;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                ditemukan = true;
                if (!k.isTersedia()) {
                    k.setTersedia(true);
                    System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() + " (" + kode
                            + ") berhasil dikembalikan. Status: Tersedia.");
                } else {
                    System.out.println("[INFO] Kendaraan " + k.getNamaKendaraan() + " (" + kode
                            + ") sudah berstatus tersedia (belum disewa).");
                }
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("[ERROR] Kendaraan dengan kode " + kode + " tidak ditemukan.");
        }
    }
}