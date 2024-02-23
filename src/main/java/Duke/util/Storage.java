package duke.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.UnknownInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * The Storage class handles reading from and writing to the file that stores tasks.
 */
public class Storage {
    private final String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path of the file storing tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads tasks from the file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList containing tasks read from the file.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner readTextFile = new Scanner(new File(this.filepath));
            while (readTextFile.hasNextLine()) {
                String currentLine = readTextFile.nextLine();
                String[] splits = currentLine.split(" [|] ");
                Task readTask;
                String taskType = splits[0].trim();
                switch (taskType) {
                case "todo":
                    String todoStatus = splits[1].trim();
                    String todoDescription = splits[2].trim();
                    readTask = new ToDo(todoStatus, todoDescription);
                    break;
                case "deadline":
                    String deadlineStatus = splits[1].trim();
                    String deadlineDescription = splits[2].trim();
                    String deadlineDate = splits[3].trim();
                    readTask = new Deadline(deadlineStatus, deadlineDescription, deadlineDate);
                    break;
                case "event":
                    String eventStatus = splits[1].trim();
                    String eventDescription = splits[2].trim();
                    String eventStartDate = splits[3].trim();
                    String eventEndDate = splits[4].trim();
                    readTask = new Event(eventStatus, eventDescription, eventStartDate, eventEndDate);
                    break;
                default:
                    assert false : "Invalid Task Type";
                    throw new UnknownInputException();
                }
                result.add(readTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file, program will now exit");
            return result;
        } catch (UnknownInputException e) {
            System.out.println("Invalid Read");
        }
        return result;
    }

    /**
     * Appends a task's string representation to the file.
     *
     * @param task The task to be written to the file.
     */
    public void addToWriteFile(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath, true));
            writer.append(task.writeObject());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred while writing to file");
        }
    }

    /**
     * Rewrites the file with the tasks from the current list.
     *
     * @param current The current list of tasks.
     */
    public void rewriteFile(ArrayList<Task> current) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath));
            for (int i = 0; i < current.size(); i++) {
                Task currentTask = current.get(i);
                writer.write(currentTask.writeObject());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred while writing to file");
        }
    }
}
