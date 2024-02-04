public class Deadline extends Task {
    private String date;
    public Deadline (String desc, String date) {
        super(desc);
        this.date = date;
    }
    public Deadline (String desc, boolean isDone, String date) {
        super(desc, isDone);
        this.date = date;
    }
    @Override
    public String saveStorage(){
        String details = super.saveStorage();
        details = "D|" + details +"|" +  date;
        return details;
    }
    public String toString() {
        return "[D]" + super.toString() + "(" + date + ")";
    }
}
