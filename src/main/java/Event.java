import java.util.ArrayList;

public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static void addEventTask(ArrayList<Task> tasks, int taskCounter, String description, String start, String end) {
        tasks.add(new Event(description, start, end));
        taskCounter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).getStatusIcon());
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.");
    }

    @Override
    public String getStatusIcon() {
        return "[E] " + super.getStatusIcon() + " " + description + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
