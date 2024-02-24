package jerome.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jerome.commands.FindCommand;
import jerome.commands.ListCommand;
import jerome.exception.MalformedUserInputException;
import jerome.tasklist.Deadline;
import jerome.tasklist.Event;
import jerome.tasklist.Priority;
import jerome.tasklist.Task;
import jerome.tasklist.Todo;

/**
 * Stores and manages tasks in a data storage file.
 * <p>
 * Persist to long-term storage when a new task is added or current task is modified.
 * For retrieval, it makes use of the one that is cached in RAM.
 */
public class DataStorage {
    private ArrayList<Task> tasksList;
    private int taskCount;
    private int maxTask;
    private File file;

    /**
     * Constructs a new DataStorage object with maximum number of tasks and file name.
     * Exits the program if there are problem accessing device IO.
     *
     * @param maxTask  the maximum number of tasks to store.
     * @param fileName the name of the file to use for storage.
     */
    public DataStorage(int maxTask, String fileName) {
        this.maxTask = maxTask;
        this.tasksList = new ArrayList<>();
        this.taskCount = 0;
        this.file = new File(fileName);

        this.createNewFileOrOpenExisting();

    }

    private void createNewFileOrOpenExisting() {
        // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
        try {
            if (!this.file.createNewFile()) {
                // This means that the file already exists here.
                this.tasksList = readTextFileIfAlreadyCreated();
            }
        } catch (IOException e) {
            // The 100 MB is just based on my gut feeling.
            System.err.println("An error occurred while opening the file. \n"
                    + "An error occurred on your device; "
                    + "please check that there is at least 100 MB of free disk space.");
            System.exit(1); // Exit the program. Non-zero indicates abnormal termination.
        }
    }

    /**
     * Retrieves the task at the requested index.
     *
     * @param index the index of the task to be retrieved.
     * @return the task at the specified index.
     * @throws IndexOutOfBoundsException if the index is lesser than zero or greater than the task count.
     */
    public Task getTask(int index) {
        if (index < 0 || index > this.taskCount) {
            throw new IndexOutOfBoundsException();
        }
        return this.tasksList.get(index);
    }

    /**
     * Adds a task to the task list and updates the storage file on HDD.
     *
     * @param task the task to be added.
     */
    public void addTaskToTextFile(Task task) {
        // To check if there is an improper reduction in internal task count.
        assert (this.taskCount >= 0);

        this.tasksList.add(task);
        addTaskToTextFile(task.toStorageString(), true);
        this.taskCount++;
    }


    /**
     * Adds a task to the storage file on HDD.
     *
     * @param line     the entry to be added to the file.
     * @param isAppend flag to select append the line to the end of the file or overwrite the file (completely).
     */
    public void addTaskToTextFile(String line, boolean isAppend) {
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


    /**
     * Deletes everything in the storage file.
     * Used before re-building to ensure that the last
     * item is properly deleted.
     */
    public void deleteAllTasksFromFile() {
        try {
            // False for not append, which means delete.
            // Solution below adapted from: https://www.w3schools.com/java/java_files_create.asp
            FileWriter myWriter = new FileWriter(this.file, false);
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
        }
    }


    /**
     * Reads tasks from the database if it has already been created.
     * Returns an ArrayList of Task objects.
     * An error will be displayed if the file is corruped.
     *
     * @return ArrayList of Task objects read from the database.
     */
    public ArrayList<Task> readTextFileIfAlreadyCreated() {
        ArrayList<Task> tasksList = new ArrayList<>();

        try {
            this.appendReadTasksIntoList(tasksList);
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
        } catch (MalformedUserInputException malformedUserInputException) {
            System.out.println("Your database may be corrupted. " + malformedUserInputException.getMessage());
        }

        return tasksList;
    }

    private void appendReadTasksIntoList(ArrayList<Task> tasksList) throws IOException, MalformedUserInputException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        // Continuously read until the end of file.
        while ((line = bufferedReader.readLine()) != null) {
            Task task = parseTaskStoredInFile(line);
            tasksList.add(task);
            this.taskCount++;
        }

        bufferedReader.close();
        fileReader.close();
    }

    private Task parseTaskStoredInFile(String line) throws MalformedUserInputException {
        String[] splitTask = line.split(" \\| ");
        int length = splitTask.length;

        if (line.startsWith("T")) {
            return this.getTodoTaskFromDatabase(length, splitTask);
        } else if (line.startsWith("D")) {
            return this.getDeadlineTaskFromDatabase(length, splitTask);
        } else if (line.startsWith("E")) {
            return this.getEventTaskFromDatabase(length, splitTask);
        } else {
            throw new MalformedUserInputException("Your database is potentially corrupted");
        }
    }

    private static Event getEventTaskFromDatabase(int length, String[] splitTask) throws MalformedUserInputException {
        // then it is an event
        if (length != 6) {
            throw new MalformedUserInputException("Your database is corrupted.");
        }
        return new Event(splitTask[1], splitTask[3], splitTask[4],
                Boolean.valueOf(splitTask[2]), Priority.valueOf(splitTask[5]));
    }

    private static Deadline getDeadlineTaskFromDatabase(int length, String[] splitTask)
            throws MalformedUserInputException {
        // then it is a deadline
        if (length != 5) {
            throw new MalformedUserInputException("Your database is corrupted.");
        }
        return new Deadline(splitTask[1], splitTask[3], Boolean.valueOf(splitTask[2]), Priority.valueOf(splitTask[4]));
    }

    private static Todo getTodoTaskFromDatabase(int length, String[] splitTask) throws MalformedUserInputException {
        // then it is a task
        if (length != 4) {
            throw new MalformedUserInputException("Your database is corrupted.");
        }
        return new Todo(splitTask[1], Boolean.valueOf(splitTask[2]), Priority.valueOf(splitTask[3]));
    }


    /**
     * Sets the completion status of a task at the specified index, and updates the HDD file.
     *
     * @param taskIndex the index of the task.
     * @param status    the status of the task (true indicates completed, while false indicates incomplete).
     * @throws MalformedUserInputException if the task index is out of bounds or the task has not been created yet.
     * @throws IndexOutOfBoundsException   if the task index is out of bounds.
     */
    public void setTaskStatus(int taskIndex, boolean status) throws MalformedUserInputException {
        if (taskIndex < 0 || taskIndex > this.maxTask) {
            throw new IndexOutOfBoundsException();
        } else if (taskIndex >= taskCount) {
            // It is a valid index, but there is no task there yet.
            throw new MalformedUserInputException("\t The task has not been created yet.");
        }
        this.tasksList.get(taskIndex).setDone(status);
        // We rebuild the dataStorage again
        rebuildStorage();

    }


    /**
     * Retrieves the number of tasks stored in the data storage.
     *
     * @return the number of tasks.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Rebuilds the storage file by iterating through all the tasks in the task list
     * and adding them to the storage file again.
     * <p>
     * Uses the addTaskToTextFile method to add a task to the storage file.
     */
    public void rebuildStorage() {
        deleteAllTasksFromFile();

        for (int i = 0; i < this.taskCount; i++) {
            // i != 0 means that refresh the whole file.
            addTaskToTextFile(tasksList.get(i).toStorageString(), i != 0);
        }
    }


    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param indexToDelete the index of the task to be deleted.
     * @throws MalformedUserInputException if the task index is out of bounds or the task has not been created yet.
     * @throws IndexOutOfBoundsException   if the task index is out of bounds.
     */
    public void deleteTask(int indexToDelete) throws MalformedUserInputException {
        if (indexToDelete < 0 || indexToDelete > this.maxTask) {
            throw new IndexOutOfBoundsException();
        } else if (indexToDelete >= this.taskCount) {
            // It is a valid index, but there is no task there yet.
            throw new MalformedUserInputException("There are no task stored at the specified location.");
        }
        // If we reach here, it means that there is no problem.
        this.tasksList.remove(indexToDelete);
        this.taskCount--;

        // To check if there is an improper reduction in internal task count.
        assert (this.taskCount >= 0);

        // We rebuild the dataStorage again.
        rebuildStorage();
    }


    /**
     * Searches for tasks whose description contains the given search term.
     *
     * @param searchTerm the keyword used to search for tasks. If no terms are specified,
     *                   then everything is returned.
     * @return a string representation of the tasks whose description contains the search term.
     */
    public String searchResultsInList(String searchTerm) {

        ArrayList<String> arrayList = new ArrayList<>();

        if (this.taskCount == 0) {
            return ListCommand.MESSAGE_NO_EVENTS;
        } else {

            // Create the list that contains all the items that matches the search term.
            this.tasksList.stream()
                    // Ignore the case during the search by converting searchTerm
                    // and item stored to lowercase.
                    .filter(task -> task.getDescription().toLowerCase().contains(searchTerm.toLowerCase()))
                    .forEach(task -> {
                        // Gets the index of the particular item.
                        int indexNumber = this.tasksList.indexOf(task) + 1;
                        arrayList.add("\t " + indexNumber + ". " + task.toString());
                    }); // Append the description of each filtered task to the StringBuilder


            // Return no elements found message, otherwise return all rows that matches the search term.
            if (arrayList.size() == 0) {
                return FindCommand.MESSAGE_EMPTY_SEARCH_RESULTS;
            } else {
                return convertArrayListToLineSeparatedString(arrayList);
            }
        }


    }

    /**
     * Converts an ArrayList of Strings into a single String
     * with each item separated by a newline character,
     * except for the last line.
     *
     * @param arrayList the ArrayList of Strings to be converted.
     * @return a single String with each item separated by a newline character.
     */
    public String convertArrayListToLineSeparatedString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();

        for (int i = 0; i < size; i++) { // Use a traditional for loop

            sb.append(arrayList.get(i));

            if (i < size - 1) { // Check if it's not the last item
                sb.append("\n"); // Append new line except for the last item
            }
        }
        return sb.toString();
    }

}
