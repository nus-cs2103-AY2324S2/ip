package seedu.chatteroo.storage;

import seedu.chatteroo.tasks.Task;
import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.tasks.ToDo;
import seedu.chatteroo.tasks.Deadline;
import seedu.chatteroo.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the storage of tasks in a file.
 */
public class Storage {
    private static String DIRECTORY = "data";
    private static String FILE_NAME = "chatteroo.txt";
    private static File listFile = new File(Paths.get(DIRECTORY, FILE_NAME).toString());

    public Storage() throws IOException {
        Path directoryPath = Paths.get(DIRECTORY);
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }
        if (!listFile.exists()) {
            try {
                listFile.createNewFile();
            } catch (IOException e) {
                System.out.println("ChatterOOHNOO! Chatteroo can't create a new file!");
            }
        }
    }

    /**
     * Loads the tasks from the file.
     * @return The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> listStore = new ArrayList<>();
        try {
            if (listFile.exists() && listFile.length() > 0) {
                Scanner sc = new Scanner(listFile);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    String[] inputArr = input.split(" \\| ");
                    String taskType = inputArr[0];
                    String taskStatus = inputArr[1];
                    String taskDescription = inputArr[2];
                    Task newTask = null;
                    if (taskType.equals("T")) {
                        newTask = new ToDo(taskDescription);
                    } else if (taskType.equals("D")) {
                        String by = inputArr[3];
                        newTask = new Deadline(taskDescription, by);
                    } else if (taskType.equals("E")) {
                        String from = inputArr[3];
                        String to = inputArr[4];
                        newTask = new Event(taskDescription, from, to);
                    }
                    if (taskStatus.equals("1")) {
                        newTask.markAsDone();
                    }
                    listStore.add(newTask);
                }
                sc.close();
            }
        } catch (IOException e) {
            System.out.println("ChatterOOHNOO! Chatteroo can't create a new file!");
        }
        return listStore;
    }

    /**
     * Saves the tasks to the file.
     * @param listStore The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(TaskList listStore) throws IOException{
        FileWriter fw = new FileWriter(Paths.get(DIRECTORY, FILE_NAME).toString());
        for (int i = 1; i <= listStore.getTaskListSize(); i++) {
            Task currTask = listStore.getTask(i);
            String taskType = currTask.getTaskType();
            String taskStatus = "";
            String taskDescription = currTask.getDescription();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            if (currTask.getIsDone()) {
                taskStatus = "1";
            } else {
                taskStatus = "0";
            }
            if (currTask instanceof ToDo) {
                fw.write("T | " + taskStatus + " | " + taskDescription + "\n");
            } else if (currTask instanceof Deadline) {
                taskType = "D";
                String taskBy = ((Deadline) currTask).getBy().format(dateFormat);
                fw.write(taskType + " | " + taskStatus + " | " + taskDescription + " | " + taskBy + "\n");
            } else if (currTask instanceof Event) {
                taskType = "E";
                String taskFrom = ((Event) currTask).getFrom().format(dateFormat);
                String taskTo = ((Event) currTask).getTo().format(dateFormat);
                fw.write(taskType + " | " + taskStatus + " | " + taskDescription + " | "
                        + taskFrom + " | " + taskTo + "\n");
            }
        }
        fw.close();
    }

}
