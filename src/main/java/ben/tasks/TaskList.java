package ben.tasks;

import ben.exceptions.BenException;
import ben.ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a list of tasks in the Ben task management application.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList by reading tasks from a file.
     *
     * @param file The file from which tasks are read.
     * @throws FileNotFoundException If the specified file is not found.
     * @throws BenException          If there is an error while reading or parsing tasks from the file.
     */
    public TaskList(File file) throws FileNotFoundException, BenException {
        this.tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" \\| ");
            String taskType = tokens[0];
            boolean isDone = tokens[1].equals("1");
            String description = tokens[2];

            switch (taskType) {
                case "T":
                    this.tasks.add(new Todo(isDone, description));
                    break;

                case "D":
                    String by = tokens[3];

                    try {
                        LocalDate deadline = LocalDate.parse(by);
                        this.tasks.add(new Deadline(isDone, description, deadline));
                        break;
                    } catch (DateTimeParseException e) {
                        throw new BenException("Invalid deadline format");
                    }

                case "E":
                    String startDate = tokens[3];
                    String endDate = tokens[4];

                    try {
                        LocalDate dateFormattedStartDate = LocalDate.parse(startDate);
                        LocalDate dateFormattedEndDate = LocalDate.parse(endDate);
                        this.tasks.add(new Event(isDone, description, dateFormattedStartDate, dateFormattedEndDate));
                        break;
                    } catch (DateTimeParseException e) {
                        throw new BenException("Invalid deadline format");
                    }

                default:
                    break;
            }
        }
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Checks if the given index is within the bounds of the TaskList.
     *
     * @param index The index to check.
     * @return true if the index is within bounds, false otherwise.
     */
    public boolean isOutOfBounds(int index) {
        return index < 0 || index >= tasks.size();
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        Task currTask = this.tasks.get(index);
        currTask.markTask();
    }

    /**
     * Unmarks a task at the specified index as not done.
     *
     * @param index The index of the task to unmark.
     */
    public void unmarkTask(int index) {
        Task currTask = this.tasks.get(index);
        currTask.unmarkTask();
    }

    /**
     * Gets the string representation of a task at the specified index.
     *
     * @param index The index of the task.
     * @return The string representation of the task.
     */
    public String toString(int index) {
        Task currTask = this.tasks.get(index);
        return currTask.toString();
    }

    /**
     * Displays the entire TaskList.
     */
    public String showTaskList() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            output.append(i + 1).append(". ").append(currTask).append("\n");
        }

        return output.toString();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        Task deletedTask = this.tasks.remove(index);
        return deletedTask;
    }

    /**
     * Converts the entire TaskList to a formatted string for saving to a file.
     *
     * @return A string representing the formatted TaskList for saving to a file.
     */
    public String formatSave() {
        StringBuilder s = new StringBuilder();
        for (Task currTask : this.tasks) {
            s.append(currTask.saveTask())
                    .append(System.lineSeparator());
        }
        return s.toString();
    }

    /**
     * Finds tasks containing a specific keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks containing the specified keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task currTask : this.tasks) {
            if (currTask.contains(keyword)) {
                matchedTasks.add(currTask);
            }
        }

        return matchedTasks;
    }
}
