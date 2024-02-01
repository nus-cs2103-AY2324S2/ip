package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TaskStorage {
    public static final String FILE_NAME = "tasks.txt";

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Task task : tasks) {
                bufferedWriter.write(taskToFileString(task));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
        System.out.println("Saved " + tasks.size() + " tasks from file.");
    }

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tasks.add(taskFromString(line));
            }
        } catch (IOException e) {
            saveTasksToFile(tasks);
        }
        System.out.println("Loaded " + tasks.size() + " tasks from file.");
        return tasks;
    }

    public static String taskToFileString(Task task) {
        // Convert a task to a string format suitable for saving to the file
        if (task instanceof ToDo) {
            return "T|" + (task.isDone ? "1" : "0") + "|" + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return "D|" + (task.isDone ? "1" : "0") + "|" + task.description + "|" + deadlineTask.by;
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return "E|" + (task.isDone ? "1" : "0") + "|" + task.description + "|" + eventTask.start + "|"
                    + eventTask.end;
        } else {
            return ""; // Handle other task types if needed
        }
    }

    public static Task taskFromString(String line) {
        // Convert a string from the file into a Task object
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        if (type.equals("T")) {
            task = new ToDo(description);
        } else if (type.equals("D")) {
            String by = parts[3];
            task = new Deadline(description, by);
        } else if (type.equals("E")) {
            String start = parts[3];
            String end = parts[4];
            task = new Event(description, start, end);
        } else {
            task = null; // Handle other task types if needed
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}