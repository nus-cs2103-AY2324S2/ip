package dave;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import dave.tasks.Deadline;
import dave.tasks.Event;
import dave.tasks.Task;
import dave.tasks.Todo;

public class Storage {
    /** The filepath to store tasks. */
    private String storageFilepath;

    /** The type of tasks. */
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates new Storage object.
     * 
     * @param filepath Output file to operate on for storing and updating tasks in.
     */
    public Storage(String filepath) {
        storageFilepath = filepath;
    }

    /**
     * Loads the tasks stored, if any.
     * 
     * @return An ArrayList<Task> to be used as the task list.
     * @throws IOException If output file is not found or corrupted.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader(storageFilepath));
        String line = br.readLine();
        while (line != null) {
            String[] taskDescription = line.split(" \\| ");
            TaskType type = TaskType.valueOf(taskDescription[0].toUpperCase()); // Solution inspired by https://www.tutorialspoint.com/java/lang/enum_valueof.htm

            switch (type) {
            case TODO:
                assert type.equals(TaskType.TODO);
                taskList.add(new Todo(taskDescription[2]));
                break;

            case DEADLINE:
                assert type.equals(TaskType.DEADLINE);
                taskList.add(new Deadline(taskDescription[2], taskDescription[3]));
                break;

            case EVENT:
                assert type.equals(TaskType.EVENT);
                taskList.add(new Event(taskDescription[2], taskDescription[3], taskDescription[4]));
                break;

            default:
                throw new IOException();
            }

            if (isSavedTaskMarked(taskDescription[1])) {
                assert taskDescription[1].equals("COMPLETED");
                taskList.get(taskList.size() - 1).setDone(true);
            }

            line = br.readLine();
        }
        br.close();
        return taskList;
    }

    public boolean isSavedTaskMarked(String completedInfo) {
        return completedInfo.equals("COMPLETED");
    }

    /**
     * Saves the task to output file if user adds a task in the task list.
     * 
     * @throws IOException If output file is not found.
     */
    public void saveTask(Task newTask) throws IOException {
        File fileToWrite = new File(storageFilepath);
        if (!fileToWrite.exists()) {
            fileToWrite.getParentFile().mkdir();
            fileToWrite.createNewFile();
        }
        assert fileToWrite.exists();
        BufferedWriter writer = new BufferedWriter(new FileWriter(storageFilepath, true));
        writer.append(newTask.fileString());
        writer.newLine();
        writer.close();
    }

    /**
     * Rewrites the tasks to the output file when the task list changes.
     * Changes to task list include marking, unmarking and deletion of tasks.
     * 
     * @param taskList Current task list to read from.
     */
    public void rewriteOutput(TaskList taskList) {
        File fileToDelete = new File(storageFilepath);
        try {
            fileToDelete.delete();
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                saveTask(taskList.getTask(i));
            }
        } catch (IOException exc) {
            System.out.println(String.format("Dave had a problem updating the output file."));
        }
    }

}
