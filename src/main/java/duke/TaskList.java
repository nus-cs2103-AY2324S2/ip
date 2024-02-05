package duke;
import java.util.ArrayList;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskNumberException;
import duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public void addStoredTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void addNewTask(Task task) {
        this.tasks.add(task);
        ui.addTaskRespond(task, this.tasks.size());
    }

    public void deleteTask(int deleteNumber) throws InvalidCommandException {
        if (deleteNumber < 1 || deleteNumber > tasks.size()) {
            throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
        } else {
            Task task = tasks.get(deleteNumber - 1);
            tasks.remove(deleteNumber - 1);
            ui.deleteTaskRespond(task, this.tasks.size());
        }
    }

    public void markTask(int number) throws InvalidCommandException {
        if (number < 1 || number > tasks.size()) {
            throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
        } else {
            Task task = tasks.get(number - 1);
            task.markDone();
            ui.markTaskRespond(task);
        }
    }

    public void unmarkTask(int number) throws InvalidCommandException {
        if (number < 1 || number > tasks.size()) {
            throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
        } else {
            Task task = tasks.get(number - 1);
            task.markNotDone();
            ui.unmarkTaskRespond(task);
        }
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
    public void printList() {
        int count = 1;
        if (tasks.size() == 0) {
            System.out.println("      Nothing added to list yet!");
        }
        for (Task task : tasks) {
            System.out.printf("      %d. %s\n", count, task.toString());
            count++;
        }
    }
}
