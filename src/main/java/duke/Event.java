package duke;

public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static void addEventTask(TaskList taskList, String description, String start, String end) throws DukeException {
        taskList.addTask(new Event(description, start, end));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.size() - 1).getStatusIcon());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
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
