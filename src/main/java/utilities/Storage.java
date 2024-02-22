package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WilliamException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    // MATCHING_PATTERN has been improved by ChatGPT, the previous pattern
    // cannot be detected when given a wrong format. The current format can be detected.
    private final String MATCHING_PATTERN = "^\\s*(\\S+)\\s*\\|\\s*(\\S+)\\s*\\|"
            + "\\s*([^|]+?)\\s*\\|\\s*([^|]+?)\\s*\\|\\s*([^|]+?)\\s*\\|\\s*([^|\\s]+?)\\s*$";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the hard disk
     *
     * @return arraylist ArrayList of tasks
     * @throws WilliamException If the lists in the txt file is not in the expected format OR if the
     *                          pattern is wrong
     */
    public List<Task> loadFromFile() throws FileNotFoundException, WilliamException {

        File file = new File(this.filePath);
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<Task>();
        Pattern pattern = Pattern.compile(MATCHING_PATTERN);

        while (sc.hasNext()) {
            Matcher matcher = pattern.matcher(sc.nextLine());
            if (matcher.matches() == false) {
                throw new WilliamException("The lists is not in the expected format OR The pattern is wrong!");
            }
            String type = matcher.group(1);
            boolean isDone = Integer.parseInt(matcher.group(2)) == 1;
            String name = matcher.group(3);
            String firstPart = matcher.group(4);
            String secondPart = matcher.group(5);
            String priority = matcher.group(6);

            addTaskToList(type, isDone, name, firstPart, secondPart, priority, tasks);
        }
        sc.close();
        return tasks;
    }

    /**
     * Add task into list of tasks
     *
     * @param type       Type of task
     * @param isDone     Status of task
     * @param name       Description of task
     * @param firstPart  First part of the date (/by or /from)
     * @param secondPart Second part of the date (/to)
     * @param priority   Priority of the task
     * @param tasks      List of tasks
     */
    public void addTaskToList(String type, boolean isDone, String name, String firstPart,
                              String secondPart, String priority, List<Task> tasks) {
        switch (type) {
        case "T":
            tasks.add(new Todo(name, isDone, priority));
            break;
        case "E":
            tasks.add(new Event(name, DateAndTimeParser.convertStringToDate(firstPart),
                 DateAndTimeParser.convertStringToDate(secondPart), isDone, priority));
            break;
        case "D":
            tasks.add(new Deadline(name, DateAndTimeParser.convertStringToDate(firstPart),
                 isDone, priority));
            break;
        default:
            break;
        }
    }

    /**
     * Saves tasks into hard disk
     *
     * @throws IOException if the arraylist cannot be written to the file
     */
    public void writeToFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        StringBuilder sb = new StringBuilder();
        int actualSize = tasks.size() - 1;
        try {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String name = task.getName();
                String priority = task.getPriority();
                int isDone = task.getIsDone() ? 1 : 0;
                String[] times = task.getTimes();
                if (i != actualSize) {
                    sb.append(task.getType()).append(" | ").append(isDone).append(" | ")
                            .append(name).append(" | ").append(times[0]).append(" | ")
                            .append(times[1]).append(" | ").append(priority).append(System.lineSeparator());
                } else {
                    sb.append(task.getType()).append(" | ").append(isDone).append(" | ")
                            .append(name).append(" | ").append(times[0]).append(" | ")
                            .append(times[1]).append(" | ").append(priority);
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
