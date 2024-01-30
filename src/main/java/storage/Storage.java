package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import exception.BluException;
import task.Task;
import task.TaskList;

public class Storage implements AutoCloseable {
    private File file;
    private BufferedWriter writer;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
        }
        this.writer = new BufferedWriter(new FileWriter(this.file, true));
    }

    public void saveTasks(TaskList taskList) throws IOException {
        this.writer.close();
        this.writer = new BufferedWriter(new FileWriter(this.file, false));
        List<Task> tasks = taskList.getAllTasks();
        for (Task task : tasks) {
            this.writer.write(task.toCsv());
            this.writer.newLine();
        }
    }

    public TaskList loadTasks() throws IOException, BluException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        List<Task> tasks = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            Task task = TaskDecoder.fromCsv(line);
            tasks.add(task);
            line = reader.readLine();
        }
        reader.close();
        if (tasks.isEmpty()) {
            return new TaskList();
        }
        return new TaskList(tasks);
    }

    public void close() throws IOException {
        this.writer.close();
    }
    
}
