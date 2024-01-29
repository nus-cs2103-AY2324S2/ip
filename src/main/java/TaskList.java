import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<Task> tasklist;
    public TaskList(List<Task> tasks) {
        tasklist = tasks;
    }

    public Task getTask(int n) {
        return tasklist.get(n);
    }

    public void add(Task t) {
        tasklist.add(t);
    }
    public void mark(int num) {
        tasklist.get(num).mark();
    }

    public void unmark(int num) {
        tasklist.get(num).unmark();
    }

    public void delete(int num) {

        tasklist.remove(num);
    }

    public int length() {

        return tasklist.size();
    }
    public void iterateout() {
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + tasklist.get(i));
        }
    }
}
