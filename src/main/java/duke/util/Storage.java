package duke.util;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.LoadStorageException;
import duke.exception.SaveStorageException;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {
    String relativeFilePath;

    public Storage(String relativeFilePath) {
        this.relativeFilePath = relativeFilePath;
    }

    public ArrayList<Task> load() throws LoadStorageException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Path absolutePath = Paths.get(this.relativeFilePath).toAbsolutePath();

            // Create the file if it doesn't exist
            if (!Files.exists(absolutePath)) {
                Files.createFile(absolutePath);
                System.out.println("Welcome to theGiantPeach!");
            } else {
                List<String> lines = Files.readAllLines(absolutePath);

                // Process the lines
                for (String line : lines) {
                    String[] words = line.split("\\|");
                    switch (words[0]) {
                        case "T":
                            Todo todo = new Todo(words[2]);
                            if (Objects.equals(words[1], "1")) {
                                todo.changeDone();
                            }
                            taskList.add(todo);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(words[2], words[3]);
                            if (Objects.equals(words[1], "1")) {
                                deadline.changeDone();
                            }
                            taskList.add(deadline);
                            break;
                        case "E":
                            Event event = new Event(words[2], words[3], words[4]);
                            if (Objects.equals(words[1], "1")) {
                                event.changeDone();
                            }
                            taskList.add(event);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new LoadStorageException();
        }
        return taskList;
    }

    public void save(TaskList taskList) throws SaveStorageException {
        List<String> linesToWrite = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            linesToWrite.add(taskList.get(i).toDBString());
        }

        try {
            Path absolutePath = Paths.get(relativeFilePath).toAbsolutePath();

            Files.write(absolutePath, linesToWrite, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (Exception e) {
            throw new SaveStorageException();
        }
    }

}
