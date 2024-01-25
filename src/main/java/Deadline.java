public class Deadline extends Task {
    private String date;
    public Deadline (String desc,int type, String date) {
        super(desc, type);
        this.date = date;
    }
    public String toString() {
        return super.toString() + "(" + date + ")";
    }
}
