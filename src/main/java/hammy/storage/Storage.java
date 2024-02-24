package hammy.storage;//package duke;

import hammy.task.DeadlineTask;
import hammy.task.EventTask;
import hammy.task.Task;
import hammy.task.TodoTask;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Storage class manages the loading and saving of tasks to a file.
 */
public class Storage {
    String filePath;
    ArrayList<Task> tasks;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath the file path where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Validates if the start date is before the end date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return true if the start date is before the end date, false otherwise
     */
    private static boolean checkValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File creation failed.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return the list of tasks loaded from the file
     */
    public ArrayList<Task> loadTasksFromFile() {
        File file = new File(filePath);
        createFileIfNotExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 7) {
                    System.out.println("Error found: Bad input");
                    System.out.println(line);
                }

                char taskType = line.charAt(1);
                char isDoneX = line.charAt(4);
                if (taskType == 'T') {
                    String todoDescription = line.substring(7);
                    if (isDoneX == 'X') {
                        Task newTodo = new TodoTask(todoDescription, true);
                        this.tasks.add(newTodo);
                    } else if (isDoneX == ' ') {
                        Task newTodo = new TodoTask(todoDescription);
                        this.tasks.add(newTodo);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized todo's Done.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else if (taskType == 'D') {
                    if (!line.contains(" (by: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Deadline task without a deadline.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    String deadlineDescription = line.substring(7, line.indexOf(" (by: "));
                    Pattern pattern = Pattern.compile("\\(by: (.*?)\\)");
                    Matcher matcher = pattern.matcher(line);
                    String deadlineString = "";
                    if (matcher.find()) {
                        deadlineString = matcher.group(1);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate deadline = LocalDate.parse(deadlineString, formatter);
                    if (isDoneX == 'X') {
                        Task newDeadline = new DeadlineTask(deadlineDescription, deadline, true);
                        this.tasks.add(newDeadline);
                    } else if (isDoneX == ' ') {
                        Task newDeadline = new DeadlineTask(deadlineDescription, deadline);
                        this.tasks.add(newDeadline);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized deadline's Done.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else if (taskType == 'E') {
                    if (!line.contains(" (from: ") && !line.contains(" to: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Event task without a starting time and an ending time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (!line.contains(" (from: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Event task without a starting time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (!line.contains(" to: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Deadline task without an ending time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    String eventDescription = line.substring(7, line.indexOf(" (from: "));
                    Pattern pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
                    Matcher matcher = pattern.matcher(line);
                    String startTimeString = "";
                    String endTimeString = "";
                    if (matcher.find()) {
                        startTimeString = matcher.group(1);
                        endTimeString = matcher.group(2);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate startTime = LocalDate.parse(startTimeString, formatter);
                    LocalDate endTime = LocalDate.parse(endTimeString, formatter);
                    if (!checkValidDates(startTime, endTime)) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Start time should be earlier than end time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (isDoneX == 'X') {
                        Task newEvent = new EventTask(eventDescription, startTime, endTime, true);
                        this.tasks.add(newEvent);
                    } else if (isDoneX == ' ') {
                        Task newEvent = new EventTask(eventDescription, startTime, endTime);
                        this.tasks.add(newEvent);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized event's Done.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Error found: Unrecognized task type (task type should be one of T, D, E).");
                    System.out.println("--> " + line);
                    System.out.println("Removed from list");
                    System.out.println("____________________________________________________________");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param taskList the list of tasks to be saved
     * @throws IOException if an I/O error occurs while saving the tasks
     */
    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        createFileIfNotExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
