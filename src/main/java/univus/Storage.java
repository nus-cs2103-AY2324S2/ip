package univus;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import univus.task.Deadline;
import univus.task.Event;
import univus.task.Task;
import univus.task.TaskList;
import univus.task.ToDo;


/**
 * Handles the storage of task data in a file for the Univus application.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new instance of the Storage class.
     *
     * @param filepath The file path where task data will be stored and loaded.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Saves the task data from the provided TaskList to the file specified in the constructor.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     */
    public void saveToFile(TaskList taskList) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            ArrayList<Task> store = taskList.getTaskList();
            for (Task task : store) {
                writer.println(task.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Extracts the description from a message.
     *
     * @param message The input message containing the description.
     * @return The extracted description.
     */
    public String getDescription(String message) {
        int startIndex = message.lastIndexOf("]");
        int endIndex = message.lastIndexOf("(");
        String description = message.substring(startIndex + 2, endIndex);
        return description;
    }
    /**
     * Loads a Todo task into the provided taskList based on the given message.
     *
     * @param taskList   The taskList to which the Todo task will be added.
     * @param message The message containing the description of the Todo task.
     */
    public void loadTodo(ArrayList<Task> taskList, String message) {
        int index = message.lastIndexOf("]");
        String description = "todo" + message.substring(index + 1);
        ToDo todo = new ToDo(description);
        taskList.add(todo);
    }
    /**
     * Loads a Deadline task into the provided TaskList based on the given message.
     *
     * @param taskList   The taskList to which the Deadline task will be added.
     * @param message The message containing the description and due date of the Deadline task.
     */
    public void loadDeadline(ArrayList<Task> taskList, String message) {
        int timeIndex = message.lastIndexOf(":");
        String dueDate = "by " + message.substring(timeIndex + 1, message.length() - 1);
        String description = getDescription(message);
        Deadline deadline = new Deadline(description, dueDate);
        taskList.add(deadline);
    }
    /**
     * Loads an Event task into the provided taskList based on the given message.
     *
     * @param taskList   The taskList to which the Event task will be added.
     * @param message The message containing the description, start time, and end time of the Event task.
     */
    public void loadEvent(ArrayList<Task> taskList, String message) {
        int startIdx = message.indexOf(":");
        int endIdx = message.lastIndexOf(":");
        String description = getDescription(message);
        String start = "from" + message.substring(startIdx + 1, endIdx - 2);
        String end = "to" + message.substring(endIdx + 1, message.length() - 1);
        Event event = new Event(description, start, end);
        taskList.add(event);
    }
    /**
     * Loads task data from the file specified in the constructor and returns a TaskList.
     *
     * @return A TaskList containing tasks loaded from the file.
     */
    public TaskList loadFromFile() {
        TaskList store = new TaskList();
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.startsWith("[T]")) {
                    loadTodo(taskList, message);
                } else if (message.startsWith("[D]")) {
                    loadDeadline(taskList, message);
                } else if (message.startsWith("[E]")) {
                    loadEvent(taskList, message);
                }
            }
            store.setTaskList(taskList);
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Creating an empty list.");
            return new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return store;
    }
}
