package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
        private static final String FILE_PATH = "./data/duke.txt";
        private TaskList taskList = new TaskList(new ArrayList<Task>());

        public void saveTasks(TaskList tasks) throws IOException {
            File file = new File(FILE_PATH);
            File parentFolder = file.getParentFile();

            if (!parentFolder.exists()) {
                if (!parentFolder.mkdirs()) {
                    throw new IOException("Failed to create the data folder.");
                }
            }

            try (FileWriter fileWriter = new FileWriter(file, false)) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTask(i);
                    String text = task.getType() + " | " + (task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.getDescription();
                    if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.getBy() != null) {
                            try {
                                String formattedDateTime = deadline.getByTime().format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm"));
                                text += " | " + formattedDateTime;
                            } catch (DateTimeException e) {
                                text += " | " + deadline.getByString();
                            }
                        } else {
                            text += " | " + deadline.getByString();
                        }
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        String fromString = (event.getFromTime() != null) ?
                                event.getFromTime().format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm")) :
                                (event.getFromString() != null ? event.getFromString() : "");

                        String toString = (event.getToTime() != null) ?
                                event.getToTime().format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm")) :
                                (event.getToString() != null ? event.getToString() : "");

                        text += " | " + fromString + " - " + toString;
                    }
                    fileWriter.write(text);
                    fileWriter.append("\n");
                }
            }

        }

        public ArrayList <Task> load() throws DukeException {
            ArrayList <Task> loadedTasks = new ArrayList < > ();
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                System.out.println("\nData file does not currently exist. However, as you add a task, it will save it to\nthe " +
                        "path specified. You can view your task list after exiting the chatbot.");
                return loadedTasks;
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    TaskType taskType = TaskType.valueOf(parts[0].trim());
                    boolean isDone = parts[1].trim().equals("1");
                    String description = parts[2].trim();
                    String additionalInfo = (parts.length > 3) ? parts[3].trim() : null;

                    Task task = new Task(null, null);

                    if (taskType == TaskType.T) {
                        task = new ToDo(description);
                    } else if (taskType == TaskType.D) {
                        task = new Deadline(description, additionalInfo);
                    } else if (taskType == TaskType.E) {
                        String[] p = additionalInfo.split("-");
                        task = new Event(description, p[0].trim(), p[1].trim());
                    }

                    loadedTasks.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            return loadedTasks;
        }
    }

