package duke;

import duke.exceptions.InvalidInstructionException;
import duke.exceptions.MissingTaskToMarkException;
import duke.exceptions.MissingToDoNameException;
import duke.parsers.DateTimeParser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import javafx.util.Pair;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

import java.io.IOException;
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
                return parseToDo(tasksList, input);
            } else if (input.toLowerCase().startsWith("deadline")) {
                return parseDeadline(tasksList, input);

            } else if (input.toLowerCase().startsWith("event")) {
                return parseEvent(tasksList, input);

            } else if (input.toLowerCase().startsWith("unmark")) {
                return parseUnmark(tasksList, input);

            } else if (input.toLowerCase().startsWith("mark")) {
                return parseMark(tasksList, input);

            } else if (input.toLowerCase().startsWith("delete")) {
                return parseDelete(tasksList, input);
                //task name can partially contain keyword
            } else if (input.toLowerCase().startsWith("find")) {
                return parseFind(tasksList, input);

            } else {
                output += ("Try entering a valid instruction! Eg. 'Todo Chores' or 'Mark 2'\n");
            }

        } else {
            output = "Bye! Hope to see you again soon!";
            Storage storage = new Storage(tasksList);
            try {
                storage.store();
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return new Pair<TaskList, String>(tasksList, output);
    }

    public Pair<TaskList, String> parseToDo(TaskList tasksList, String input) throws InvalidInstructionException {
        String output = "";
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
        return new Pair<TaskList, String>(tasksList, output);
    }

    public Pair<TaskList, String> parseDeadline(TaskList tasksList, String input) throws InvalidInstructionException {
        String output = "";
        int endChar = input.indexOf("/");
        int startChar = 9;
        String name = input.substring(9, endChar);
        String deadline = input.substring(endChar + 4);
        LocalDate d = DateTimeParser.stringToDT(deadline);
        String response = tasksList.add(new Deadline(name, d, false, "D"));
        output += this.line;
        output += ((response) + "\n");
        output += this.line;
        return new Pair<TaskList, String>(tasksList, output);
    }

    public Pair<TaskList, String> parseEvent(TaskList tasksList, String input) throws InvalidInstructionException {
        String output = "";
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
        output += ((response) + "\n");
        output += this.line;
        return new Pair<TaskList, String>(tasksList, output);
    }

    public Pair<TaskList, String> parseUnmark(TaskList tasksList, String input) {
        String output = "";
        assert (input.split(" ").length != 1) : "Please provide a task to unmark :)";

        int index = Integer.parseInt(input.substring(7));
        String response = tasksList.unmark(index);
        output += (response);
        return new Pair<TaskList, String>(tasksList, output);
    }


    public Pair<TaskList, String> parseMark(TaskList tasksList, String input) throws MissingTaskToMarkException {
        String output = "";
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
        return new Pair<TaskList, String>(tasksList, output);
    }

    public Pair<TaskList, String> parseDelete(TaskList tasksList, String input) throws MissingTaskToMarkException {
        String output = "";
        int index = Integer.parseInt(input.substring(7));
        String response = tasksList.delete(index);
        output += (response);
        return new Pair<TaskList, String>(tasksList, output);
    }

    public Pair<TaskList, String> parseFind(TaskList tasksList, String input) throws MissingTaskToMarkException {
        String output = "";
        String keyword = input.split(" ")[1];
        //TaskList temp = new TaskList();
        TaskList temp = new TaskList(tasksList.getTasksList().stream()
                .filter(t -> t.getTaskName().contains(keyword))
                .collect(Collectors
                        .toCollection(ArrayList::new)));
        output += this.line;
        output += "Here are the matching tasks in your list:\n";
        output += temp.toString();
        output += ("\n" + this.line);
        return new Pair<TaskList, String>(tasksList, output);
    }
}
