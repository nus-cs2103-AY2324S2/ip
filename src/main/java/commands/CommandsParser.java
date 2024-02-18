package commands;

import exceptions.RyanGoslingException;
import utilities.ResponseHandler;
import utilities.Storage;
import utilities.TaskList;

/**
 * Processes user commands related to task management.
 */
public class CommandsParser {

    // Manages the encapsulated list of tasks
    private final TaskList taskListManager;
    private String filePath;
    // Handles the loading and saving of tasks to a text file
    private final Storage taskLoader;

    /**
     * Constructor for CommandsParser.
     *
     * @param taskListManager The task list manager.
     * @param filePath        The file path for task storage.
     * @param taskLoader      The task loader for reading and writing tasks.
     */
    public CommandsParser(TaskList taskListManager, String filePath, Storage taskLoader) {
        this.taskListManager = taskListManager;
        this.filePath = filePath;
        this.taskLoader = taskLoader;
    }

    /**
     * Parses user commands and performs corresponding actions.
     *
     * @param taskInputByUser User-inputted command.
     * @return Response based on the executed command.
     * @throws RyanGoslingException If there is an issue with command parsing.
     */
    public String parseCommands(String taskInputByUser) throws RyanGoslingException {
        PatternParser.inputSpacesChecker(taskInputByUser);
        String[] commandSplit = taskInputByUser.split(" ");
        switch (taskInputByUser) {
        case "bye":
            return ResponseHandler.bye();
        case "list":
            return taskListManager.printList();
        default:
        }

        // Items are 0-indexed, unless otherwise stated.
        if (commandSplit[0].equals(String.valueOf(CommandsEnum.mark))
                || commandSplit[0].equals(String.valueOf(CommandsEnum.unmark))) {
            String responseReturn = taskListManager.changeStatusOfItem(commandSplit);
            taskListManager.writeToFile(taskLoader);
            return responseReturn;
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.todo))) {
            return PatternParser.todoParser(taskInputByUser, taskListManager, taskLoader);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.deadline))) {
            return PatternParser.deadlineParser(taskInputByUser, taskListManager, taskLoader);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.event))) {
            return PatternParser.eventParser(taskInputByUser, taskListManager, taskLoader);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.delete))) {

            String responseReturn = taskListManager.removeIndex(commandSplit);
            taskListManager.writeToFile(taskLoader);
            return responseReturn;
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.find))) {
            return PatternParser.findParser(taskInputByUser, taskListManager);
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.help))) {
            return CommandsEnum.getAllCommands();
        } else if (commandSplit[0].equals(String.valueOf(CommandsEnum.update))) {
            String responseReturn = PatternParser.updateTaskParser(taskListManager, taskInputByUser, taskLoader);
            taskListManager.writeToFile(taskLoader);
            return responseReturn;
        } else {
            throw new RyanGoslingException("I am artificially intelligent but not in a smart way. \nTry a valid "
                                                   + "command! or check them out by typing help");
        }
    }
}
