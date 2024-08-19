package pan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import pan.enums.TaskStatus;

/**
 * Storage - Represents the Storage Class that handles read and write operations
 *
 * @author Jerome Goh
 */
public class Storage {
    public Storage() {
    }

    /**
     * Persists the state of the tasks into a .txt file through overwriting the file
     * at every function call.
     *
     * @param tasks the list of tasks to take reference from when program tries to save it to a .txt file.
     */
    public void save(List<Task> tasks) {
        try {
            String filePath = "./src/main/java/pan/output/pan.txt";
            File outputFile = new File(filePath);
            FileWriter writer = new FileWriter(outputFile, false);
            // attempts to create the new file
            outputFile.createNewFile();
            StringBuilder sb = new StringBuilder();
            tasks.stream().forEach(task -> sb.append(task.toString() + "\n"));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Updates the completion status of a given task within the Task List to completed.
     *
     * @return list of Tasks instances that used to set the intitial state of the task list.
     */
    public List<Task> readList() {
        List<Task> newList = new ArrayList<Task>();
        try {
            String filePath = "./src/main/java/pan/output/pan.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String taskString = bufferedReader.readLine();
            while (taskString != null) {
                newList.add(parseCommand(taskString));
                taskString = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return newList;
    }

    /**
     * Updates the completion status of a given task within the Task List to completed.
     *
     * @param commandLine
     * @return Task instance that represents the command of a single line within the .txt file.
     */
    public Task parseCommand(String commandLine) {
        String baseString = commandLine.substring(7);
        String commandDescription = baseString.split(" ")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        TaskStatus completionStatus = TaskStatus.INCOMPLETE;
        if (commandLine.charAt(4) == 'X') {
            completionStatus = TaskStatus.COMPLETE;
        }
        switch (commandLine.charAt(1)) {
        case 'T':
            ToDos todo = new ToDos(commandDescription, completionStatus);
            return todo;
        case 'D':
            LocalDate by = LocalDate.parse(
                baseString.substring(baseString.indexOf(":") + 1, baseString.indexOf(")")).trim(), formatter);
            Deadlines deadline = new Deadlines(commandDescription, completionStatus, by);
            return deadline;
        case 'E':
            int fromIndex = baseString.indexOf("from:") + 5;
            int toIndex = baseString.indexOf("to:") - 1;
            String fromDateString = baseString.substring(fromIndex, toIndex).trim();

            int endIndex = baseString.indexOf(")", toIndex + 3);
            String toDateString = baseString.substring(toIndex + 4, endIndex).trim();

            LocalDate fromDate = LocalDate.parse(fromDateString, formatter);
            LocalDate toDate = LocalDate.parse(toDateString, formatter);
            Events event = new Events(commandDescription, completionStatus, fromDate, toDate);
            return event;

        default:
            return null;
        }
    }
}
