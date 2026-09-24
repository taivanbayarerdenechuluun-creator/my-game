import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Togloch implements Saveable {
    private String ner;
    private int niitChuluu;
    private int eeljToo;
    private List<Integer> tuuhUshig;

    public Togloch(String ner) {
        this.ner = ner;
        this.niitChuluu = 0;
        this.eeljToo = 0;
        this.tuuhUshig = new ArrayList<>();
    }

    public Togloch(String ner, int niitChuluu) {
        this.ner = ner;
        this.niitChuluu = niitChuluu;
        this.eeljToo = 0;
        this.tuuhUshig = new ArrayList<>();
    }

    public Togloch(String ner, int niitChuluu, int eeljToo) {
        this.ner = ner;
        this.niitChuluu = niitChuluu;
        this.eeljToo = eeljToo;
        this.tuuhUshig = new ArrayList<>();
    }

    public String getNer() { return ner; }
    public int getNiitChuluu() { return niitChuluu; }
    public int getEeljToo() { return eeljToo; }

    public void setNiitChuluu(int n) { this.niitChuluu = n; }

    public void chuluuNem(int too) { this.niitChuluu += too; }
    public void chuluuButsaa(int too) {
        this.niitChuluu -= too;
        if (this.niitChuluu < 0) this.niitChuluu = 0;
    }
    public void eeljNem() { this.eeljToo++; }
    public void tuuhNem(int niilber) { tuuhUshig.add(niilber); }

    @Override
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.println(toFileString());
        }
    }

    @Override
    public void loadFromFile(String filename) throws IOException {}

    @Override
    public String toFileString() {
        return ner + "," + niitChuluu + "," + eeljToo;
    }

    @Override
    public String toString() {
        return String.format("Togloch: %-10s | Chuluu: %3d | Eelj: %d",
                ner, niitChuluu, eeljToo);
    }
}
