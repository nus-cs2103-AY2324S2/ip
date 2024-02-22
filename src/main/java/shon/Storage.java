package shon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage that stores the user's todo list.
 */
public class Storage {
    /** The file object storing the data for the user's todo list */
    private File file;
    /** The different type of task encoded in the data file */
    private enum TaskType {
        T, D, E
    }

    /**
     * Returns a <code>Storage</code> object with the data found at filepath. If no pre-existing data exists, a new file
     * along with any relevant parent directory is created.
     *
     * @param filepath The filepath to the file storing the user's todo list.
     * @throws Error If an IO error occurred while trying to create the new data file. Suggests potential errors in
     *     creating the relevant parent directories, or bug in storage initialisation.
     */
    public Storage(String filepath) {
        try {
            this.file = new File(filepath);
            File dir = this.file.getParentFile();
            boolean isNewDir = dir.mkdirs();
            boolean isNewFile = this.file.createNewFile();

            assert dir.exists() : "Parent directory(s) for storage is not created";
            assert this.file.exists() : "Storage file is not created";
        } catch (IOException e) {
            throw new Error("There is an error in creating/opening the \"Shon.txt\" file."
                    + " Check if new directory is created.");
        }
    }

    /**
     * Loads the pre-existing list from the stored data file.
     *
     * @return <code>TaskList</code> loaded with the tasks and data from the existing data.
     * @throws Error If the data file is not found. Suggests that data file does not exist and
     *     potential errors in storage initialisation.
     */
    public TaskList loadList() {
        try {
            assert this.file.exists() : "Storage file is not created";
            Scanner scanner = new Scanner(this.file);
            TaskList list = new TaskList();
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                TaskType taskType = TaskType.valueOf(String.valueOf(data.charAt(0)));
                switch (taskType) {
                case T:
                    addTodo(data, list);
                    break;
                case D:
                    addDeadline(data, list);
                    break;
                case E:
                    addEvent(data, list);
                    break;
                default:
                    System.out.println("Storage is in wrong format.");
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new Error("Check that new data file is created (if needed) in initializer.");
        }
    }

    private void addTodo(String data, TaskList list) {
        String[] d = data.split(" \\| ", 3);
        assert d[0].equals("T") : "Provided data is not a Todo storage data";
        String isDoneStatus = d[1];
        String description = d[2];
        boolean isDone = isDoneStatus.equals("1");
        list.addTodo(description, isDone);
    }

    private void addDeadline(String data, TaskList list) {
        String[] d = data.split(" \\| ", 4);
        assert d[0].equals("D") : "Provided data is not a Deadline storage data";
        String isDoneStatus = d[1];
        String description = d[2];
        String by = d[3];
        boolean isDone = isDoneStatus.equals("1");
        list.addDeadline(description, by, isDone);
    }

    private void addEvent(String data, TaskList list) {
        String[] d = data.split(" \\| ", 5);
        assert d[0].equals("E") : "Provided data is not an Event storage data";
        String isDoneStatus = d[1];
        String description = d[2];
        String from = d[3];
        String to = d[4];
        boolean isDone = isDoneStatus.equals("1");
        list.addEvent(description, from, to, isDone);
    }

    /**
     * Writes to the data file with the updated data in the TaskList.
     *
     * @param tasks TaskList to be formatted and stored.
     */
    public void updateData(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.file.getPath());
            for (String line : tasks.formatData()) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to data file.");
        }
    }
}
