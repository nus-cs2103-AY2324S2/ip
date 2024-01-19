import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (this.tasks.size() < MAX_ITEMS) {
            parseTask(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(this.tasks.get(this.tasks.size() - 1));
            System.out.println(
                    "Now you have " + this.tasks.size() +  " task" +
                    (this.tasks.size() == 1 ? "" : "s") + " in the list");
        } else {
            System.out.println("The task list is full.");
        }
    }

    public void parseTask(String task) {
        String[] parsed = task.split(" ", 2);
        if (parsed.length <= 1) {
            System.out.println("Please enter a task name");
            return;
        }
        String taskType = parsed[0].toLowerCase();
        String taskDesc = parsed[1];
        switch (taskType) {
            case "todo":
                this.tasks.add(new ToDo(taskDesc));
                break;
            case "deadline":
                String[] parsedDeadline = taskDesc.split(" /by ");
                if (parsedDeadline.length <= 1) {
                    System.out.println("Please enter valid deadline format");
                    return;
                }
                String deadlineName = parsedDeadline[0];
                String by = parsedDeadline[1];
                this.tasks.add(new Deadline(deadlineName, by));
                break;
            case "event":
                String[] parsedEvent = taskDesc.split(" /from | /to ");
                if (parsedEvent.length <= 2) {
                    System.out.println("Please enter valid event format");
                }
                String eventName = parsedEvent[0];
                String start = parsedEvent[1];
                String end = parsedEvent[2];
                this.tasks.add(new Event(eventName, start, end));
                break;
            default:
                System.out.println("Please enter valid task type");
                return;
        }
    }

    public void markTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Task index is out of range.");
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        currTask.toString();
    }

    public void unmarkTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Task index is out of range.");
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        currTask.toString();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.toString());
            }
        }
    }
}
