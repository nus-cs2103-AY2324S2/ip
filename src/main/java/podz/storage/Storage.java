package podz.storage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import podz.task.Deadline;
import podz.task.Event;
import podz.task.Task;
import podz.task.Todo;

/**
 * Represents a Storage object that handles the reading and writing of tasks to a data file.
 */
public class Storage {
    private static String FILE_PATH;

    /**
     * Constructs a Storage object with the specified file path.
     * 
     * @param filePath the file path where the data are stored.
     */
    public Storage(String filePath) {
        Storage.FILE_PATH = filePath;
    }

    /**
     * Updates the saved tasks in the data file.
     * 
     * @param tasks the list of tasks to be saved.
     */
    public static void updateSaved(ArrayList<Task> tasks) {
        try {
            File data = initDataFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(data));

            for (Task t : tasks) {
                writer.write(t.savedFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Loads tasks from the data file and returns them as an <code>ArrayList<Task></code>.
     * 
     * @return the list of tasks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File data = initDataFile();
            BufferedReader reader = new BufferedReader(new FileReader(data));

            String task;
            while ((task = reader.readLine()) != null) {
                String[] taskInfo = task.split(" \\| ");
                boolean isTodoTask = taskInfo[0].equals("T");
                boolean isDeadlineTask = taskInfo[0].equals("D");
                boolean isEventTask = taskInfo[0].equals("E");

                if (isTodoTask) {
                    addTodo(tasks, taskInfo);
                } else if (isDeadlineTask) {
                    addDeadline(tasks, taskInfo);
                } else if (isEventTask) {
                    addEvent(tasks, taskInfo);
                }
            }
            reader.close();
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private void addEvent(ArrayList<Task> tasks, String[] taskInfo) {
        Event event = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
        checkMarked(taskInfo, event);
        tasks.add(event);
    }

    private void addDeadline(ArrayList<Task> tasks, String[] taskInfo) {
        Deadline deadline = new Deadline(taskInfo[2], taskInfo[3]);
        checkMarked(taskInfo, deadline);
        tasks.add(deadline);
    }

    private void addTodo(ArrayList<Task> tasks, String[] taskInfo) {
        Todo todo = new Todo(taskInfo[2]);
        checkMarked(taskInfo, todo);
        tasks.add(todo);
    }

    private void checkMarked(String[] taskInfo, Task task) {
        boolean isMarked = Integer.parseInt(taskInfo[1]) == 1;
        if (isMarked) {
            task.mark();
        }
    }

    private static File initDataFile() throws IOException {
        File data = new File(FILE_PATH);
        if (!data.getParentFile().exists()) {
            data.getParentFile().mkdirs();
        }
        if (!data.exists()) {
            data.createNewFile();
        }

        return data;
    }
}
