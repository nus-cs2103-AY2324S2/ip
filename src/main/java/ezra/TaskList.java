package ezra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
     * Deletes a task at the specified index.
     *
     * @param taskIndex The arrayList index of the task in to be deleted.
     * @param storage The storage to update after deletion.
     * @return A message to be displayed after the delete operation.
     */
    public String delete(int taskIndex, Storage storage) {
        StringBuilder message = new StringBuilder();

        // Check for invalid taskIndex
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            message.append("Invalid task number");
        } else {
            // Print task that was deleted and number of tasks remaining
            message.append(String.format("Noted, I've removed this task:\n%s\n", this.tasks.get(taskIndex)));
            this.tasks.remove(taskIndex);
            message.append(String.format("Now you have %d tasks in the list.\n", this.tasks.size()));

            // Update tasks in storage
            try {
                storage.writeToFile(this);
            } catch (IOException e) {
                message.append("Something went wrong: ").append(e.getMessage());
            }
        }
        return message.toString();
    }

    /**
     * Updates the TaskList by adding a new task.
     *
     * @param task The task to be added.
     * @param storage The storage to update after addition.
     * @return A message to be displayed after adding the task.
     */
    public String updateTasks(Task task, Storage storage) {
        this.tasks.add(task);

        // Print task that was added and number of tasks remaining
        StringBuilder message = new StringBuilder();
        message.append(String.format("Got it. I've added this task:\n%s\n", task));
        message.append(String.format("Now you have %d tasks in the list.\n", this.tasks.size()));

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            message.append("Something went wrong: ").append(e.getMessage());
        }
        return message.toString();
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param taskIndex The arrayList index of the task to be marked as done.
     * @param storage The storage to update after marking.
     * @return A message to be displayed after marking the task.
     */
    public String mark(int taskIndex, Storage storage) {
        StringBuilder message = new StringBuilder();

        // Check for invalid taskIndex
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            message.append("Invalid task number");
        } else {
            this.tasks.get(taskIndex).isDone = true;

            // Print task that was marked
            message.append(String.format(
                    "Nice! I have marked this task as done:\n%s\n",
                    this.tasks.get(taskIndex)));

            // Update tasks in storage
            try {
                storage.writeToFile(this);
            } catch (IOException e) {
                message.append("Something went wrong: ").append(e.getMessage());
            }
        }
        return message.toString();
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param taskIndex The arrayList index of the task to be marked as not done.
     * @param storage The storage to update after marking.
     * @return A message to be displayed after unmarking the task.
     */
    public String unmark(int taskIndex, Storage storage) {
        StringBuilder message = new StringBuilder();

        // Check for invalid taskIndex
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            message.append("Invalid task number");
        } else {
            this.tasks.get(taskIndex).isDone = false;

            // Print task that was unmarked
            message.append(String.format(
                    "OK, I've marked this task as not done yet:\n%s\n",
                    this.tasks.get(taskIndex)));

            // Update tasks in storage
            try {
                storage.writeToFile(this);
            } catch (IOException e) {
                message.append("Something went wrong: ").append(e.getMessage());
            }
        }
        return message.toString();
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
