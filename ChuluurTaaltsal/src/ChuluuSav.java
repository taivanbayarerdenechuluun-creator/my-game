import java.util.Random;

public class ChuluuSav {
    private int niitChuluu;
    private int ugugdsunChuluu;
    private Random rand;

    public ChuluuSav(int niitChuluu) throws TogloomAldaa {
        if (niitChuluu < 2) {
            throw new TogloomAldaa("Chuluunii too hamagiin bagadaa 2 baih yostoi!", 1);
        }
        this.niitChuluu = niitChuluu;
        this.ugugdsunChuluu = 0;
        this.rand = new Random();
    }

    public int chuluuOlgo() throws TogloomAldaa {
        int uldsen = niitChuluu - ugugdsunChuluu;
        if (uldsen <= 0) {
            throw new TogloomAldaa("Sav hooson bollloo!", 2);
        }
        int avah = rand.nextInt(Math.min(uldsen, 9)) + 1;
        ugugdsunChuluu += avah;
        return avah;
    }

    public void chuluuHuleen(int too) {
        ugugdsunChuluu -= too;
        if (ugugdsunChuluu < 0) ugugdsunChuluu = 0;
    }

    public boolean chuluuBainaUu() {
        return (niitChuluu - ugugdsunChuluu) > 0;
    }

    public int getNiitChuluu() { return niitChuluu; }
    public int getUldsenChuluu() { return niitChuluu - ugugdsunChuluu; }

    @Override
    public String toString() {
        return String.format("ChuluuSav [Niit: %d | Uldsen: %d]",
                niitChuluu, niitChuluu - ugugdsunChuluu);
    }
}
