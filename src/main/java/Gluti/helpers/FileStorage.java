package Gluti.helpers;

import Gluti.utils.*;

import java.io.*;
import java.util.ArrayList;

public class FileStorage {
    protected static final File DATA_FOLDER = new File("./src/main/data");
    protected static final File DATA_FILE = new File("./src/main/data/Gluti.txt");
    protected ArrayList<Task> tasklist = new ArrayList<>();

    public FileStorage() throws IOException, GlutiException {
        checkifexist();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = reader.readLine();
            while (line != null) {
                tasklist.add(read(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task read(String line) {
        char tasktype = line.charAt(1);
        char completion = line.charAt(4);
        Task nextTask = null;
        String description;
        int seperator;

        switch (tasktype) {
            case 'T':
                description = line.substring(7);
                nextTask = new Todo(description);
                if (completion == 'X') {
                    nextTask.setDone();
                }
                break;

            case 'D':
                seperator = line.indexOf(" (by: ");
                description = line.substring(7, seperator);
                String by = line.substring(seperator + 6, line.length() - 1);
                nextTask = new Deadline(description, by);
                if (completion == 'X') {
                    nextTask.setDone();
                }
                break;

            case 'E':
                seperator = line.indexOf("(from: ");
                description = line.substring(7, seperator);
                String[] date = line.substring(seperator + 6, line.length() - 1).split("to:");
                for (String x : date) {
                    System.out.println(x);
                }
                nextTask = new Event(description, date[0],date[1]);
                if (completion == 'X') {
                    nextTask.setDone();
                }
                break;

            default:
                nextTask = null;
        }

        return nextTask;
    }
    private void checkifexist() throws GlutiException {
        if (!DATA_FILE.exists()) {
            if (!DATA_FOLDER.exists()) {
                DATA_FOLDER.mkdirs();
            }
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                throw new GlutiException("Gluti detects IO Error!");
            }
        }
    }

    public void saveList(ArrayList<Task> newTasks) throws GlutiException {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            StringBuilder tasks = new StringBuilder();
            for (Task newTask : newTasks) {
                tasks.append(newTask).append(System.lineSeparator());
            }
            fileWriter.write(tasks.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new GlutiException("Gluti detects IO Error!");
        }
    }

    public ArrayList<Task> readList() {
        return tasklist;
    }
}
