package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected Scanner scanner;
    protected static String filePath;
    protected File file;

    public Storage (String filePath) throws DukeException {
        try {
            Storage.filePath = filePath;
            this.file = new File(filePath);
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("Error loading file");
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file);
        fw.write(textToAdd);
        fw.close();
    }

    public static void saveCurrentList(TaskList list) throws IOException{
        StringBuilder sb = new StringBuilder();
        for (Task task : list.getList()) {
            sb.append(task.toFileString());
            sb.append("\n");
        }
        writeToFile(filePath, sb.toString());
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String type = line.substring(0, 1);
            switch (type) {
                case "T":
                    list.add(Task.fromFileString(line));
                    break;
                case "D":
                    list.add(Deadline.fromFileString(line));
                    break;
                case "E":
                    list.add(Event.fromFileString(line));
                    break;
            }
        }
        return list;
    }
}
