public class Events extends Task{
    protected String dateTime;

    public String start;
    public  String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public void print() {
        System.out.println("\t" + "[E]" + getStatusIcon() + getDescription() + "(from:" + start + " to" + end +")");
    }

    @Override
    public String toString() {
        return isDone + " event " + getDescription() + "/from" + start + " /to" + end;
    }

    @Override
    public String type() {
        return ("[E]");
    }
}
