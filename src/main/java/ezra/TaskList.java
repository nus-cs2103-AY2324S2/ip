package ezra;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

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
    public TaskList(File f) throws FileNotFoundException{
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
     */
    public void listTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("\tThere are no tasks in your list.");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf(
                    "\t%d.%s\n",
                    i + 1,
                    this.tasks.get(i));
        }
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param taskIndex The arrayList index of the task in to be deleted.
     * @param storage The storage to update after deletion.
     */
    public void delete(int taskIndex, Storage storage) {
        // Check for invalid taskIndex
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("\tInvalid task number");
            return;
        }

        // Print task that was deleted and number of tasks remaining
        System.out.println("\tNoted, I've removed this task:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex));
        this.tasks.remove(taskIndex);
        System.out.printf("\tNow you have %d tasks in the list.\n", this.tasks.size());

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            System.out.println("\tSomething went wrong: " + e.getMessage());
        }
    }

    /**
     * Updates the TaskList by adding a new task.
     *
     * @param task The task to be added.
     * @param storage The storage to update after addition.
     */
    public void updateTasks(Task task, Storage storage) {
        this.tasks.add(task);

        // Print task that was added and number of tasks remaining
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n", task);
        System.out.printf("\tNow you have %d tasks in the list.\n", this.tasks.size());

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            System.out.println("\tSomething went wrong: " + e.getMessage());
        }
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param taskIndex The arrayList index of the task to be marked as done.
     * @param storage The storage to update after marking.
     */
    public void mark(int taskIndex, Storage storage) {
        // Check for invalid taskIndex
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("\tInvalid task number");
            return;
        }

        this.tasks.get(taskIndex).isDone = true;

        // Print task that was marked
        System.out.println("\tNice! I have marked this task as done:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex));

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            System.out.println("\tSomething went wrong: " + e.getMessage());
        }
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param taskIndex The arrayList index of the task to be marked as not done.
     * @param storage The storage to update after marking.
     */
    public void unmark(int taskIndex, Storage storage) {
        // Check for invalid taskIndex
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            System.out.println("\tInvalid task number");
            return;
        }

        this.tasks.get(taskIndex).isDone = false;

        // Print task that was unmarked
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.printf(
                "\t  %s\n",
                this.tasks.get(taskIndex));

        // Update tasks in storage
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            System.out.println("\tSomething went wrong: " + e.getMessage());
        }
    }

    /**
     * Finds tasks in the TaskList that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void find(String keyword) {
        ArrayList<Integer> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).description.contains(keyword)) {
                matchingTasks.add(i);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("\tThere are no matching tasks in your list:");
            return;
        }
        System.out.println("\tHere are the matching tasks in your list:");
        for (Integer i : matchingTasks) {
            System.out.printf(
                    "\t%d.%s\n",
                    i + 1,
                    this.tasks.get(i));
        }
    }

}
