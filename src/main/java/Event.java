public class Event extends Task{
    private String from;
    private String to;
    private static final String symbol = "E";

    public Event(String des, String f, String t){
        super(des);
        from = f;
        to = t;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.from + " to: " + this.to + ")";
        return s;
    }
}
