package duke.storage;

import java.util.ArrayList;

import duke.parser.Priority;


/**
 * The TaskList class represents a list of tasks in the Duke task manager.
 * It manages operations such as adding, marking, unmarking, and removing tasks.
 * The class also provides methods for obtaining the length of the task list and generating a formatted
 * string representation of the tasks.
 */
public class TaskList {
    private static final String lines = "    ____________________________________________________________";
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list and saves the updated list to a file.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
        saveToFile();
    }

    /**
     * Loads a task from a save file and adds it to the task list.
     *
     * @param task The task to be loaded and added.
     */
    public void loadFromSave(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a task at the specified index as done and saves the updated list to a file.
     *
     * @param number The index of the task to be marked.
     */
    public String markTask(int number) {
        this.tasks.get(number).mark();
        saveToFile();
        return "      " + this.tasks.get(number).toString();
    }

    /**
     * Marks a task at the specified index as not done and saves the updated list to a file.
     *
     * @param number The index of the task to be unmarked.
     */
    public String unMarkTask(int number) {
        this.tasks.get(number).unMark();
        saveToFile();
        return "      " + this.tasks.get(number).toString();
    }

    /**
     * Removes a task at the specified index and saves the updated list to a file.
     *
     * @param number The index of the task to be removed.
     */
    public String remove(int number) {

        String toPrint = "      " + this.tasks.get(number).toString();
        this.tasks.remove(number);
        saveToFile();

        return toPrint;
    }

    /**
     * Updates the priority of the task at the specified index with the given priority,
     * then saves the changes to the file.
     *
     * @param number   the index of the task to update
     * @param priority the new priority to set
     * @return a string representation of the updated task
     */
    public String updatePriority(int number, Priority priority) {
        this.tasks.get(number).setPriority(priority);
        System.out.println(this.tasks.get(number).getPriority());
        saveToFile();
        return "      " + this.tasks.get(number).toString();
    }

    /**
     * Searches for tasks containing a specified keyword in their descriptions.
     *
     * @param key The keyword to search for in task descriptions.
     * @return A formatted string representation of tasks that match the search criteria.
     */
    public String find(String key) {
        StringBuilder result = new StringBuilder();
        result.append(lines).append("\n");
        for (int i = 1; i <= tasks.size(); i++) {
            if (this.tasks.get(i - 1).getDescription().toLowerCase()
                    .contains(key.toLowerCase())) {
                result.append(String.format("    %d.", i)).append(this.tasks.get(i - 1).toString()).append("\n");
            }
        }
        result.append(lines);
        return result.toString();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int taskLength() {
        return this.tasks.size();
    }

    /**
     * Generates a formatted string representation of the tasks in the task list.
     *
     * @return A formatted string representation of the tasks.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            result.append(String.format("  %d.", i)).append(this.tasks.get(i - 1).toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns the priority of the given task as a string representation.
     *
     * @param task the task to get the priority for
     * @return the priority of the task as a string ("-1" for LOW, "0" for empty, "1" otherwise)
     */
    private String getPriority(Task task) {
        if (task.getPriority().equals("")) {
            return "0";
        } else if (task.getPriority().equals("LOW")) {
            return "-1";
        } else {
            return "1";
        }
    }

    /**
     * Saves the current list of tasks to a file.
     * The format of the save file is "save [doneStatus] [originalCommand]" for each task.
     * Each task is represented as a line in the file.
     */
    private void saveToFile() {
        StringBuilder temporary = new StringBuilder();

        for (Task task : tasks) {
            temporary.append("save ")
                    .append(task.isDone ? "1 " : "0 ")
                    .append(getPriority(task))
                    .append(" ")
                    .append(task.getOriginalCommand() + "\n");
        }
        Storage.save(temporary.toString());
    }

}
