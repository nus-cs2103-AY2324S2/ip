public class EventTask extends Task {

    private String start, end;

    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {

        String taskString = "[E] ";

        String temp = "[ ] ";

        if(this.isDone()){
            temp = "[X] ";
        }

        return taskString + temp + this.getName() + "(from: " + this.start + "to: " + this.end + ")";
    }

}