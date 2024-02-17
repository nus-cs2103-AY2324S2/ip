package duke.tasks;

import duke.exceptions.InvalidIndexException;
import duke.exceptions.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {

    public void addTask(Task task) {
        add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("         " + task.toString());
        int numTask = this.size();
        System.out.println("     Total number of duke.tasks: " + numTask);
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
            System.out.println("     Here are the duke.tasks in your list:");
            int numTask = this.size();
            for (int i = 0; i < size(); i++) {
                System.out.println("     " + (i + 1) + ". " + getTask(i));
            }
            System.out.println("     Total number of duke.tasks: " + numTask);
        }
    }
    public void deleteTask(int index) throws InvalidIndexException {
        try {
            Task task = this.get(index);
            System.out.println("     OK! I've removed this task:");
            System.out.println("         " + task.toString());
            this.remove(index);
            int numTask = this.size();
            System.out.println("     Total number of duke.tasks: " + numTask);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void findTasks(String searchTerm) throws TaskNotFoundException, InvalidIndexException {
        TaskList foundTasks = new TaskList();
        for (Task task : this) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasks.add(task);
            }
        }
        if (searchTerm.isEmpty() || foundTasks.isEmpty()) {
            throw new TaskNotFoundException();
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + foundTasks.getTask(i));
            }
        }
    }

}
