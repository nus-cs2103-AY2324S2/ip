package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Prints out all the tasks in the list.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    public void printTaskCount() {
        int count = getTaskCount();
        System.out.println("Now you have " + count + " " + (count == 1 ? "task" : "tasks") + " in the list.");
    }

    /**
     * Returns the description of the task at the specified index.
     *
     * @param index The index of the task in the list.
     * @return The description of the task.
     */
    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the task at the given index as done.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public void unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();
    }

    /**
     * Clears all tasks in the list.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Loads the from saved data.
     *
     * @param lines The list of commands representing the stored data.
     * @param parser {@link Parser} object.
     * @throws DukeException if the storage file is corrupted.
     */
    public void loadFromSavedData(List<String> lines, Parser parser) throws DukeException {
        clear();
        try {
            for (String command : lines) {
                parser.parse(command).executeSilently(this);
            }
        } catch (DukeException e) {
            clear();
            throw e;
        }
    }

    public List<String> getSaveData() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add(tasks.get(i).serializeToCommand(i));
        }
        return lines;
    }
}
