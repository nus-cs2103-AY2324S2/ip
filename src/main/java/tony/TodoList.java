package tony;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tony.exceptions.BadDateException;
import tony.exceptions.InvalidTaskException;
import tony.tasks.Deadline;
import tony.tasks.Event;
import tony.tasks.Task;
import tony.tasks.TaskType;

/**
 * Represents a list of tasks in the Tony application.
 */
public class TodoList {
    private List<Task> list = new ArrayList<>();

    private Parser parser;

    /**
     * Adds a task to the task list.
     *
     * @param item The task to be added.
     * @returns A message indicating the success or failure of the add operation.
     */
    public String add(Task item) {
        list.add(item);
        int numberOfTasks = list.size();
        String addString = "_______________________\n"
                + "Got it dawg. I've added this task: \n"
                + item.toString() + "\n"
                + "Now you got " + numberOfTasks + " tasks fam \n"
                + "_______________________\n";
        return addString;
    }

    /**
     * Marks a task as done based on the provided input.
     *
     * @param input The input representing the task to mark as done.
     * @returns A message indicating the success or failure of the mark operation.
     */
    public String mark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsDone();
            String markString = "_______________________\n"
                    + "Marked item " + index + " as done dawg."
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
     * Updates the description, dueDate, fromDate, or toDate of the task depending on the command.
     *
     * @param parts The input representing the task to update.
     * @return A message indicating the success or failure of the update operation.
     */
    public String update(String[] parts) {
        try {
            int index = Integer.parseInt(parts[0]);

            if (parts.length == 2) {
                return updateDescription(index, parts[1]);
            } else if (parts.length == 3) {
                return updateDate(index, parts[1], parts[2]);
            }
        } catch (NumberFormatException e) {
            return "_______________________\n"
                    + "Invalid index provided."
                    + "_______________________\n";
        }
        return "_______________________\n"
                + "Invalid input for 'update' command."
                + "_______________________\n";
    }

    /**
     * Updates the description of the task at the specified index.
     *
     * @param index       The index of the task to update.
     * @param description The new description for the task.
     * @return A message indicating the success or failure of the description update operation.
     */
    private String updateDescription(int index, String description) {
        try {
            list.get(index - 1).updateDescription(description);
            return "_______________________\n"
                    + "Updated description of item " + index + " successfully."
                    + "_______________________\n";
        } catch (IndexOutOfBoundsException e) {
            return handleInvalidIndex();
        }
    }

    /**
     * Updates the dueDate, fromDate, or toDate of the task at the specified index.
     *
     * @param index The index of the task to update.
     * @param field The field to update (dueDate, fromDate, or toDate).
     * @param date  The new date value.
     * @return A message indicating the success or failure of the date update operation.
     */
    private String updateDate(int index, String field, String date) {
        try {
            LocalDateTime parsedDate = parser.parseDate(date);
            Task task = list.get(index - 1);
            if (task instanceof Deadline && field.equals("by")) {
                Deadline deadline = (Deadline) task;
                deadline.setDueDate(parsedDate);
                return "_______________________\n"
                        + "Updated due date of item " + index + " successfully.\n"
                        + "_______________________\n";
            } else if (task instanceof Event && (field.equals("from") || field.equals("to"))) {
                Event event = (Event) task;
                if (field.equals("from")) {
                    event.setFromDate(parsedDate);
                    return "_______________________\n"
                            + "Updated from date of item " + index + " successfully.\n"
                            + "_______________________\n";
                } else {
                    event.setToDate(parsedDate);
                    return "_______________________\n"
                            + "Updated to date of item " + index + " successfully.\n"
                            + "_______________________\n";
                }
            } else {
                return "_______________________\n"
                        + "Cannot update date for this type of task.\n"
                        + "_______________________\n";
            }
        } catch (IndexOutOfBoundsException e) {
            return handleInvalidIndex();
        } catch (BadDateException e) {
            return "_______________________\n"
                    + "Invalid date input.\n"
                    + "_______________________\n";
        }
    }

    /**
     * Handles the case where an invalid index is provided.
     *
     * @return A message indicating that an invalid index was provided.
     */
    private String handleInvalidIndex() {
        return "_______________________\n"
                + "Invalid index provided.\n"
                + "_______________________\n";
    }




    /**
     * Unmarks a previously marked task as undone based on the provided input.
     *
     * @param input The input representing the task to unmark as done.
     * @returns A message indicating the success or failure of the unmark operation.
     */
    public String unmark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsUndone();
            String unmarkString = "_______________________\n"
                    + "Unmarked item " + index + " as done.\n"
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
     * @returns A message indicating the success or failure of the delete operation.
     */
    public String delete(String input) {
        try {
            int index = Integer.parseInt(input);
            String task = list.get(index - 1).toString();
            list.remove(index - 1);
            String deleteString = "_______________________\n"
                    + "Deleted item: \n" + task + "\n"
                    + "Now you have " + list.size() + " tasks left in the list.\n"
                    + "_______________________\n";
            return deleteString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String deleteString = "Invalid input for 'delete' command.\n";
            return deleteString;
        }
    }

    /**
     * Prints the list of tasks.
     * @returns A string containing the list of tasks.
     */
    public String print() {
        return "_______________________\n"
                + "Here are the tasks in your list: \n"
                + list.stream()
                        .map(Task::toString)
                        .collect(Collectors.joining("\n"))
                + "\n_______________________\n";
    }

    /**
     * Returns a formatted string representation of all tasks in the list for storage.
     *
     * @return A formatted string representing all tasks in the list.
     */
    public String printTasksToString() {
        return list.stream()
                .map(Task::formattedString)
                .collect(Collectors.joining("\n"));
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
        if (parts.length < 3) {
            throw new InvalidTaskException("Task information incomplete");
        }
        String taskType = parts[0];
        int completionStatus;
        try {
            completionStatus = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid completion status format");
        }
        String taskDetails = parts[2];
        TaskFactory taskFactory = new TaskFactory();
        Task task = null;
        switch (taskType) {
        case "T":
            task = taskFactory.createTask(TaskType.TODO, taskDetails);
            break;
        case "D":
            if (parts.length >= 4) {
                String deadlineDate = parts[3];
                task = taskFactory.createTask(TaskType.DEADLINE, taskDetails, "by: " + deadlineDate);
            } else {
                throw new InvalidTaskException("Deadline task information incomplete");
            }
            break;
        case "E":
            if (parts.length >= 5) {
                String startDate = parts[3];
                String endDate = parts[4];
                task = taskFactory.createTask(TaskType.EVENT, taskDetails, "from " + startDate, "to " + endDate);
            } else {
                throw new InvalidTaskException("Event task information incomplete");
            }
            break;
        default:
            throw new InvalidTaskException("Invalid task type: " + taskType);
        }
        if (task != null && completionStatus == 1) {
            task.markAsDone();
        }
        return task;
    }



    /**
     * Function to find tasks via description
     *
     * @param input The description of the tasks that want to be listed
     * @returns A message indicating the success or failure of the find operation.
     */
    public String find(String input) {
        List<String> output = list.stream()
                .filter(task -> task.getDescription().contains(input))
                .map(task -> task.toString())
                .collect(Collectors.toList());

        StringBuilder findString = new StringBuilder("_______________________\n");
        if (output.isEmpty()) {
            findString.append("Sorry there are no tasks matching ").append(input).append("\n");
        } else {
            findString.append("Here are the matching tasks in your list: \n")
                    .append(String.join("\n", output))
                    .append("\n");
        }
        findString.append("_______________________\n");
        return findString.toString();
    }

    public int size() {
        return list.size();
    }
}
