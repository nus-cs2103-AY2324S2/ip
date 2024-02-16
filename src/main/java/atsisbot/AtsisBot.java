package atsisbot;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import atsisbot.task.TaskList;

/**
 * The atsisbot.AtsisBot class represents a chatbot that can perform various
 * tasks. It allows users
 * to interact with the bot by entering commands and receiving responses. The
 * bot can manage a list
 * of tasks, including adding, marking, and deleting tasks.
 */
public class AtsisBot {
    private static TaskList taskList;
    private static Storage storage;

    /**
     * Represents the AtsisBot chatbot.
     * AtsisBot is responsible for managing tasks and interacting with the user.
     */
    public AtsisBot() {
        storage = new Storage("data/tasks.txt");
        taskList = storage.loadData();
    }

    /**
     * The main method is the entry point of the atsisbot.AtsisBot program. It
     * prompts the user for
     * input and executes corresponding commands based on the input. The program
     * continues to accept
     * input until the user enters "bye".
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        processRequest();
        Ui.printEndingMessage();
        Ui.closeScanner();
    }

    /**
     * Processes user requests until the user enters "bye".
     *
     * This method continuously reads user input, parses the input into a command and arguments,
     * executes the command, saves the updated task list to storage, and then reads the next user input.
     * This loop continues until the user input is "bye".
     */
    private static void processRequest() {
        String input = Ui.readCommand();
        assert input != null : "Input cannot be null";

        while (!input.equals("bye")) {
            CommandEnum command = Parser.parseCommand(input);
            String param = Parser.parseArgs(input);
            getResponse(command, param);
            storage.saveList(taskList);
            input = Ui.readCommand();
        }
    }

    /**
     * Executes the appropriate command based on the given commandEnum and args.
     *
     * @param commandEnum the command enum representing the type of command to
     *                    execute
     * @param args        the arguments for the command
     */
    public static String getResponse(CommandEnum commandEnum, String args) {
        assert commandEnum != null : "CommandEnum cannot be null";
        assert args != null : "Args cannot be null";

        ByteArrayOutputStream outputs = new ByteArrayOutputStream();
        PrintStream previous = System.out;
        System.setOut(new PrintStream(outputs));
        processCommand(commandEnum, args);
        System.out.flush();
        System.setOut(previous);
        return outputs.toString().trim();
    }

    /**
     * Processes the given command and executes the corresponding action.
     *
     * @param commandEnum the command enum representing the type of command to execute
     * @param args        the arguments for the command
     */
    private static void processCommand(CommandEnum commandEnum, String args) {
        try {
            switch (commandEnum) {
            case LIST:
                Command.list(taskList);
                break;
            case MARK:
                Command.mark(args, taskList);
                break;
            case UNMARK:
                Command.unmark(args, taskList);
                break;
            case DELETE:
                Command.delete(args, taskList);
                break;
            case TODO:
                Command.todo(args, taskList);
                break;
            case DEADLINE:
                Command.deadline(args, taskList);
                break;
            case EVENT:
                Command.event(args, taskList);
                break;
            case FIND:
                Command.find(args, taskList);
                break;
            case SET_PRIORITY:
                Command.setPriority(args, taskList);
                break;
            default:
                Ui.printUnknownCommandMessage();
            }
        } catch (NumberFormatException e) {
            Ui.printInvalidTaskNumberMessage();
        }
    }
}
