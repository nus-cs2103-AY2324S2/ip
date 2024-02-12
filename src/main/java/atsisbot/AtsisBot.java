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
        String input = Ui.readCommand();

        while (!input.equals("bye")) {
            CommandEnum command = Parser.parseCommand(input);
            String param = Parser.parseArgs(input);
            getResponse(command, param);
            storage.saveList(taskList);
            input = Ui.readCommand();
        }

        Ui.printEndingMessage();
        Ui.closeScanner();
    }

    /**
     * Executes the appropriate command based on the given commandEnum and args.
     *
     * @param commandEnum the command enum representing the type of command to
     *                    execute
     * @param args        the arguments for the command
     */
    public static String getResponse(CommandEnum commandEnum, String args) {
        ByteArrayOutputStream outputs = new ByteArrayOutputStream();
        PrintStream previous = System.out;
        System.setOut(new PrintStream(outputs));
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
            default:
                Ui.printUnknownCommandMessage();
            }
        } catch (NumberFormatException e) {
            Ui.printInvalidTaskNumberMessage();
        }

        System.out.flush();
        System.setOut(previous);
        return outputs.toString().trim();
    }
}
