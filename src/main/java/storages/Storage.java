package storages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import tasks.taskType.Deadline;
import tasks.taskType.Event;
import tasks.taskType.Task;
import tasks.taskType.ToDo;
import tasks.TaskList;

public class Storage {
    private File file;
    private Path path;

    public Storage(String file) {
        this.file = new File(file);
        this.path = Paths.get(this.file.toURI());
    }

    public Task createNewTask(String taskType, String content, boolean isDone) {
        switch (taskType) {
        case "D": {
            Deadline newTask = new Deadline(content, taskType, isDone);
            return newTask;
        }
        case "T": {
            ToDo newTask = new ToDo(content, taskType, isDone);
            return newTask;
        }
        case "E": {
            Event newTask = new Event(content, taskType, isDone);
            return newTask;
        }
        default:
            return new ToDo(content, taskType, isDone);
        }
    }

    /**
     * Returns an ArrayList of Task based on the data
     * stored in the Fredricksen.txt file.
     *
     * @return an ArrayList of Task
     * @throws IOException if file cannot be opened or read.
     */
    public TaskList loadFile() throws IOException {
        TaskList list = new TaskList();

        try (BufferedReader br = new BufferedReader(new FileReader(this.path.toFile()))) {
            String next;
            while ((next = br.readLine()) != null) {
                int type = next.indexOf("type: ");
                int doneIndex = next.indexOf("isDone: ");
                int content = next.indexOf("content: ");
                boolean isDone = next.substring(doneIndex + 8, content - 1).equals("true");
                Task newTask = createNewTask(next.substring(type + 6, type + 7), next.substring(content + 9), isDone);
                list.addTask(newTask);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

    public void updateFile(TaskList list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path.toFile()))) {
            for (int i = 0; i < list.size(); i++) {
                Task task = list.getTask(i);
                String type = task.getType();
                String content = task.getTask();
                boolean isDone = task.getDone();
                bw.write("type: " + type + " isDone: " + isDone + " content: " + content);
                bw.newLine();
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public void createFileInData() {
        Path dataDir = this.path.getParent();

        if (!Files.exists(dataDir)) {
            try {
                Files.createDirectories(dataDir);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }

        if (!Files.exists(this.path)) {
            try {
                Files.createFile(this.path);
                System.out.println("Storage file created");
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
