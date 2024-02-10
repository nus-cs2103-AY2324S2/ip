package utilities;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The Storage class is responsible for loading and saving tasks to/from the hard drive.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses and loads tasks from the hard drive.
     *
     * @return An ArrayList of Task objects representing the loaded tasks.
     * @throws FileNotFoundException If the file specified by filePath is not found.
     * @throws RyanGoslingException  If the task data on the hard drive is not in the expected format.
     */
    public ArrayList<Task> parseAndLoadTasks() throws FileNotFoundException, RyanGoslingException {
        File file = new File(filePath);
        //We first check if file directory exists. We will create the folder for user instead.
        if (!file.exists()) {
            try {
                Files.createDirectories(file.toPath().getParent());
                Files.createFile(file.toPath());
                System.out.println("File created successfully\n"
                                           + "It will appear in data folder. " + filePath);
            } catch (IOException e) {
                System.err.println("Failed to create file.\n"
                                           + "Do try again!: " + e.getMessage());
            }
        }



        Scanner s = new Scanner(file);
        String patternString = "^\\s*(\\w+)\\s*\\|\\s*(\\d+)\\s*\\|\\s*(\\S+)\\s*\\|\\s*(\\S+)\\s*"
                + "\\|\\s*(\\S+)\\s*\\|\\"
                + "s*(\\S+)\\s*\\|\\s*(\\S+)\\s*$";
        //Format is "T | 0 | lol | dateFrom | timeFrom | dateTo | timeTo";
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            Pattern regex = Pattern.compile(patternString);
            Matcher matcher = regex.matcher(currentLine);
            if (!matcher.matches()) {
                throw new RyanGoslingException("Task lists stored in hard drive is not in expected format!\n"
                                                       + "It should follow (T/E/D) | (0/1) | Description | dateFrom | "
                                                       + "timeFrom "
                                                       + "| dateTo | timeTo");
            } else {
                String typeOfTask = matcher.group(1);
                int isTaskDone = Integer.parseInt(matcher.group(2));
                assert isTaskDone == 1 || isTaskDone == 0 : "Wrong isTaskDone input format in text file!";
                String taskDescription = matcher.group(3);
                String dateFrom = matcher.group(4);
                String timeFrom = matcher.group(5);
                String dateTo = matcher.group(6);
                String timeTo = matcher.group(7);
                switch (typeOfTask) {
                case "T":
                    listOfTasks.add(new Todo(taskDescription, isTaskDone));
                    break;
                case "D":
                    listOfTasks.add(new Deadline(taskDescription, dateFrom, timeFrom, isTaskDone));
                    break;
                case "E":
                    listOfTasks.add(new Events(taskDescription, dateFrom, timeFrom, dateTo, timeTo, isTaskDone));
                    break;
                default:

                }
            }
        }
        return listOfTasks;
    }

    /**
     * Writes the provided task list to the hard drive.
     *
     * @param taskList The ArrayList of Task objects to be written to the hard drive.
     */
    public void writeToTaskList(ArrayList<Task> taskList) {
        StringBuilder toAdd = new StringBuilder();
        for (int i = 0; i < taskList.size(); i += 1) {
            Task taskToAdd = taskList.get(i);
            toAdd.append(taskToAdd.getTaskType());
            int taskDone = taskToAdd.isTaskDone() ? 1 : 0;
            String[] possibleDates = taskToAdd.getDates();
            String[] possibleTimes = taskToAdd.getTimes();
            toAdd.append(" | ")
                    .append(taskDone).append(" | ")
                    .append(taskToAdd.getTaskName()).append(" | ")
                    .append(possibleDates[0]).append(" | ")
                    .append(possibleTimes[0]).append(" | ")
                    .append(possibleDates[1]).append(" | ")
                    .append(possibleTimes[1]);
            if (i != taskList.size() - 1) {
                toAdd.append(System.lineSeparator());
            }
        }
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(toAdd.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing! This a Level 5 bruh moment!");
        }
    }

}
