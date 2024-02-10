package commands;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Todo;
import utilities.ResponseHandler;
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


    public String parseCommands(String task) throws RyanGoslingException {
        String[] commandSplit = task.split(" ");
        switch (task) {
        case "bye":
            return ResponseHandler.bye();
        case "list":
            return taskList.printList();
        default:
        }

        //Items are 0-indexed, unless otherwise stated.
        if (commandSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || commandSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
            try {
                String responseReturn = taskList.changeStatusOfItem(commandSplit[0],
                                                                    Integer.parseInt(commandSplit[1]) - 1);
                taskList.writeToFile(taskLoader);
                return responseReturn;

            } catch (Exception e) {
                throw new RyanGoslingException("Wrong format! (un)mark <number>");
            }

        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.todo))) {

            //Idea from chatGPT
            Pattern pattern = Pattern.compile("todo (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                String responseReturn = taskList.add(new Todo(matcher.group(1)));
                taskList.writeToFile(taskLoader);
                return responseReturn;
            } else {
                throw new RyanGoslingException("Incomplete todo command, todo <event>");
            }

        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            Pattern pattern = Pattern.compile("deadline (.*?) /by (.*?) (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                String responseReturn = taskList.add(new Deadline(matcher.group(1), matcher.group(2),
                                                                  matcher.group(3)));
                taskList.writeToFile(taskLoader);
                return responseReturn;
            } else {
                throw new RyanGoslingException("Incomplete deadline command, " + "deadline <event> /by <date> <time> "
                                                       + "\n If no specific time, leave time as 2359");
            }
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            //System.out.println(task);
            Pattern pattern = Pattern.compile("event (.*?) /from (.*?) (.*?) /to (.*?) (.*?)");
            Matcher matcher = pattern.matcher(task);
            if (matcher.matches()) {
                // Retrieve matched groups
                //System.out.println(matcher.group(3));

                String responseReturn = taskList.add(new Events(matcher.group(1), matcher.group(2), matcher.group(3),
                                                               matcher.group(4), matcher.group(5)));
                taskList.writeToFile(taskLoader);
                return responseReturn;

            } else {
                throw new RyanGoslingException("Incomplete event command, "
                        + "event <event> /from <date> <time> /to <date> <time>\n"
                                                       + "If no time, leave time as 2359");
            }
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.delete))) {
            String responseReturn = taskList.removeIndex(Integer.parseInt(commandSplit[1]) - 1);
            taskList.writeToFile(taskLoader);
            return responseReturn;
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.find))) {
            Pattern pattern = Pattern.compile("find (.*?)");
            Matcher matcher = pattern.matcher(task);

            if (matcher.matches()) {
                return taskList.findTasks(matcher.group(1));
            } else {
                throw new RyanGoslingException("Incomplete find command! find <task_word>");
            }
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.help))) {
            return CommandsEnum.getAllCommands();
        } else {
            throw new RyanGoslingException("I am artificially intelligent but not in a smart way. \nTry a valid "
                                                   + "command! or check them out by typing help");
        }
    }
}
