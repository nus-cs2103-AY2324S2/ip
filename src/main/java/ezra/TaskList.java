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
    public TaskList(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            // Extract command, isDone and description from input
            String task = s.nextLine();
            String[] arr = task.split(" \\| ");
            String command = arr[0];
            boolean isDone = arr[1].equals("1");
            String description = arr[2];

            if (command.equals("T")) {
                ToDo todo = new ToDo(description);
                todo.isDone = isDone;
                this.tasks.add(todo);
            } else if (command.equals("D")) {
                String by = arr[3];
                Deadline deadline = new Deadline(description, by);
                deadline.isDone = isDone;
                this.tasks.add(deadline);
            } else {
                String from = arr[3];
                String to = arr[4];
                Event event = new Event(description, from, to);
                event.isDone = isDone;
                this.tasks.add(event);
            }
        }
    }

    /**
     * Lists all tasks in the TaskList.
     *
     * @return A string representing the list of tasks.
     */
    public String listTasks() {
        StringBuilder message = new StringBuilder();
        if (this.tasks.isEmpty()) {
            message.append("There are no tasks in your list.");
        } else {
            message.append("Here are the tasks in your list:\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                message.append(String.format(
                        "%d. %s\n",
                        i + 1,
                        this.tasks.get(i)));
            }
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
        int[] sortedTaskNumbers = Arrays.stream(taskNumbers).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(sortedTaskNumbers);
        StringBuilder deletedTasks = new StringBuilder("I've removed the following task(s):\n");
        StringBuilder invalidTaskNumbers = new StringBuilder();
        boolean hasDeletedTasks = false;

        // Delete task numbers from largest to smallest so that there are no changes in task number after each deletion
        for (int i = sortedTaskNumbers.length - 1; i >= 0; i--) {
            int taskIndex = sortedTaskNumbers[i] - 1;
            assert taskIndex >= -1: "sortedTaskNumbers[i] >= 0 so taskIndex >= -1";

            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                invalidTaskNumbers.append(String.format("%d is an invalid task number\n", sortedTaskNumbers[i]));
            } else {
                deletedTasks.append(this.tasks.get(taskIndex)).append("\n");
                this.tasks.remove(taskIndex);
                hasDeletedTasks = true;

                // Update tasks in storage
                try {
                    storage.writeToFile(this);
                } catch (IOException e) {
                    return String.format("Something went wrong: %s\n", e.getMessage());
                }
            }
        }

        if (hasDeletedTasks) {
            invalidTaskNumbers.append(deletedTasks);
        }
        return invalidTaskNumbers.toString();
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

        // Print task that was added and number of tasks remaining
        StringBuilder message = new StringBuilder();
        message.append(String.format("Got it. I've added this task:\n%s\n", task));
        message.append(String.format("Now you have %d tasks in the list.\n", this.tasks.size()));

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            return String.format("Something went wrong: %s\n", e.getMessage());
        }
        return message.toString();
    }

    /**
     * Marks the tasks corresponding to the give task numbers as done.
     *
     * @param storage The storage to update after marking.
     * @param taskNumbers The array of task numbers to be marked as done.
     * @return A message to be displayed after marking the task.
     */
    public String mark(Storage storage, String... taskNumbers) {
        StringBuilder markedTasks = new StringBuilder("I've marked the following task(s) as done:\n");
        StringBuilder invalidTaskNumbers = new StringBuilder();
        boolean hasMarkedTasks = false;

        for (String taskNumber : taskNumbers) {
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            assert taskIndex >= -1: "Integer.parseInt(taskNumber) >= 0 so taskIndex >= -1";

            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                invalidTaskNumbers.append(String.format("%d is an invalid task number\n", taskIndex + 1));
            } else {
                this.tasks.get(taskIndex).isDone = true;
                markedTasks.append(this.tasks.get(taskIndex)).append("\n");
                hasMarkedTasks = true;

                // Update tasks in storage
                try {
                    storage.writeToFile(this);
                } catch (IOException e) {
                    return String.format("Something went wrong: %s\n", e.getMessage());
                }
            }
        }

        if (hasMarkedTasks) {
            invalidTaskNumbers.append(markedTasks);
        }
        return invalidTaskNumbers.toString();
    }

    /**
     * Marks the tasks corresponding to the give task numbers as not done.
     *
     * @param taskNumbers The array of task numbers to be marked as not done.
     * @param storage The storage to update after marking.
     * @return A message to be displayed after unmarking the task.
     */
    public String unmark(Storage storage, String... taskNumbers) {
        StringBuilder unmarkedTasks = new StringBuilder("I've marked the following task(s) as not done:\n");
        StringBuilder invalidTaskNumbers = new StringBuilder();
        boolean hasUnmarkedTasks = false;

        for (String taskNumber : taskNumbers) {
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            assert taskIndex >= -1: "Integer.parseInt(taskNumber) >= 0 so taskIndex >= -1";

            // Check for invalid taskIndex
            if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
                invalidTaskNumbers.append(String.format("%d is an invalid task number\n", taskIndex + 1));
            } else {
                this.tasks.get(taskIndex).isDone = false;
                unmarkedTasks.append(this.tasks.get(taskIndex)).append("\n");
                hasUnmarkedTasks = true;

                // Update tasks in storage
                try {
                    storage.writeToFile(this);
                } catch (IOException e) {
                    return String.format("Something went wrong: %s\n", e.getMessage());
                }
            }
        }

        if (hasUnmarkedTasks) {
            invalidTaskNumbers.append(unmarkedTasks);
        }
        return invalidTaskNumbers.toString();
    }

    /**
     * Finds tasks in the TaskList that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A string representing the matching tasks.
     */
    public String find(String keyword) {
        ArrayList<Integer> matchingTasks = new ArrayList<>();
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).description.contains(keyword)) {
                matchingTasks.add(i);
            }
        }

        if (matchingTasks.isEmpty()) {
            message.append("There are no matching tasks in your list:");
        } else {
            message.append("Here are the matching tasks in your list:\n");
            for (Integer i : matchingTasks) {
                message.append(String.format(
                        "%d. %s\n",
                        i + 1,
                        this.tasks.get(i)));
            }
        }
        return message.toString();
    }

}
