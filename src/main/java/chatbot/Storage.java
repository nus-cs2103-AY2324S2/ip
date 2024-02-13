package chatbot;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;

/**
 * Represents the storage of the chatbot.
 */
public class Storage {
    private File file;
    public Storage(String filepath) {
        try {
            this.file = new File(filepath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Sorry Master Bruce. I cannot create a new file.");
        }
    }
    private LocalDateTime parseTime(String[] endTimeParts) {
        int endDay = Integer.parseInt(endTimeParts[0].trim());
        int endMonth = Integer.parseInt(endTimeParts[1].trim());
        int endYear = Integer.parseInt(endTimeParts[2].trim());
        String endTimeStr = endTimeParts[3].trim();
        int endHour = Integer.parseInt(endTimeStr.substring(0, 2));
        int endMinute = Integer.parseInt(endTimeStr.substring(2));
        return LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute);
    }
    /**
     * Processes the data in the file.
     * @param taskList The list of tasks to be updated.
     * @return The updated list of tasks.
     */
    public TaskList processData(TaskList taskList) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.startsWith("T")) {
                String[] splitResult = input.split("\\|", 3);
                int toggle = Integer.parseInt(splitResult[1].trim());
                String description = splitResult[2].trim();
                ToDo todo = new ToDo(description);
                if (toggle == 1) {
                    todo.toggleDone();
                }
                taskList.addTask(todo);
            } else if (input.startsWith("D")) {
                String[] splitResult = input.split("\\|", 4);
                int toggle = Integer.parseInt(splitResult[1].trim());
                String description = splitResult[2].trim();
                // Parsing LocalDateTime directly from splitResult
                String[] dateParts = splitResult[3].split("\\|");
                LocalDateTime by = parseTime(dateParts);
                // Create Deadline object and add it to the list
                Deadline deadline = new Deadline(description, by);
                if (toggle == 1) {
                    deadline.toggleDone();
                }
                taskList.addTask(deadline);
            } else if (input.startsWith("E")) {
                String[] splitResult = input.split("\\|", 4);
                int toggle = Integer.parseInt(splitResult[1].trim());
                String description = splitResult[2].trim();
                String[] formattedBy = splitResult[3].split("\\|");
                // Split the formattedBy array into two parts: start and end times
                int halfLength = formattedBy.length / 2;
                String[] startTimeParts = Arrays.copyOfRange(formattedBy, 0, halfLength);
                String[] endTimeParts = Arrays.copyOfRange(formattedBy, halfLength, formattedBy.length);
                LocalDateTime startTime = parseTime(startTimeParts);
                LocalDateTime endTime = parseTime(endTimeParts);
                Event event = new Event(description, startTime, endTime);
                if (toggle == 1) {
                    event.toggleDone();
                }
                taskList.addTask(event);
            }
        }
        return taskList;
    }
    /**
     * Updates the data in the file.
     * @param taskList The list of tasks to be updated.
     * @return A message indicating the success of the operation.
     */
    public String updateData(TaskList taskList) throws AlfredException {
        StringBuilder stringBuilder = new StringBuilder();
        taskList.forEach(task -> addToData(task, stringBuilder));
        try (FileWriter fw = new FileWriter("data/alfred.txt")) {
            fw.write(stringBuilder.toString());
            return "Data has been updated successfully.";
        } catch (IOException e) {
            throw new AlfredException("Error while updating data");
        }
    }


    /**
     * Adds a task to the string builder.
     * @param task The task to be added.
     * @param stringBuilder The StringBuilder to append the task data to.
     */
    public <R extends Task> void addToData(R task, StringBuilder stringBuilder) {
        String taskType = task.getClass().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        switch (taskType) {
        case "chatbot.ToDo":
            stringBuilder.append("T | ").append(task.isDone() ? 1 : 0).append(" | ").append(task.getDescription()).append("\n");
            break;
        case "chatbot.Deadline":
            Deadline deadline = (Deadline) task;
            LocalDateTime by = deadline.getBy();
            // Extract date components
            int day = by.getDayOfMonth();
            int month = by.getMonthValue(); // make this double-digit?
            int year = by.getYear();
            // Extract time components
            int hour = by.getHour();
            int minute = by.getMinute();
            // Append the components to the StringBuilder
            stringBuilder.append("D | ").append(task.isDone() ? 1 : 0).append(" | ").append(task.getDescription()).append(" | ")
                    .append(day).append(" | ").append(month).append(" | ").append(year).append(" | ")
                    .append(String.format("%02d%02d", hour, minute)).append("\n");
            break;
        case "chatbot.Event":
            Event event = (Event) task;
            LocalDateTime startTime = event.getStartTime();
            LocalDateTime endTime = event.getEndTime();

            // Extract start time components
            int startDay = startTime.getDayOfMonth();
            int startMonth = startTime.getMonthValue();
            int startYear = startTime.getYear();
            int startHour = startTime.getHour();
            int startMinute = startTime.getMinute();

            // Extract end time components
            int endDay = endTime.getDayOfMonth();
            int endMonth = endTime.getMonthValue();
            int endYear = endTime.getYear();
            int endHour = endTime.getHour();
            int endMinute = endTime.getMinute();

            // Append the components to the StringBuilder
            stringBuilder.append("E | ").append(task.isDone() ? 1 : 0).append(" | ").append(task.getDescription()).append(" | ")
                    .append(startDay).append(" | ").append(startMonth).append(" | ").append(startYear).append(" | ")
                    .append(String.format("%02d%02d", startHour, startMinute)).append(" | ")
                    .append(endDay).append(" | ").append(endMonth).append(" | ").append(endYear).append(" | ")
                    .append(String.format("%02d%02d", endHour, endMinute)).append("\n");
            break;
        }
    }
}
