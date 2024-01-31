package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to handle loading and saving of tasks to a text file.
 */
public class Storage {
    private String filePath;

    /**
     * Initializes a Storage object with file path to text file.
     *
     * @param filePath the path of the file to save the tasks to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file, processing of text to task is done in parseTasksFromString.
     *
     * @return the list of tasks loaded from the file.
     * @throws IOException if there is an error reading the file.
     * @throws DukeException if the file could not be created.
     */
    public ArrayList<Task> loadTaskslist() throws IOException, DukeException {
        ArrayList<Task> tasksList = new ArrayList<Task>();

        File file = new File(filePath);
        if (!file.exists()) {
            boolean isCreated = false;
            isCreated = file.getParentFile().mkdirs();
            isCreated = file.createNewFile();

            if (!isCreated) {
                throw new DukeException("File could not be created.");
            }
        } else {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = parseTasksFromString(line);
                tasksList.add(task);
            }
            s.close();
        }

        return tasksList;
    }

    private Task parseTasksFromString(String line) throws DukeException {
        String[] split = line.split(" \\| ");

        // common fields
        String taskType = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];

        Task task = null;
        // class specific fields
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            String by = split[3];
            task = new Deadline(description, Task.getInputDateFormat(by));
        } else if (taskType.equals("E")) {
            String from = split[3];
            String to = split[4];
            task = new Event(description, Task.getInputDateFormat(from), Task.getInputDateFormat(to));
        }

        if (task == null) {
            throw new DukeException("Invalid task type");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Writes the tasks to the file and overwrites the file if it already exists.
     *
     * @param tasksList the list of tasks to be written to the file.
     * @throws DukeException if there is an error writing to the file.
     * @throws IOException if there is an error writing to the file.
     */
    public void writeToFile(TaskList tasksList) throws DukeException, IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        for (int i = 0; i < tasksList.getSize(); i++) {
            Task task = tasksList.getTask(i);
            if (task instanceof Todo) {
                fileWriter.write(
                    task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fileWriter.write(
                    deadline.getTaskType() + " | " + (deadline.isDone ? "1" : "0") + " | "
                        + deadline.getDescription() + " | " + Task.getLocalDateOutputFormat(deadline.getBy()) + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fileWriter.write(
                    task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | "
                        + task.getDescription() + " | " + Task.getLocalDateOutputFormat(event.getFrom()) + " | "
                        + Task.getLocalDateOutputFormat(event.getTo()) + "\n");
            }
        }
        fileWriter.close();
    }


}
