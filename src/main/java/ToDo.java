import java.util.ArrayList;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static void addToDoTask(ArrayList<Task> tasks, int taskCounter, String description) {
        tasks.add(new ToDo(description));
        taskCounter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(taskCounter - 1).getStatusIcon());
        System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
    }

    @Override
    public String getStatusIcon() {
        return "[T] " + super.getStatusIcon() + " " + description;
    }

}
