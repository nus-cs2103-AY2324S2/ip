package commands;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import utilities.ResponseHandler;
import utilities.Storage;
import utilities.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsParser {
    //Handles adding/removing to array of tasks
    private TaskList taskListManager;
    private String filePath;
    //Handles the loading and saving of tasks to text file
    private Storage taskLoader;

    public CommandsParser(TaskList taskListManager, String filePath, Storage taskLoader) {
        this.taskListManager = taskListManager;
        this.filePath = filePath;
        this.taskLoader = taskLoader;
    }


    public String parseCommands(String taskInputByUser) throws RyanGoslingException {
        String[] commandSplit = taskInputByUser.split(" ");
        switch (taskInputByUser) {
        case "bye":
            return ResponseHandler.bye();
        case "list":
            return taskListManager.printList();
        default:
        }

        //Items are 0-indexed, unless otherwise stated.
        if (commandSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || commandSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
            try {
                String responseReturn = taskListManager.changeStatusOfItem(commandSplit[0],
                                                                           Integer.parseInt(commandSplit[1]) - 1);
                taskListManager.writeToFile(taskLoader);
                return responseReturn;

            } catch (Exception e) {
                throw new RyanGoslingException("Wrong format! (un)mark <number>");
            }

        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            return PatternParser.todoParser(taskInputByUser,
                                            taskListManager, taskLoader);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            return PatternParser.deadlineParser(taskInputByUser,
                                                taskListManager, taskLoader);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            return PatternParser.eventParser(taskInputByUser,
                                             taskListManager, taskLoader);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.delete))) {
            String responseReturn = taskListManager.removeIndex(Integer.parseInt(commandSplit[1]) - 1);
            taskListManager.writeToFile(taskLoader);
            return responseReturn;
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.find))) {
            return PatternParser.findParser(taskInputByUser,
                                            taskListManager);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.help))) {
            return CommandsEnum.getAllCommands();
        } else {
            throw new RyanGoslingException("I am artificially intelligent but not in a smart way. \nTry a valid "
                                                   + "command! or check them out by typing help");
        }
    }
}
