package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.EmptyBodyException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.OutOfBoundException;
import duke.utils.KeyEnum;

/**
 * Class represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes task list with given arraylist.
     *
     * @param tasks Existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns Number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public Integer getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns the formatted task string to be stored in file.
     *
     * @param i Index of task being used.
     * @return String format of that task in file storage format.
     */
    public String getTaskInfileStringFormat(Integer i) {
        return this.tasks.get(i).inFileStringFormat();
    }

    /**
     *
     */
    public String listTask() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return result;
    }

    /**
     * Adds a task into list.
     *
     * @param detail     Description of task.
     * @param from       Start date of task.
     * @param to         Due date of task.
     * @param currentKey Type of task.
     * @return Added task.
     * @throws EmptyBodyException       If the command body is empty.
     * @throws InvalidDateTimeException If the due date for deadline is not in recognisable format.
     */
    public Task addTask(String detail, String from, String to, KeyEnum currentKey)
            throws EmptyBodyException, InvalidDateTimeException {
        Task task = null;
        switch (currentKey) {
        case DEADLINE:
            task = new Deadline(false, detail, to);
            break;
        case TODO:
            task = new Todo(false, detail);
            break;
        case EVENT:
            task = new Event(false, detail, from, to);
            break;
        default:
            break;
        }
        // Throw empty body exception if the added
        if (task == null || detail.length() == 0) {
            throw new EmptyBodyException();
        }
        tasks.add(task);
        assert tasks.size() > 0 : "Task should be added";
        return task;
    }

    /**
     * Marks the status of a task.
     *
     * @param id     Index of task used.
     * @param status Task to be marked True or False
     * @return The marked task.
     * @throws OutOfBoundException If the index is out of bound.
     */
    public Task markTaskById(Integer id, Boolean status) throws OutOfBoundException, IndexOutOfBoundsException {
        // Test if the id is out of bound
        if (id >= this.getNumOfTasks() || id < 0) {
            throw new OutOfBoundException();
        }
        this.tasks.get(id).setStatus(status);
        assert this.tasks.get(id).getStatus() == status : "Task should be marked";
        return this.tasks.get(id);
    }

    /**
     * Deletes a task from list.
     *
     * @param id Index of task used.
     * @return The deleted task.
     * @throws OutOfBoundException If the index is out of bound.
     */
    public Task deleteTaskById(Integer id) throws OutOfBoundException, IndexOutOfBoundsException {
        // Test if the id is out of bound
        if (id >= this.getNumOfTasks() || id < 0) {
            throw new OutOfBoundException();
        }
        Task taskToDelete = this.tasks.get(id);
        this.tasks.remove(taskToDelete);
        return taskToDelete;
    }

    /**
     * Finds matched tasks based on the word.
     *
     * @param word Keyword to find a match.
     * @return Matched task list.
     */
    public TaskList findTasks(String word) {
        ArrayList<Task> matchedTasksArr = new ArrayList<>();
        // iterate through the task list
        System.out.println(word);
        for (Task task : tasks) {
            if (task.getDetail().contains(word)) {
                matchedTasksArr.add(task);
            }
        }
        TaskList matchedTasks = new TaskList(matchedTasksArr);
        return matchedTasks;
    }
}
