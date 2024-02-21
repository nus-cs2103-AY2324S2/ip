package pookie.tasks;

import java.util.ArrayList;

/**
 * Represents a list of pookie tasks.
 */
public class TaskList {

    /**
     * The list of pookie tasks.
     */
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructor for the list of pookie tasks.
     */
    public TaskList() {
    }

    /**
     * Adds a task to the list of pookie tasks.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        list.add(task);
        String m1 = " Got it. I've added this task:\n";
        String m2 = "\n Now you have " + (this.getSize()) + " tasks in the list.\n";
        return m1 + task.toString() + m2;
    }

    /**
     * Deletes a task from the list of pookie tasks.
     * @param index The index of the task to be deleted.
     */
    public String deleteTask(int index) {

        list.remove(index - 1);
        String m1 = "I remove this one alrdy: \n";
        String m2 = "\n Now you have " + (this.getSize() - 1) + " pookie.tasks in the list.\n";
        return m1 + this.getTask(index - 1).toString() + m2;
    }

    /**
     * Returns the size of the list of pookie tasks.
     * @return The size of the list of pookie tasks.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task at index int.
     * @param index The index of the task to be returned.
     * @return the task at index int.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Returns the list of pookie tasks.
     * @param search The search string to find tasks.
     * @return The list of pookie tasks.
     */
    public TaskList findTasks(String search) {
        TaskList found = new TaskList();
        for (Task t : list) {
            if (t.getDescription().contains(search)) {
                found.addTask(t);
            }
        }
        return found;
    }

    public String markTask(String indexes) {
        // Split the input string by whitespace
        String[] numbersAsString = indexes.split("\\s+");
        String message = "";

        // Parse each substring into an integer
        int[] numbers = new int[numbersAsString.length];
        for (int i = 0; i < numbersAsString.length; i++) {
            message += list.get(Integer.parseInt(numbersAsString[i]) - 1).toggle();
        }
    return message;
    }

    public String unmarkTask(String indexes) {
        // Split the input string by whitespace
        String[] numbersAsString = indexes.split("\\s+");
        String message = "";

        // Parse each substring into an integer
        int[] numbers = new int[numbersAsString.length];
        for (int i = 0; i < numbersAsString.length; i++) {
            message += list.get(Integer.parseInt(numbersAsString[i]) - 1).toggle();
        }
        return message;
    }

    /**
     * Shows the list of pookie tasks to the user..
     */
    public String showList() {
        String message = "Here are the tasks in your list";
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < this.getSize(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(this.getTask(i).toString()).append("\n");
        }
        message = message + "\n" + tasks;
        return message;
    }

}
