import CustomExceptions.MalformedUserInputException;
import CustomExceptions.NoTaskCreatedYetException;
import CustomExceptions.TooManyTasksException;
import TaskList.Deadline;
import TaskList.Event;
import TaskList.Task;
import TaskList.Todo;

import java.io.*;
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
                System.out.println("\t The database has not been created. A new database has been created at the following location: " + this.file.getAbsolutePath());
            } else {
                // This means that the file already exists here.
                System.out.println("\t Using the existing database located at: " + this.file.getAbsolutePath());
                this.tasksList = readFromDatabaseIfAlreadyCreated();
            }


        } catch (IOException e) {
            System.err.println("An error occurred while opening the file. \n" +
                    "An error occurred on your device; please check that there is at least 100 MB of free disk space.");
            System.exit(1); // Exit the program. Non-zero indicates abnormal termination.
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
            addTaskToFile(task.toStorageString(), true);
            this.taskCount++;
        }
    }


    public void addTaskToFile(String line, boolean isAppend) {
        System.out.println(line);
        try {
            // Solution below adapted from: https://www.w3schools.com/java/java_files_create.asp
            FileWriter myWriter = new FileWriter(this.file, isAppend);
            myWriter.append(line);
            myWriter.append("\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    public ArrayList<Task> readFromDatabaseIfAlreadyCreated() {
        ArrayList<Task> tasksList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // Continuously read until the end of file.
            while ((line = bufferedReader.readLine()) != null) {
                Task task = parseTask(line);
                tasksList.add(task);
                this.taskCount++;
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        } catch (MalformedUserInputException malformedUserInputException) {
            System.out.println("Your database may be corrupted.");
        }

        return tasksList;
    }

    private Task parseTask(String line) throws MalformedUserInputException {
        String[] splitTask = line.split(" \\| ");

        // TODO: Handle a dirty input.
        if (line.startsWith("T")) {
            // then it is a task
            return new Todo(splitTask[1], Boolean.valueOf(splitTask[2]));
        } else if (line.startsWith("D")) {
            // then it is a deadline
            return new Deadline(splitTask[1], splitTask[3], Boolean.valueOf(splitTask[2]));
        } else if (line.startsWith("E")) {
            // then it is an event
            return new Event(splitTask[1], splitTask[3], splitTask[4], Boolean.valueOf(splitTask[2]));
        } else {
            throw new MalformedUserInputException();
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
            // We rebuild the dataStorage again
            rebuildStorage();
        }
    }


    public int getTaskCount() {
        return this.taskCount;
    }

    private void rebuildStorage() {
        // TODO: might not be a very good idea to rebuild the entire database.
        for (int i = 0; i < this.taskCount; i++) {
            // i != 0 means that refresh the whole file.
            addTaskToFile(tasksList.get(i).toStorageString(), i != 0);
        }
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

            // We rebuild the dataStorage again
            rebuildStorage();
        }
    }
}
