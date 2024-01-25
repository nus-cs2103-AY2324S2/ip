public class Events extends Task {
    String from;
    String to;
    public Events(String c, String from, String to) {
        super(c);
        this.from = from.split("from ")[1];
        this.to = to.split("to ")[1];
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + ")";
        return s;
    }
}
