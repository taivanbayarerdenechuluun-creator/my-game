import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║         CHULUU TAALTSAH TOGLOOM - OOP        ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        while (running) {
            System.out.println("\nUNDSEN TSES:");
            System.out.println("  1. Shine togloom ehluuleh");
            System.out.println("  2. Omnoh dungiig harah");
            System.out.println("  3. Dunguud erembeleh");
            System.out.println("  4. Togloch haih");
            System.out.println("  0. Garah");
            System.out.print("Songolt: ");

            String songoolt = sc.nextLine().trim();

            switch (songoolt) {
                case "1":
                    shineTogloom(sc);
                    break;
                case "2":
                    dunHaruulah();
                    break;
                case "3":
                    dunErembeleh();
                    break;
                case "4":
                    System.out.print("Haih toglochiin ner: ");
                    String ner = sc.nextLine().trim();
                    toglochHaih(ner);
                    break;
                case "0":
                    System.out.println("\nTogloomos garlaa. Bayartai!");
                    running = false;
                    break;
                default:
                    System.out.println("  Buruu songolt. Dahin oruulna uu.");
            }
        }
        sc.close();
    }

    private static void shineTogloom(Scanner sc) {
        TogloomManager manager = new TogloomManager();
        try {
            manager.togloomEhluuleh(sc);
            boolean duussav = false;
            while (!duussav) {
                duussav = manager.eeljTogloh(sc);
            }
            manager.etstDunHaruulah();
        } catch (TogloomAldaa e) {
            System.out.println("\nTogloomiin aldaa: " + e.getMessage());
            System.out.println("   Aldaanii kod: " + e.getAldaaToo());
        } catch (IOException e) {
            System.out.println("\nFailiin aldaa: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nGenetiin aldaa: " + e.getMessage());
        }
    }

    private static void dunHaruulah() {
        try {
            TogloomDun dun = new TogloomDun("togloom_dun.txt");
            dun.loadFromFile("togloom_dun.txt");
            dun.dunHaruulah();
        } catch (IOException e) {
            System.out.println("  Dun fail oldsongui: " + e.getMessage());
        }
    }

    private static void dunErembeleh() {
        try {
            TogloomDun dun = new TogloomDun("togloom_dun.txt");
            dun.loadFromFile("togloom_dun.txt");
            dun.sortScores();
            dun.dunHaruulah();
            dun.saveToFile("togloom_dun.txt");
        } catch (IOException e) {
            System.out.println("  Failiin aldaa: " + e.getMessage());
        }
    }

    private static void toglochHaih(String ner) {
        try {
            TogloomDun dun = new TogloomDun("togloom_dun.txt");
            dun.loadFromFile("togloom_dun.txt");
            int idx = dun.searchPlayer(ner);
            if (idx >= 0) {
                dun.toglochTogloomHaruulah(ner);
            } else {
                System.out.println("  '" + ner + "' togloch oldsongui.");
            }
        } catch (IOException e) {
            System.out.println("  Failiin aldaa: " + e.getMessage());
        }
    }
}
