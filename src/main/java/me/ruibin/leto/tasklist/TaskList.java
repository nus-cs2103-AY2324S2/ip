package me.ruibin.leto.tasklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ruibin.leto.parser.Result;
import me.ruibin.leto.storage.Storage;
import me.ruibin.leto.ui.Ui;

/** Class representing the list of tasks in Leto. */
public class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>(100);

    /** Static method to initialise the task list from csv file. */
    public static Result initFromCsvFile() {
        return Storage.readFile();
    }

    /** Save task list to csv file. */
    public static Result saveTasks() {
        return Storage.writeFile();
    }

    /**
     * Takes in a list of tasks as varargs to add to the task list.
     *
     * @param tasks Array containing Tasks.
     * @return Result of the add. Should be ok.
     */
    public static Result addToList(Task... tasks) {
        Result r = Result.makeResultOk("Adding to task list:\n");
        for (Task t : tasks) {
            list.add(t);
            r.updateMessage(Ui.letoSpeak("Task added, " + t
                    + "\n  > You have " + TaskList.list.size() + " tasks."));
        }
        return r;
    }

    /**
     * From user input mark the task specified as completed.
     *
     * @param inputs Command from the user.
     * @return Result of the task.
     */
    public static Result markTaskCompleted(String inputs) {
        Task temp;
        Result r = Result.makeResultOk("");
        try {
            int index = getIndexFromInput(inputs);
            temp = getTaskByIndex(index);
            assert temp != null : "Shouldn't occur!! Task is null";
            if (temp.isCompleted()) {
                throw new InvalidTaskException("Task already completed");
            }
            temp.markCompleted();
            r.updateMessage(Ui.letoSpeak("Task marked as completed! Congratulations"));
            return r;
        } catch (InvalidTaskException e) {
            r.updateWithException(e.printException(), e);
            return r;
        }
    }

    /**
     * From user input mark the task specified as uncompleted.
     *
     * @param inputs Command from the user.
     * @return Result of the task.
     */
    public static Result markTaskUncompleted(String inputs) {
        Task temp;
        Result r = Result.makeResultOk("");
        try {
            int index = getIndexFromInput(inputs);
            temp = getTaskByIndex(index);
            assert temp != null : "Shouldn't occur!! Task is null";
            if (!temp.isCompleted()) {
                r.updateMessage(Ui.letoSpeak("Task is already not completed :< "));
            }
            temp.markUncompleted();
            r.updateMessage(
                    Ui.letoSpeak("Task marked as uncompleted! Things happen, don't worry we account for it"));
            return r;
        } catch (InvalidTaskException e) {
            r.updateWithException(e.printException(), e);
            return r;
        }
    }

    /**
     * From user input delete the task specified.
     *
     * @param inputs Command from the user.
     * @return Result of the task.
     */
    public static Result deleteTask(String inputs) {
        Result r = Result.makeResultOk("");
        try {
            int index = getIndexFromInput(inputs);
            Task t = getTaskByIndex(index);
            TaskList.list.remove(index);
            r.updateMessage(Ui.letoSpeak("Task deleted, [" + t.toString()
                    + "]\n  > You have " + TaskList.list.size() + " tasks."));
        } catch (InvalidTaskException e) {
            r.updateWithException(e.printException(), e);
        }
        return r;
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
            return Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("We cannot get task index from your input, "
                    + "it should be an integer, `(un)mark _int_`");
        }
    }

    public static Task getTaskByIndex(int i) throws InvalidTaskException {
        if (list.isEmpty()) {
            throw new InvalidTaskException("Good news at least, you have no task!");
        }
        if (i >= list.size() || i < 0) {
            throw new BadTaskIndexException(list.size());
        }
        return list.get(i);
    }

    /**
     * Convert tasks in the list to an output string and then call Ui print.
     *
     * @return Result of the task.
     */
    public static Result printList() {
        StringBuilder toPrint = new StringBuilder(" < Task List >\n");
        for (int i = 0; i < TaskList.list.size(); i++) {
            toPrint.append(" ").append(i + 1).append(": ")
                    .append(TaskList.list.get(i).toString()).append("\n");
        }
        toPrint.append("\n < End of Task List >");
        return Result.makeResultOk(Ui.letoSpeak(toPrint.toString()));
    }

    /**
     * Get tasks as an unmodifiable list when there is a need to iterate through it.
     *
     * @return An unmodifiable List of Task.
     */
    public static List<Task> getTasks() {
        return Collections.unmodifiableList(list);
    }
}
