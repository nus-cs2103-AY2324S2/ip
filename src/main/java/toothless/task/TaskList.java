package toothless.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import toothless.exception.ToothlessException;

/**
 * A TaskList class to store and operate on tasks in list.
 */
public class TaskList {
    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private ArrayList<Task> tasks;

    /**
     * A public constructor to initialize new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A public constructor to initialize new TaskList with given ArrayList.
     *
     * @param taskArrayList ArrayList with Tasks.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.tasks = taskArrayList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return integer of number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Getter for ArrayList of Tasks.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index Index of task to mark as done.
     * @return Marked task.
     */
    public Task markTask(int index) {
        assert index <= tasks.size() : "index should be less than or equal to size of tasks";
        Task task = tasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param index Index of task to mark as not done.
     * @return Unmarked task.
     */
    public Task unmarkTask(int index) {
        assert index <= tasks.size() : "index should be less than or equal to size of tasks";
        Task task = tasks.get(index - 1);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Deletes a task in the list.
     *
     * @param index Index of task to delete.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        assert index <= tasks.size() : "index should be less than or equal to size of tasks";
        Task deletedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        return deletedTask;
    }

    /**
     * Finds and returns a list of tasks with given keyword.
     *
     * @param keyword A String to search in tasklist.
     * @return List of tasks with keyword.
     */
    public ArrayList<Task> findKeyword(String keyword) {
        assert keyword != null : "keyword should not be null";
        assert !keyword.isBlank() : "keyword should not be an empty string";
        ArrayList<Task> keywordTasks = new ArrayList<>();
        for (Task currentTask : this.tasks) {
            if (currentTask.toString().contains(keyword)) {
                keywordTasks.add(currentTask);
            }
        }
        return keywordTasks;
    }

    /**
     * Adds Tag to a Task in TaskList.
     *
     * @param index Integer index of task to add tag to.
     * @param label String label of tag to add to task.
     * @return Task with new tag added.
     * @throws ToothlessException if task already has tag with same label.
     */
    public Task addTaskTag(int index, String label) throws ToothlessException {
        assert index <= tasks.size() : "index should be less than or equal to size of tasks";
        assert label != null : "label should not be null";
        assert !label.isBlank() : "label should not be an empty string";
        Task task = tasks.get(index - 1);
        task.addTag(label);
        return task;
    }

    /**
     * Deletes Tag from a Task in TaskList.
     *
     * @param index Integer index of task to delete tag from.
     * @param label String label of tag to delete from task.
     * @return Task with tag deleted.
     * @throws ToothlessException if task does not have tag with given label.
     */
    public Task untagTask(int index, String label) throws ToothlessException {
        assert index <= tasks.size() : "index should be less than or equal to size of tasks";
        assert label != null : "label should not be null";
        assert !label.isBlank() : "label should not be an empty string";
        Task task = tasks.get(index - 1);
        task.unTag(label);
        return task;
    }

    /**
     * Adds ToDo task to list.
     *
     * @param taskDescription String to describe the task.
     * @return New ToDo task.
     */
    public Task addToDoToList(String taskDescription) {
        assert taskDescription != null : "taskDescription should not be null";
        assert !taskDescription.isBlank() : "taskDescription should not be an empty string";
        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds Deadline task to list.
     *
     * @param taskDescription String to describe the task.
     * @param by String for datetime deadline the task is due by.
     * @return New Deadline task.
     * @throws ToothlessException if datetime fails to parse.
     */
    public Task addDeadlineToList(String taskDescription, String by) throws ToothlessException {
        assert taskDescription != null : "taskDescription should not be null";
        assert !taskDescription.isBlank() : "taskDescription should not be an empty string";
        assert by != null : "by should not be null";
        assert !by.isBlank() : "by should not be an empty string";
        try {
            LocalDateTime deadlineBy = LocalDateTime.parse(by, DATETIME_PARSE_FORMATTER);
            Task newTask = new Deadline(taskDescription, deadlineBy);
            tasks.add(newTask);
            return newTask;
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Sorry, /by field datetime should be in the following format: "
                    + "yyyy-mm-dd hh:mm");
        }
    }

    /**
     * Adds Event task to list.
     *
     * @param taskDescription String to describe the task.
     * @param from String for datetime start of event.
     * @param to String for datetime end of event.
     * @return New Event task.
     * @throws ToothlessException if datetime fails to parse.
     */
    public Task addEventToList(String taskDescription, String from, String to) throws ToothlessException {
        assert taskDescription != null : "taskDescription should not be null";
        assert !taskDescription.isBlank() : "taskDescription should not be an empty string";
        assert from != null : "from should not be null";
        assert !from.isBlank() : "from should not be an empty string";
        assert to != null : "to should not be null";
        assert !to.isBlank() : "to should not be an empty string";
        try {
            LocalDateTime eventFrom = LocalDateTime.parse(from, DATETIME_PARSE_FORMATTER);
            LocalDateTime eventTo = LocalDateTime.parse(to, DATETIME_PARSE_FORMATTER);
            Task newTask = new Event(taskDescription, eventFrom, eventTo);
            tasks.add(newTask);
            return newTask;
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Sorry, /from and /to field datetime should be in the following format: "
                    + "yyyy-mm-dd hh:mm");
        }

    }
}
