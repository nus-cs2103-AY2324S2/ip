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

public class Storage {
    private static Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public void setupDirectoryAndFile() {
        try {
            Path directoryPath = filePath.getParent();
            boolean directoryExists = Files.exists(directoryPath);
            boolean fileExists = Files.exists(filePath);

            if (!directoryExists) {
                Files.createDirectory(directoryPath);
                System.out.println("Directory does not exist; hence, created");
            } else {
                System.out.println("Directory exists");
            }

            if (!fileExists) {
                Files.createFile(filePath);
                System.out.println("File does not exist; hence, created");
            } else {
                System.out.println("File exists");
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
        }
    }

    public void storeTasks(ArrayList<Task> tasks) throws IOException {
        ArrayList<String> tempTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            boolean isTodo = currentTask.getTaskType() == TaskType.TODO;
            boolean isDeadline = currentTask.getTaskType() == TaskType.DEADLINE;
            boolean isEvent = currentTask.getTaskType() == TaskType.EVENT;
            String done = currentTask.getStatus() ? "1" : "0";
            String temp = currentTask.getTaskType().getIcon() + " / " + done + " / " + currentTask.getTask();
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d/M/yy Hmm");
            if (isDeadline) {
                Deadline deadlineTask = (Deadline) currentTask;
                temp += " / " + deadlineTask.getDeadline().format(outputFormatter);
            } else if (isEvent) {
                Event eventTask = (Event) currentTask;
                temp += " / " + eventTask.getFrom().format(outputFormatter) + " / " + eventTask.getTo().format(outputFormatter);
            }
            tempTasks.add(temp);
        }
        Files.write(filePath, tempTasks);
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath.toString());
        Scanner sc = new Scanner(file);
        int numberOfTasks = 0;
        System.out.println("Loading saved tasks");
        while (sc.hasNextLine()) {
            numberOfTasks++;
            String[] splitMessage = sc.nextLine().split(" / ");
            boolean isTodo = splitMessage.length == 3;
            boolean isDeadline = splitMessage.length == 4;
            boolean isEvent = splitMessage.length == 5;
            boolean isDone = splitMessage[1].equals("1");
            Task temp = null;
            if (isTodo) {
                temp = new Todo(splitMessage[2], isDone);
            } else if (isDeadline) {
                temp = new Deadline(splitMessage[2], Parser.parseDateTime(splitMessage[3]), isDone);
            } else if (isEvent) {
                temp = new Event(splitMessage[2], Parser.parseDateTime(splitMessage[3]),
                        Parser.parseDateTime(splitMessage[4]), isDone);
            }
            tasks.add(temp);
        }
        if (numberOfTasks == 0) {
            System.out.println("No Tasks Saved");
        } else {
            System.out.println("Loaded saved tasks");
        }

        return tasks;
    }
}
