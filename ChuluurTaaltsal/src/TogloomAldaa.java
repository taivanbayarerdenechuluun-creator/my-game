public class TogloomAldaa extends Exception {
    private int aldaaToo;

    public TogloomAldaa(String mesej) {
        super(mesej);
        this.aldaaToo = 0;
    }

    public TogloomAldaa(String mesej, int aldaaToo) {
        super(mesej);
        this.aldaaToo = aldaaToo;
    }

    public TogloomAldaa(String mesej, Throwable shaltgaan) {
        super(mesej, shaltgaan);
        this.aldaaToo = -1;
    }

    public int getAldaaToo() { return aldaaToo; }

    @Override
    public String toString() {
        return "TogloomAldaa [" + aldaaToo + "]: " + getMessage();
    }
}
