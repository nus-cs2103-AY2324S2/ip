package UiRelated;

import Tasks.DeadLineTask;
import Tasks.EventTask;
import Tasks.Task;
import Tasks.ToDoTask;
import TaskList.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private final String  filePath;

    public Storage(String inPut) {
        filePath = inPut;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> l = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String x = scanner.nextLine();
            String[] c = x.split(Pattern.quote("|"));
            String type = c[0];
            String mark = c[1];
            String TaskName = c[2];
            Task t;
            switch (type) {
                case "T":
                    t = new ToDoTask(TaskName);
                    l.add(t);
                    break;
                case "D":
                    t = new DeadLineTask(c[3], TaskName);
                    l.add(t);
                    break;
                default:
                    t = new EventTask(c[3], c[4], TaskName);
                    l.add(t);
            }
            if (mark.equals("Y")) {
                t.markAsDone();
            }
        }


        scanner.close();
        return l;

    }

    public void saveTasks(TaskList l) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : l) {
                writer.write(task.logString());
                writer.newLine();
            }
        }
        // Close the writer to ensure changes are saved
    }

}


