package awex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import tasks.*;

public class TaskList {
    private LinkedList<Task> list;

    /**
     * Constructor for TaskList object
     *
     * @param f file object with saved tasks
     */
    public TaskList(File f) throws IOException {
        this.list = new LinkedList<Task>();
        if (!f.createNewFile()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(" / ");
                if (arr[0].equals("T")) {
                    this.list.add(new TodoTask(arr[2], arr[1]));
                } else if (arr[0].equals("D")) {
                    this.list.add(new DeadlineTask(arr[2], arr[1], arr[3]));
                } else {
                    this.list.add(new EventTask(arr[2], arr[1], arr[3], arr[4]));
                }
            }
        }
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void listSaver(FileWriter fw) throws IOException {
        int len = this.list.size();
        for (int i = 0; i < len; i++) {
            fw.write(this.list.get(i).toString() + System.lineSeparator());
        }
    }

    public Task remove(int i) {
        return this.list.remove(i);
    }

    public int size() {
        return this.list.size();
    }
}