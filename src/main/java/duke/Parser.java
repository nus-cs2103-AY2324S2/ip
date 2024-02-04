package duke;

import duke.Exceptions.InvalidInstructionException;
import duke.Exceptions.MissingTaskToMarkException;
import duke.Exceptions.MissingToDoNameException;
import duke.Parsers.DateTimeParser;
import duke.Tasks.*;
import javafx.util.Pair;

import java.time.LocalDate;

/**
 * Class that takes in the user input and executes the command, returning a response string to be printed
 * and the modified TaskList.
 */
public class Parser {
    //String response;
    String line;
    public Parser() {
        //this.response = "";
        this.line = "____________________________________________________________\n";
    }

    /**
     *
     * @param tasksList TaskList to be modified depending on user input
     * @param input User input to execute instructions on TaskList
     * @return Returns a String response and modified TaskList
     * @throws InvalidInstructionException if instruction is not formatted properly or has logical issues.
     */
    public Pair<TaskList, String> parse(TaskList tasksList, String input) throws InvalidInstructionException {
        String output = "";
        if (!input.toLowerCase().equals("bye")) {

            if (input.equals("list")) {
                return new Pair<TaskList, String>(tasksList, tasksList.toString());

            } else if (input.toLowerCase().startsWith("todo")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new MissingToDoNameException("Please provide the description of the todo task :) Eg. 'Todo Chores'");
                    } else {
                        String name = input.substring(5);
                        String response = tasksList.add(new ToDo(name, false, "T"));
                        output += this.line;
                        output += (response + "\n");
                        output += this.line;
                    }
                } catch (MissingToDoNameException err) {
                    output += this.line;
                    output += (err.getMessage());
                    output += this.line;
                }


            } else if (input.toLowerCase().startsWith("deadline")) {
                int endChar = input.indexOf("/");
                int startChar = 9;
                String name = input.substring(9, endChar);
                String deadline = input.substring(endChar + 4);
                LocalDate d = DateTimeParser.stringToDT(deadline);
                String response = tasksList.add(new Deadline(name, d, false, "D"));
                output += this.line;
                output += (response);
                output += this.line;

            } else if (input.toLowerCase().startsWith("event")) {
                int endChar = input.indexOf("/");
                int endChar2 = input.indexOf("/", endChar + 1);
                int startChar = 6;
                String name = input.substring(6, endChar);
                String startTime = input.substring(endChar + 5, endChar2);
                String endTime = input.substring(endChar2 + 3);
                LocalDate start = DateTimeParser.stringToDT(startTime);
                LocalDate end = DateTimeParser.stringToDT(endTime);
                String response = tasksList.add(new Event(name, start, end, false, "E"));
                output += this.line;
                output += (response);
                output += this.line;

            } else if (input.toLowerCase().startsWith("unmark")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new MissingTaskToMarkException("Please provide a task to unmark :)");

                    } else {
                        int index = Integer.parseInt(input.substring(7));
                        String response = tasksList.unmark(index);
                        output += (response);
                    }
                } catch (MissingTaskToMarkException err) {
                    output += (err.getMessage());
                }

            } else if (input.toLowerCase().startsWith("mark")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new MissingTaskToMarkException("Please provide a task to mark :)");
                    } else {
                        int index = Integer.parseInt(input.substring(5));
                        String response = tasksList.mark(index);
                        output += (response);
                    }
                } catch (MissingTaskToMarkException err) {
                    output += (err.getMessage());
                }


            } else if (input.toLowerCase().startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7));
                String response = tasksList.delete(index);
                output += (response);

            } else if (input.toLowerCase().startsWith("find")) {
                String keyword = input.split(" ")[1];
                TaskList temp = new TaskList();
                for (Task t : tasksList.getTasksList()) {
                    if (t.getTaskName().contains(keyword)) {
                        temp.add(t);
                    }

                }
                output += this.line;
                output += "Here are the matching tasks in your list:\n";
                output += temp.toString();
                output += ("\n" + this.line);

            } else {
                output += ("Try entering a valid instruction! Eg. 'Todo Chores' or 'Mark 2'\n");
            }

        } else {
            output = "";
        }


        return new Pair<TaskList, String>(tasksList, output);
    }
}
