package alfred.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides utilities for converting between task objects and their string representations.
 * This class supports saving tasks to and loading tasks from a string-based format, facilitating
 * persistence in text files or similar storage mechanisms.
 */
public class TaskConverter {

    /**
     * Converts a list of Task objects into a list of string representations suitable for saving.
     *
     * @param taskList An ArrayList of Task objects to be converted.
     * @return A List of strings, each representing a Task's data for persistence.
     */
    public static List<String> saveConvert(ArrayList<Task> taskList){
        List<String> stringList = new ArrayList<>();
        for (Task task : taskList) {
            String saveLine = task.getType() + "%" + task.isDone() + "%" + task.getName() + "%" + task.getRawTime();
            stringList.add(saveLine);
        }
        return stringList;
    }

    /**
     * Converts a list of strings into an ArrayList of Task objects.
     * Each string is expected to follow the format produced by saveConvert, with task properties separated by "%".
     *
     * @param strings A List of strings, each representing a task's persisted data.
     * @return An ArrayList of Task objects reconstructed from the input strings.
     */
    public static ArrayList<Task> loadConvert(List<String> strings) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            for (String str : strings) {
                String[] split = str.split("%");
                assert split.length >= 3 : "Incorrect Task string format";
                if (split[0].equals("[T]")) {
                    Task currTask = new Todo(split[2]);
                    currTask.setDone(Boolean.parseBoolean(split[1]));
                    tasks.add(currTask);
                } else if (split[0].equals("[D]")) {
                    Task currTask = new Deadline(split[2], split[3]);
                    currTask.setDone(Boolean.parseBoolean(split[1]));
                    tasks.add(currTask);
                } else if (split[0].equals("[E]")) {
                    Task currTask = makeEvent(split[2], split[3]);
                    currTask.setDone(Boolean.parseBoolean(split[1]));
                    tasks.add(currTask);
                }
            }
        } catch (Exception e) {
            System.out.println("Save File Corrupted :(");
        }
        return tasks;
    }

    /**
     * Helper method to create an Event object from a string containing its name and timeline.
     *
     * @param name The name of the event.
     * @param timeline The timeline of the event, expected to contain start and end times.
     * @return An Event object constructed from the provided name and timeline.
     */
    public static Event makeEvent(String name, String timeline){
        String[] timeSplit = timeline.split(" ");
        int toIndex = finder("to:", timeSplit);
        String startTime = String.join(" ", Arrays.copyOfRange(timeSplit, 1, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(timeSplit, toIndex + 1, timeSplit.length));
        Event current = new Event(name, startTime, endTime);
        return current;
    }

    /**
     * Searches for a specific string within an array of strings and returns its index.
     *
     * @param checker The string to find within the array.
     * @param list The array of strings to search through.
     * @return The index of the string if found, or -1 if not found.
     */
    public static int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }
}
