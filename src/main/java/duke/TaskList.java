package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @return the status of the task addition.
     */
    public AddTaskResult addTask(Task task) {
        // check if it doesn't clash first
        for (Task t : tasks) {
            if (t.isClashingWith(task)) {
                return AddTaskResult.buildClashingResult(t);
            }
        }
        tasks.add(task);
        return AddTaskResult.buildSuccessfulResult();
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
     * Returns a string representation of all the tasks in the list.
     */
    public String getDisplayListTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task count suitable to be displayed to the user.
     */
    public String getDisplayTaskCount() {
        int count = getTaskCount();
        return "Now you have " + count + " " + (count == 1 ? "task" : "tasks") + " in the list.";
    }

    public TaskList findTasks(String query) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                AddTaskResult additionResult = foundTasks.addTask(task);
                assert additionResult.isSuccessful() : "This task list should not have clashing tasks.";
            }
        }
        return foundTasks;
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
                parser.parse(command).execute(this);
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
