package duke;

import duke.Tasks.Task;
import duke.Tasks.TodoTask;
import duke.Tasks.DeadlineTask;
import duke.Tasks.EventTask;
import duke.Tasks.EventTaskLoad;
import duke.Tasks.DeadlineTaskLoad;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

/**
 * The Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return The list of tasks loaded from the file.
     * @throws DukeException If there is an error loading tasks from the file.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");

                    if (parts.length >= 3) {
                        Task task;

                        switch (parts[0]) {
                            case "T":
                                task = new TodoTask(parts[2]);
                                break;
                            case "D":
                                task = new DeadlineTaskLoad(parts[2], parts[3]);
                                break;
                            case "E":
                                task = new EventTaskLoad(parts[2], parts[3]);
                                break;
                            default:
                                continue;
                        }

                        if (parts[1].equals("1")) {
                            task.markDone();
                        }

                        tasks.add(task);
                    }
                }

                scanner.close();
            }
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }


    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If there is an error saving tasks to the file.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                String taskType;

                if (task instanceof TodoTask) {
                    taskType = "T";
                } else if (task instanceof DeadlineTask) {
                    taskType = "D";
                } else if (task instanceof DeadlineTaskLoad) {
                    taskType = "D";
                } else if (task instanceof EventTask) {
                    taskType = "E";
                } else if (task instanceof EventTaskLoad) {
                    taskType = "E";
                } else {
                    continue;
                }
                writer.write(taskType + " | " + (task.marked ? "1" : "0") + " | " + task.getTask());
                if (task instanceof DeadlineTask) {
                    writer.write(" | " + ((DeadlineTask) task).getDateTime());

                } else if (task instanceof EventTask) {
                    writer.write(" | " + ((EventTask) task).getDateTime());

                } else if (task instanceof EventTaskLoad) {
                    writer.write(" | " + ((EventTaskLoad) task).getTime());

                } else if (task instanceof DeadlineTaskLoad) {
                    writer.write(" | " + ((DeadlineTaskLoad) task).getBy());
                }

                writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}

