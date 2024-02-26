package chimp.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chimp.task.TaskStatus;
import chimp.task.Todo;
import chimp.task.Deadline;
import chimp.task.Event;

public class Storage {
    /**
     * Checks if the given command is a save command.
     *
     * @param command the command to be checked
     * @return true if the command is a save command, false otherwise
     */
    public static boolean isSaveCommand(String command) {
        HashSet<String> saveCommands = new HashSet<>(
                List.of(
                        "mark",
                        "unmark",
                        "todo",
                        "event",
                        "deadline",
                        "delete"));
        return saveCommands.contains(command);
    }

    /**
     * Saves the output to a file.
     *
     * @param output the output to be saved
     */
    public static void saveOutputToFile(TaskList output) {
        Path filePath = Path.of("./data/chimp.txt");

        // Create file if it does not exist
        try {
            Path directoryPath = filePath.getParent();
            if (directoryPath != null) {
                Files.createDirectories(directoryPath);
            }
            Files.createFile(filePath);
        } catch (FileAlreadyExistsException e) {
            // Ignore this error
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write to file
        try {
            Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(filePath, output.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a file if it does not already exist.
     * The file path is "./data/chimp.txt".
     * If the file already exists, the method does nothing.
     * If an error occurs during file creation, the error is printed to the standard error stream.
     */
    public static void createFileIfNotExist() {
        Path filePath = Path.of("./data/chimp.txt");

        try {
            Path directoryPath = filePath.getParent();
            if (directoryPath != null) {
                Files.createDirectories(directoryPath);
            }
            Files.createFile(filePath);
        } catch (FileAlreadyExistsException e) {
            // Ignore this error
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Reads the output from the file and creates a task list.
     *
     * @return The task list containing the tasks read from the file.
     */
    public static TaskList readOutputFromFile() {
        TaskList taskList = new TaskList();
        Path filePath = Path.of("./data/chimp.txt");

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseLineToTask(line, taskList);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return taskList;
    }

    private static void parseLineToTask(String line, TaskList taskList) {
        // Acknowledgement: The code below was written and adapted
        // with the help of GPT4
        Pattern pattern = Pattern.compile("\\[(T|D|E)] \\[([ X])] (.*?)(?: \\(by: (.*?)\\)| \\(from: (.*?) to: (.*?)\\))?$");

        Matcher matcher = pattern.matcher(line);

        
        if (matcher.find()) {
            String type = matcher.group(1);
            boolean isMarked = matcher.group(2).trim().equals("X");
            String description = matcher.group(3).trim();
            TaskStatus status = isMarked ? TaskStatus.MARKED : TaskStatus.UNMARKED;
        
            switch (type) {
                case "T":
                    taskList.add(new Todo(description, status));
                    break;
                case "D":
                    if (matcher.group(4) != null && !matcher.group(4).isEmpty()) {
                        LocalDate byDate = LocalDate.parse(matcher.group(4), DateTimeFormatter.ofPattern("MMM d yyyy"));
                        taskList.add(new Deadline(description, status, byDate));
                    }
                    break;
                case "E":
                    if (matcher.group(5) != null && !matcher.group(5).isEmpty() && matcher.group(6) != null && !matcher.group(6).isEmpty()) {
                        LocalDate fromDate = LocalDate.parse(matcher.group(5), DateTimeFormatter.ofPattern("MMM d yyyy"));
                        LocalDate toDate = LocalDate.parse(matcher.group(6), DateTimeFormatter.ofPattern("MMM d yyyy"));
                        taskList.add(new Event(description, status, fromDate, toDate));
                    }
                    break;
            }
        }
    }
}
