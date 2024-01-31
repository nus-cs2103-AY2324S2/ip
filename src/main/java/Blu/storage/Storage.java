package blu.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import blu.exception.BluException;
import blu.exception.StorageException;
import blu.task.Task;
import blu.task.TaskList;

public class Storage implements AutoCloseable {
    private File file;
    private BufferedWriter writer;

    public Storage(String filePath) throws StorageException {
        this.file = new File(filePath);
        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            }
            this.writer = new BufferedWriter(new FileWriter(this.file, true));
        } catch (IOException e) {
            throw new StorageException("Failed to create storage file");
        }
    }

    public void saveTasks(TaskList taskList) throws StorageException {
        try {
            this.writer.close();
            this.writer = new BufferedWriter(new FileWriter(this.file, false));
            List<Task> tasks = taskList.getAllTasks();
            for (Task task : tasks) {
                this.writer.write(task.toCsv());
                this.writer.newLine();
            }
            this.writer.flush();
        } catch (IOException e) {
            throw new StorageException("Failed to save task to storage file");
        }
    }

    public TaskList loadTasks() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        int lineNumber = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();
            while (line != null) {
                Task task = TaskDecoder.fromCsv(line);
                tasks.add(task);
                line = reader.readLine();
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            throw new StorageException("Failed to read tasks from storage file");
        } catch (BluException e) {
            throw new StorageException(e.getLocalizedMessage() + " at line " + lineNumber);
        }
        if (tasks.isEmpty()) {
            return new TaskList();
        }
        return new TaskList(tasks);
    }

    public void close() throws StorageException {
        try {
            this.writer.flush();
            this.writer.close();
        } catch (IOException e) {
            throw new StorageException("Could not close writer");
        }
    }
}