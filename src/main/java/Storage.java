import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTaskslist() throws IOException, DukeException {
        List<Task> tasksList = new ArrayList<Task>();

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

    public void writeToFile(TaskList tasksList) throws DukeException, IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasksList.getSize(); i++) {
            Task task = tasksList.getTask(i);
            if (task instanceof Todo) {
                fileWriter.write(
                    task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fileWriter.write(
                    deadline.getTaskType() + " | " + (deadline.isDone ? "1" : "0") + " | " + deadline.getDescription() +
                        " | " + Task.getLocalDateOutputFormat(deadline.getBy()) + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fileWriter.write(
                    task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " +
                        Task.getLocalDateOutputFormat(event.getFrom()) + " | " + Task.getLocalDateOutputFormat(event.getTo()) + "\n");
            }
        }
        fileWriter.close();
    }


}
