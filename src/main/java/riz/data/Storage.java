package riz.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * The Storage class primarily deals with writing information about the tasks
 * from the current TaskList to a file, keeping it in memory. As well as
 * reading and loading the existing file into the TaskList.
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }

    /**
     * This method processes and loads the current memory from the file into an ArrayList<Task>.
     * @return an ArrayList<Task> containing all the Task objects that were stored
     * in memory.
     */
    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNext()) {
                String[] token = scanner.nextLine().split(" \\| ");
                tasks = createTaskList(token);
            }
        } catch (FileNotFoundException e) {
            File dir = new File("riz/data");
            if(dir.mkdirs()) {
                System.out.println("Directory 'data' created successfully!");
            }
            File file2 = new File(dir, "riz.txt");
            try {
                if (file2.createNewFile()) {
                    System.out.println("File 'riz.txt' created successfully!");
                }
            } catch (IOException ex) {
                System.out.println("Error creating file...");
            }
        }
        return tasks;
    }

    private ArrayList<Task> createTaskList(String[] token) {
        ArrayList<Task> tasks = new ArrayList<>();
        if (token[0].equals("T")) {
            ToDo todo = new ToDo(token[2]);
            if (token[1].equals("X")) {
                todo.mark();
            }
            tasks.add(todo);
        } else if (token[0].equals("D")) {
            LocalDateTime by = LocalDateTime.parse(token[3], DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
            Deadline deadline = new Deadline(token[2], by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
            if (token[1].equals("X")) {
                deadline.mark();
            }
            tasks.add(deadline);
        } else {
            String[] time = token[3].split(" - ");
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(time[0], inputFormatter);
            LocalDateTime to = LocalDateTime.parse(time[1], inputFormatter);
            Event event = new Event(token[2], from.format(outputFormatter), to.format(outputFormatter));
            if (token[1].equals("X")) {
                event.mark();
            }
            tasks.add(event);
        }
        return tasks;
    }

    /**
     * The method writes all tasks from the TaskList into memory.
     * @param tasks ArrayList<Task> taken from the TaskList containing all current tasks.
     */
    public void writeToFile(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        try (FileWriter fw = new FileWriter(this.filePath)) {
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("There is something wrong: " + e.getMessage());
        }
    }

    /**
     * The method prints out all the current tasks that are stored in memory.
     */
    public String printFromFile() {
        StringBuilder sb = new StringBuilder();
        File file = new File(this.filePath);
        assert file.exists() : "File does not exist";
        try (Scanner scanner = new Scanner(file)) {
            int count = 1;
            while (scanner.hasNext()) {
                sb.append(count).append(". ").append(scanner.nextLine()).append("\n");
                count++;
            }
        } catch (FileNotFoundException e) {
            return "File is not found...";
        }
        return sb.toString();
    }

    /**
     * Count the number of tasks in memory currently.
     * @return The number of tasks in the file.
     */
    public int countFromFile() {
        File file = new File(this.filePath);
        int i = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                i++;
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found...");
        }
        return i;
    }
}