package aaron.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import aaron.exception.AaronBotException;
import aaron.task.Deadline;
import aaron.task.Event;
import aaron.task.Task;
import aaron.task.TaskList;
import aaron.task.Todo;

/**
 * This class writes to and loads existing tasklist files for the aaronbot
 * 
 * @param fileToPath
 * @param taskList
 * @throws IOException
 */
public class Storage {
    /**
     * Method to write a tasklist to the filepath specified
     * @param filePath filepath
     * @param taskListWrite task list to be written
     * @throws IOException if error occurs during file writing
     */
    public static void writeToFile(String filePath, TaskList taskListWrite) throws IOException {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        File prevTaskList = new File(filePath);
        prevTaskList.createNewFile();
        if (prevTaskList.exists()) {
            prevTaskList.delete();
        }
        try {
            FileWriter writer = new FileWriter(filePath, true);
            for (int i = 1; i < taskListWrite.getTasklistSize() + 1; i++) {
                String taskDesc = taskListWrite.printTask(i).toString();
                writer.write(taskDesc + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Error loading file");
        }
    }

    /**
     * Method to load tasklist from text file
     * @param filePath filepath of text file
     * @return arraylist<task> of tasks
     * @throws FileNotFoundException if file not found
     */
    public static ArrayList<Task> loadFromFile(String filePath) throws FileNotFoundException {
        createFiles(filePath);
        ArrayList<Task> loadTaskList = new ArrayList<Task>();
        File taskListFile = new File(filePath);
        Scanner fileScanner = new Scanner(taskListFile);
        while (fileScanner.hasNext()) {
            String task = fileScanner.nextLine();
            char taskType = task.charAt(1);
            boolean isDone = (task.charAt(4) == 'X');
            switch (taskType) {
            case ('T'):
                loadTodo(task, isDone, loadTaskList);
                break;
            case ('E'):
                loadEvent(task, isDone, loadTaskList);
                break;
            case ('D'):
                loadDeadline(task, isDone, loadTaskList);
                break;
            default:
                throw new FileNotFoundException("File not found?");
            }
        }
        fileScanner.close();
        return loadTaskList;
    }

    /**
     * Helper method to load a todo task specifically
     * @param task task string
     * @param isDone done-ness
     * @param taskList new tasklist to load task onto
     */
    private static void loadTodo(String task, boolean isDone, ArrayList<Task> taskList) {
        String taskDescTodo = task.substring(9);
        Todo todoTask = new Todo(taskDescTodo, isDone);
        taskList.add(todoTask);
    }

    /**
     * Helper method to load an event task specifically
     * @param task task string
     * @param isDone done-ness
     * @param taskList new tasklist to load task onto
     */
    private static void loadEvent(String task, boolean isDone, ArrayList<Task> taskList) {
        String taskDescEvent = task.substring(9);
        String[] taskStartEnd = taskDescEvent.split(" \\| ", 3);
        try {
            Event eventTask = new Event(taskStartEnd[0], taskStartEnd[1], taskStartEnd[2], isDone);
            taskList.add(eventTask);
        } catch (AaronBotException e) {
            System.out.println("Error reading a task: " + task);
        }
    }

    /**
     * Helper method to load a deadline task specifically
     * @param task task string
     * @param isDone done-ness
     * @param taskList new tasklist to load task onto
     */
    private static void loadDeadline(String task, boolean isDone, ArrayList<Task> taskList) {
        String taskDescDeadline = task.substring(9);
        String taskDeadline[] = taskDescDeadline.split(" \\| ", 2);
        try {
            System.out.println("task deadline \n" + taskDeadline[1]);
            Deadline deadlineTask = new Deadline(taskDeadline[0], taskDeadline[1], isDone);
            taskList.add(deadlineTask);
        } catch (AaronBotException e) {
            System.out.println("Error reading a task: " + task);
        }
    }

    /**
     * Helper method to create folder and file for tasklist
     * @param filePath filepath
     */
    private static void createFiles(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(file);
                writer.close();
            } catch (IOException e) {
                //Error creating files will be caught in loadFromFile function
            }
        }
    }
}
