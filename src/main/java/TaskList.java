import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) throws DukeException {
        if (this.tasks.size() < MAX_ITEMS) {
            try {
                parseTask(task);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
                System.out.println(
                        "\tNow you have " + this.tasks.size() + " task" +
                                (this.tasks.size() == 1 ? "" : "s") + " in the list");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new DukeException("The task list is full.");
        }
    }

    public void parseTask(String task) throws DukeException {
        String[] parsed = task.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            throw new DukeException("OOPS! Please enter a task name");
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
                    throw new DukeException("Please enter a valid deadline format");
                }
                String deadlineName = parsedDeadline[0];
                String by = parsedDeadline[1];
                this.tasks.add(new Deadline(deadlineName, by));
                break;
            case "event":
                String[] parsedEvent = taskDesc.split(" /from | /to ");
                if (parsedEvent.length <= 2) {
                    throw new DukeException("Please enter valid event format");
                }
                String eventName = parsedEvent[0];
                String start = parsedEvent[1];
                String end = parsedEvent[2];
                this.tasks.add(new Event(eventName, start, end));
                break;
            default:
                throw new DukeException("Please enter valid task type");
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (this.tasks.size() == 0) {
            throw new DukeException("Task index is out of range.");
        }
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Index out of range");
        }
        Task deletedTask = this.tasks.remove(index - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + deletedTask.toString());
        System.out.println(
                "\tNow you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    public void markTask(int index) throws DukeException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        currTask.toString();
    }

    public void unmarkTask(int index) throws DukeException{
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet:");
        currTask.toString();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("\tThe task list is empty.");
        } else {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println("\t" + (i + 1) + "." + currTask.toString());
            }
        }
    }
}
