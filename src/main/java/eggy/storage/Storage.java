package eggy.storage;

import eggy.task.TaskList;
import eggy.task.Deadline;
import eggy.task.Event;
import eggy.task.Task;
import eggy.task.Todo;
import eggy.exception.EggyException;
import eggy.exception.InvalidTaskTypeException;
import eggy.exception.LoadTasksException;
import eggy.exception.SaveTasksException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    private enum TaskType {
        T, D, E
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws EggyException {
        List<Task> tempTasks = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    String[] taskStrings = line.split(" \\| ");
                    try {
                        TaskType taskType = TaskType.valueOf(taskStrings[0]);
                        Task task;
                        switch (taskType) {
                            case T:
                                task = new Todo(taskStrings[2], taskStrings[1].equals("1"));
                                break;
                            case D:
                                task = new Deadline(taskStrings[2], LocalDateTime.parse(taskStrings[3]), taskStrings[1].equals("1"));
                                break;
                            case E:
                                task = new Event(taskStrings[2], LocalDateTime.parse(taskStrings[3]), LocalDateTime.parse(taskStrings[4]), taskStrings[1].equals("1"));
                                break;
                            default:
                                throw new InvalidTaskTypeException();
                        }
                        tempTasks.add(task);
                        line = br.readLine();
                    } catch (IllegalArgumentException e) {
                        throw new InvalidTaskTypeException();
                    }
                }
                br.close();
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            return tempTasks;
        } catch (IOException e) {
            throw new LoadTasksException(this.filePath);
        }
    }

    public void save(TaskList tasks) throws EggyException {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.toFileString());
            fw.close();
        } catch (IOException e) {
            throw new SaveTasksException(this.filePath);
        }
    }
}
