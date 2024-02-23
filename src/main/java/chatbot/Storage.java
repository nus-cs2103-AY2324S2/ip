package chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;



/**
 * Represents the storage of the chatbot.
 */
public class Storage {
    private File file;
    private String filePath;
    /**
     * Constructs the storage.
     * @param filepath The filepath of the file.
     */
    public Storage(String filepath) {
        try {
            filePath = filepath;
            file = new File(filepath);
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
                processTask(input, 1, taskList);
            } else if (input.startsWith("D")) {
                processDeadline(input, taskList);
            } else if (input.startsWith("E")) {
                processEvent(input, taskList);
            }
        }
        return taskList;
    }

    private void processTask(String input, int toggle, TaskList taskList) {
        String[] splitResult = input.split("\\|", 3);
        String description = splitResult[2].trim();
        ToDo todo = new ToDo(description);
        if (toggle == 1) {
            todo.toggleDone();
        }
        taskList.addTask(todo);
    }

    private void processDeadline(String input, TaskList taskList) {
        String[] splitResult = input.split("\\|", 4);
        int toggle = Integer.parseInt(splitResult[1].trim());
        String description = splitResult[2].trim();
        String[] dateParts = splitResult[3].split("\\|");
        LocalDateTime by = parseTime(dateParts);
        Deadline deadline = new Deadline(description, by);
        if (toggle == 1) {
            deadline.toggleDone();
        }
        taskList.addTask(deadline);
    }

    private void processEvent(String input, TaskList taskList) {
        String[] splitResult = input.split("\\|", 4);
        int toggle = Integer.parseInt(splitResult[1].trim());
        String description = splitResult[2].trim();
        String[] formattedBy = splitResult[3].split("\\|");
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
    /**
     * Updates the data in the file.
     * @param taskList The list of tasks to be updated.
     * @return A message indicating the success of the operation.
     */
    public String updateData(TaskList taskList) throws AlfredException {
        StringBuilder stringBuilder = new StringBuilder();
        taskList.forEach(task -> addToData(task, stringBuilder));
        try (FileWriter fw = new FileWriter(filePath)) {
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
        switch (taskType) {
        case "chatbot.ToDo":
            appendToDoData((ToDo) task, stringBuilder);
            break;
        case "chatbot.Deadline":
            appendDeadlineData((Deadline) task, stringBuilder);
            break;
        case "chatbot.Event":
            appendEventData((Event) task, stringBuilder);
            break;
        default:
            break;
        }
    }

    private void appendToDoData(ToDo todo, StringBuilder stringBuilder) {
        stringBuilder.append("T | ").append(todo.isDone() ? 1 : 0).append(" | ").append(todo.getDescription()).append("\n");
    }

    private void appendDeadlineData(Deadline deadline, StringBuilder stringBuilder) {
        LocalDateTime by = deadline.getBy();
        stringBuilder.append("D | ").append(deadline.isDone() ? 1 : 0).append(" | ")
                .append(deadline.getDescription()).append(" | ")
                .append(by.getDayOfMonth()).append(" | ")
                .append(by.getMonthValue()).append(" | ")
                .append(by.getYear()).append(" | ")
                .append(String.format("%02d%02d", by.getHour(), by.getMinute())).append("\n");
    }

    private void appendEventData(Event event, StringBuilder stringBuilder) {
        LocalDateTime startTime = event.getStartTime();
        LocalDateTime endTime = event.getEndTime();
        stringBuilder.append("E | ").append(event.isDone() ? 1 : 0).append(" | ")
                .append(event.getDescription()).append(" | ")
                .append(startTime.getDayOfMonth()).append(" | ")
                .append(startTime.getMonthValue()).append(" | ")
                .append(startTime.getYear()).append(" | ")
                .append(String.format("%02d%02d", startTime.getHour(), startTime.getMinute())).append(" | ")
                .append(endTime.getDayOfMonth()).append(" | ")
                .append(endTime.getMonthValue()).append(" | ")
                .append(endTime.getYear()).append(" | ")
                .append(String.format("%02d%02d", endTime.getHour(), endTime.getMinute())).append("\n");
    }

}
