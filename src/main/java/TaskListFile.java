import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class writes to and loads existing tasklist files for the aaronbot
 * @param fileToPath
 * @param taskList
 * @throws IOException
 */
public class TaskListFile {
    public static void writeToFile(String filePath, ArrayList<Task> taskListWrite) throws IOException {
        File prevTaskList = new File(filePath);
        if (prevTaskList.exists()) {
            prevTaskList.delete();
        }
        FileWriter writer = new FileWriter(filePath, true);
        for (int i = 0; i < taskListWrite.size(); i++) {
            String taskDesc = taskListWrite.get(i).toString();
            writer.write(taskDesc + System.lineSeparator());
        }
        writer.close();
    }

    public static ArrayList<Task> loadFromFile(String filePath) throws FileNotFoundException {
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
                } catch (InvalidDateException e) {
                    System.out.println("Error reading a task: " + task);
                }
                break;
            case ('D'):
                String taskDescDeadline = task.substring(9);
                String taskDeadline[] = taskDescDeadline.split(" \\| ", 2);
                try {
                    Deadline deadlineTask = new Deadline(taskDeadline[0], taskDeadline[1], isDone);
                    loadTaskList.add(deadlineTask);
                } catch (InvalidDateException e) {
                    System.out.println("Error reading a task: " + task);
                }
                break;
            default:
                System.out.println(task);
            }
        }
        fileScanner.close();
        return loadTaskList;
    }
}
