package tony;

import java.util.ArrayList;
import java.util.List;

import tony.exceptions.InvalidTaskException;
import tony.tasks.Task;
import tony.tasks.TaskType;

/**
 * Represents a list of tasks in the Tony application.
 */
public class TodoList {
    private List<Task> list = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param item The task to be added.
     */
    public String add(Task item) {
        list.add(item);
        int numberOfTasks = list.size();
        String addString = "_______________________\n"
                + "Got it dawg. I've added this task: \n"
                + item.toString() + "\n"
                + "Now you got " + numberOfTasks + " tony.tasks fam \n"
                + "_______________________\n";
        return addString;
    }

    /**
     * Marks a task as done based on the provided input.
     *
     * @param input The input representing the task to mark as done.
     */
    public String mark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsDone();
            String markString = "_______________________\n"
                    +"Marked item " + index + " as done dawg."
                    + "_______________________\n";
            return markString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String markString = "_______________________\n"
                    + "Invalid input for 'mark' command dawg.\n"
                    + "_______________________\n";
            return markString;
        }
    }

    /**
     * Unmarks a previously marked task as undone based on the provided input.
     *
     * @param input The input representing the task to unmark as done.
     */
    public String unmark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsUndone();
            String unmarkString = "_______________________\n"
                    +"Unmarked item " + index + " as done.\n"
                    + "_______________________\n";
            return unmarkString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String unmarkString = "_______________________\n"
                    + "Invalid input for 'unmark' command dawg.\n"
                    + "_______________________\n";
            return unmarkString;
        }
    }

    /**
     * Deletes a task from the task list based on the provided input.
     *
     * @param input The input representing the task to delete.
     */
    public String delete(String input) {
        try {
            int index = Integer.parseInt(input);
            String task = list.get(index - 1).toString();
            list.remove(index - 1);
            String deleteString = "_______________________\n"
                    + "Deleted item: \n" + task + "\n"
                    + "Now you have " + list.size() + " tony.tasks left in the list.\n"
                    + "_______________________\n";
            return deleteString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String deleteString = "Invalid input for 'delete' command.\n";
            return deleteString;
        }
    }

    /**
     * Prints the list of tasks.
     */
    public String print() {
        String printString =  "_______________________\n"
        + "Here are the tony.tasks in your list: \n";

        for (int i = 0; i < list.size(); i++) {
            printString += list.get(i).toString() + "\n";
        }
        printString += "_______________________\n";
        return printString;
    }

    /**
     * Returns a formatted string representation of all tasks in the list for storage.
     *
     * @return A formatted string representing all tasks in the list.
     */
    public String printTasksToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).formattedString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Loads a task from a given line of text and adds it to the list.
     *
     * @param line The line of text representing a task.
     */
    public void loadTask(String line) throws InvalidTaskException {
        Task task = createTaskFromLine(line);
        list.add(task);
    }

    /**
     * Function to create a Task from it's formatted String representation
     *
     * @param line The line of text representing a task.
     * @return Task object
     */
    private Task createTaskFromLine(String line) throws InvalidTaskException {
        String[] parts = line.split("\\|");

        if (parts.length >= 3) {
            String taskType = parts[0];
            int completionStatus = Integer.parseInt(parts[1]);
            String taskDetails = parts[2];

            if (taskType.equals("T")) {
                Task todo = new TaskFactory().createTask(TaskType.TODO, taskDetails);
                if (completionStatus == 1) {
                    todo.markAsDone();
                }
                return todo;
            } else if (taskType.equals("D")) {
                String deadlineDate = parts[3];
                Task deadline = new TaskFactory().createTask(TaskType.DEADLINE, taskDetails, "by: " + deadlineDate);
                if (completionStatus == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            } else if (taskType.equals("E")) {
                Task event = new TaskFactory().createTask(TaskType.EVENT, taskDetails,
                        "from: " + parts[3], "to:" + parts[4]);
                if (completionStatus == 1) {
                    event.markAsDone();
                }
                return event;
            }
        }
        return null;
    }

    private static void line() {
        System.out.println("_______________________\n");
    }

    /**
     * Function to find tasks via description
     *
     * @param input The description of the tasks that want to be listed
     */
    public String find(String input) {
        int count = 1;
        List<String> output = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String description = task.getDescription();
            if (description.contains(input)) {
                output.add("" + count + ". " + task.toString() + "\n");
                count++;
            }
        }
        if (count == 1) {
            String findString = "_______________________\n"
                    + "Sorry there are no tasks matching " + input + "\n"
                    + "_______________________\n";
            return findString;
        } else {
            String findString = "_______________________\n" +
            "Here are the matching tasks in your list: \n";
            for (int i = 0; i < count - 1; i++) {
                findString += output.get(i) + "\n";
            }
            findString += "_______________________\n";
            return findString;
        }
    }

    public int size() {
        return list.size();
    }
}
