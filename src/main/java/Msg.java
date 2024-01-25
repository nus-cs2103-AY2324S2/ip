public class Msg {
    private String text;
    private static final String divider = "____________________________________________________________";
    private boolean need_divider = true;

    public Msg(String text) {
        // by default msg will have divider
        this.text = text + "\n" + Msg.divider + "\n";
    }

    public Msg(String text, boolean need_divider) {
        if (need_divider) {
            this.text = text + "\n" + Msg.divider + "\n";
        } else {
            this.text = text + "\n";
        }

    }
    @Override
    public String toString() {
        return this.text;
    }
}
