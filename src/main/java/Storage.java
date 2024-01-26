import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createLocalStorage() {
        try {
            File file = new File(filePath);

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.createNewFile()) {
                System.out.println("   File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("   File alr exists: " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void localToList(String data, TaskList tasks) {
        try {
            if (data.startsWith("T")) {
                boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

                int desc = data.indexOf("|desc");
                String description = data.substring(desc+5);

                Task newTask = new ToDo(description, isDone);
                tasks.addToList(newTask);

            }
            else if (data.startsWith("D")) {
                boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

                int desc = data.indexOf("|desc");
                int by = data.indexOf("|by");
                String description = data.substring(desc+5, by);
                String byDate = data.substring(by+3);
                LocalDateTime dd = Parser.dateFromString(byDate);

                Task newTask = new Deadline(description, isDone, dd);

                tasks.addToList(newTask);

            }
            else if (data.startsWith("E")) {
                boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

                int desc = data.indexOf("|desc");
                int from = data.indexOf("|from");
                int to = data.indexOf("|to");
                String description = data.substring(desc+5, from);
                String fromDate = data.substring(from+5, to);
                String toDate = data.substring(to+3);

                LocalDateTime f = Parser.dateFromString(fromDate);
                LocalDateTime t = Parser.dateFromString(toDate);

                Task newTask = new Event(description, isDone, f, t);
                tasks.addToList(newTask);
            }
            else {
                throw new TobiasException("   Saved file is corrupted!");
            }
        } catch (Exception e) {
            System.out.println("local to list function " + e.getMessage());
        }
    }

    public TaskList localToCurrent() {
        TaskList tasks = new TaskList();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                localToList(s.nextLine(), tasks);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("local to current function " + e.getMessage());
        }
        return tasks;
    }

    public void storeToLocal(TaskList tasks) {
        String result = tasks.saveMechanism();

        try {
            FileWriter fw = new FileWriter("data/tobias.txt");
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
