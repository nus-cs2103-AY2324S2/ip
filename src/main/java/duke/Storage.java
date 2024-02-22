package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    /**
     * The constructor of Storage.
     *
     * @param filePath The file to read and write from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write task into the filePath.
     *
     * @param taskList The full tasklist.
     */
    public void writeTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : taskList.getTaskList()) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * Load task from a filePath.
     * If the data file does not exist, a new one will be created and
     * an empty ArrayList will be returned.
     *
     * @return An ArrayList of Tasks with the initial tasks added in where applicable.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            assert directory.exists() : "File directory should already exist";
            File inputFile = new File("./data/tasks.txt");
            if (inputFile.createNewFile()) {
                // new file created
            } else {
                assert inputFile.exists() : "The file should already exist";
                list = readTasks(inputFile);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    /**
     * Read task from the filePath.
     *
     * @param inputFile File which the tasks are read from.
     * @return An ArrayList of Tasks which the tasks read are added into.
     */
    private ArrayList<Task> readTasks(File inputFile) {
        ArrayList<Task> list = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try {
            Scanner s = new Scanner(inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String type = line.substring(1, 2);
                String mark = line.substring(4, 5);

                if (type.equals("T")) {
                    String description = line.substring(7);
                    Todo t = new Todo(description);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("D")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(by: ");
                    String description = result[0].trim();
                    String by = result[1].substring(0, result[1].length() - 1);
                    LocalDate date = LocalDate.parse(by.substring(0, 11), dateTimeFormatter);
                    LocalTime time = LocalTime.parse(by.substring(12, 17));
                    Deadline t = new Deadline(description, date, time);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("E")) {
                    String temp = line.substring(7);
                    String[] temp2 = temp.split("\\(from: ");
                    String description = temp2[0].trim();
                    String result = temp2[1];
                    LocalDate startDate = LocalDate.parse(result.substring(0, 11), dateTimeFormatter);
                    LocalTime startTime = LocalTime.parse(result.substring(12, 17));
                    LocalDate endDate = LocalDate.parse(result.substring(24, 35), dateTimeFormatter);
                    LocalTime endTime = LocalTime.parse(result.substring(36, 41));
                    Event t = new Event(description, startDate, startTime, endDate, endTime);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

}
