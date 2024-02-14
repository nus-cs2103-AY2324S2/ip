package virtue;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VirtueTaskList {
    // The task list.
    private List<VirtueTask> taskList;

    public VirtueTaskList() {
        taskList = new ArrayList<>();
    }

    // Gets the number of tasks in the task list.
    protected int numTasks() {
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
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Got it. I've added this task:");
        Ui.printWithIndent("   " + todo);
        Ui.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Ui.printHorizontalLine();
    }

    // Adds a deadline task to the task list.
    private void addDeadline(String description, VirtueDateTime by) {
        Deadline deadline = new Deadline(description, by);
        taskList.add(deadline);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Got it. I've added this task:");
        Ui.printWithIndent("   " + deadline);
        Ui.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Ui.printHorizontalLine();
    }

    // Adds an event task to the task list.
    private void addEvent(String description, VirtueDateTime from, VirtueDateTime to) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        int numTasks = numTasks();
        String sOrNone = numTasks == 1 ? "" : "s";
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Got it. I've added this task:");
        Ui.printWithIndent("   " + event);
        Ui.printWithIndent(" Now you have " + numTasks + " task" + sOrNone + " in the list.");
        Ui.printHorizontalLine();
    }

    // Marks the i-th task in the task list as done, where i is the index.
    private void markTaskAsDone(int index) {
        VirtueTask task = getTask(index);
        task.markAsDone();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Nice! I've marked this task as done:");
        Ui.printWithIndent("   " + task);
        Ui.printHorizontalLine();
    }

    // Marks the i-th task in the task list as not done, where i is the index.
    private void markTaskAsNotDone(int index) {
        VirtueTask task = getTask(index);
        task.markAsNotDone();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" OK, I've marked this task as not done yet:");
        Ui.printWithIndent("   " + task);
        Ui.printHorizontalLine();
    }

    // Prints out the task list.
    protected void printOut() {
        int numOfTasks = taskList.size();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Here are the tasks in your list:");

        for (int index = 1; index <= numOfTasks; index++) {
            Ui.printWithIndent(" " + index + "." + taskList.get(index - 1));
        }

        Ui.printHorizontalLine();
    }

    // Deletes the i-th task in the task list, where i is the index.
    private void deleteTask(int index) {
        VirtueTask deletedTask = taskList.remove(index - 1);
        int numTasks = numTasks();
        Ui.printHorizontalLine();
        Ui.printWithIndent(" Noted. I've removed this task:");
        Ui.printWithIndent("   " + deletedTask);
        Ui.printWithIndent(" Now you have " + numTasks + " tasks in the list.");
        Ui.printHorizontalLine();
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

    public void addFromFile(String str) throws VirtueDateTimeException {
        String taskType = str.split(" \\| ")[0];
        String description = str.split(" \\| ")[2];
        int marked = Integer.parseInt(str.split(" \\| ")[1]);

        switch (taskType) {
            case "T":
                taskList.add(new Todo(description));
                break;
            case "D":
                try {
                    VirtueDateTime dateTime = new VirtueDateTime(str.split(" \\| ")[3]);
                    taskList.add(new Deadline(description, dateTime));
                    break;
                } catch (DateTimeParseException e) {
                    throw new VirtueDateTimeException("by", "deadline");
                }
            case "E":
                VirtueDateTime fromDateTime;
                VirtueDateTime toDateTime;

                try {
                    fromDateTime = new VirtueDateTime(str.split(" \\| ")[3]);
                } catch (DateTimeParseException e) {
                    throw new VirtueDateTimeException("from", "event");
                }

                try {
                    toDateTime = new VirtueDateTime(str.split(" \\| ")[4]);
                } catch (DateTimeParseException e) {
                    throw new VirtueDateTimeException("to", "event");
                }

                taskList.add(new Event(description, fromDateTime, toDateTime));
        }

        if (marked == 1) {
            getTask(numTasks()).markAsDone();
        }
    }

    public String fileFormat() {
        String str = "";

        if (numTasks() == 0) {
            return str;
        }

        for (int i = 1; i <= numTasks() - 1; i++) {
            str += getTask(i).fileFormat();
            str += System.lineSeparator();
        }

        str += getTask(numTasks()).fileFormat();
        return str;
    }
}
