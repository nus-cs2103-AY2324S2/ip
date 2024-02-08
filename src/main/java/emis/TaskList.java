package main.java.emis;
import main.java.emis.exceptions.EmisException;
import main.java.emis.command.*;
import main.java.emis.task.*;
import java.util.ArrayList;

// TaskList class, contains task list and its operations
public class TaskList {
    private ArrayList<Task> al;

    public TaskList(ArrayList<Task> al) {
        this.al = al;
    }

    TaskList() {
        this.al = new ArrayList<>();
    }

    public void printList() {
        Ui.showLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.al.size(); i++) {
            System.out.println("\t\t" + (i + 1) + "." + this.al.get(i).toString());
        }
        Ui.showLine();
    }

    public void deleteTask(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > al.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Ui.showLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + this.al.get(taskNo - 1).toString());
            this.al.remove(taskNo - 1);
            System.out.println("\tNow you have " + this.al.size() + " tasks in the list.");
            Ui.showLine();
        }
    }

    public void addTask(Task task) {
        this.al.add(task);
        Ui.showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + this.al.size() + " tasks in the list.");
        Ui.showLine();
    }

    public void markAsDone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > al.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task t = this.al.get(taskNo - 1);
            t.markAsDone();
            this.al.set(taskNo - 1, t);
        }
    }

    public void markAsUndone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > al.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task t = this.al.get(taskNo - 1);
            t.markAsUndone();
            this.al.set(taskNo - 1, t);
        }
    }
}