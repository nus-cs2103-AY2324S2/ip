package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static void addToDoTask(TaskList taskList, String description) throws DukeException {
        taskList.addTask(new ToDo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.size() - 1).getStatusIcon());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    @Override
    public String getStatusIcon() {
        return "[T] " + super.getStatusIcon() + " " + description;
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
