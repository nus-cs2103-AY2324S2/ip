package bartenderbob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage class to store user tasks into the hard disk.
 */
public class Storage {
    /** File path to the storage file */
    private String filePath; //eg "./data/tasks.txt"
    /** Displays messages to the user */
    private Ui ui = new Ui();

    /**
     * Creates an instance of the Storage class and initialises its file path.
     *
     * @param filePath File path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the storage file into BartenderBob.
     *
     * @return ArrayList of tasks
     * @throws BartenderBobException If error occurred while loading the tasks into BartenderBob.
     */
    public ArrayList<Task> load() throws BartenderBobException {
        try {
            assert filePath != null : "File path cannot be null";
            ArrayList<Task> taskArray = new ArrayList<>();
            Path path = Paths.get(filePath); //Operating system independent
            if (Files.exists(path)) {
                List<String> tasks = Files.readAllLines(path);
                for (String taskString : tasks) {
                    String[] taskStringComponents = taskString.split(" \\| ");
                    String taskType = taskStringComponents[0];
                    String taskStatus = taskStringComponents[1];
                    boolean isDone = taskStatus.equals("X");
                    String description = taskStringComponents[2];

                    if (taskType.equals("T")) {
                        taskArray.add(new ToDo(description, isDone));
                    } else if (taskType.equals("D")) {
                        String dueDate = taskStringComponents[3];
                        dueDate = convertDateFormat(dueDate);
                        taskArray.add(new Deadline(description, dueDate, isDone));
                    } else if (taskType.equals("E")) {
                        String fromDate = taskStringComponents[3];
                        fromDate = convertDateFormat(fromDate);
                        String toDate = taskStringComponents[4];
                        toDate = convertDateFormat(toDate);
                        taskArray.add(new Event(description, fromDate, toDate, isDone));
                    }
                }
            } else {
                //Create data directory
                //if path is "./data/tasks.txt", then path.getParent()
                //gives data.
                Files.createDirectories(path.getParent());
                //Create tasks.txt
                Files.createFile(path);
            }
            return taskArray;
        } catch (IOException e) {
            throw new BartenderBobException();
        }
    }

    /**
     * Save changes to the files in the hard disk
     * especially after unmarking, marking and deleting them.
     *
     * @param tasks The tasks that the user has inputted to BartenderBob.
     */
    public void saveChanges(ArrayList<Task> tasks) {
        try {
            assert filePath != null : "File path cannot be null";
            assert tasks != null : "Task cannot be null";
            Path path = Paths.get(filePath);
            Files.write(path, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task : tasks) {
                saveTask(task);
            }
        } catch (IOException e) {
            ui.showSaveChangesError();
        }

    }

    /**
     * Convert date format from MMM dd yyyy to yyyy-MM-dd
     *
     * @param oldDateFormat Date which is of MMM dd yyyy format.
     * @return Date which is of yyyy-MM-dd format.
     */
    private String convertDateFormat(String oldDateFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        // Parse the original string to LocalDate
        LocalDate localDate = LocalDate.parse(oldDateFormat, inputFormatter);

        // Define the formatter for the output pattern
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the LocalDate to the desired output pattern
        return localDate.format(outputFormatter);
    }

    /**
     * Saves tasks to the storage file specified by the filepath,
     * especially after adding them to the tasks list.
     *
     * @param task The task that we are saving to the storage file.
     */
    public void saveTask(Task task) {
        //[T][X] read book = T | X | read book
        //[D][ ] return book (by: June 6th) = D |  | return book | June 6th
        //[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
        // = E |  | project meeting | Aug 6th 2pm | 4pm
        try {
            assert task != null : "Task cannot be null";
            Path path = Paths.get(filePath);
            String taskString = task.show();
            String typeOfTask = taskString.substring(1, 2); //T
            String taskStatus = taskString.substring(4, 5); //X
            String taskDescription = "";
            String saveEntry = "";
            if (typeOfTask.equals("T")) {
                taskDescription = taskString.substring(7);
                saveEntry = typeOfTask + " | " + taskStatus + " | " + taskDescription;
            } else if (typeOfTask.equals("D")) {
                int startIndex = taskString.indexOf("(by: ");
                int endIndex = taskString.indexOf(")");
                taskDescription = taskString.substring(7, startIndex - 1);
                String deadline = taskString.substring(startIndex + 5, endIndex);
                saveEntry = typeOfTask + " | " + taskStatus + " | " + taskDescription
                        + " | " + deadline;
            } else if (typeOfTask.equals("E")) {
                int startIndex = taskString.indexOf("(from: ");
                taskDescription = taskString.substring(7, startIndex - 1);
                int endIndex = taskString.indexOf(" to:");
                String fromDate = taskString.substring(startIndex + 7, endIndex);
                startIndex = taskString.indexOf(")");
                String toDate = taskString.substring(endIndex + 5, startIndex);
                saveEntry = typeOfTask + " | " + taskStatus + " | " + taskDescription
                        + " | " + fromDate + " | " + toDate;
            }
            Files.write(path, (saveEntry + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            ui.showSaveTasksError();
        }
    }
}
