package commands;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Todo;
import utilities.MessagePrinter;
import utilities.Storage;
import utilities.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsParser {
    //Handles adding/removing to array of tasks
    private TaskList taskList;
    private String filePath;
    //Handles the loading and saving of tasks to text file
    private Storage taskLoader;

    public CommandsParser(TaskList taskList, String filePath, Storage taskLoader) {
        this.taskList = taskList;
        this.filePath = filePath;
        this.taskLoader = taskLoader;
    }
    public int parseCommands(String task) throws RyanGoslingException {
        String[] taskSplit = task.split(" ");
        if (task.equals(String.valueOf(CommandsEnum.bye))) {
            MessagePrinter.bye();
            return 1;
        } else if (task.equals(String.valueOf(CommandsEnum.list))) {
            taskList.printList();
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || taskSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
            //All items to be 0-index referenced other than user input.
            taskList.changeStatusOfItem(taskSplit[0], Integer.parseInt(taskSplit[1])-1);
            taskList.writeToFile(taskLoader);
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            //Idea from chatGPT
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                taskList.add(new Todo(matcher.group(1)));
                taskList.writeToFile(taskLoader);
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                taskList.add(new Deadline(matcher.group(1), matcher.group(2)));
                taskList.writeToFile(taskLoader);
            } else {
                throw new RyanGoslingException("Incomplete deadline command, " +
                        "deadline <event> /by <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            //System.out.println(task);
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                // Retrieve matched groups
                System.out.println(matcher.group(3));
                taskList.add(new Events(matcher.group(1), matcher.group(2), matcher.group(3)));
                taskList.writeToFile(taskLoader);
            } else {
                throw new RyanGoslingException("Incomplete event command, " +
                        "event <event> /from <time> /to <time>");
            }
        } else if (taskSplit[0].equals(String.valueOf(CommandsEnum.delete))) {
            taskList.removeIndex(Integer.parseInt(taskSplit[1])-1);
            taskList.writeToFile(taskLoader);
        }
        else {
            throw new RyanGoslingException("I was created in a few hours so " +
                    "I don't know what that means :(");
        }
        return 0;
    }
}
