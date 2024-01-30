package Storage;

import java.util.ArrayList;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import UI.UI;

public class Storage {
    private static final String lines = "    ____________________________________________________________";
    private final ArrayList<Task> storage;

    public Storage() {
        this.storage = new ArrayList<>();
    }
    public void add(Task task) {
        this.storage.add(task);
        saveToFile();
    }

    public void markTask(int number) {
        this.storage.get(number).mark();
        System.out.println("      " + this.storage.get(number).toString());
        saveToFile();
    }
    public void unMarkTask(int number) {
        this.storage.get(number).unMark();
        System.out.println("      " + this.storage.get(number).toString());
        saveToFile();
    }

    public void remove(int number) {
        System.out.println("      " + this.storage.get(number).toString());
        this.storage.remove(number);
        saveToFile();
    }
    public int taskLength() {
        return this.storage.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(lines).append("\n");
        for(int i=1;i<=storage.size();i++) {
           result.append(String.format("    %d.",i)).append(this.storage.get(i-1).toString()).append("\n");
        }
        result.append(lines);
        return result.toString();
    }

    private void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("./src/main/java/Storage/data.txt");
            fileWriter.write(toString());
            fileWriter.close();
        } catch (IOException e) {
            UI.error(e.getMessage());
        }
    }

}
