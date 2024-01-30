package ezra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Represents a collection of tasks and provides methods for managing them.
 */
public class TaskList {

    protected ArrayList<Task> arrayList = new ArrayList<>();

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
            String task = s.nextLine();
            String[] arr = task.split(" \\| ");
            String command = arr[0];
            boolean isDone = arr[1].equals("1");
            String description = arr[2];
            if (command.equals("T")) {
                ToDo todo = new ToDo(description);
                todo.isDone = isDone;
                this.arrayList.add(todo);
            } else if (command.equals("D")) {
                String by = arr[3];
                Deadline deadline = new Deadline(description, by);
                deadline.isDone = isDone;
                this.arrayList.add(deadline);
            } else {
                String from = arr[3];
                String to = arr[4];
                Event event = new Event(description, from, to);
                event.isDone = isDone;
                this.arrayList.add(event);
            }
        }
    }

    /**
     * Lists all tasks in the TaskList.
     */
    public void listTasks() {
        if (this.arrayList.size() == 0) {
            System.out.println("\tThere are no tasks in your list.");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.arrayList.size(); i++) {
            System.out.printf(
                    "\t%d.%s\n",
                    i + 1,
                    this.arrayList.get(i)
            );
        }
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param taskIndex The arrayList index of the task in to be deleted.
     * @param storage The storage to update after deletion.
     */
    public void delete(int taskIndex, Storage storage) {
        if (taskIndex < 0 || taskIndex >= this.arrayList.size()) {
            System.out.println("\tInvalid task number");
            return;
        }
        System.out.println("\tNoted, I've removed this task:");
        System.out.printf(
                "\t  %s\n",
                this.arrayList.get(taskIndex)
        );
        this.arrayList.remove(taskIndex);
        System.out.printf("\tNow you have %d tasks in the list.\n", this.arrayList.size());
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
        this.arrayList.add(task);
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n", task);
        System.out.printf("\tNow you have %d tasks in the list.\n", this.arrayList.size());
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
        if (taskIndex < 0 || taskIndex >= this.arrayList.size()) {
            System.out.println("\tInvalid task number");
            return;
        }
        this.arrayList.get(taskIndex).isDone = true;
        System.out.println("\tNice! I have marked this task as done:");
        System.out.printf(
                "\t  %s\n",
                this.arrayList.get(taskIndex)
        );
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
        if (taskIndex < 0 || taskIndex >= this.arrayList.size()) {
            System.out.println("\tInvalid task number");
            return;
        }
        this.arrayList.get(taskIndex).isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.printf(
                "\t  %s\n",
                this.arrayList.get(taskIndex)
        );
        try {
            storage.writeToFile(this);
        } catch (IOException e) {
            System.out.println("\tSomething went wrong: " + e.getMessage());
        }
    }
}
