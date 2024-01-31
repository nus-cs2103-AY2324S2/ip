package duke.task;
import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList< Task > tasks;

    public TaskList(ArrayList < Task > tasks) {
        this.tasks = tasks;
    }

    public TaskList() {

    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addToDoTask(ToDo task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    public void addDeadlineTask(Deadline task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    public void addEventTask(Event task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("You have not created task " + (index + 1) + " for me to delete!");
        }
        Task removedTask = tasks.remove(index);
        Ui.printDeletedTaskMessage(removedTask);
    }

    public void markStatus(Task job) throws DukeException {
        if (job.isDone) {
            throw new DukeException("This task is already marked as done.");
        }
        job.isDone = true;
        Ui.markTask(job);
    }

    public void unmarkStatus(Task job) throws DukeException {
        if (!job.isDone) {
            throw new DukeException("This task is already marked as not done.");
        }
        job.isDone = false;
        Ui.unmarkTask(job);
    }

    public static void getList(TaskList taskList) throws DukeException { //need to put in UI class?
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            String taskDetails = (i + 1) + ".[" + task.type + "][" + (task != null ? task.getStatusIcon() : "") + "] " + task.description;
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + (deadlineTask.by == null ? deadlineTask.byString : deadlineTask.by) + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + (eventTask.from == null ? eventTask.fromString : eventTask.from) + " to: " +
                        (eventTask.to == null ? eventTask.toString : eventTask.to) + ")";
            }
            System.out.println(taskDetails);
        }
    }
}
