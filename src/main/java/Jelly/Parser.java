package Jelly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

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

        while (!read().equals("bye")) ;
    }

    /**
     * Reads a single user command/message and parses it.
     *
     * @return initial message given by user (first word)
     */
    public String read() {

        String message = scanner.nextLine();
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
                System.out.println(taskList);
                break;

            case "mark":
                taskList.markTask(Integer.parseInt(lines[1]));
                break;

            case "unmark":
                taskList.unmarkTask(Integer.parseInt(lines[1]));
                break;

            case "todo":

                if (argument.length() == 0) {

                    ui.printMessage("(X_x) Formatting error! Task name missing");
                }
                taskList.addTodo(argument, false);
                break;

            case "deadline":

                Integer deadlineIndex = argument.indexOf("/by ");

                if (deadlineIndex.equals(-1)) { //formatting error

                    ui.printMessage("(X_x) Formatting error! /by is missing");
                    break;
                }

                String deadline = argument.substring(deadlineIndex + 3);

                if (deadline.length() == 1) {

                    ui.printMessage("(X_x) Formatting error! nothing after /by");
                    break;
                }

                deadline = deadline.substring(1);

                argument = argument.substring(0, argument.indexOf("/"));

                if (!argument.endsWith(" ")) {

                    ui.printMessage("(X_x) Formatting error! you need a space before any '/'");
                    break;
                }

                taskList.addDeadline(argument, deadline, false);

                break;

            case "event":

                Integer startIndex = argument.indexOf("/from ");

                if (startIndex.equals(-1)) {

                    ui.printMessage("(X_x) Formatting error! /from is missing");
                    break;
                }

                String timeframe = argument.substring(startIndex + 1);

                String start = timeframe.substring(4);

                Integer endIndex = start.indexOf("/to ");

                if (endIndex.equals(-1)) {

                    ui.printMessage("(X_x) Formatting error! /to is missing");
                    break;
                }

                String end = start.substring(endIndex + 3);
                start = start.substring(0, endIndex);

                if (start.length() == 1) {

                    ui.printMessage("(X_x) Formatting error! nothing after /from ");
                    break;
                }

                start = start.substring(1);

                if (!start.endsWith(" ")) {

                    ui.printMessage("(X_x) Formatting error! you need a space before any '/'");
                    break;
                }

                start = start.substring(0, start.length() - 1);

                if (end.length() == 1) {

                    ui.printMessage("(X_x) Formatting error! nothing after /to");
                    break;
                }

                end = end.substring(1);

                argument = argument.substring(0, argument.indexOf("/"));

                if (!argument.endsWith(" ")) {

                    ui.printMessage("(X_x) Formatting error! you need a space before any '/'");
                    break;
                }

                taskList.addEvent(argument, start, end, false);

                break;

            case "delete":

                if (argument.length() == 0) {

                    ui.printMessage("(X_x) Formatting error! no index received");
                    break;
                }

                taskList.deleteTask(Integer.parseInt(lines[1]));

                break;

            case "find":

                ui.printMessage("(@_@) Here are the tasks that match your keyword!");
                ui.printSearchResults(taskList, lines[1]);

                break;

            default:

                ui.printMessage("(O_o) Huh? What does that even mean?");
        }

        ui.printLine();

        return command;
    }
}
