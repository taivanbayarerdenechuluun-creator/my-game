import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TogloomManager {

    private Togloch togloch1;
    private Togloch togloch2;
    private ChuluuSav chuluuSav;
    private TogloomDun dunBurtgel;
    private int zovNiilber;
    private int odoogiinEelj;
    private static final String DUN_FILE = "togloom_dun.txt";
    private static final String LOG_FILE = "togloom_log.txt";

    public TogloomManager() {
        this.dunBurtgel = new TogloomDun(DUN_FILE);
        this.odoogiinEelj = 1;
    }

    public void togloomEhluuleh(Scanner sc) throws TogloomAldaa, IOException {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       CHULUU TAALTSAH TOGLOOM            ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        System.out.print("1-r toglochiin ner: ");
        String ner1 = sc.nextLine().trim();
        System.out.print("2-r toglochiin ner: ");
        String ner2 = sc.nextLine().trim();

        if (ner1.isEmpty() || ner2.isEmpty()) {
            throw new TogloomAldaa("Toglochiin ner hoson baij bolohgui!", 3);
        }
        if (ner1.equalsIgnoreCase(ner2)) {
            throw new TogloomAldaa("Toglochiin ner ijil baij bolohgui!", 4);
        }

        System.out.print("Niit heden chuluu baih ve? (doод tal ni 4): ");
        int chuluuToo;
        try {
            chuluuToo = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new TogloomAldaa("Too bish utga orulsan!", e);
        }

        if (chuluuToo < 4) {
            throw new TogloomAldaa("Chuluu hamagiin bagadaa 4 baih yostoi!", 6);
        }

        chuluuSav = new ChuluuSav(chuluuToo);
        togloch1 = new Togloch(ner1);
        togloch2 = new Togloch(ner2);
        zovNiilber = chuluuToo;
        odoogiinEelj = 1;

        System.out.println("\nTogloom belee bollloo! Niit chuluu: " + chuluuToo);
        System.out.println("Zov niilber: " + zovNiilber);
        System.out.println("------------------------------------------\n");

        try {
            dunBurtgel.loadFromFile(DUN_FILE);
            System.out.println("(Omnoh " + dunBurtgel.getDunToo() + " togloomiin dun achaallagdlaa)");
        } catch (IOException e) {
            System.out.println("(Omnoh dun alga - shine burtgel ehlenе)");
        }

        chuluuAnhHuvaarila();
    }

    private void chuluuAnhHuvaarila() throws TogloomAldaa {
        int c1 = chuluuSav.chuluuOlgo();
        int c2 = chuluuSav.chuluuOlgo();
        togloch1.chuluuNem(c1);
        togloch2.chuluuNem(c2);
        System.out.println("** " + togloch1.getNer() + " --> " + c1 + " chuluu avlaa");
        System.out.println("** " + togloch2.getNer() + " --> " + c2 + " chuluu avlaa");
    }

    public boolean eeljTogloh(Scanner sc) throws TogloomAldaa, IOException {
        Togloch odoogiin = (odoogiinEelj == 1) ? togloch1 : togloch2;
        Togloch nuguu    = (odoogiinEelj == 1) ? togloch2 : togloch1;

        odoogiin.eeljNem();
        System.out.println("\n--- " + odoogiin.getNer() + "-iin eelj ---");
        System.out.println("  Tanii chuluu: " + odoogiin.getNiitChuluu());
        System.out.println("  " + nuguu.getNer() + "-iin chuluu: [nuugdsan]");
        System.out.println("  (Zov hariу = " + zovNiilber + " - ta medehgui tul taaj!)");

        System.out.print("  Niit heden chuluu gej taah ve? ");
        int taalt;
        try {
            taalt = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new TogloomAldaa("Too bish utga orulsan!", 7);
        }

        odoogiin.tuuhNem(taalt);
        int jinh = odoogiin.getNiitChuluu() + nuguu.getNiitChuluu();

        if (taalt == zovNiilber) {
            System.out.println("  >> ZOWTOI! Niit " + jinh + " chuluu (zov: " + zovNiilber + ")");
            System.out.println("  " + odoogiin.getNer() + " nugoogiin " +
                               nuguu.getNiitChuluu() + " chuluuniig avlaa!");
            odoogiin.chuluuNem(nuguu.getNiitChuluu());
            nuguu.setNiitChuluu(0);
            dunBurtgelHadgalah(odoogiin, nuguu, true);
            return true;

        } else {
            System.out.println("  X Buruu! Niit chuluu " + jinh + " baisan.");
            System.out.println("  Toglochid oor ooriin chuluugaa butsaaj avna.");

            int c1 = odoogiin.getNiitChuluu();
            int c2 = nuguu.getNiitChuluu();
            odoogiin.setNiitChuluu(0);
            nuguu.setNiitChuluu(0);
            chuluuSav.chuluuHuleen(c1 + c2);

            if (!chuluuSav.chuluuBainaUu()) {
                System.out.println("  Sav hoosон bollloo! Togloom duussav.");
                dunBurtgelHadgalah(odoogiin, nuguu, false);
                return true;
            }

            int sn1 = chuluuSav.chuluuOlgo();
            int sn2 = chuluuSav.chuluuOlgo();
            odoogiin.chuluuNem(sn1);
            nuguu.chuluuNem(sn2);
            System.out.println("  ** " + odoogiin.getNer() + " --> " + sn1 + " shine chuluu avlaa");
            System.out.println("  ** " + nuguu.getNer() + " --> " + sn2 + " shine chuluu avlaa");

            odoogiinEelj = (odoogiinEelj == 1) ? 2 : 1;
            return false;
        }
    }

    private void dunBurtgelHadgalah(Togloch odoogiin, Togloch nuguu, boolean zovTaalt)
            throws IOException {
        String ognoo = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String ylagchNer;
        if (zovTaalt) {
            ylagchNer = odoogiin.getNer();
        } else {
            ylagchNer = (togloch1.getNiitChuluu() >= togloch2.getNiitChuluu())
                    ? togloch1.getNer() : togloch2.getNer();
        }

        TogloomDun.DunBichleg dun = new TogloomDun.DunBichleg(
            togloch1.getNer(), togloch2.getNer(),
            togloch1.getNiitChuluu(), togloch2.getNiitChuluu(),
            ylagchNer, ognoo
        );
        dunBurtgel.dunNem(dun);
        dunBurtgel.saveToFile(DUN_FILE);

        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            pw.println("[" + ognoo + "] Togloom duussaa. Yalagch: " + ylagchNer);
        }

        System.out.println("\n  Dun hadgalagdlaa --> " + DUN_FILE);
    }

    public void etstDunHaruulah() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           TOGLOOMIIN DUN                 ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  " + togloch1.toString());
        System.out.println("║  " + togloch2.toString());
        System.out.println("╚══════════════════════════════════════════╝");

        if (togloch1.getNiitChuluu() > togloch2.getNiitChuluu()) {
            System.out.println("  YALAGCH: " + togloch1.getNer() +
                               " (" + togloch1.getNiitChuluu() + " chuluutai)");
        } else if (togloch2.getNiitChuluu() > togloch1.getNiitChuluu()) {
            System.out.println("  YALAGCH: " + togloch2.getNer() +
                               " (" + togloch2.getNiitChuluu() + " chuluutai)");
        } else {
            System.out.println("  Tentseen togloom!");
        }
    }

    public void dunBurtgelHaruulah() { dunBurtgel.dunHaruulah(); }
    public void dunErembeleh() { dunBurtgel.sortScores(); }
    public void toglochHaih(String ner) {
        int idx = dunBurtgel.searchPlayer(ner);
        if (idx >= 0) {
            dunBurtgel.toglochTogloomHaruulah(ner);
        } else {
            System.out.println("  '" + ner + "' togloch oldsongui.");
        }
    }
}
