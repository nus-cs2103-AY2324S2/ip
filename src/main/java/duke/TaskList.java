package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for new TaskList.
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param taskList Existing arrayList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to add task.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Method to delete task.
     *
     * @param deleteId Id of task to be deleted.
     * @param ui Ui to generate deleteMessage.
     */
    public void deleteTask(int deleteId, Ui ui) {
        ui.deleteMessage(taskList.get(deleteId));
        taskList.remove(deleteId);
    }

    /**
     * Method to unmark task.
     *
     * @param unmarkId Id of task to be unmarked.
     * @param ui Ui to generate unmarkMessage.
     */
    public void unmarkTask(int unmarkId, Ui ui) {
        taskList.get(unmarkId).markNotDone();
        ui.unmarkMessage(taskList.get(unmarkId));
    }

    /**
     * Method to mark task.
     *
     * @param markId Id of task to be marked.
     * @param ui Ui to generate markMessage.
     */
    public void markTask(int markId, Ui ui) {
        taskList.get(markId).markDone();
        ui.markMessage(taskList.get(markId));
    }

    /**
     * Method to get tasks from taskList.
     *
     * @return taskList.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Method to get size of taskList.
     *
     * @return size of taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Method to print list of tasks.
     *
     * @param ui Ui to generate indented messages.
     */
    public void listTask(Ui ui) {
        if (taskList.isEmpty()) {
            ui.setIndentedLine();
            System.out.println("  Sorry, Tasklist is empty.");
            ui.setIndentedLine();
        } else {
            ui.setIndentedLine();
            System.out.println("  " + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println("  " + (i + 1) + "." + currTask.toString());
            }
            ui.setIndentedLine();
        }
    }
}
