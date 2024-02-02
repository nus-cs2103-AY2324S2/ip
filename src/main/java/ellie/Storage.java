package ellie;

import ellie.task.Deadline;
import ellie.task.Event;
import ellie.task.Task;
import ellie.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.io.FileNotFoundException;

import java.util.ArrayList;

// Purpose is to save tasks and load tasks
public class Storage {

    private final String filePath;
    private final File f;

    // Constructor
    public Storage(String filePath) {
        // Recommended file path: "./data/toDoList.txt"
        this.filePath = filePath;
        f = new File(filePath);

        // add mkdir function

        // If file does not exist, create the file
        if (!f.exists()) {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write("");
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void load(ArrayList<Task> taskList) {
        try {
            Task task;
            Scanner scanner = new Scanner(f);
            if (!scanner.hasNext()) {
                System.out.println("No data loaded");
                return;
            }

            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split("\\|");

                switch(parts[0].charAt(0)) {
                case 'T':
                    task = new Todo(parts[2], Integer.parseInt(parts[1]));
                    taskList.add(task);
                    break;
                case 'D':
                    task = new Deadline(parts[2], Integer.parseInt(parts[1]), parts[3]);
                    taskList.add(task);
                    break;
                case 'E':
                    task = new Event(parts[2], Integer.parseInt(parts[1]), parts[3], parts[4]);
                    taskList.add(task);
                    break;
                }
            }
            System.out.println("Data loaded");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return;
        }

        // Save tasks in txt file.
        try {
            FileWriter fw = new FileWriter(f);

            // type |   1/0  | <event-name> | <date> | <date>
            for (Task element : taskList) {
                fw.write(element.getTaskType());
                fw.write('|' + Integer.toString(element.getIsDone()) );
                fw.write('|' + element.toString());

                // add dates, if relevant
                if (element instanceof Deadline) {
                    Deadline d = (Deadline) element;
                    fw.write('|' + d.getDueDate());
                }

                if (element instanceof Event) {
                    Event e = (Event) element;
                    fw.write('|' + e.getStartDate() + '|' + e.getEndDate());
                }

                fw.write(System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Format of toDoList.txt: Each line represents a new todo
    // type |   1/0 for marked status | <event-name> |
    //  T   |   1/0   | <event-name>
    //  D   |   1/0   | <event-name>  | <date>
    //  E   |   1/0   | <event-name>  | <start-date> | <end-date>




}
