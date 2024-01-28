import CustomExceptions.NoTaskCreatedYetException;
import CustomExceptions.TooManyTasksException;
import TaskList.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DataStorage {
    private ArrayList<Task> tasksList;
    private int taskCount;
    private int maxTask;
    private File file;

    public DataStorage(int maxTask, String fileName) {
        this.maxTask = maxTask;
        this.tasksList = new ArrayList<>();
        this.taskCount = 0;

        this.file = new File(fileName);

        // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
        try {
            if (this.file.createNewFile()) {
                // TODO: Remove this.
                System.out.println("\t The database has not been created. A new database has been created at the following location: " + this.file.getAbsolutePath());
            } else {
                System.out.println("\t Using the existing database located at: " + this.file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("An error occurred while opening the file. \n" +
                    "An error occurred on your device; please check that there is at least 100 MB of free disk space.");
            System.exit(1); // Exit the program. 1 indicates abnormal termination.
        }

    }

    public Task getTask(int index) {
        if (index < 0 || index > this.taskCount) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.tasksList.get(index);
        }
    }

    public void addTask(Task task) throws TooManyTasksException {
        if (taskCount >= maxTask) {
            throw new TooManyTasksException();
        } else {
            this.tasksList.add(task);
            this.taskCount++;
        }
    }

    public void setTaskStatus(int taskIndex, boolean status) throws NoTaskCreatedYetException {
        if (taskIndex < 0 || taskIndex > this.maxTask) {
            throw new IndexOutOfBoundsException();
        } else if (taskIndex >= taskCount) {
            // It is a valid index, but there is no task there yet.
            throw new NoTaskCreatedYetException();
        } else {
            this.tasksList.get(taskIndex).setDone(status);
        }
    }


    public int getTaskCount() {
        return this.taskCount;
    }


    public void deleteTask(int indexToDelete) throws NoTaskCreatedYetException {
        System.out.println(indexToDelete + " " + taskCount);
        if (indexToDelete < 0 || indexToDelete > this.maxTask) {
            throw new IndexOutOfBoundsException();
        } else if (indexToDelete >= this.taskCount) {
            // It is a valid index, but there is no task there yet.
            throw new NoTaskCreatedYetException();
        } else {
            // If we reach here, it means that there is no problem.
            this.tasksList.remove(indexToDelete);
            this.taskCount--;
        }
    }
}
