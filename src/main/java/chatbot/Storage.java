package chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;



/**
 * Represents the storage of the chatbot.
 */
public class Storage {
    private File file;
    /**
     * Constructs the storage.
     * @param filepath The filepath of the file.
     */
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
        assert endTimeParts.length == 4 : "Invalid endTimeParts array length";
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
                assert splitResult.length == 3 : "Invalid splitResult array length for ToDo";
                int toggle = Integer.parseInt(splitResult[1].trim());
                String description = splitResult[2].trim();
                ToDo todo = new ToDo(description);
                if (toggle == 1) {
                    todo.toggleDone();
                }
                taskList.addTask(todo);
            } else if (input.startsWith("D")) {
                String[] splitResult = input.split("\\|", 4);
                assert splitResult.length == 4 : "Invalid splitResult array length for Deadline";
                int toggle = Integer.parseInt(splitResult[1].trim());
                String description = splitResult[2].trim();
                String[] dateParts = splitResult[3].split("\\|");
                assert dateParts.length == 3 : "Invalid dateParts array length for Deadline";
                LocalDateTime by = parseTime(dateParts);
                Deadline deadline = new Deadline(description, by);
                if (toggle == 1) {
                    deadline.toggleDone();
                }
                taskList.addTask(deadline);
            } else if (input.startsWith("E")) {
                String[] splitResult = input.split("\\|", 4);
                assert splitResult.length == 4 : "Invalid splitResult array length for Event";
                int toggle = Integer.parseInt(splitResult[1].trim());
                String description = splitResult[2].trim();
                String[] formattedBy = splitResult[3].split("\\|");
                assert formattedBy.length % 2 == 0 : "Invalid formattedBy array length for Event";
                LocalDateTime startTime = parseTime(Arrays.copyOfRange(formattedBy, 0, formattedBy.length / 2));
                LocalDateTime endTime = parseTime(Arrays.copyOfRange(formattedBy, formattedBy.length / 2, formattedBy.length));
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
            stringBuilder.append("T | ").append(task.isDone() ? 1 : 0).append(" | ")
                    .append(task.getDescription()).append("\n");
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
            stringBuilder.append("D | ").append(task.isDone() ? 1 : 0).append(" | ")
                    .append(task.getDescription()).append(" | ")
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
            stringBuilder.append("E | ").append(task.isDone() ? 1 : 0).append(" | ")
                    .append(task.getDescription()).append(" | ")
                    .append(startDay).append(" | ").append(startMonth).append(" | ")
                    .append(startYear).append(" | ")
                    .append(String.format("%02d%02d", startHour, startMinute)).append(" | ")
                    .append(endDay).append(" | ").append(endMonth).append(" | ").append(endYear).append(" | ")
                    .append(String.format("%02d%02d", endHour, endMinute)).append("\n");
            break;
        default:
            break;
        }
    }
}
