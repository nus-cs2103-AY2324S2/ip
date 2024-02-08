package me.ruibin.leto.tasklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ruibin.leto.storage.Storage;
import me.ruibin.leto.ui.Ui;

public class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>(100);

    public TaskList() {}

    public static void initFromFile() {
        Storage.readFile();
    }

    public static void saveTasks() {
        Storage.writeFile();
    }

    public static void addTaskToList(Task t) {
        list.add(t);
    }

    public static void addToList(String inputs) {
        try {
            Task t = null;
            String typeOfTask = inputs.split(" ")[0];
            switch (typeOfTask.toLowerCase()) {
            case "event":
                t = Event.eventFromCommand(inputs);
                break;
            case "deadline":
                t = Deadline.deadlineFromCommand(inputs);
                break;
            case "todo":
                t = Todo.todoFromCmd(inputs);
                break;
            default:
                throw new InvalidTaskException("This task does not fit known tasks (event, deadline, todo)");
            } // end switch for type of task
            TaskList.list.add(t);
            Ui.letoSpeak("Task added, " + t
                    + "\n  > You have " + TaskList.list.size() + " tasks.");
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    public static void markTaskCompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
            temp = TaskList.list.get(index);
            if (temp == null) {
                // should never happen
                throw new InvalidTaskException("WARNING Task is null, try creating a task first!");
            }
            if (temp.isCompleted()) {
                throw new InvalidTaskException("Task already completed");
            } else {
                temp.markCompleted();
                Ui.letoSpeak("Task marked as completed! Congratulations");
            }
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    public static void markTaskUncompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
            temp = TaskList.list.get(index);
            if (temp == null) {
                // should not happen btw
                throw new InvalidTaskException("WARNING Task is null, try creating a task first!");
            }
            if (!temp.isCompleted()) {
                Ui.letoSpeak("Task is already not completed :< ");
            } else {
                temp.markUncompleted();
                Ui.letoSpeak("Task marked as uncompleted! Things happen, don't worry we account for it");
            }
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    public static void deleteTask(String inputs) {
        try {
            int index = getIndexFromInput(inputs);
            Task t = TaskList.list.get(index);
            TaskList.list.remove(index);
            Ui.letoSpeak("Task deleted, [" + t.toString()
                    + "]\n  > You have " + TaskList.list.size() + " tasks.");
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    private static int getIndexFromInput(String input) throws InvalidTaskException {
        try {
            String[] inputs = input.split(" ");
            if (inputs.length != 2) {
                throw new InvalidTaskException("You need to enter a task index number");
            }
            int i = Integer.parseInt(inputs[1]) - 1;
            if (TaskList.list.isEmpty()) {
                throw new InvalidTaskException("Good news at least, you have no task!");
            }
            if (i >= TaskList.list.size() || i < 0) {
                throw new BadTaskIndexException(TaskList.list.size());
            }
            return i;
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("We cannot get task index from your input, "
                    + "it should be an integer, `(un)mark _int_`");
        }
    }

    public static void printList() {
        StringBuilder toPrint = new StringBuilder(" < Task List >\n");
        for (int i = 0; i < TaskList.list.size(); i++) {
            toPrint.append(" ").append(i + 1).append(": ")
                    .append(TaskList.list.get(i).toString()).append("\n");
        }
        toPrint.append("\n < End of Task List >");
        Ui.letoSpeak(toPrint.toString());
    }

    public static List<Task> getTasks() {
        return Collections.unmodifiableList(list);
    }
}
