package duke;

import java.io.*;
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
     * @throws DukeException if unable to understand file content
     */
    public ArrayList<Task> readData() throws DukeException {
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

                // format [T | 0 | borrow book]
                if (Objects.equals(taskType, "T")) {
                    task = new Todo(lineArray[2]);
                }
                // format [D | 0 | borrow book | today]
                else if (Objects.equals(taskType, "D")) {
                    task = new Deadline(lineArray[2], Utils.convertStringToDateTime(lineArray[3]));
                }
                // format [E | 0 | project meeting | Mon 2pm | 4pm]
                else if (Objects.equals(taskType, "E")) {
                    task = new Event(lineArray[2], Utils.convertStringToDateTime(lineArray[3]),
                            Utils.convertStringToDateTime(lineArray[4]));
                }

                if (task == null) {
                    throw new DukeException("Task Type not found!");
                }

                task.isDone = isDone;
                items.add(task);
            }
            reader.close();

        } catch (IOException e) {
            throw new DukeException("Task Type not found!");
        }
        return items;
    }

    /**
     * Saves data to text file.
     *
     * @param items list of tasks
     * @throws DukeException if unable to save tasks/write file
     */
    public void saveData(TaskList items) throws DukeException {
        try {
            StringBuilder builder = new StringBuilder();

            for (Task item : items.getAllTasks()) {
                builder.append(item.saveToText()).append("\n");
            }

            FileWriter fileWriter = new FileWriter(this.filepath);
            fileWriter.write(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to store tasks!");
        }
    }
}
