/**
 * This is a Task List Class.
 * It contains all the tasks that have been added by the user.
 */

package task;

public class TaskList {
    public Task[] taskList;

    public TaskList(Task[] tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new Task[100];
    }

    /**
     * Returns a copy of the Task List in the form of an array.
     *
     * @return Task List Array
     */
    public Task[] getTaskList() {
        return taskList.clone();
    }

    /**
     * Replaces the task list array found in this task list.
     *
     * @param tasks task list that overwrites the current one
     */
    public void updateTaskList(TaskList tasks) {
        this.taskList = tasks.getTaskList();
    }
}
