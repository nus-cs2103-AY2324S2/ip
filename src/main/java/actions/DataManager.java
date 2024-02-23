package actions;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {
    private static String path;

    public DataManager(String path) {
        this.path = path;
    }

    public static LocalDateTime parseFromFileString(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
        return LocalDateTime.parse(time,formatter);
    }


    public static void saveTasks(ArrayList<Task> tasks) {
        try{
            File dataFile = new File(path);
            dataFile.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(dataFile, false);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to save tasks.");
        }
    }


    public ArrayList<Task> retrieveTasks () {
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            File file = new File(path);
            if(file.exists()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String data =scanner.nextLine();
                    Task task = getTask(data);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to load tasks.");
        }
        return tasks;
    }


    public static boolean getStatus(String num) {
        return num.equals("1");
    }
    public static Task getTask(String message) {
        String parts[] = message.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0].trim();
        boolean status = getStatus(parts[1].trim());
        String taskName = parts[2].trim();
        Task task = null;

        if(type.equals("T")) {
            task = new Todo(taskName, status);
        }else if(type.equals("D")) {
            String time = parts[3].trim();
            task = new Deadline(taskName, status, parseFromFileString(time));
        }else {
            String time = parts[3].trim();
            String timeParts[] = time.split(" - ");
            String from = timeParts[0];
            String to = timeParts[1];
            task = new Event(taskName, status, parseFromFileString(from), parseFromFileString(to) );
        }
        return task;
    }
}