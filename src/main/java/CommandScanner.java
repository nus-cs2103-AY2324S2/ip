import exceptions.DukeException;
import exceptions.InvalidDateException;
import exceptions.StorageException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommandScanner {


    public static void stringToTaskParser(String[] textSections, TaskList taskList) throws StorageException, InvalidDateException {
        String taskType = textSections[0].trim().toUpperCase();
        boolean isDone = textSections[1].trim().equals("[X]");
        Task task = null;
        Pattern pattern;
        Matcher matcher;
        try {
            switch (taskType) {
                case ("T"):
                    task = new ToDoTask(textSections[2].trim());
                    break;
                case ("D"):
                    pattern = Pattern.compile("\\(by: (.*?)\\)");
                    matcher = pattern.matcher(textSections[3].trim());
                    if (matcher.find()) {
                        String deadline = matcher.group(1);
                        task = new DeadlineTask(textSections[2].trim(), Parser.parseDateTime(deadline));
                        if (isDone) {
                            task.markDone();
                        }
                    } else {
                        throw new StorageException();
                    }
                    break;
                case ("E"):
                    pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
                    matcher = pattern.matcher(textSections[3].trim());
                    if (matcher.find()) {
                        String startBy = matcher.group(1);
                        String endBy = matcher.group(2);
                        task = new EventTask(textSections[2].trim(), Parser.parseDateTime(startBy), Parser.parseDateTime(endBy));
                        if (isDone) {
                            task.markDone();
                        }
                    } else {
                        System.out.println("Invalid event task format.");
                    }
                    break;
                default:
                    System.out.println("Invalid task format.");
            }
            taskList.add(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Accessing out of bounds index.");
        }
    }



}
