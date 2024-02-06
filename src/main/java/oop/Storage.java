package oop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class Storage {
    private static String filePath;
    private static final String line = "\t______________________________________________________";
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static String listToString(TaskList list) {
        String content = "";
        for (int i = 0; i < list.size() - 1; i++) {
            Task task = list.get(i);
            content = content + task.getTaskInfo() + "\n";
        }
        Task task = list.get(list.size() - 1);
        content = content + task.getTaskInfo();
        return content;
    }

    public static ArrayList<Task> stringToList(ArrayList<Task> tasks, String text) {
        String[] list = text.split("(new) ");
        try {
            for (String s : list) {
                String[] info = s.split("/ ");
                switch (info[0]) {
                    case "[T] ":
                        Task task = new Todo(info[2]);
                        if (info[1].equals("[X]")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    case "[D] ":
                        String dueDate = LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                        task = new Deadline(info[2], dueDate);
                        if (info[1].equals("[X]")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    case "[E] ":
                        String startTime = LocalDateTime.parse(info[3], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                        String endTime = LocalDateTime.parse(info[4], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                        task = new Event(info[2], startTime, endTime);
                        if (info[1].equals("[X]")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    default:
                        System.out.println("File is corrupted!");
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println(line);
            System.out.println("\t I think you haven't had enough vitamin C."
                    + "\n\t Your time format should be :"
                    + "\n\t\t { dd/MM/yyyy HHmm }"
                    + "\n\t I suggest you take some LEMONA.");
            System.out.println(line);
        }
        return tasks;
    }

    public static ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            tasks = stringToList(tasks, task);
        }
        return tasks;
    }

    public static void save(TaskList tasks){
        try {
            if (tasks.size() != 0) {
                File file = new File(filePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                fw.write(listToString(tasks));
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("\t Sorry, I think I haven't had enough vitamin C."
                    + "\n\t I am unable to save tasks into the file."
                    + "\n\t I will need to go have some LEMONA.");
            System.out.println(line);
        }
    }
}
