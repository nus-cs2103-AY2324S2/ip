package Irwyn.Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    ArrayList<Task> storage;

    private Task StringToTask(String data) {
        String[] parts = data.split(" \\| ");
        switch (parts[0]) {
            case "T":
                Task todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) {
                    todo.mark();
                }
                return todo;
            case "D":
                Task deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) {
                    deadline.mark();
                }
                return deadline;
            case "E":
                Task event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) {
                    event.mark();
                }
                return event;
            default:
                return null;
        }
    }

    public TaskList(File filePath) {
        storage = new ArrayList<>();
         try {
            Scanner fileReader = new Scanner(filePath);
            while (fileReader.hasNextLine()) {
                Task task = StringToTask(fileReader.nextLine());
                if (task != null) {
                    storage.add(task);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading tasks from file.\n");
        }
    }

    public ArrayList<Task> getTasks() {
        return this.storage;
    }

    public Task getTask(int tasks) {
        return this.storage.get(tasks);
    }

    public void addTask(Task task) {
        this.storage.add(task);
    }

    public Task delete(int task) {
        return this.storage.remove(task);
    }

    public int getTasksSize() {
        return this.storage.size();
    }

    public void mark(int task) {
        storage.get(task).mark();
    }

    public void unmark(int task) {
        storage.get(task).unmark();
    }
}
