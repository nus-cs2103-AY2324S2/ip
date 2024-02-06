import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    private static final String FILE_NAME = "ByteTalker.txt";
    private static final String DIRECTORY_PATH = "./data";
    private static Path filePath = Paths.get(DIRECTORY_PATH, FILE_NAME);

    public static void setupDirectoryAndFile() throws IOException {
        Path directoryPath = filePath.getParent();
        boolean directoryExists = Files.exists(directoryPath);
        boolean fileExists = Files.exists(filePath);

        if (!directoryExists) {
            Files.createDirectory(directoryPath);
        }

        if (!fileExists) {
            Files.createFile(filePath);
        }
    }

    public static void storeTasks(ArrayList<Task> tasks) {
        try {
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath.toString());
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] splitMessage = sc.nextLine().split(" / ");
                boolean isTodo = splitMessage.length == 3;
                boolean isDeadline = splitMessage.length == 4;
                boolean isEvent = splitMessage.length == 5;
                boolean isDone = splitMessage[1].equals("1");
                Task temp = null;
                if (isTodo) {
                    temp = new Todo(splitMessage[2], isDone);
                } else if (isDeadline) {
                    temp = new Deadline(splitMessage[2], ByteTalker.convertDateTime(splitMessage[3]), isDone);
                } else if (isEvent) {
                    temp = new Event(splitMessage[2], ByteTalker.convertDateTime(splitMessage[3]), ByteTalker.convertDateTime(splitMessage[4]), isDone);
                }
                tasks.add(temp);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
    }
}
