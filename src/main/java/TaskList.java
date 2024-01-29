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
    public iterateout() {
        for (int i = 0; i < tasklist.size(); i++) {
            //HAVENT DONE YET, SUPPOSED TO PRINT ALL TASKS
        }
    }
}
