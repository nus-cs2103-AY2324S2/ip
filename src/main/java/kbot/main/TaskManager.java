package kbot.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import kbot.tasks.Task;

/**
 * Manages all communications between the bot simulation and the database of
 * Tasks. Inspired by ChatGPT, but I simply just created this class and
 * transferred all my original code over from KBot.java
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class TaskManager {
    /** ArrayList<Task> to store all the Tasks the user has created. */
    private static ArrayList<Task> TASKS = new ArrayList<>();

    public static ArrayList<Task> getTasks() {
        return TASKS;
    }

    /**
     * Checks if there is local save and load it into system if there exist.
     */
    public static void loadLocalSavedTasks() {
        try {
            TASKS = TaskFileManager.loadTasksFromFile();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while accessing the file: " + e.getMessage());
        }
    }

    /**
     * Saves the data in the Tasks ArrayList onto a local file.
     */
    public static void saveToLocalStorage() {
        try {
            TaskFileManager.saveTasksToFile(TASKS);
        } catch (IOException e) {
            System.out.println("Error while accessing the file: " + e.getMessage());
        }
    }
}
