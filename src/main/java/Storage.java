package main.java;

import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import main.java.Storage;


public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> loadTasks() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] inputs = line.split(" \\| ");
                Task task = null;
                switch (inputs[0]) {
                case "T":
                    task = new ToDo(inputs[2]);
                    break;
                case "D":
                    task = new Deadline(inputs[2], inputs[3]);
                    break;
                case "E":
                    task = new Event(inputs[2], inputs[3], inputs[4]);
                    break;
                }
                if (!task.equals(null)) {
                    if (inputs[1].equals("1")) {
                        task.markDone(true);
                    }
                    taskList.add(task);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error reading the datafile, it might be corrupted. Creating a new database with any salvaged data");
            TaskList salvagedTasks = new TaskList(taskList);
            this.saveTasks(salvagedTasks);
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                System.out.println("Failed to create a new blank file: " + ioException.getMessage());
            }
        }
        return taskList;
    }

    public void saveTasks(TaskList tasks) {
        try {
            PrintWriter pw = new PrintWriter(filePath);
            for (Task task : tasks.getTasks()) {
                pw.println(task.toFileFormat());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
