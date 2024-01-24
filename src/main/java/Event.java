public class Event extends Task {

    String start;
    String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[E][X] ";
        } else {
            checkbox = "[E][ ] ";
        }
        return checkbox + name + "(from: " + start +  " to " + end + ")";
    }
}
