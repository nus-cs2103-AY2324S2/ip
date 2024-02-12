package storage;

import exception.BuddyException;
import task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage component of buddy.Buddy, storing Task data.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates Storage object with specified location of data file.
     * @param filePath Location of data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from pre-existing file specified in filePath.
     *
     * @return ArrayList of Tasks.
     * @throws BuddyException If unable to load file or if format of saved data is incorrect.
     */
    public ArrayList<Task> load() throws BuddyException {
        File f = new File(filePath);
        try {
            if (!f.createNewFile()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String data = sc.nextLine();
                    String[] dataParts = data.split(" \\| ");
                    boolean isDone = dataParts[1].equals("true");
                    switch (dataParts[0]) {
                    case "T":
                        Todo todo = new Todo(dataParts[2]);
                        todo.setDone(isDone);
                        taskList.add(todo);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(
                                dataParts[3], Task.DATE_TIME_STRING_FORMAT);
                        Deadline deadline = new Deadline(dataParts[2], by);
                        deadline.setDone(isDone);
                        taskList.add(deadline);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(
                                dataParts[3], Task.DATE_TIME_STRING_FORMAT);
                        LocalDateTime to = LocalDateTime.parse(
                                dataParts[4], Task.DATE_TIME_STRING_FORMAT);
                        Event event = new Event(dataParts[2], from, to);
                        event.setDone(isDone);
                        taskList.add(event);
                        break;
                    default:
                        assert false : "File contents are incorrect";
                    }
                }
            }
        } catch (IOException ioe) {
            throw new BuddyException(ioe.getMessage());
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new BuddyException("Contents of data file are incorrect!\nPlease delete/modify the file!\n");
        }
        return taskList;
    }

    /**
     * Saves data from TaskList to a file specified in filePath.
     *
     * @param taskList TaskList consisting of Tasks to be saved
     * @throws BuddyException If unable to save to file.
     */
    public void save(TaskList taskList) throws BuddyException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i).writeTask());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException ioe) {
            throw new BuddyException(ioe.getMessage());
        }
    }
}
