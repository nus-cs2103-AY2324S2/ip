package duke.util;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String fileLocation;

    /**
     * Creates a new Storage object, storing the filePath information.
     *
     * @param filePath file path of the save file.
     */
    public Storage(String filePath) {
        fileLocation = filePath;
    }

    /**
     * Converts Date information from 'MMM dd yyyy' format in save file into 'yyyy-MM-dd' format when reading the file.
     *
     * @param date String date of task in save file.
     * @return Converted format of String date.
     */
    public String convertDateFormat(String date) {
        LocalDate temp;
        DateTimeFormatter readDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        temp = LocalDate.parse(date, readDateFormat);
        String formattedString = temp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(formattedString);
        return formattedString;
    }

    /**
     * Reads the tasks saved in file previously and parses it individually.
     *
     * @param inputCommand Task information saved previously.
     * @return new Task.
     */
    public Task parseInput(String inputCommand) {
        String[] tokens = inputCommand.split(" \\| ");
        String taskType = tokens[0];
        boolean isDone = tokens[1].equals("O");
        String description = tokens[2];
        Task task;

        switch(taskType) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "D":
            String deadlineBy = convertDateFormat(tokens[3]);
            task = new Deadline(description, deadlineBy, isDone);
            break;
        case "E":
            String eventAt = tokens[3];
            task = new Event(description, eventAt, isDone);
            break;
        default:
            throw new IllegalStateException("I cannot recognize this duke.task type!" + taskType);
        }
        return task;
    }

    /**
     * Creates a new file when the file is not found or has not been initialised yet.
     */
    public void createFile() {
        File file = new File(fileLocation);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created!!");
            } catch (IOException e) {
                System.out.println("Error creating the file...");
            }
        } else {
            System.out.println("File already exists, you can continue");
        }
    }

    /**
     * Reads the previous tasks saved in file and loads it into a TaskList.
     *
     * @return TaskList.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(fileLocation);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                tasks.add(parseInput(input));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return tasks;
    }

    /**
     * When chatbot is terminated, write down all tasks in the TaskList into the save file.
     *
     * @param tasklist Final updated tasklist.
     */
    public void writeFile(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter(fileLocation);
            for (int i = 0; i < tasklist.getSize(); i++) {
                Task task = tasklist.getTask(i);
                fw.write(task.outputString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error detected: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
