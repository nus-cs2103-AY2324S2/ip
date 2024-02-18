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
                String taskDescTodo = task.substring(9);
                Todo todoTask = new Todo(taskDescTodo, isDone);
                loadTaskList.add(todoTask);
                break;
            case ('E'):
                String taskDescEvent = task.substring(9);
                String[] taskStartEnd = taskDescEvent.split(" \\| ", 3);
                try {
                    Event eventTask = new Event(taskStartEnd[0], taskStartEnd[1], taskStartEnd[2], isDone);
                    loadTaskList.add(eventTask);
                } catch (AaronBotException e) {
                    System.out.println("Error reading a task: " + task);
                }
                break;
            case ('D'):
                String taskDescDeadline = task.substring(9);
                String taskDeadline[] = taskDescDeadline.split(" \\| ", 2);
                try {
                    System.out.println("task deadline \n" + taskDeadline[1]);
                    Deadline deadlineTask = new Deadline(taskDeadline[0], taskDeadline[1], isDone);
                    loadTaskList.add(deadlineTask);
                } catch (AaronBotException e) {
                    System.out.println("Error reading a task: " + task);
                }
                break;
            default:
                throw new FileNotFoundException("File not found?");
            }
        }
        fileScanner.close();
        return loadTaskList;
    }

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
