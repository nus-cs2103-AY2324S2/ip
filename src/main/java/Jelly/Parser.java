package jelly;


import java.util.Scanner;


/**
 * deals with making sense of the user command
 */
public class Parser {

    private Scanner scanner;
    private TaskList taskList;
    private Ui ui;

    /**
     * @param taskList TaskList to store parsed data
     * @param ui       UI to interact with user
     */
    public Parser(TaskList taskList, Ui ui) {

        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reads each input given by user until user sends a terminating message
     */
    public void loop() {


    }

    /**
     * Reads a single user command/message and parses it.
     *
     * @return initial message given by user (first word)
     */
    public String read(String message) {

        String[] lines = message.split("\\s+");
        String command = lines[0];

        String argument = "";

        if (lines.length > 1) {
            argument = message.substring(command.length() + 1);
        }

        ui.printLine();

        switch (command) {

        case "bye":

            return command;

        case "list":

            return taskList.toString();

        case "mark":

            return taskList.markTask(Integer.parseInt(lines[1]));

        case "unmark":

            return taskList.unmarkTask(Integer.parseInt(lines[1]));

        case "todo":

            if (argument.length() == 0) {

                return "(X_x) Formatting error! Task name missing";
            }

            return taskList.addTodo(argument, false);

        case "deadline":

            Integer deadlineIndex = argument.indexOf("/by ");

            if (deadlineIndex.equals(-1)) { //formatting error

                return "(X_x) Formatting error! /by is missing";
            }

            String deadline = argument.substring(deadlineIndex + 3);

            if (deadline.length() == 1) {

                return "(X_x) Formatting error! nothing after /by";
            }

            deadline = deadline.substring(1);

            argument = argument.substring(0, argument.indexOf("/"));

            if (!argument.endsWith(" ")) {

                return "(X_x) Formatting error! you need a space before any '/'";
            }

            return taskList.addDeadline(argument, deadline, false);

        case "event":

            Integer startIndex = argument.indexOf("/from ");

            if (startIndex.equals(-1)) {

                return "(X_x) Formatting error! /from is missing";
            }

            String timeframe = argument.substring(startIndex + 1);

            String start = timeframe.substring(4);

            Integer endIndex = start.indexOf("/to ");

            if (endIndex.equals(-1)) {

                return "(X_x) Formatting error! /to is missing";
            }

            String end = start.substring(endIndex + 3);
            start = start.substring(0, endIndex);

            if (start.length() == 1) {

                return "(X_x) Formatting error! nothing after /from ";
            }

            start = start.substring(1);

            if (!start.endsWith(" ")) {

                return "(X_x) Formatting error! you need a space before any '/'";
            }

            start = start.substring(0, start.length() - 1);

            if (end.length() == 1) {

                return "(X_x) Formatting error! nothing after /to";
            }

            end = end.substring(1);

            argument = argument.substring(0, argument.indexOf("/"));

            if (!argument.endsWith(" ")) {

                return "(X_x) Formatting error! you need a space before any '/'";
            }

            return taskList.addEvent(argument, start, end, false);

        case "delete":

            if (argument.length() == 0) {

                return "(X_x) Formatting error! no index received";
            }

            return taskList.deleteTask(Integer.parseInt(lines[1]));

        case "find":

            return "(@_@) Here are the tasks that match your keyword!\n" + taskList.find(lines[1]);

        default:

            return ChatGptApi.chatGptResponse(message);
        }

    }
}
