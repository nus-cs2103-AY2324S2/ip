public class Msg {
    private String text;
    private static final String divider = "____________________________________________________________";

    public Msg(String text) {
        this.text = text + "\n";
    }
    @Override
    public String toString() {
        return this.text + divider + "\n";
    }
}
