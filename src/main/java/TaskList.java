import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<Task>();
    private Storage s;

    public TaskList(Storage s) {
        this.s = s;
    }

    public void write() {
        for (int i = 0; i < tasklist.size(); i++) {
            tasklist.get(i).writeToFile(s.getFile());
        }
    }

    public void list() {
        System.out.println("All tasks:");
        s.getFileContent();
        System.out.println();
        System.out.println("Current tasks: ");
        for (int i = 0; i < tasklist.size(); i++) {
            int j = i + 1;
            System.out.println("    " + j + ". " + tasklist.get(i).getCat()
                    + tasklist.get(i).marked() + " "
                    + tasklist.get(i).getTask() + tasklist.get(i).getDetails());
        }
    }

    public void addTask(Task task) {
        try {
            tasklist.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.add());
            System.out.println("Now you have " + tasklist.size() + " tasks in the list.");

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("ENTER INSTRUCTION");
        }

    }

    public void mark(int number) {
        try {
            Task task = tasklist.get(number);
            task.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.mark(number));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task to mark.");
        }
    }

    public void unmark(int number) {
        try {
            Task task = tasklist.get(number);
            task.unmark();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(task.unmark(number));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task to unmark.");
        }

    }

    public void delete(int number) {
        try {
            Task task = tasklist.get(number);
            tasklist.remove(number);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task.delete());
            System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task to delete.");
        }
    }
}

