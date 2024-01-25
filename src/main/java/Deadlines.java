public class Deadlines extends Task {
    String end;
    public Deadlines(String c, String end) {
        super(c);
        this.end = end.split("by ")[1];

    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString() +"(by: "+this.end+")";
        return s;
    }
}
