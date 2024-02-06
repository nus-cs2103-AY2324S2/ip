public class Event extends Task{
    private String time;
    public Event(String name, Boolean status, String time) {
        super(name, status);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override public String toString() {
        return "[E]"+super.toString()+ "(" +time+ ")";
    }
}
