package harvard;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import harvard.exceptions.HarvardException;
import harvard.tasks.Deadline;
import harvard.tasks.Event;
import harvard.tasks.Task;
import harvard.tasks.Todo;

/**
 * The TaskList class represents a list of tasks in the Harvard application.
 * It provides methods to manipulate the task list, such as adding, deleting, and marking tasks.
 */
public class TaskList {

    /**
     * The list that stores tasks.
     */
    private List<Task> taskList = new ArrayList<>();

    /**
     * Constructs a TaskList instance and populates it with tasks from a BufferedReader.
     *
     * @param br The BufferedReader containing task information.
     */
    public TaskList(BufferedReader br) {

        populateTaskList(br);
    }

    /**
     * Constructs an empty TaskList instance.
     */
    public TaskList() {
    }

    /**
     * Retrieves the string representation of the task at the specified index.
     *
     * @param index The index of the task in the list.
     * @return The string representation of the task.
     * @throws HarvardException If the index is out of bounds.
     */
    public String printString(int index) throws HarvardException {
        if (index > this.taskList.size() - 1) {
            throw new HarvardException("Sorry, this task could not be found.");
        }
        return taskList.get(index).toString();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 : "index should be more than -1";
        assert index < this.taskList.size() : "index should be less than size of task list - 1";
        return this.taskList.get(index);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        assert index >= 0 : "index should be more than -1";
        taskList.remove(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void mark(int index) {
        assert index >= 0 : "index should be more than -1";
        assert index < this.taskList.size() : "index should be less than size of task list - 1";
        this.taskList.get(index).mark();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmark(int index) {
        assert index >= 0 : "index should be more than -1";
        assert index < this.taskList.size() : "index should be less than size of task list - 1";
        this.taskList.get(index).unmark();
    }


    /**
     * Finds tasks in the TaskList whose descriptions contain the specified search string.
     *
     * @param searchString the string to search for within task descriptions
     * @return a TaskList containing tasks whose descriptions contain the search string
     */
    public TaskList find(String searchString) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            // C-BetterSearch implementation for partial matches
            if (taskList.get(i).getDescription().contains(searchString)) {
                filteredList.add(taskList.get(i));
            }
        }
        return filteredList;
    }

    /**
     * Populates the task list with tasks from a BufferedReader.
     *
     * @param buffReader The BufferedReader containing task information.
     */
    public void populateTaskList(BufferedReader buffReader) {
        try {
            String line;
            while ((line = buffReader.readLine()) != null) {
                String taskType = line.split(",")[0];
                Boolean isDone = line.split(",")[1].equals("0") ? false : true;
                if (taskType.equals("T")) {
                    taskList.add(new Todo(line.split(",")[2], isDone));
                } else if (taskType.equals("D")) {
                    taskList.add(new Deadline(line.split(",")[2], LocalDate.parse(line.split(",")[3]), isDone));
                } else {
                    taskList.add(new Event(line.split(",")[2],
                            LocalDate.parse(line.split(",")[3]),
                            LocalDate.parse(line.split(",")[4]),
                            isDone));
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

}
