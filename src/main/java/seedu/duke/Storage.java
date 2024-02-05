package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.Events;
import seedu.duke.task.Deadlines;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static File f;
    private static String filePath;
    private static TaskList taskList;
    public Storage(String filePath, TaskList taskList) { //constructor
        this.filePath = filePath;
        f = new File(filePath);
        this.taskList = taskList;
    }
    public void loadFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasksToLoad  = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String task = scanner.nextLine();
            String[] taskDetails = task.split(" \\| ");
            Task newTask;
            if (taskDetails.length == 3) { //todo task
                newTask = new Task(taskDetails[2]);
            } else if (taskDetails.length == 4) { //deadline
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HHmm");
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[3], formatter);
                newTask = new Deadlines(taskDetails[2], deadline);
            } else {
                newTask = new Events(taskDetails[2], taskDetails[3], taskDetails[4]);
            }
            if (taskDetails[1].equals("1")) { //if task/event/deadline is marked
                newTask.markDone(false);
            }
            tasksToLoad.add(newTask);
        }
        scanner.close();
        tasksToLoad.forEach(x -> this.taskList.addItem(x, false));
    }
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void deleteFromFile(int num) throws IOException {
        Scanner s = new Scanner(f);
        String newData = "";
        int count = 0;
        while (s.hasNextLine()) {
            String temp = s.nextLine();
            if (count != num) {
                newData += temp + "\n";
            }
            count++;
        }
        FileWriter fw = new FileWriter(f);
        fw.write(newData);
        fw.close();
    }
    public static void changeMarking(int num, String textToReplace) throws IOException {
        Scanner s = new Scanner(f);
        String newData = "";
        int count = 0;
        while (s.hasNextLine()) {
            String temp = s.nextLine();
            if (count != num) {
                newData += temp + "\n";
            } else {
                newData += textToReplace;
            }
            count++;
        }
        FileWriter fw = new FileWriter(f);
        fw.write(newData);
        fw.close();
    }

    public static void add(Task task) { //to append items to taskList
        try {
            writeToFile(task.toStore());
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }
    public static void delete(int num) {
        try {
            deleteFromFile(num);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }
}
