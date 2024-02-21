package aurora.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import aurora.objects.*;

/** The TaskList class represents a list of task objects. */
public class TaskList {

    /** TaskList of tasks */
    private ArrayList<Task> taskList;

    private static final String MARK_DONE = "I've marked this task as done: \n";
    private static final String UNMARK_DONE = "I've marked this task as not done yet: \n";
    private static final String DELETE_TASK = "I've removed this task as you instructed: \n";
    private static final String NUMBER_OF_TASKS = "\nNumber of tasks in the list: ";

    /**
     * Constructs a TaskList object.
     *
     * @param taskList An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a Todo object to the task list.
     *
     * @param description Description of the Todo object.
     */
    public void addTodo(String description) {
        Todo newTask = new Todo(description);
        this.taskList.add(newTask);
    }

    /**
     * Adds a Deadline object to the task list.
     *
     * @param description Description of the Deadline object.
     * @param date Datetime associated with the Deadline object.
     */
    public void addDeadline(String description, LocalDateTime date) {
        Deadline newTask = new Deadline(description, date);
        this.taskList.add(newTask);
    }

    /**
     * Adds an Event object to the task list.
     *
     * @param description Description of the Event object.
     * @param start Start time associated with the event.
     * @param end End time associated with the event.
     */
    public void addEvent(String description, LocalDateTime start, LocalDateTime end) {
        Event newTask = new Event(description, start, end);
        this.taskList.add(newTask);
    }

    /**
     * Adds a DoAfter object associated with a datetime to the task list.
     *
     * @param description Description of the DoAfter object.
     * @param date Datetime associated with the DoAfter object.
     */
    public void addDoAfter(String description, LocalDateTime date) {
        DoAfter newTask = new DoAfter(description, date);
        this.taskList.add(newTask);
    }

    /**
     * Adds a DoAfter object associated with a task to the task list.
     *
     * @param description Description of the DoAfter object.
     * @param taskNumber Index of the task associated with the DoAfter object.
     */
    public void addDoAfter(String description,int taskNumber) {
        Task previousTask = this.taskList.get(taskNumber);
        DoAfter newTask = new DoAfter(description, taskNumber);
        newTask.setTask(previousTask);
        this.taskList.add(newTask);
    }

    /**
     * Returns a String representing an alert to the user to notify them
     * that a task in the task list was marked as done.
     *
     * @param taskIndex Index of the task to be marked as done.
     * @return String alert notifying the user that the task was marked as done.
     */
    public String markTaskGui(int taskIndex) {
        this.taskList.get(taskIndex).setDone();
        return MARK_DONE + this.taskList.get(taskIndex).toString();
    }

    /**
     * Returns a String representing an alert to the user to notify them
     * that a task in the task list was unmarked.
     *
     * @param taskIndex Index of the task to be unmarked.
     * @return String alert notifying the user that the task was unmarked.
     */
    public String unmarkTaskGui(int taskIndex) {
        this.taskList.get(taskIndex).setNotDone();
        return UNMARK_DONE + this.taskList.get(taskIndex).toString();
    }

    /**
     * Returns a String representing an alert to the user to notify them
     * that a task in the task list was deleted.
     *
     * @param taskIndex Index of the task to be deleted.
     * @return String alert notifying the user that the task was deleted.
     */
    public String deleteTaskGui(int taskIndex) throws AuroraException {
        Task deletedTask = this.taskList.get(taskIndex);
        String taskString = deletedTask.toString();
            for (Task task : this.taskList) {
                updateDoAfterTasks(task, deletedTask, taskIndex);
            }

        this.taskList.remove(taskIndex);
        return DELETE_TASK + taskString + NUMBER_OF_TASKS + this.taskList.size();
    }

    /**
     * Updates DoAfters associated with deleted tasks or affected by the deletion of tasks after
     * the deletion of a task.
     *
     * @param task Task to possibly update.
     * @param deletedTask Task deleted.
     * @param deletedTaskIndex Index of deleted task.
     * @throws AuroraException If the deletedTask is not de-referenced successfully.
     */
    private void updateDoAfterTasks(Task task, Task deletedTask, int deletedTaskIndex) throws AuroraException {
        if (!(task instanceof DoAfter)) {
            return;
        }

        DoAfter doAfter = (DoAfter) task;
        if (doAfter.typeOfDoAfter() != 2 || doAfter.getTaskNumber() == -2) {
            return;
        }

        if (doAfter.getTask().equals(deletedTask)) {
            doAfter.setTaskNumberAfterDelete("delete");
        } else if (deletedTaskIndex < doAfter.getTaskNumber()) {
            doAfter.setTaskNumberAfterDelete("affected");
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
