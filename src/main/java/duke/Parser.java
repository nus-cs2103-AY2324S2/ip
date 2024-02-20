package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Parser class is responsible for parsing user input commands and executing corresponding actions.
 * It interacts with the TaskList, Ui, and Storage classes to manage tasks and data.
 */
public class Parser {
    private Scanner scanner;
    private TaskList tasklist;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Parser object with the given TaskList and Storage.
     *
     * @param t The TaskList object to interact with.
     * @param st The Storage object to interact with.
     */
    public Parser(TaskList t, Storage st) {
        tasklist = t;
        storage = st;
    }

    /**
     * Reads and processes user input commands.
     *
     * @param userInput The user input command to be processed.
     * @return A response message based on the processed command.
     */
    public String read(String userInput) {
        Ui ui = new Ui();
        String userInputLowercase = userInput.toLowerCase();

        if (checkFeeding(userInputLowercase)) {
            return ui.happy();
        } else if (checkIfBaseCommand(userInputLowercase)) {
            String reply = handleBaseCommand(userInput.split(" "));
            storage.writeToFile(tasklist);
            return reply;
        } else if (checkIfFind(userInputLowercase)) {
            String reply = handleFind(userInput.split(" "));
            storage.writeToFile(tasklist);
            return reply;
        } else if (checkIfLeave(userInputLowercase)) {
            return ui.goodbye();
        } else if (checkIfList(userInputLowercase)) {
            return handleList(tasklist);
        } else if (checkIfTodo(userInputLowercase)) {
            String reply = handleTodo(userInput, tasklist);
            storage.writeToFile(tasklist);
            return reply;
        } else if (checkIfEvent(userInputLowercase)) {
            String reply = handleEvent(userInput, tasklist);
            storage.writeToFile(tasklist);
            return reply;
        } else if (checkIfDeadline(userInputLowercase)) {
            String reply = handleDeadline(userInput, tasklist);
            storage.writeToFile(tasklist);
            return reply;
        } else {
            return ui.instructionMessage();
        }
    }

    /**
     * Handles the user input command for adding an event task.
     *
     * @param s The user input command string for adding an event task.
     * @param t The task list to which the event task will be added.
     * @return A message indicating the status of the operation.
     */
    public String handleEvent(String s, TaskList t) {
        String eventName = "";
        String[] temp = s.split(" ");
        if (temp.length == 1 || temp[1].startsWith("/from")) {
            return "Event cannot be blank";
        }
        for (int a = 1; a < temp.length; a++) {
            if (temp[a].startsWith("/from")) {
                break;
            }
            eventName = eventName.concat(temp[a]);
            eventName = eventName.concat(" ");
        }
        try {
            String[] findPeriod = s.split(" /from ");
            String start = findPeriod[1].split(" /to ")[0];
            String end = findPeriod[1].split(" /to ")[1];
            if (!canBeHandled(start) || !canBeHandled(end)) {
                return "Please enter a event with the format event eventName /from mm/dd/yyyy /to mm/dd/yyyy!";
            }
            Task ne = new Event(eventName, dateConvert(start), dateConvert(end));
            t.add(ne);
            return "Task added! You now have " + t.length() + " tasks to attend to.";
        } catch (ArrayIndexOutOfBoundsException b) {
            return "Please enter a event with the format event eventName /from mm/dd/yyyy /to mm/dd/yyyy!";
        }
    }

    /**
     * Handles the user input command for finding tasks.
     *
     * @param commandsplit An array containing the command split into parts.
     * @return A message containing the found tasks or an error message.
     */
    public String handleFind(String[] commandsplit) {
        try {
            String findTarget = commandsplit[1].toLowerCase();
            String returntasks = "";
            for (int i = 0; i < tasklist.length(); i++) {
                if (tasklist.getTask(i).getDescription().toLowerCase().contains(findTarget)) {
                    returntasks = returntasks.concat(tasklist.getTask(i).toString());
                    returntasks = returntasks.concat("\n");
                }
            }
            if (returntasks.isEmpty()) {
                return "It doesn't exist!";
            }
            return returntasks;
        } catch (Error e) {
            return "[angry quacking] I can only find words!";
        }
    }

    /**
     * Checks if a string can be converted to a LocalDate object.
     *
     * @param s The string to be checked.
     * @return True if the string can be converted to a LocalDate, otherwise false.
     */
    public boolean canBeHandled(String s) {
        return (dateConvert(s) != null);
    }


    /**
     * Converts a string to a LocalDate object.
     *
     * @param s The string to be converted.
     * @return The corresponding LocalDate object, or null if conversion fails.
     */
    public LocalDate dateConvert(String s) {
        assert s.length() > 5 : "Invalid length";
        String[] patterns = {"MM/dd/yyyy", "M/dd/yyyy", "MM/d/yyyy",
            "M/d/yyyy", "MM-dd-yyyy", "M-dd-yyyy", "MM-d-yyyy", "M-d-yyyy"};
        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return null;
    }

    /**
     * Handles the user input command for adding a deadline task.
     * Parses the input string, creates a deadline task, and adds it to the task list.
     *
     * @param s The user input command string for adding a deadline task.
     * @param t The task list to which the deadline task will be added.
     */
    public String handleDeadline(String s, TaskList t) {
        String deadlineName = "";
        String[] temp = s.split(" ");
        if (temp.length == 1 || temp[1].startsWith("/by")) {
            return "Deadline cannot be blank";
        }
        //create the deadline name
        for (int a = 1; a < temp.length; a++) {
            if (temp[a].startsWith("/by")) {
                break;
            }
            deadlineName = deadlineName.concat(temp[a]);
            deadlineName = deadlineName.concat(" ");
        }

        try {
            String[] findDeadline = s.split(" /by ");
            String deadline = findDeadline[1];
            if (!canBeHandled(deadline)) {
                return "Please enter a deadline with the format deadline deadlineName /by mm/dd/yyyy!";

            }
            Task nd = new Deadline(deadlineName, dateConvert(deadline));
            t.add(nd);
            return "Task added! You now have " + t.length() + " tasks to attend to.";

        } catch (ArrayIndexOutOfBoundsException b) {
            return "Please enter a deadline with the format deadline deadlineName /by mm/dd/yyyy!";
        }
    }

    /**
     * Handles the user input command for adding a todo task.
     * Parses the input string, creates a todo task, and adds it to the task list.
     *
     * @param s The user input command string for adding a todo task.
     * @param t The task list to which the todo task will be added.
     */
    public String handleTodo(String s, TaskList t) {
        String todoName = "";
        String[] temp = s.split(" ");
        if (temp.length == 1) {
            return "Todo cannot be blank";
        }
        for (int a = 1; a < temp.length; a++) {
            todoName = todoName.concat(temp[a]);
            todoName = todoName.concat(" ");
        }
        Task nt = new ToDo(todoName);
        t.add(nt);
        return "Task added! You now have " + t.length() + " tasks to attend to.";
    }

    /**
     * Checks if the input string represents a todo task command.
     *
     * @param s The input string to be checked.
     * @return True if the input string starts with "todo ", otherwise false.
     */
    public boolean checkIfTodo(String s) {
        return s.startsWith("todo ");
    }

    /**
     * Checks if the input string represents an event task command.
     *
     * @param s The input string to be checked.
     * @return True if the input string starts with "event ", otherwise false.
     */
    public boolean checkIfEvent(String s) {
        return s.startsWith("event ");
    }

    /**
     * Checks if the input string represents a deadline task command.
     *
     * @param s The input string to be checked.
     * @return True if the input string starts with "deadline ", otherwise false.
     */
    public boolean checkIfDeadline(String s) {
        return s.startsWith("deadline ");
    }

    /**
     * Handles the user input command for listing tasks.
     * Prints the list of tasks if available, or a message indicating no tasks.
     *
     * @param t The task list to be listed.
     */
    public String handleList(TaskList t) {
        if (t.length() == 0) {
            return "You're a lazy duck, get back on the grind!";
        } else {
            return t.iterateout();
        }
    }

    /**
     * Checks if the input string represents a "list" command.
     *
     * @param f The input string to be checked.
     * @return True if the input string is "list", otherwise false.
     */
    public boolean checkIfList(String f) {
        return f.equals("list");
    }

    /**
     * Checks if the input string represents a "feed bread to bearducky" command.
     *
     * @param f The input string to be checked.
     * @return True if the input string is "feed bread to bearducky", otherwise false.
     */
    public boolean checkFeeding(String f) {
        return f.equals("feed bread to bearducky");
    }

    /**
     * Checks if the input string represents a "bye" command.
     *
     * @param f The input string to be checked.
     * @return True if the input string is "bye", otherwise false.
     */
    public boolean checkIfLeave(String f) {
        return f.equals("bye");
    }

    /**
     * Checks if the input string represents a base command for marking, unmarking, or deleting tasks.
     *
     * @param f The input string to be checked.
     * @return True if the input string starts with "mark ", "unmark ", or "delete ", otherwise false.
     */
    public boolean checkIfBaseCommand(String f) {
        return (f.startsWith("mark ") || f.startsWith("unmark ") || f.startsWith("delete "));
    }

    public boolean checkIfFind(String f) {
        return (f.startsWith("find "));
    }

    /**
     * Handles the base commands for marking, unmarking, or deleting tasks.
     * Parses the input command, performs the corresponding task operation, and handles exceptions.
     *
     * @param commandSplit An array containing the command split into parts.
     */

    public String handleBaseCommand(String[] commandSplit) {
        String firstWord = commandSplit[0].toLowerCase();
        assert firstWord.length() < 7 : "Invalid word/I can only mark numbers!";
        try {
            int num = Integer.parseInt(commandSplit[1]);
            if ("mark".equals(firstWord)) {
                tasklist.mark(num - 1);
            } else if ("unmark".equals(firstWord)) {
                tasklist.unmark(num - 1);
            } else if ("delete".equals(firstWord)) {
                tasklist.delete(num - 1);
            } else {
                return "Done!";
            }
        } catch (NumberFormatException e) {
            return "[angry quacking] I can only mark/delete numbers!";
        } catch (IndexOutOfBoundsException a) {
            return "[exasperated quacking] You're not that busy - numbers from 1 to "
                + tasklist.length()
                    + " only, please.";
        }
        return "Done!";
    }
}
