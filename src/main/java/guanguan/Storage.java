package guanguan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Responsible for saving tasks to a text file.
 */
public class Storage {
    private final String filepath;
    private final ArrayList<Task> items = new ArrayList<>();

    /**
     * Constructor for Storage.
     *
     * @param filepath path of text file
     */
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads data from text file.
     *
     * @return list of tasks
     * @throws GgException if unable to understand file content
     */
    public ArrayList<Task> readData() throws GgException {
        try {
            File file = new File(filepath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                return new ArrayList<>();
            }

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] lineArray = reader.nextLine().split(" \\| ");

                Task task = null;
                String taskType = lineArray[0];
                boolean isDone = Objects.equals(lineArray[1], "1");

                switch (taskType) {
                case "T":
                    // format [T | 0 | borrow book]
                    task = new Todo(lineArray[2]);
                    break;
                case "D":
                    // format [D | 0 | borrow book | today]
                    task = new Deadline(lineArray[2], Utils.convertStringToDateTime(lineArray[3]));
                    break;
                case "E":
                    // format [E | 0 | project meeting | Mon 2pm | 4pm]
                    task = new Event(lineArray[2], Utils.convertStringToDateTime(lineArray[3]),
                            Utils.convertStringToDateTime(lineArray[4]));
                    break;
                default:
                    break;
                }
                if (task == null) {
                    throw new GgException("Task Type not found!");
                }

                task.isDone = isDone;
                items.add(task);
            }
            reader.close();

        } catch (IOException e) {
            throw new GgException("Task Type not found!");
        }
        return items;
    }

    /**
     * Saves data to text file.
     *
     * @param items list of tasks
     * @throws GgException if unable to save tasks/write file
     */
    public void saveData(TaskList items) throws GgException {
        try {
            StringBuilder builder = new StringBuilder();

            for (Task item : items.getAllTasks()) {
                builder.append(item.saveToText()).append("\n");
            }

            FileWriter fileWriter = new FileWriter(this.filepath);
            fileWriter.write(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new GgException("Unable to store tasks!");
        }
    }
}
