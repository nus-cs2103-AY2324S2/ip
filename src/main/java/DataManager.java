import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {
    private static String path;

    public DataManager(String path) {
        this.path = path;
    }

    private static String convertToFile(Task task) {
        String symbol = " ";
        String time = " ";
        String status = " ";
        if(task instanceof Todo) {
            symbol = "T";
        }else if(task instanceof Deadline) {
            symbol = "D";
            time = ((Deadline) task).getTime();
        }else{
            symbol = "E";
            time = ((Event)task).getFrom() + " - " + ((Event)task).getTo();
        }
        if(task.getStatus()) {
            status = "1";
        }else{
            status = "0";
        }
        return symbol + " | " + status + " | " + task.name + " | " + time;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try{
            File dataFile = new File(path);
            dataFile.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(dataFile, false);
            for (Task task : tasks) {
                writer.write(convertToFile(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
        System.out.println("Unable to save tasks.");
        }
    }

    public ArrayList<Task> retrieveTasks () {
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            File file = new File(path);
            if(file.exists()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    String data =scanner.nextLine();
                    Task task = getTask(data);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to load tasks.");
        }
        return tasks;
    }

    public static boolean getStatus(String num) {
        return num.equals("1");
    }
    public static Task getTask(String message) {
        Task task = null;
        String parts[] = message.split(" \\| ");
        String type = parts[0];
        boolean status = getStatus(parts[1]);
        String taskName = parts[2];
        String time = parts[3];

        if(type.equals("T")) {
            task = new Todo(taskName, status);
        }else if(type.equals("D")) {
            task = new Deadline(taskName, status, time);
        }else {
            String timeParts[] = time.split(" - ");
            String from = timeParts[0];
            String to = timeParts[1];
            task = new Event(taskName, status, from, to );
        }
        return task;
    }

}
