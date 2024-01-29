import java.io.IOException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
public class TaskManager {
    ArrayList<Task> list = new ArrayList<>();
    private int numOfTask = 0;
    public void addTask(Task t) {
        System.out.println("added: ");
        System.out.println(t.toString());
        this.list.add(t);
        this.numOfTask++;
        System.out.println("There are now " + this.numOfTask + " tasks in the list.");
        this.saveTaskList();
    }
    public void listTasks() {
        int count = 1;
        for (Task t : list) {
            System.out.println(count + "." + t.toString());
            count++;
        }
    }
    public void markDone(int i) {
        Task t = list.get(i - 1);
        t.setDone(true);
        this.saveTaskList();
    }

    public void undo(int i) {
        Task t = list.get(i - 1);
        t.setDone(false);
        this.saveTaskList();
    }

    public void deleteTask(int i) {
        Task t = list.remove(i - 1);
        this.numOfTask--;
        System.out.println(t.toString());
        System.out.println("There are now " + this.numOfTask + " tasks in the list.");
        this.saveTaskList();
    }

    public void saveTaskList() {
        try {
            File taskList = new File("./data/duke.txt");
            if (!taskList.getParentFile().exists()) {
                taskList.getParentFile().mkdirs();
            }
            if (!taskList.exists()) {
                taskList.createNewFile();
            }
            FileWriter fw = new FileWriter(taskList);
            if (this.numOfTask == 1) {
                fw.write("There is 1 task in the list.\n");
            } else {
                fw.write("There are " + this.numOfTask + " tasks in the list.\n");
            }
            for (Task t : list) {
                fw.append(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
