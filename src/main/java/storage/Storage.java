package storage;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private final String path;

    public Storage(String filePath) {
        this.path = filePath;
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(textToAppend);
        fw.close();
    }

    public TaskList loadFile() throws FileNotFoundException {
        File f = new File(path);
        if (!f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
        TaskList taskList = new TaskList();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] inputs = s.nextLine().split("/");
            Task task;
            if ("[T]".equals(inputs[0])) {
                task = new Todo(inputs[2]);
            } else if ("[D]".equals(inputs[0])) {
                task = new Deadline(inputs[2], LocalDate.parse(inputs[3]));
            } else if ("[E]".equals(inputs[0])) {
                task = new Event(inputs[2], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]));
            } else {
                task = new Task(inputs[0], inputs[2]);
            }

            if (inputs[1].equals("1")) {
                task.mark();
            }
            taskList.addTasks(task);
        }
        return taskList;
    }
}
