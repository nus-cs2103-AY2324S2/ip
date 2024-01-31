package duke;

import duke.exception.TasksFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task readTask(String taskEntry) {
        String[] fields = taskEntry.split(" \\| ", 5);

        Task task = null;

        switch (fields[0]) {
        case "T":
            task = new Todo(fields[2]);
            break;

        case "D":
            LocalDateTime dueDateTime = LocalDateTime.parse(fields[3]);
            task = new Deadline(fields[2], dueDateTime);
            break;

        case "E":
            LocalDateTime fromDateTime = LocalDateTime.parse(fields[3]);
            LocalDateTime toDateTime = LocalDateTime.parse(fields[4]);
            task = new Event(fields[2], fromDateTime, toDateTime);
            break;
        }
        if (fields[1].equals("1")) {
            task.changeMark("MARK");
        }
        return task;
    }

    public ArrayList<Task> readTasksFile() throws TasksFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Task task = readTask(sc.nextLine());
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createTaskFile();
        }
        return tasks;
    }
    private void createTaskFile() throws TasksFileException {
        File tasksFile = new File(filePath);
        File dataDirectory = tasksFile.getParentFile();
        dataDirectory.mkdirs();
        try {
            tasksFile.createNewFile();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }

    public void saveTasksFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i <= tasks.getLength(); i++) {
            String formattedTask = tasks.get(i).formatTask();
            fw.write(formattedTask + System.lineSeparator());
        }
        fw.close();
    }

}
