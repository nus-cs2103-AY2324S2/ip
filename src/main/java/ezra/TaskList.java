package ezra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a collection of tasks and provides methods for managing them.
 */
public class TaskList {

    protected ArrayList<Task> tasks = new ArrayList<>();
    protected static final String LIST_MESSAGE_START = "Here are the tasks in your list:\n";
    protected static final String DELETE_MESSAGE_START = "I've removed the following task(s):\n";
    protected static final String MARK_MESSAGE_START = "I've marked the following task(s) as done:\n";
    protected static final String UNMARK_MESSAGE_START = "I've marked the following task(s) as not done:\n";
    protected static final String FIND_MESSAGE_START = "Here are the matching tasks in your list:\n";

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
            message.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        return message.toString();
    }

    /**
     * Deletes the tasks corresponding to the give task numbers.
     *
     * @param storage The storage to update after deletion.
     * @param taskNumbers The array of task numbers to be deleted.
     * @return A message to be displayed after the delete operation.
     */
    public String delete(Storage storage, String... taskNumbers) {
        String invalidTaskNumbers = getInvalidTaskNumbers(taskNumbers);
        String deletedTasks = getDeletedTasks(taskNumbers);
        boolean hasDeletedTasks = !deletedTasks.equals(DELETE_MESSAGE_START);

        if (!hasDeletedTasks) {
            return invalidTaskNumbers;
        }

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s\n", e.getMessage());
        }
        return invalidTaskNumbers + deletedTasks;
    }

    public String getInvalidTaskNumbers(String... taskNumbers) {
        StringBuilder invalidTaskNumbers = new StringBuilder();
        for (String taskNumber : taskNumbers) {
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                invalidTaskNumbers.append(String.format("%d is an invalid task number.\n", taskIndex + 1));
            }
        }
        return invalidTaskNumbers.toString();
    }

    public String getDeletedTasks(String... taskNumbers) {
        int[] sortedTaskNumbers = Arrays.stream(taskNumbers).mapToInt(Integer::parseInt).sorted().toArray();
        StringBuilder deletedTasks = new StringBuilder(DELETE_MESSAGE_START);

        // Delete task numbers from largest to smallest so that there are no changes in task number after each deletion
        for (int i = sortedTaskNumbers.length - 1; i >= 0; i--) {
            int taskIndex = sortedTaskNumbers[i] - 1;
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                continue;
            }
            deletedTasks.append(this.tasks.get(taskIndex)).append("\n");
            this.tasks.remove(taskIndex);
        }
        return deletedTasks.toString();
    }

    /**
     * Updates the TaskList by adding a new task.
     *
     * @param storage The storage to update after addition.
     * @param task The task to be added.
     * @return A message to be displayed after adding the task.
     */
    public String updateTasks(Storage storage, Task task) {
        this.tasks.add(task);

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s\n", e.getMessage());
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                task, this.tasks.size());
    }

    /**
     * Marks the tasks corresponding to the give task numbers as done.
     *
     * @param storage The storage to update after marking.
     * @param taskNumbers The array of task numbers to be marked as done.
     * @return A message to be displayed after marking the task.
     */
    public String mark(Storage storage, String... taskNumbers) {
        String invalidTaskNumbers = getInvalidTaskNumbers(taskNumbers);
        String markedTasks = getMarkedTasks(taskNumbers);
        boolean hasMarkedTasks = !markedTasks.equals(MARK_MESSAGE_START);

        if (!hasMarkedTasks) {
            return invalidTaskNumbers;
        }

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s\n", e.getMessage());
        }
        return invalidTaskNumbers + markedTasks;
    }

    public String getMarkedTasks(String... taskNumbers) {
        StringBuilder markedTasks = new StringBuilder(MARK_MESSAGE_START);
        for (String taskNumber : taskNumbers) {
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                continue;
            }

            this.tasks.get(taskIndex).isDone = true;
            markedTasks.append(this.tasks.get(taskIndex)).append("\n");
        }
        return markedTasks.toString();
    }

    /**
     * Marks the tasks corresponding to the give task numbers as not done.
     *
     * @param taskNumbers The array of task numbers to be marked as not done.
     * @param storage The storage to update after marking.
     * @return A message to be displayed after unmarking the task.
     */
    public String unmark(Storage storage, String... taskNumbers) {
        String invalidTaskNumbers = getInvalidTaskNumbers(taskNumbers);
        String unmarkedTasks = getUnmarkedTasks(taskNumbers);
        boolean hasUnmarkedTasks = !unmarkedTasks.equals(UNMARK_MESSAGE_START);

        if (!hasUnmarkedTasks) {
            return invalidTaskNumbers;
        }

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s\n", e.getMessage());
        }
        return invalidTaskNumbers + unmarkedTasks;
    }

    public String getUnmarkedTasks(String... taskNumbers) {
        StringBuilder unmarkedTasks = new StringBuilder(UNMARK_MESSAGE_START);
        for (String taskNumber : taskNumbers) {
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                continue;
            }

            this.tasks.get(taskIndex).isDone = false;
            unmarkedTasks.append(this.tasks.get(taskIndex)).append("\n");
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

    public ArrayList<Integer> getMatchingTaskIndices(String keyword) {
        ArrayList<Integer> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).description.contains(keyword)) {
                matchingTasks.add(i);
            }
        }
        return matchingTasks;
    }

    public String getMatchingTasks(ArrayList<Integer> matchingTaskIndices) {
        if (matchingTaskIndices.isEmpty()) {
            return "There are no matching tasks in your list.";
        }

        StringBuilder message = new StringBuilder(FIND_MESSAGE_START);
        for (Integer i : matchingTaskIndices) {
            message.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        return message.toString();
    }

}
