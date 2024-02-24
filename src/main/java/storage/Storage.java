package storage;

import Exceptions.InvalidInputException;
import commands.Command;
import exceptions.LoadCacheException;
import parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import TaskList.TaskList;

/**
 * Represents a storage to store and load tasks from a file.
 * A <code>Storage</code> object corresponds to a storage with a file path
 * e.g., <code>"./cache.txt"</code>
 */
public class Storage {
    private String storageDirectoryPath = "./storage";
    private final String defaultFilePath = storageDirectoryPath + "/defaultCache";
    private String currentFilePath = defaultFilePath;
    private List<String> caches;
    public Storage() throws InvalidInputException {
        createDirectory(storageDirectoryPath);
        makeFile("defaultCache");
        this.caches = new ArrayList<>();
    }

    /**
     * Loads tasks from the file.
     * @param cachedTasks the task list to be loaded with tasks from the file
     * @return the task list loaded with tasks from the file
     * @throws IOException if an I/O error occurs
     */
    public TaskList loadCache(TaskList cachedTasks) throws IOException, LoadCacheException {
        File f = new File(this.currentFilePath); // create a File for the given file path
        // Create the file if it doesn't exist
        if (!f.exists()) {
            f.createNewFile();
            return cachedTasks; // Return the unmodified task list
        }
        cachedTasks.clear();
        try {
            Scanner s = new Scanner(f);
            Parser parser = new Parser();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] str = line.split(" ");
                switch (str[0]) {
                case "completed":
                    for (int i = 1; i < str.length; i++) {
                        cachedTasks.getTask(Integer.parseInt(str[i])).markCompleted();
                    }
                    break;
                default:
                    Command command = parser.parseCommand(line);
                    command.setData(cachedTasks, this);
                    command.execute();
                }
            }
        } catch (IOException | InvalidInputException e) {
            throw new LoadCacheException("An error occurred while loading cache: " + e.getMessage());
        }

        this.caches.clear();
        File dir = new File(this.storageDirectoryPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                System.out.println(child.getName());
                if (child.isFile()) {
                    String fileName = child.getName();
                    caches.add(fileName);
                }
            }
        }
        System.out.println(this.caches);
        return cachedTasks;
    }

    /**
     * Saves tasks to the file.
     * @param cachedTasks the task list to be saved to the file
     * @throws IOException if an I/O error occurs
     */
    public void saveToCache(TaskList cachedTasks) throws IOException {
        FileWriter fw = new FileWriter(this.currentFilePath);
        for (int i = 0; i < cachedTasks.size(); i++) {
            fw.write(cachedTasks.getTask(i).save()+ "\n");
        }
        fw.write("completed");
        for (int i = 0; i < cachedTasks.size(); i++) {
            if (cachedTasks.getTask(i).isCompleted()) {
                fw.write(" " + i);
            }
        }
        fw.close();
    }

    public boolean createDirectory(String directoryPath) throws InvalidInputException {
        if (directoryPath == null || directoryPath.isEmpty()) {
            throw new InvalidInputException("Directory name cannot be empty");
        }
        File directory = new File(directoryPath);
        if (directory.exists()) {
            return false;
        }
        boolean created = directory.mkdirs();
        if (!created) {
            throw new InvalidInputException("Failed to create directory: " + directoryPath);
        }
        return true;
    }

    public boolean makeFile(String filePath) throws InvalidInputException {
        if (filePath == null || filePath.isEmpty()) {
            throw new InvalidInputException("File name cannot be empty");
        }
        File file = new File(this.storageDirectoryPath+"/"+filePath);
        if (file.exists()) {
            return false;
        }
        try {
            boolean created = file.createNewFile();
            if (!created) {
                throw new InvalidInputException("Failed to create file: " + filePath);
            }
        } catch (IOException e) {
            throw new InvalidInputException("Failed to create file: " + filePath);
        }
        this.caches.add(filePath);
        return true;
    }

    public String listCaches() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your folders:\n");
        for (int i = 0; i < this.caches.size(); i++) {
            sb.append(i + 1).append(". ").append(this.caches.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void changeCache(String fileName, TaskList cachedTasks) throws InvalidInputException, IOException, LoadCacheException {
        System.out.println(fileName);
        for (String cache : this.caches) {
            if (cache.equals(fileName)) {
                this.currentFilePath = storageDirectoryPath + "/" + fileName;
                this.loadCache(cachedTasks);
                return;
            }
        }
        throw new InvalidInputException("File not found: " + fileName);
    }
}
