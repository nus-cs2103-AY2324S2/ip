package ezra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a collection of tasks and provides methods for managing them.
 */
public class TaskList {

    protected static final String LIST_MESSAGE_START = "Here are the tasks in your list:";
    protected static final String DELETE_MESSAGE_START = "I've removed the following task(s):";
    protected static final String MARK_MESSAGE_START = "I've marked the following task(s) as done:";
    protected static final String UNMARK_MESSAGE_START = "I've marked the following task(s) as not done:";
    protected static final String FIND_MESSAGE_START = "Here are the matching tasks in your list:";
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected ArrayList<Task> prevTasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {

    }

    /**
     * Constructs a TaskList by reading tasks from a file.
     *
     * @param f The file containing tasks.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public TaskList(File f) throws FileNotFoundException, WrongFormatException {
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            // Extract command, isDone and description from input
            String task = s.nextLine();
            String[] arr = task.split(" \\| ");
            String command = arr[0];
            boolean isDone = arr[1].equals("1");
            String description = arr[2];

            switch (command) {
            case "T":
                ToDo todo = new ToDo(description);
                todo.isDone = isDone;
                this.tasks.add(todo);
                break;
            case "D":
                String by = arr[3];
                Deadline deadline = new Deadline(description, by);
                deadline.isDone = isDone;
                this.tasks.add(deadline);
                break;
            case "E":
                String from = arr[3];
                String to = arr[4];
                Event event = new Event(description, from, to);
                event.isDone = isDone;
                this.tasks.add(event);
                break;
            default:
                throw new WrongFormatException("Cannot load task from file");
            }
        }
        this.prevTasks = this.tasks;
    }

    /**
     * Lists all tasks in the TaskList.
     *
     * @return A string representing the list of tasks.
     */
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            return "There are no tasks in your list.";
        }

        StringBuilder message = new StringBuilder(LIST_MESSAGE_START);
        for (int i = 0; i < this.tasks.size(); i++) {
            message.append(String.format("\n%d. %s", i + 1, this.tasks.get(i)));
        }
        return message.toString();
    }

    /**
     * Deletes the tasks corresponding to the give task numbers.
     *
     * @param storage The storage object that updates the saved tasks.
     * @param taskIndices The array of task indices to be deleted.
     * @return A message to be displayed after the delete operation.
     */
    public String delete(Storage storage, int... taskIndices) {
        String invalidTaskNumbers = getInvalidTaskNumbers(taskIndices);
        this.prevTasks = new ArrayList<>(this.tasks); // Save state before the tasks are deleted
        String deletedTasks = getDeletedTasks(taskIndices);
        boolean hasDeletedTasks = !deletedTasks.equals(DELETE_MESSAGE_START);

        if (!hasDeletedTasks) {
            return invalidTaskNumbers;
        }

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s", e.getMessage());
        }
        return invalidTaskNumbers + deletedTasks;
    }

    /**
     * Returns a string representing the invalid task numbers.
     *
     * @param taskIndices The array of task indices.
     * @return A string containing information about invalid task numbers.
     */
    public String getInvalidTaskNumbers(int... taskIndices) {
        StringBuilder invalidTaskNumbers = new StringBuilder();
        for (int taskIndex : taskIndices) {
            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                invalidTaskNumbers.append(String.format("%d is an invalid task number.\n", taskIndex + 1));
            }
        }
        return invalidTaskNumbers.toString();
    }

    /**
     * Returns a string representing the deleted tasks.
     *
     * @param taskIndices The array of task indices.
     * @return A string containing information about deleted tasks.
     */
    public String getDeletedTasks(int... taskIndices) {
        StringBuilder deletedTasks = new StringBuilder(DELETE_MESSAGE_START);

        // Delete task numbers from largest to smallest so that there are no changes in task number after each deletion
        Arrays.sort(taskIndices);
        for (int i = taskIndices.length - 1; i >= 0; i--) {
            int taskIndex = taskIndices[i];
            assert taskIndex >= -1 : "taskNumber >= 0 so taskIndex >= -1";

            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                continue;
            }
            deletedTasks.append("\n").append(this.tasks.get(taskIndex));
            this.tasks.remove(taskIndex);
        }
        return deletedTasks.toString();
    }

    /**
     * Updates the TaskList by adding a new task.
     *
     * @param storage The storage object that updates the saved tasks.
     * @param task The task to be added.
     * @return A message to be displayed after adding the task.
     */
    public String updateTasks(Storage storage, Task task) {
        this.prevTasks = new ArrayList<>(this.tasks); // Save state before the task is added
        this.tasks.add(task);

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s", e.getMessage());
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task, this.tasks.size());
    }

    /**
     * Marks the tasks corresponding to the give task numbers as done.
     *
     * @param storage The storage object that updates the saved tasks.
     * @param taskIndices The array of task indices to be marked as done.
     * @return A message to be displayed after marking the task.
     */
    public String mark(Storage storage, int... taskIndices) {
        String invalidTaskNumbers = getInvalidTaskNumbers(taskIndices);
        // Save state before the tasks are marked
        this.prevTasks = this.tasks.stream().map(Task::copy).collect(Collectors.toCollection(ArrayList::new));
        String markedTasks = getMarkedTasks(taskIndices);
        boolean hasMarkedTasks = !markedTasks.equals(MARK_MESSAGE_START);

        if (!hasMarkedTasks) {
            return invalidTaskNumbers;
        }

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s", e.getMessage());
        }
        return invalidTaskNumbers + markedTasks;
    }

    /**
     * Returns a string representing the marked tasks.
     *
     * @param taskIndices The array of task indices.
     * @return A string containing information about marked tasks.
     */
    public String getMarkedTasks(int... taskIndices) {
        StringBuilder markedTasks = new StringBuilder(MARK_MESSAGE_START);
        for (int taskIndex : taskIndices) {
            assert taskIndex >= -1 : "taskNumber >= 0 so taskIndex >= -1";

            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                continue;
            }

            this.tasks.get(taskIndex).isDone = true;
            markedTasks.append("\n").append(this.tasks.get(taskIndex));
        }
        return markedTasks.toString();
    }

    /**
     * Marks the tasks corresponding to the give task numbers as not done.
     *
     * @param taskIndices The array of task indices to be marked as not done.
     * @param storage The storage object that updates the saved tasks.
     * @return A message to be displayed after unmarking the task.
     */
    public String unmark(Storage storage, int... taskIndices) {
        String invalidTaskNumbers = getInvalidTaskNumbers(taskIndices);
        // Save state before the tasks are unmarked
        this.prevTasks = this.tasks.stream().map(Task::copy).collect(Collectors.toCollection(ArrayList::new));
        String unmarkedTasks = getUnmarkedTasks(taskIndices);
        boolean hasUnmarkedTasks = !unmarkedTasks.equals(UNMARK_MESSAGE_START);

        if (!hasUnmarkedTasks) {
            return invalidTaskNumbers;
        }

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s", e.getMessage());
        }
        return invalidTaskNumbers + unmarkedTasks;
    }

    /**
     * Returns a string representing the unmarked tasks.
     *
     * @param taskIndices The array of task indices.
     * @return A string containing information about unmarked tasks.
     */
    public String getUnmarkedTasks(int... taskIndices) {
        StringBuilder unmarkedTasks = new StringBuilder(UNMARK_MESSAGE_START);
        for (int taskIndex : taskIndices) {
            assert taskIndex >= -1 : "taskNumber >= 0 so taskIndex >= -1";

            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                continue;
            }

            this.tasks.get(taskIndex).isDone = false;
            unmarkedTasks.append("\n").append(this.tasks.get(taskIndex));
        }
        return unmarkedTasks.toString();
    }

    /**
     * Finds tasks in the TaskList that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A string representing the matching tasks.
     */
    public String find(String keyword) {
        ArrayList<Integer> matchingTaskIndices = getMatchingTaskIndices(keyword);
        return getMatchingTasks(matchingTaskIndices);
    }

    /**
     * Returns a list of task indices matching the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of Integer containing matching task indices.
     */
    public ArrayList<Integer> getMatchingTaskIndices(String keyword) {
        ArrayList<Integer> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).description.contains(keyword)) {
                matchingTasks.add(i);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns a string representing the matching tasks.
     *
     * @param matchingTaskIndices The list of task indices that match the keyword.
     * @return A string containing information about matching tasks.
     */
    public String getMatchingTasks(ArrayList<Integer> matchingTaskIndices) {
        if (matchingTaskIndices.isEmpty()) {
            return "There are no matching tasks in your list.";
        }

        StringBuilder message = new StringBuilder(FIND_MESSAGE_START);
        for (Integer i : matchingTaskIndices) {
            message.append(String.format("\n%d. %s", i + 1, this.tasks.get(i)));
        }
        return message.toString();
    }

    /**
     * Undoes the previous command by restoring the tasks to their previous state.
     *
     * @param storage The storage object that updates the saved tasks.
     * @return A message indicating the result of the undo operation.
     */
    public String undo(Storage storage) {
        this.tasks = this.prevTasks;

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s", e.getMessage());
        }
        return "Your previous command has been undone.";
    }

}
