package drew.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import drew.command.Command;
import drew.exceptions.InsufficientArgumentsException;
import drew.exceptions.UnknownCommandException;
import drew.storage.TaskList;
import drew.task.Deadline;
import drew.task.Event;
import drew.task.Task;
import drew.task.Todo;


/**
 * This class handles the interpretation and execution of user input.
 */
public class Parser {
    /**
     * Checks the identity of the command.
     *
     * @param input String containing full user input.
     * @return drew.command.Command specified by user input.
     * @throws UnknownCommandException Command is not recognized.
     * @throws InsufficientArgumentsException Command is supplied with too little arguments.
     * @throws IllegalArgumentException Command is supplied with the wrong arguments.
     */
    public Command checkCommandIdentity(String input) throws UnknownCommandException,
            InsufficientArgumentsException, IllegalArgumentException {

        int inputLength = input.length();
        Command userCommand;
        if (inputLength == 3 && input.substring(0, 3).equalsIgnoreCase("bye")) {
            userCommand = Command.BYE;
        } else if (inputLength == 4 && input.substring(0, 4).equalsIgnoreCase("list")) {
            userCommand = Command.LIST;
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("mark")) {
            userCommand = Command.MARK;
        } else if (inputLength >= 6 && input.substring(0, 6).equalsIgnoreCase("unmark")) {
            userCommand = Command.UNMARK;
        } else if (inputLength >= 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            userCommand = Command.DELETE;
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("todo")) {
            userCommand = Command.TODO;
        } else if (inputLength >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline")) {
            userCommand = Command.DEADLINE;
        } else if (inputLength >= 5 && input.substring(0, 5).equalsIgnoreCase("event")) {
            userCommand = Command.EVENT;
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("find")) {
            userCommand = Command.FIND;
        } else {
            userCommand = Command.UNKNOWN;
        }

        String inputNoWhitespace = input.replaceAll("\\s", "");
        int inputNoWhitespaceLength = inputNoWhitespace.length();
        switch (userCommand) {
        case UNKNOWN:
            throw new UnknownCommandException("drew.command.Command not recognized");
        case LIST:
            if (inputNoWhitespaceLength != 4) {
                throw new IllegalArgumentException("No arguments are needed");
            }
            break;
        case TODO:
            if (inputNoWhitespaceLength == 4) {
                throw new InsufficientArgumentsException("'Todo task' cannot be empty");
            }
            break;
        case MARK:
            if (inputNoWhitespaceLength == 4) {
                throw new InsufficientArgumentsException("'Mark index' cannot be empty");
            }
            break;
        case UNMARK:
            if (inputNoWhitespaceLength == 6) {
                throw new InsufficientArgumentsException("'Unmark index' cannot be empty");
            }
            break;
        case DELETE:
            if (inputNoWhitespaceLength == 6) {
                throw new InsufficientArgumentsException("'Delete index' cannot be empty");
            }
            break;
        case DEADLINE:
            if (inputNoWhitespaceLength == 8) {
                throw new InsufficientArgumentsException("'Deadline task' cannot be empty");
            }
            break;
        case EVENT:
            if (inputNoWhitespaceLength == 5) {
                throw new InsufficientArgumentsException("'Event task' cannot be empty");
            }
            break;
        case FIND:
            if (inputNoWhitespaceLength == 4) {
                throw new InsufficientArgumentsException("'Find' String cannot be empty");
            }
            break;
        default:
            System.out.println("Shouldn't make it here.");
        }
        return userCommand;
    }

    /**
     * Handles the execution of commands
     *
     * @param list List that the command will operate upon.
     * @param command Identity of the command identified.
     * @param input String that contains the user input.
     * @return Reply from the chatbot depending on the operation performed.
     */
    public String executeCommand(TaskList list, Command command, String input) {
        ArrayList<Task> ls = list.getList();
        String reply = "";
        int listLength = ls.size();
        try {
            switch (command) {
            case LIST: {
                reply = reply + "Here are the tasks in your list:" + "\n";
                for (int i = 0; i < listLength; i++) {
                    reply = reply + Integer.toString(i + 1) + ". "
                            + ls.get(i).toStatusString() + "\n";
                }
                reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                break;
            }
            case MARK: {
                int taskIndex;
                taskIndex = Integer.parseInt(input.substring(5));
                if (taskIndex > listLength) {
                    throw new IllegalArgumentException("This task does not exist!");
                }
                ls.get(taskIndex - 1).setDone();
                reply = "Well done! I have marked this task as done:\n"
                        + ls.get(taskIndex - 1).toStatusString() + "\n";
                break;
            }
            case UNMARK: {
                int taskIndex;
                taskIndex = Integer.parseInt(input.substring(7));
                if (taskIndex > listLength) {
                    throw new IllegalArgumentException("This task does not exist!");
                }
                ls.get(taskIndex - 1).setNotDone();
                reply = "Ok. I have marked this task as not done yet:\n"
                        + ls.get(taskIndex - 1).toStatusString() + "\n";
                break;
            }
            case DELETE:
                int taskIndex;
                taskIndex = Integer.parseInt(input.substring(7));
                if (taskIndex > listLength) {
                    throw new IllegalArgumentException("This task does not exist!");
                }
                reply = "Ok. I have deleted this task :\n"
                        + ls.get(taskIndex - 1).toStatusString() + "\n";
                ls.remove(taskIndex - 1);
                listLength--;
                break;
            case TODO: {
                String todoDescription = input.substring(5);
                Todo newTask = new Todo(todoDescription);
                ls.add(newTask);
                reply = "Got it. I've added this task:\n";
                reply = reply + newTask.toStatusString() + "\n";
                listLength++;
                reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                break;
            }
            case DEADLINE: {
                int firstBackslashIndex = input.indexOf("/by");
                if (firstBackslashIndex == -1) {
                    throw new IllegalArgumentException("Incorrect input. Ensure that date begins with /by");
                }
                String deadlineDescription = input.substring(9, firstBackslashIndex);
                LocalDate deadline = LocalDate.parse(input.substring(firstBackslashIndex + 4).trim());
                Deadline newTask = new Deadline(deadlineDescription, deadline);
                ls.add(newTask);
                reply = "Got it. I've added this task:\n";
                reply = reply + newTask.toStatusString() + "\n";
                listLength++;
                reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                break;
            }
            case EVENT: {
                int firstBackslashIndex = input.indexOf("/from");
                if (firstBackslashIndex == -1) {
                    throw new IllegalArgumentException("Incorrect input. Ensure that start date begins with /from");
                }
                int secondBackslashIndex = input.indexOf("/to", firstBackslashIndex + 5);
                if (secondBackslashIndex == -1) {
                    throw new IllegalArgumentException("Incorrect input. Ensure that end date begins with /to");
                }
                String eventDescription = input.substring(6, firstBackslashIndex);
                LocalDate startDate = LocalDate.parse(
                        input.substring(firstBackslashIndex + 5, secondBackslashIndex).trim());
                LocalDate endDate = LocalDate.parse(input.substring(secondBackslashIndex + 3).trim());
                Event newTask = new Event(eventDescription, startDate, endDate);
                ls.add(newTask);
                reply = "Got it. I've added this task:\n";
                reply = reply + newTask.toStatusString() + "\n";
                listLength++;
                reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                break;
            }
            case FIND: {
                String search = input.substring(4).trim();
                reply = "Here are the matching tasks in your list:i\n";
                ArrayList<Task> matchedTasks = new ArrayList<>();
                for (int i = 0; i < listLength; i++) {
                    Task task = ls.get(i);
                    if (task.toString().contains(search)) {
                        matchedTasks.add(task);
                    }
                }
                for (int i = 0; i < matchedTasks.size(); i++) {
                    reply = reply + Integer.toString(i + 1) + ". "
                            + matchedTasks.get(i).toStatusString() + "\n";
                }
                break;
            }
            default:
                System.out.println("Shouldn't make it here.");
            }
        } catch (UnknownCommandException e) {
            reply = "That does not seem to be a valid command. Please try again.\n";
        } catch (InsufficientArgumentsException e) {
            reply = e.getMessage() + "\n";
        } catch (NumberFormatException e) {
            reply = "Please enter a valid value.\n";
        } catch (IllegalArgumentException e) {
            reply = e.getMessage() + "\n";
        } catch (DateTimeParseException e) {
            reply = "Incorrect date. Please enter date in YYYY-MM-DD format\n";
        }
        return reply;
    }
}
