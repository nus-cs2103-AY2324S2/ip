package Mitsuki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Tee Chu Jie
 */
public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Retrieves the saved list file from the hard disk.
     * @return an array list of tasks that the user previously added.
     * @throws FileNotFoundException if file does not exist yet.
     */
    public static ArrayList<Task> load() throws FileNotFoundException {
        File list = new File(filePath);
        Scanner scanner = new Scanner(list);
        ArrayList<Task> taskList = new ArrayList<>();

        while (scanner.hasNext()) {
            String fullString = scanner.nextLine();
            if (fullString.contains("[T]")) {
                String description = fullString.substring(7);

                Task nextTask = new Todo(description);
                if (fullString.contains("[X]")) {
                    nextTask.markAsDone();
                }
                taskList.add(nextTask);

            } else if (fullString.contains("[D]")) {
                int descriptionEnd = fullString.indexOf('(');
                int deadlineEnd = fullString.indexOf(')');

                String description = fullString.substring(7, descriptionEnd - 1);
                String deadline = fullString.substring(descriptionEnd + 4, deadlineEnd);
                LocalDate processedDeadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("MMM d yyyy"));
                Task nextTask = new Deadline(description, processedDeadline);

                if (fullString.contains("[X]")) {
                    nextTask.markAsDone();
                }

                taskList.add(nextTask);

            } else if (fullString.contains("[E]")) {
                int descriptionEnd = fullString.indexOf('(');
                int startTimeEnd = fullString.indexOf("to");
                int endTimeEnd = fullString.indexOf(')');

                String description = fullString.substring(7, descriptionEnd - 2);
                String startTime = fullString.substring(descriptionEnd + 1, startTimeEnd - 1);
                String endTime = fullString.substring(startTimeEnd - 1, endTimeEnd);

                Task nextTask = new Event(description, startTime, endTime);

                if (fullString.contains("[X]")) {
                    nextTask.markAsDone();
                }

                taskList.add(nextTask);
            }
        }

        return taskList;
    }

    /**
     * Saves the user's list as a text file in the hard disk.
     * @param fileName the file name of the file to be saved.
     * @throws IOException if file cannot be created.
     */
    public static void save(String fileName) throws IOException {
        File list = new File(fileName);

        FileWriter saver = new FileWriter(list);

        for (int i = 0; i < Mitsuki.toDoList.size(); i++) {
            saver.write(Mitsuki.toDoList.get(i).toString() + System.lineSeparator());
        }

        System.out.println("I have saved your list for your future reference. :D");
        saver.close();
    }
}
