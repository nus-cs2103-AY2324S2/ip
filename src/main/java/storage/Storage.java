package storage;

import taskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;
    private FileWriter fileWriter;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public TaskList read() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            Task curr;
            String temp = s.nextLine();
            String[] split = temp.split(" \\| ");
            boolean status = checkStatus(split[1]);
            if (split[0].equals("T")) {
                curr = new ToDo(split[2], status);
            } else if (split[0].equals("D")) {
                curr = new Deadline(split[2], split[3], status);
            } else {
                curr = new Event(split[2], split[3], split[4], status);
            }
            tasks.add(curr);
        }
        return tasks;
    }

    private boolean checkStatus(String status) {
        if (status.equals("X")) {
            return true;
        }
        return false;
    }

    public void save(TaskList tasks) throws IOException {
        fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task temp = tasks.getTask(i);
            fileWriter.write(temp.toString() + "\n");
        }
        fileWriter.close();
    }
}
