import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TogloomDun implements Sortable, Saveable {

    public static class DunBichleg {
        String togloch1Ner;
        String togloch2Ner;
        int togloch1Chuluu;
        int togloch2Chuluu;
        String ylagch;
        String ognoo;

        public DunBichleg(String t1, String t2, int c1, int c2, String ylagch, String ognoo) {
            this.togloch1Ner = t1;
            this.togloch2Ner = t2;
            this.togloch1Chuluu = c1;
            this.togloch2Chuluu = c2;
            this.ylagch = ylagch;
            this.ognoo = ognoo;
        }

        @Override
        public String toString() {
            return String.format("%s vs %s | %d:%d | Yalagch: %s | %s",
                    togloch1Ner, togloch2Ner, togloch1Chuluu, togloch2Chuluu, ylagch, ognoo);
        }

        public String toFileString() {
            return togloch1Ner + "," + togloch2Ner + "," +
                   togloch1Chuluu + "," + togloch2Chuluu + "," + ylagch + "," + ognoo;
        }
    }

    private List<DunBichleg> dunList;
    private String filename;

    public TogloomDun(String filename) {
        this.dunList = new ArrayList<>();
        this.filename = filename;
    }

    public void dunNem(DunBichleg dun) { dunList.add(dun); }

    public void dunHaruulah() {
        if (dunList.isEmpty()) {
            System.out.println("  Odoogoor burtgeltei togloom alga.");
            return;
        }
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│             TOGLOOMIIN DUNGIIG BURTGEL             │");
        System.out.println("├─────────────────────────────────────────────────────┤");
        for (int i = 0; i < dunList.size(); i++) {
            System.out.printf("│ %2d. %s%n", (i + 1), dunList.get(i).toString());
        }
        System.out.println("└─────────────────────────────────────────────────────┘");
    }

    @Override
    public void sortScores() {
        int n = dunList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                DunBichleg a = dunList.get(j);
                DunBichleg b = dunList.get(j + 1);
                if (a.ylagch.compareTo(b.ylagch) > 0) {
                    dunList.set(j, b);
                    dunList.set(j + 1, a);
                }
            }
        }
        System.out.println("  Dunguud yalagchiin nereer erembelegdlee.");
    }

    @Override
    public int searchPlayer(String playerName) {
        for (int i = 0; i < dunList.size(); i++) {
            if (dunList.get(i).togloch1Ner.equalsIgnoreCase(playerName) ||
                dunList.get(i).togloch2Ner.equalsIgnoreCase(playerName)) {
                return i;
            }
        }
        return -1;
    }

    public void toglochTogloomHaruulah(String playerName) {
        boolean oldsOn = false;
        System.out.println("  [" + playerName + "] toglochiin togloomuud:");
        for (DunBichleg d : dunList) {
            if (d.togloch1Ner.equalsIgnoreCase(playerName) ||
                d.togloch2Ner.equalsIgnoreCase(playerName)) {
                System.out.println("    --> " + d.toString());
                oldsOn = true;
            }
        }
        if (!oldsOn) System.out.println("    Togloom oldsongui.");
    }

    @Override
    public void saveToFile(String fn) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fn, false))) {
            pw.println("# Chuluu taaltsah togloomiin dun");
            pw.println("# t1,t2,c1,c2,yalagch,ognoo");
            for (DunBichleg d : dunList) pw.println(d.toFileString());
        }
    }

    @Override
    public void loadFromFile(String fn) throws IOException {
        dunList.clear();
        File file = new File(fn);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                String[] p = line.split(",");
                if (p.length >= 6) {
                    dunList.add(new DunBichleg(p[0], p[1],
                        Integer.parseInt(p[2]), Integer.parseInt(p[3]), p[4], p[5]));
                }
            }
        }
    }

    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        for (DunBichleg d : dunList) sb.append(d.toFileString()).append("\n");
        return sb.toString();
    }

    public int getDunToo() { return dunList.size(); }
}
