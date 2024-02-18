package duke.actions;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.Task;

/**
 * A class representing the action of finding tasks in a task management application.
 * Searches for tasks based on a specified keyword and provides a list of matching tasks.
 * @author SITHANATHAN RAHUL
 * @version CS2103T AY23/24 Semester 2
 */
public class FindTask {
    private Storage storage;
    private String task;
    private ArrayList<Task> matches;

    /**
     * Constructs a FindTask object with the given storage and task keyword.
     * @param storage The Storage object responsible for loading tasks.
     * @param task    A String representing the keyword to search for in tasks.
     */
    public FindTask(Storage storage, String task) {
        this.storage = storage;
        this.task = task;
        this.matches = new ArrayList<>();
    }

    /**
     * Fills the list of tasks with tasks that match the specified keyword.
     */
    public void createMatches() {
        ArrayList<Task> inventory = storage.load();
        for (int i = 0; i < inventory.size(); i++) {
            Task t = inventory.get(i);
            if (t.getDescription().toLowerCase().contains(this.task.toLowerCase())) {
                this.matches.add(t);
            }
        }
    }

    /**
     * Returns a formatted String representation of the matching tasks.
     * @return A String displaying the matching tasks or an apology if none are found.
     */
    @Override
    public String toString() {
        if (this.matches.isEmpty()) {
            return "Sorry :( ! There are no such tasks.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks on your list!: \n");
            int count = 1;
            for (Task t : this.matches) {
                result.append(count + ": " + t.toString()).append("\n");
                count++;
            }
            return result.toString();
        }
    }
}
