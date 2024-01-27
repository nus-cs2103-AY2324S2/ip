package util;

import java.io.*;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;

import exception.*;
import task.Task;

public class Store {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "src/logs/tasks.txt";
    private static final String DIR_PATH = "src/logs";

    public Store() throws IOException {
        initStore();
    }

    public static void resetStore() throws IOException {
        File f = new File(FILE_PATH);
        if (f.exists()) {
            f.delete();
        }
        f = new File(FILE_PATH);
        f.createNewFile();
    }

    // Reads from the file and adds the tasks to ArrayList.
    private static void initStore() throws IOException {
        File dir = new File(DIR_PATH);

        // Create the directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println(dir.getAbsolutePath());
        }

        File file = new File(FILE_PATH);

        // Create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }

        try (Scanner sc = new Scanner(file)) {
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
