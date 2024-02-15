package duke.tasks;

import duke.exceptions.InvalidIndexException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void addTask(Task task) {
        add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("         " + task.toString());
        getCurTotalTasks();
    }

    public Task getTask(int index) throws InvalidIndexException {
        try {
            return this.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
    public void displayTasks() throws InvalidIndexException {
        if (isEmpty()) {
            System.out.println("     No pending duke.tasks, congrats!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
            getCurTotalTasks();
        }
    }
    public void deleteTask(int index) throws InvalidIndexException {
        try {
            Task task = this.get(index);
            System.out.println("     OK! I've removed this task:");
            System.out.println("         " + task.toString());
            this.remove(index);
            getCurTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void getCurTotalTasks() {
        int numTask = this.size();
        System.out.println("     Total number of tasks: " + numTask);
    }

}
