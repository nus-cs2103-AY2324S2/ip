package virtue;

import java.util.ArrayList;
import java.util.List;

public class VirtueTaskList {
    // The task list.
    List<VirtueTask> taskList;

    public VirtueTaskList() {
        taskList = new ArrayList<>();
    }

    // Gets the number of tasks in the task list.
    private int numTasks() {
        return taskList.size();
    }

    // Gets the i-th task from the task list, where i is the index.
    private VirtueTask getTask(int index) {
        return taskList.get(index - 1);
    }

    // Adds a todo task to the task list.
    private void addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" Got it. I've added this task:");
        Virtue.printWithIndent("   " + todo);
        Virtue.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Virtue.printHorizontalLine();
    }

    // Adds a deadline task to the task list.
    private void addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" Got it. I've added this task:");
        Virtue.printWithIndent("   " + deadline);
        Virtue.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Virtue.printHorizontalLine();
    }

    // Adds an event task to the task list.
    private void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" Got it. I've added this task:");
        Virtue.printWithIndent("   " + event);
        Virtue.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Virtue.printHorizontalLine();
    }

    // Marks the i-th task in the task list as done, where i is the index.
    private void markTaskAsDone(int index) {
        VirtueTask task = getTask(index);
        task.markAsDone();
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" Nice! I've marked this task as done:");
        Virtue.printWithIndent("   " + task);
        Virtue.printHorizontalLine();
    }

    // Marks the i-th task in the task list as not done, where i is the index.
    private void markTaskAsNotDone(int index) {
        VirtueTask task = getTask(index);
        task.markAsNotDone();
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" OK, I've marked this task as not done yet:");
        Virtue.printWithIndent("   " + task);
        Virtue.printHorizontalLine();
    }

    // Prints out the task list.
    protected void printOut() {
        int numOfTasks = taskList.size();
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" Here are the tasks in your list:");

        for (int index = 1; index <= numOfTasks; index++) {
            Virtue.printWithIndent(" " + index + "." + taskList.get(index - 1));
        }

        Virtue.printHorizontalLine();
    }

    // Deletes the i-th task in the task list, where i is the index.
    private void deleteTask(int index) {
        VirtueTask deletedTask = taskList.remove(index - 1);
        int numTasks = numTasks();
        Virtue.printHorizontalLine();
        Virtue.printWithIndent(" Noted. I've removed this task:");
        Virtue.printWithIndent("   " + deletedTask);
        Virtue.printWithIndent(" Now you have " + numTasks + " tasks in the list.");
        Virtue.printHorizontalLine();
    }

    public void executeCommand(Command command) {
        switch (command.type) {
            case LIST:
                printOut();
                break;
            case MARK:
                markTaskAsDone(command.index);
                break;
            case UNMARK:
                markTaskAsNotDone(command.index);
                break;
            case DELETE:
                deleteTask(command.index);
                break;
            case TODO:
                addTodo(command.description);
                break;
            case DEADLINE:
                addDeadline(command.description, command.by);
                break;
            case EVENT:
                addEvent(command.description, command.from, command.to);
        }
    }

    public void addFromFile(String str) {
        String taskType = str.split(" \\| ")[0];
        String description = str.split(" \\| ")[2];
        int marked = Integer.parseInt(str.split(" \\| ")[1]);

        switch (taskType) {
            case "T":
                taskList.add(new Todo(description));
                break;
            case "D":
                taskList.add(new Deadline(description, str.split(" \\| ")[3]));
                break;
            case "E":
                taskList.add(new Event(description, str.split(" \\| ")[3], str.split(" \\| ")[4]));
        }

        if (marked == 1) {
            getTask(numTasks()).markAsDone();
        }
    }
}
