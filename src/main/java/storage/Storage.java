package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.BuddyException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;


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
                readData(f);
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
                String taskToBeWritten = taskList.getTask(i).toWritableString();
                fw.write(taskToBeWritten);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException ioe) {
            throw new BuddyException(ioe.getMessage());
        }
    }

    private void readData(File f) throws IOException, BuddyException {
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String data = sc.nextLine();
            parseData(data);
        }
    }
    private void parseData(String data) throws BuddyException {
        String[] dataParts = data.split("\\|");
        String taskType = dataParts[0].trim();
        boolean isDone = dataParts[1].trim().equals("true");
        String task = dataParts[2].trim();

        switch (taskType) {
        case "T":
            addTodo(task, isDone);
            break;
        case "D":
            String deadlineString = dataParts[3].trim();
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, Task.DATE_TIME_STRING_FORMAT);
            addDeadline(task, deadline, isDone);
            break;
        case "E":
            String startTimeString = dataParts[3].trim();
            String endTimeString = dataParts[4].trim();
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, Task.DATE_TIME_STRING_FORMAT);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, Task.DATE_TIME_STRING_FORMAT);
            addEvent(task, startTime, endTime, isDone);
            break;
        default:
            throw new BuddyException("Contents of the file are in incorrect format!");
        }
    }
    private void addTodo(String task, boolean isDone) {
        Todo td = new Todo(task);
        td.setDone(isDone);
        taskList.add(td);
    }
    private void addDeadline(String task, LocalDateTime deadline, boolean isDone) {
        Deadline dl = new Deadline(task, deadline);
        dl.setDone(isDone);
        taskList.add(dl);
    }
    private void addEvent(String task, LocalDateTime from, LocalDateTime to, boolean isDone) {
        Event ev = new Event(task, from, to);
        ev.setDone(isDone);
        taskList.add(ev);
    }
}
