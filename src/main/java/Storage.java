import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void getFileContents(TaskList tasks) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
                return;
            }

            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = createTaskFromString(line);
                tasks.addTask(task);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private Task createTaskFromString(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String byString = parts[3];
                LocalDate by = LocalDate.parse(byString);
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String fromTo = parts[3];
                String[] fromToParts = fromTo.split(" ");
                LocalDate fromDate = LocalDate.parse(fromToParts[0]);
                String fromTime = fromToParts[1];
                LocalDate toDate = LocalDate.parse(fromToParts[2]);
                String toTime = fromToParts[3];
                Event event = new Event(description, fromDate, fromTime, toDate, toTime);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                return null; // Unknown type
        }
    }

    public void writeToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                if (task == null) {
                    break;
                }
                fw.write(task.toFileString() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
