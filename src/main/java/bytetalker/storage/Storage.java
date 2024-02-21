package bytetalker.storage;

import bytetalker.parser.Parser;
import bytetalker.task.*;

import java.nio.file.Path;
import java.nio.file.Files;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents utility class for storing and loading information from a file on the hard disk.
 */
public class Storage {
    private static Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Sets up directory and file to store list in hard disk. If there is no directory or file, it will create the
     * msising one.
     */
    public void setupDirectoryAndFile() {
        assert filePath != null;

        try {
            Path directoryPath = filePath.getParent();
            boolean hasDirectory = Files.exists(directoryPath);
            boolean hasFile = Files.exists(filePath);

            createDirectory(hasDirectory, directoryPath);
            createFile(hasFile);
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
        }
    }

    private void createDirectory(boolean hasDirectory, Path directoryPath) throws IOException {
        if (!hasDirectory) {
            Files.createDirectory(directoryPath);
            System.out.println("Directory does not exist; hence, created");
        } else {
            System.out.println("Directory exists");
        }
    }

    private void createFile(boolean hasFile) throws IOException {
        if (!hasFile) {
            Files.createFile(filePath);
            System.out.println("File does not exist; hence, created");
        } else {
            System.out.println("File exists");
        }
    }

    /**
     * Stores the list of tasks into the hard disk.
     *
     * @param tasks List of tasks to be stored.
     * @throws IOException If there is no file or directory.
     */
    public void storeTasks(ArrayList<Task> tasks) throws IOException {
        assert filePath != null;
        assert tasks != null;

        ArrayList<String> tempTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tempTasks.add(convertIndividualTaskToString(tasks.get(i)));
        }
        Files.write(filePath, tempTasks);
    }

    private String convertIndividualTaskToString(Task task) {
        Task currentTask = task;
        boolean isTodo = currentTask.getTaskType() == TaskType.TODO;
        boolean isDeadline = currentTask.getTaskType() == TaskType.DEADLINE;
        boolean isEvent = currentTask.getTaskType() == TaskType.EVENT;
        String done = currentTask.getStatus() ? "1" : "0";
        String temp = currentTask.getTaskType().getIcon() + " / " + done + " / " + currentTask.getTask();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy Hmm");
        if (isDeadline) {
            Deadline deadlineTask = (Deadline) currentTask;
            temp += " / " + deadlineTask.getDeadline().format(outputFormatter);
        } else if (isEvent) {
            Event eventTask = (Event) currentTask;
            temp += " / " + eventTask.getFrom().format(outputFormatter) + " / " + eventTask.getTo().format(outputFormatter);
        }
        return temp;
    }

    /**
     * Loads the list of tasks from the hard disk.
     *
     * @return list of tasks stored in the hard disk.
     * @throws FileNotFoundException If there is no file or directory.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        assert filePath != null;

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath.toString());
        assert file != null;
        Scanner sc = new Scanner(file);
        int numberOfTasks = 0;
        System.out.println("Loading saved tasks");
        while (sc.hasNextLine()) {
            numberOfTasks++;
            tasks.add(changeStringToTask(sc));
        }
        if (numberOfTasks == 0) {
            System.out.println("No Tasks Saved");
        } else {
            System.out.println("Loaded saved tasks");
        }

        return tasks;
    }

    private Task changeStringToTask(Scanner sc) {
        String[] splitMessage = sc.nextLine().split(" / ");
        boolean isTodo = splitMessage.length == 3;
        boolean isDeadline = splitMessage.length == 4;
        boolean isEvent = splitMessage.length == 5;
        boolean isDone = splitMessage[1].equals("1");
        if (isTodo) {
            return new Todo(splitMessage[2], isDone);
        } else if (isDeadline) {
            return new Deadline(splitMessage[2], Parser.parseDateTime(splitMessage[3]), isDone);
        }
        return new Event(splitMessage[2], Parser.parseDateTime(splitMessage[3]),
                    Parser.parseDateTime(splitMessage[4]), isDone);

    }
}
