package UAP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GoDriveRentalSystem rentalSystem = new GoDriveRentalSystem();

        int menu = 0;
        do {
            System.out.println("\n====== MENU GO DRIVE RENTAL SYSTEM ======");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            try {
                menu = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                continue;
            }

            switch (menu) {
                case 1:
                    System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
                    String jenis = scanner.nextLine();
                    System.out.print("Masukkan kode kendaraan: ");
                    String kode = scanner.nextLine();
                    System.out.print("Masukkan nama kendaraan: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan harga sewa per hari: ");
                    double harga = Double.parseDouble(scanner.nextLine());

                    if (jenis.equalsIgnoreCase("mobil")) {
                        System.out.print("Masukkan kapasitas kursi: ");
                        int kursi = Integer.parseInt(scanner.nextLine());
                        rentalSystem.tambahKendaraan(new Mobil(kode, nama, harga, kursi));
                    } else if (jenis.equalsIgnoreCase("motor")) {
                        System.out.print("Masukkan jenis transmisi (Matik/Manual): ");
                        String transmisi = scanner.nextLine();
                        rentalSystem.tambahKendaraan(new Motor(kode, nama, harga, transmisi));
                    } else {
                        System.out.println("[ERROR] Jenis kendaraan tidak valid!");
                    }
                    break;

                case 2:
                    rentalSystem.tampilkanDaftarKendaraan();
                    break;

                case 3:
                    System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
                    String kodeSewa = scanner.nextLine();
                    System.out.print("Masukkan durasi sewa (dalam hari): ");
                    int durasi = Integer.parseInt(scanner.nextLine());
                    System.out.print("Apakah Anda Member VIP? (y/n): ");
                    boolean isVIP = scanner.nextLine().equalsIgnoreCase("y");

                    try {
                        rentalSystem.sewaKendaraan(kodeSewa, durasi, isVIP);
                    } catch (KendaraanException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
                    String kodeKembali = scanner.nextLine();
                    rentalSystem.kembalikanKendaraan(kodeKembali);
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan Go Drive Rental System!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (menu != 5);

        scanner.close();
    }
}