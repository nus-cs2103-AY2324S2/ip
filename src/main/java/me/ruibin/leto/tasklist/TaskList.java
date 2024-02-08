package me.ruibin.leto.tasklist;

import me.ruibin.leto.storage.Storage;
import me.ruibin.leto.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Class representing the list of tasks in Leto. */
public class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>(100);
//    private static int taskNextIndex = 0;

    public TaskList() {}

    /** Static method to initialise the task list from csv file. */
    public static void initFromFile() {
        Storage.ReadFile();
    }

    /** Save task list to csv file. */
    public static void saveTasks() {
        Storage.WriteFile();
    }

    /**
     * Provides a way to add a task to task list.
     * For example in Storage class readEntry.
     * */
    public static void addTaskToList(Task t) {
        list.add(t);
    }

    /** In charge of reading inputs and adding the corresponding task to the list */
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
                t = Todo.todoFromCMD(inputs);
                break;
            } // end switch for type of task
            if (t == null) {
                throw new InvalidTaskException("This task does not fit known tasks (event, deadline, todo)");
            }
            TaskList.list.add(t);
            Ui.letoSpeak("Task added, " + t +
                    "\n  > You have " + TaskList.list.size() + " tasks.");
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    /**
     * From user input mark the task specified as completed.
     *
     * @param inputs Command from the user.
     */
    public static void markTaskCompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
//            temp = TaskListCommands.list[index];
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
//                System.out.println("  << Duke Leto >>\n  > Task marked as completed! Congratulations");
            }
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    /**
     * From user input mark the task specified as uncompleted.
     *
     * @param inputs Command from the user.
     */
    public static void markTaskUncompleted(String inputs) {
        Task temp;
        try {
            int index = getIndexFromInput(inputs);
//            temp = TaskListCommands.list[index];
            temp = TaskList.list.get(index);
            if (temp == null) {
                // should not happen btw
                throw new InvalidTaskException("WARNING Task is null, try creating a task first!");
            }
            if (!temp.isCompleted()) {
                Ui.letoSpeak("Task is already not completed (╬▔皿▔)╯");
            } else {
                temp.markUncompleted();
                Ui.letoSpeak("Task marked as uncompleted! Things happen, don't worry we account for it");
            }
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    /**
     * From user input delete the task specified.
     *
     * @param inputs Command from the user.
     */
    public static void deleteTask(String inputs) {
        try {
            int index = getIndexFromInput(inputs);
            Task t = TaskList.list.get(index);
            TaskList.list.remove(index);
            Ui.letoSpeak("Task deleted, [" + t.toString() +
                    "]\n  > You have " + TaskList.list.size() + " tasks.");
        } catch (InvalidTaskException e) {
            e.printException();
        }
    }

    /**
     * Helper method to get index of the task from user command.
     *
     * @param input Command from the user.
     * @throws InvalidTaskException Invalid task index or parsing issue.
     */
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
            throw new InvalidTaskException("We cannot get task index from your input, it should be an integer, `(un)mark _int_`");
        }
    }

    /** Convert tasks in the list to an output string and then call Ui print. */
    public static void printList() {
        StringBuilder toPrint = new StringBuilder(" < Task List >\n");
        for (int i = 0; i < TaskList.list.size(); i++) {
            toPrint.append(" ").append(i + 1).append(": ")
                    .append(TaskList.list.get(i).toString()).append("\n");
        }
        toPrint.append("\n < End of Task List >");
        Ui.letoSpeak(toPrint.toString());
    }

    /**
     * Get tasks as an unmodifiable list when there is a need to iterate through it.
     *
     * @return A unmodifiable List of Task.
     */
    public static List<Task> getTasks() {
        return Collections.unmodifiableList(list);
    }
}
