public class Deadline extends Task {
    private String end;
    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }
    @Override
    public String getType(){
        return "[D]";
    }
    @Override
    public String getTime(){
        return "(by: " + end + ")";
    }
}
