package chatbot;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
public class Storage {
    private File file;
    public Storage(String filepath) {
        try {
            this.file = new File(filepath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (java.io.IOException e) {
            System.out.println("Sorry Master Bruce. I cannot create the file.");
        }
    }
    public File getFile() {
        return this.file;
    }
    public String addData(Task task) {
        try {
            FileWriter fw = new FileWriter("data/alfred.txt", true);
            String taskType = task.getClass().getName();
            DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            switch (taskType) {
                case "chatbot.ToDo":
                    fw.write("T | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + "\n");
                    break;
                case "chatbot.Deadline":
                    Deadline deadline = (Deadline) task;
                    java.time.LocalDateTime by = deadline.getBy();
                    // Extract date components
                    int day = by.getDayOfMonth();
                    int month = by.getMonthValue(); // make this double-digit?
                    int year = by.getYear();
                    // Extract time components
                    int hour = by.getHour();
                    int minute = by.getMinute();
                    // Write the components to the file
                    fw.write("D | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + " | " +
                            day + " | " + month + " | " + year + " | " +
                            String.format("%02d%02d", hour, minute) + "\n");
                    break;
                case "chatbot.Event":
                    Event event = (Event) task;
                    java.time.LocalDateTime startTime = event.getStartTime();
                    java.time.LocalDateTime endTime = event.getEndTime();

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

                    // Write the components to the file
                    fw.write("E | " + (task.isDone() ? 1 : 0) + " | " + task.getDescription() + " | " +
                            startDay + " | " + startMonth + " | " + startYear + " | " +
                            String.format("%02d%02d", startHour, startMinute) + " | " +
                            endDay + " | " + endMonth + " | " + endYear + " | " +
                            String.format("%02d%02d", endHour, endMinute) + "\n");
                    break;
            }
            fw.close();
        } catch (java.io.IOException e) {
            return ("Sorry Master Bruce. I cannot write to the data.");
        }
        return ("Data has been added successfully.");
    }


    public String updateData(ArrayList<Task> taskList) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("data/alfred.txt");
            for (Task task : taskList) {
                addData(task);
            }
            fw.close();
        } catch (java.io.IOException e) {
            return ("Sorry Master Bruce. I cannot write to the data.");
        }
        return ("Data has been updated successfully.");
    }
}
