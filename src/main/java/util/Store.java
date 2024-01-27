package util;

import java.io.*;
import java.lang.StringBuilder;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

import exception.*;
import task.Task;

public class Store {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "src/logs/tasks.txt";

    public Store() throws IOException {
        initStore();
    }

    public static void resetStore() throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Delete the file if it exists
        Files.deleteIfExists(filePath);

        // Create the parent directories if they do not exist
        Files.createDirectories(filePath.getParent());

        // Create the file
        Files.createFile(filePath);
    }

    // Reads from the file and adds the tasks to ArrayList.
    private static void initStore() throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Create the parent directories if they do not exist
        Files.createDirectories(filePath.getParent());

        // Create the file if it doesn't exist
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (Scanner sc = new Scanner(filePath)) {
            while (sc.hasNext()) {
                try {
                    tasks.add(new CSVUtil(sc.nextLine()).toTask());
                } catch (IllegalArgumentException e) {
                    PrintUtil.print(NarutoException.createFileCorruptedException().getMessage());
                    resetStore();
                    break;
                }
            }
        }
    }

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.format().toCSV());
        }
        fw.close();
    }

    public void add(Task t) throws IOException {
        this.tasks.add(t);
        writeToFile();
    }

    public Task delete(int i) throws IOException {
        Task t = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        writeToFile();
        return t;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task mark(int i) throws IOException {
        Task t = this.tasks.get(i - 1).mark();
        writeToFile();
        return t;
    }

    public Task unmark(int i) throws IOException {
        Task t = this.tasks.get(i - 1).unmark();
        writeToFile();
        return t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(i + 1).append(".").append(this.tasks.get(i).toString());
            if (i != this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
