package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import tasks.Task;




public class Storage {
    private static final String FILEPATH = "../../../data/rah.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    
    public void writeToFile(ArrayList<Task> inventory) throws IOException {
        File file = new File(FILEPATH);
        // Ensure the directory exists
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file, false)) { // false to overwrite
            for (Task task : inventory) {
                    fw.write(task.toString() + System.lineSeparator());
            }
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public ArrayList<Task> load() {
        return tasks;
    }

    @Override
    public String toString() {
        String result = "";
        int count = 1;
        for (Task s : tasks) {
            result += count + ". " + s.toString() + "\n";
            count++;
        }
        return result;
    }
}


