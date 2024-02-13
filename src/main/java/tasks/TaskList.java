package tasks;

import exceptions.InvalidIndexException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void addTask(Task task) {
        add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("         " + task.toString());
        int numTask = this.size();
        System.out.println("     Total number of tasks: " + numTask);
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
            System.out.println("     No pending tasks, congrats!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            int numTask = this.size();
            for (int i = 0; i < size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
            System.out.println("     Total number of tasks: " + numTask);
        }
    }
    public void deleteTask(int index) throws InvalidIndexException {
        try {
            Task task = this.get(index);
            System.out.println("     OK! I've removed this task:");
            System.out.println("         " + task.toString());
            this.remove(index);
            int numTask = this.size();
            System.out.println("     Total number of tasks: " + numTask);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
