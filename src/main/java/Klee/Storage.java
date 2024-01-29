package Klee;

import Klee.task.Deadline;
import Klee.task.Event;
import Klee.task.Task;
import Klee.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Storage which contains the tasks listed in TaskList.
 */
public class Storage {
    private static String path = "data";
    private static String fileName = "data/Klee.txt";

    /**
     * Constructor for Storage class.
     */
    public Storage () {}

    /**
     * Parse String returned from txt file into an instance of LocalDateTime.
     *
     * @param txt
     * @return LocalDateTime instance
     */
    public static LocalDateTime parseDateTimeTxt (String txt) {
        String[] dateTime = txt.split(" ");
        int year = Integer.parseInt(dateTime[0]);
        int month = Integer.parseInt(dateTime[1]);
        int day = Integer.parseInt(dateTime[2]);
        int hour = Integer.parseInt(dateTime[3]);
        int minute = Integer.parseInt(dateTime[4]);
        LocalDateTime returnVariable = LocalDateTime.of(year, month, day, hour, minute);
        return returnVariable;
    }

    /**
     * Save tasks inside TaskList into a txt file.
     *
     * @param tasks
     */
    public void saveTasks (TaskList tasks) {
        try {
            FileWriter file = new FileWriter("data/Klee.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                String line = currentTask.toText();
                file.write(line + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Klee has run out of ink! I cannot write this down!");
        }
    }

    /**
     * Open txt file and parse all tasks, save them into a new instance of TaskList and return it.
     *
     * @param ui
     * @return TaskList with all tasks inside txt file.
     */
    public TaskList retrieveTasks (Ui ui) {
        TaskList tasks = new TaskList();

        //Create /data directory only if it does not exist
        new File(path).mkdirs();
        try {
            File data = new File(fileName);
            if (!data.exists()) {
                data.createNewFile();
            } else {
                Scanner readFile = new Scanner(data);
                while (readFile.hasNext()) {
                    String read = readFile.nextLine();
                    String[] line = read.split(" / ");
                    switch (line[0]) {
                    case "T":
                        tasks.add(ToDo.fromText(line[2], line[1]));
                        break;
                    case "D":
                        tasks.add(Deadline.fromText(line[2], line[1], parseDateTimeTxt(line[3])));
                        break;
                    case "E":
                        tasks.add(Event.fromText(line[2], line[1], parseDateTimeTxt(line[3]), parseDateTimeTxt(line[4])));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            ui.showError("Klee could not find the book we wrote on last time... I'm sorry...");
        }
        return tasks;
    }
}
