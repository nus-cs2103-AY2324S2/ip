package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import task.TaskList;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import exception.XiaoBaiException;

/**
 * Handles the reading from and writing to a file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the contents of a TaskList to the file.
     *
     * @param list The TaskList containing tasks to be written to the file.
     */
    public void writeArrayListToFile(TaskList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task element : list.getList()) {
                writer.write(element.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList containing tasks loaded from the file.
     * @throws XiaoBaiException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws XiaoBaiException {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                String task = reader.readLine();
                char taskType = task.charAt(1);
                char taskStatus = task.charAt(4);
                boolean isDone;
                if (taskStatus == 'X') {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String entireTask = task.substring(7);
                switch (taskType) {
                    case 'T':
                        taskList.add(new Todo(entireTask, isDone));
                        break;
                    case 'D':
                        Deadline newDeadline = processLoadingDeadline(entireTask, isDone);
                        taskList.add(newDeadline);
                        break;
                    case 'E':
                        Event newEvent = processLoadingEvent(entireTask, isDone);
                        taskList.add(newEvent);
                        break;
                }
            }
        } catch (IOException e) {
            throw new XiaoBaiException("Unable to load txt file");
        }
        return taskList;
    }

    /**
     * Processes a string representing a deadline task loaded from storage.
     * Extracts the task details and deadline information, then constructs a
     * Deadline object.
     *
     * @param entireTask The entire string representing the deadline task.
     * @param isDone     The completion status of the task.
     * @return A Deadline object representing the loaded deadline task.
     * @throws XiaoBaiException If there is an error parsing the task details.
     */
    public Deadline processLoadingDeadline(String entireTask, boolean isDone) throws XiaoBaiException {
        int startIndex = entireTask.indexOf("(by:");
        int endIndex = entireTask.length() - 1;
        String byDetails = entireTask.substring(startIndex + 5, endIndex);
        String taskDetail = entireTask.substring(0, startIndex - 1);
        return new Deadline(taskDetail, byDetails, isDone);
    }

    /**
     * Processes a string representing an event task loaded from storage.
     * Extracts the task details and event timing information, then constructs an
     * Event object.
     *
     * @param entireTask The entire string representing the event task.
     * @param isDone     The completion status of the task.
     * @return An Event object representing the loaded event task.
     * @throws XiaoBaiException If there is an error parsing the task details.
     */
    public Event processLoadingEvent(String entireTask, boolean isDone) throws XiaoBaiException {
        int toIndex = entireTask.indexOf("to:");
        int fromIndex = entireTask.indexOf("(from:");
        int toLastIndex = entireTask.length() - 1;
        String taskDetail = entireTask.substring(0, fromIndex - 1);
        String toText = entireTask.substring(toIndex + 4, toLastIndex);
        String fromText = entireTask.substring(fromIndex + 7, toIndex - 1);
        return new Event(taskDetail, fromText, toText, isDone);
    }
}
