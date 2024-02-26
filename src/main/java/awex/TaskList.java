package awex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import tasks.*;

/**
 * Represents a list of saved tasks.
 */
public class TaskList {
    private LinkedList<Task> tasks;

    /**
     * Constructor for TaskList object
     *
     * @param f file object with saved tasks
     */
    public TaskList(File f) throws IOException {
        this.tasks = new LinkedList<>();
        if (!f.createNewFile()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(" / ");
                if (arr[0].equals("T")) {
                    this.tasks.add(new TodoTask(arr[2], arr[1]));
                } else if (arr[0].equals("D")) {
                    this.tasks.add(new DeadlineTask(arr[2], arr[1], arr[3]));
                } else {
                    this.tasks.add(new EventTask(arr[2], arr[1], arr[3], arr[4]));
                }
            }
        }
    }

    /**
     * Adds Task to TaskList
     *
     * @param t Task to be added
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns LinkedList of tasks that have a given string as a substring
     *
     * @param str
     * @return list of matching tasks
     */
    public LinkedList<Task> find(String str) {
        LinkedList<Task> list = new LinkedList<>();
        int len = this.tasks.size();
        assert len >= 0;
        for (int i = 0; i < len; i++) {
            Task t = this.tasks.get(i);
            if (t.isPartOfDesc(str)) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * Returns Task of a certain index
     *
     * @param i index of requested task
     * @return requested task
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns whether TaskList is empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns Task removed from TaskList
     *
     * @param i index of removed task
     * @return removed Task
     */
    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Returns length of TaskList
     *
     * @return TaskList length
     */
    public int size() {
        return this.tasks.size();
    }
}