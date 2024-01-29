package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Storage {

    private final static String FILE_PATH = "src/main/java/storage/data.txt";
    private final static String DELIMITER = "~";

    public Storage() {

    }

    public ArrayList<Task> readFromStorage() throws FileNotFoundException {
        try {
            File file = new File(FILE_PATH);
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(DELIMITER);
                Task task = parseInput(input);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Unable to find the file path...");
        }
    }

    private static Task parseInput(String[] input) {
        String command = input[0];
        switch (command) {
        case "event":
            // format: event~status~description~<date>~<date>
            if (input.length < 5 || !(input[1].equals("0") || input[1].equals("1"))) {
                // corrupted data
                System.out.println("Error in loading an event task...");
                return null;

            }

            Event event = new Event(input[2], input[3], input[4]);
            event.setStatus(input[1].equals("1") ? true : false);

            return event;
        case "todo":
            // format: todo~status~description
            if (input.length < 3 || !(input[1].equals("0") || input[1].equals("1"))) {
                // corrupted data
                System.out.println("Error in loading a todo task...");
                return null;

            }

            ToDo todo = new ToDo(input[2]);
            todo.setStatus(input[1].equals("1") ? true : false);

            return todo;
        case "deadline":
            // format: deadline~status~description~<date>
            if (input.length < 4 || !(input[1].equals("0") || input[1].equals("1"))) {
                // corrupted data
                System.out.println("Error in loading a deadline task...");
                return null;

            }

            Deadline deadline = new Deadline(input[2], input[3]);
            deadline.setStatus(input[1].equals("1") ? true : false);

            return deadline;
        default:
            System.out.println("Error in loading task: " + input[0]);
            return null;
        }
    }

    public void writeToStorage(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void writeToStorage(Task task) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);

            fw.write(task.toStorageString() + "\n");

            fw.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
