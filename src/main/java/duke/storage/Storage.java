package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.storage.CreateTasksFileFailedException;
import duke.exceptions.storage.LoadTasksFailedException;
import duke.exceptions.storage.SaveTasksFailedException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class Storage {
    private final Path filePath;
    private final String argDelimiter;

    public Storage(Path filePath, String argDelimiter) {
        this.filePath = filePath;
        this.argDelimiter = argDelimiter;
    }

    private void createFileIfDontExist() throws DukeException {
        try {
            // Create duke.tasks file if it doesn't exist
            if (!Files.exists(this.filePath)) {
                Path parentDir = this.filePath.getParent();

                // Create parent directory if it doesn't exist
                if (!Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                // Create duke.tasks file
                Files.createFile(this.filePath);
            }
        } catch (IOException ioException) {
            throw new CreateTasksFileFailedException(ioException);
        }
    }

    private ArrayList<Task> readTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            String fileContent = Files.readString(this.filePath);
            String[] fileContentSplit = fileContent.split("\n");

            for (String taskArgsStr : fileContentSplit) {
                String[] taskArgs = taskArgsStr.split(this.argDelimiter);
                String taskType = taskArgs[0];

                Task task;
                String description;
                String isDoneStr;
                boolean isDone;

                switch (taskType) {
                case "deadline": {
                    description = taskArgs[1];
                    String byStr = taskArgs[2];
                    LocalDate by = LocalDate.parse(byStr);
                    isDoneStr = taskArgs[3];
                    isDone = isDoneStr.equals("1");

                    task = new Deadline(description, isDone, by);
                    break;
                }
                case "event": {
                    description = taskArgs[1];
                    String fromStr = taskArgs[2];
                    String toStr = taskArgs[3];
                    LocalDate from = LocalDate.parse(fromStr);
                    LocalDate to = LocalDate.parse(toStr);
                    isDoneStr = taskArgs[4];
                    isDone = isDoneStr.equals("1");

                    task = new Event(description, isDone, from, to);
                    break;
                }
                case "todo": {
                    description = taskArgs[1];
                    isDoneStr = taskArgs[2];
                    isDone = isDoneStr.equals("1");

                    task = new Todo(description, isDone);
                    break;
                }
                default:
                    continue;
                }

                tasks.add(task);
            }

            return tasks;
        } catch (IOException ioException) {
            throw new LoadTasksFailedException(ioException);
        }
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        this.createFileIfDontExist();
        return this.readTasks();
    }

    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            ArrayList<String> tasksData = new ArrayList<>();

            Files.writeString(this.filePath, "");

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                tasksData.add(task.serialize());
            }

            Files.writeString(this.filePath, String.join("\n", tasksData));
        } catch (IOException ioException) {
            throw new SaveTasksFailedException(ioException);
        }
    }
}
