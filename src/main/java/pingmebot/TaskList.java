package pingmebot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.w3c.dom.events.Event;
import pingmebot.task.Deadline;
import pingmebot.task.Events;
import pingmebot.task.Task;



/**
 * Stores the list of tasks assigned to the bot.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a TaskList object when there are already tasks in the local file.
     *
     * @param tasks A specified list of tasks loaded from the local file
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for creating a TaskList object when there are no tasks already in the local file.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the current tasklist.
     *
     * @return The number of tasks.
     */
    public int getTaskSize() {
        return this.tasks.size();
    }

    /**
     * Updates the local file of the modification made to the tasklist.
     *
     * @param fs The actual file that the method is supposed to write into.
     * @throws PingMeException If there is an attempt to write into a non-existent file.
     */
    public void updateTaskToStorage(Storage fs) throws PingMeException {
        try {
            fs.updateFile(this.tasks);
        } catch (PingMeException e) {
            throw new PingMeException(e.getMessage());
        }
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task The task to be added into the tasklist.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param taskNumber The position of the task in the tasklist to be deleted.
     */
    public void removeTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    /**
     * Returns the desired description of the task.
     *
     * @param taskNumber The position of the task in the tasklist to retrieve its description.
     * @return The desired description of the task.
     */
    public String taskToString(int taskNumber) {
        return this.tasks.get(taskNumber).toString();
    }

    /**
     * Returns the status icon of the task to see if it has been completed.
     *
     * @param taskNumber The position of the task in the tasklist to retrieve its status icon.
     * @return Status icon of the task.
     */
    public String taskStatusIcon(int taskNumber) {
        return this.tasks.get(taskNumber).getStatusIcon();
    }

    /**
     * Marks task as completed.
     *
     * @param taskNumber The position of the task in the tasklist to mark as done.
     */
    public void taskMarkAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    /**
     * Un-marks task.
     *
     * @param taskNumber The position of the task in the tasklist to un-mark.
     */
    public void taskUncheckTask(int taskNumber) {
        this.tasks.get(taskNumber).uncheckingTask();
    }

    /**
     * Returns a list of matching task according to the specified keyword.
     *
     * @param keyword Keyword specified by the user to find.
     * @return A list of matching task lists, can be empty as well.
     */
    public ArrayList<Task> findMatchingTask(String keyword) {
        return this.tasks.stream().filter(task
                -> task.getDescription().contains(keyword)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Updates the start and end timing for the events task, and the finish by timing for the deadline task.
     *
     * @param taskNum The task number in the task list which the user is trying to postpone.
     * @param timingStart The start timing of the events task.
     * @param timingEnd The end timing of the events task.
     * @param by The finish by timing of the deadline task.
     * @throws PingMeException If the user tries to postpone a todo task.
     */
    public void postponeTask(int taskNum, String timingStart, String timingEnd, LocalDateTime by) throws PingMeException{
        Task t = tasks.get(taskNum);

        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            d.setDeadlineByTiming(by);
        } else if (t instanceof Events) {
            Events e = (Events) t;
            e.setEventsFromTiming(timingStart);
            e.setEventsToTiming(timingEnd);
        } else {
            throw new PingMeException("You can only postpone deadline or " +
                    "events task!");
        }
    }

}
