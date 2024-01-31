import java.util.ArrayList;

public class Deadline extends Task {

    protected String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public static void addDeadlineTask(ArrayList<Task> tasks, int taskCounter, String description, String dueBy) {
        tasks.add(new Deadline(description, dueBy));
        taskCounter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(taskCounter - 1).getStatusIcon());
        System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
    }

    @Override
    public String getStatusIcon() {
        return "[D] " + super.getStatusIcon() + " " + description + " (by: " + dueBy + ")";
    }
}
