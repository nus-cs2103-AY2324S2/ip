package duke.duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.Objects;

import duke.exceptions.DukeEmptyArgumentException;
import duke.exceptions.DukeErroneousArgumentException;
import duke.exceptions.DukeInvalidInputException;
import duke.exceptions.DukeWrongDateOrderException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Skibidi;


/**
 * This class helps the chatbot to parse inputs in order to execute the reactions that the user wants.
 *
 * @author Lim Zi Jia
 */
public class Parser {
    private Duke duke = new Duke();
    private Storage storage;
    private TaskList taskList;

    public Parser() {}

    enum Command {
        BYE, LIST, SAVE, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, UNKNOWN
    }

    private Command parseUserMsg(String input) {
        switch (input) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "save":
            return Command.SAVE;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        default:
            return Command.UNKNOWN;
        }
    }

    /**
     * The main parsing logic. Takes the input and calls the relevant functions for the desired outputs.
     *
     * @param input The string input that the user has passed into the program.
     * @return A TaskList that has been altered if there is a need to. The value is null if the command is 'bye'
     */
    public String parse(String input) {
        String[] inputParsed = input.split(" ");
        Command command = parseUserMsg(inputParsed[0]);
        taskList = duke.getTaskList();
        storage = duke.getStorage();

        String reply;
        assert command != null : "You are missing the command";

        try {
            checkValidFormat(inputParsed, command); // Throws various exceptions corresponding to foreseeable errors.
            switch (command) {
            case BYE:
                reply = Skibidi.BYE;
                break;
            case LIST:
                reply = taskList.printList();
                break;
            case SAVE:
                reply = storage.save(taskList);
                break;
            case MARK:
                reply = taskList.mark(inputParsed); // mark(task number)
                break;
            case UNMARK:
                reply = taskList.unmark(inputParsed); // unmark(task number)
                break;
            case DELETE:
                reply = taskList.delete(inputParsed); // delete(task number)
                break;
            case FIND:
                reply = taskList.find(inputParsed[1]); // find(task name)
                break;
            case UNKNOWN:
                reply = "Unknown command\n";
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                reply = addTask(inputParsed, command);
                break;
            default:
                reply = "Something has gone horribly wrong with your input!";
            }
        } catch (IOException e) {
            reply = "I/O Exception";
        } catch (DukeInvalidInputException e) {
            reply = "This is not a valid input!!!";
        } catch (DukeEmptyArgumentException e) {
            reply = "There is an argument that is empty!!!";
        } catch (DukeErroneousArgumentException e) {
            reply = "There is an argument in the wrong format!!!";
        } catch (DateTimeException e) {
            reply = "The format of your date is wrong! Make sure it is of the form 'yyyy-MM-dd'.\n";
            reply += "More specifically: \n" + e.getMessage();
        } catch (DukeWrongDateOrderException e) {
            reply = "The end date should be after the start date";
        }
        return reply;
    }

    /**
     * A helper function that is dedicated to adding tasks.
     *
     * @param inputs Array of Strings of inputs to be parsed.
     * @param command The command
     * @return The edited task list.
     */
    public String addTask(String[] inputs, Command command) {
        String s;

        switch (command) {
        case TODO:
            taskList.add(new Todo(inputs[1])); // Todo(name)
            break;
        case DEADLINE:
            taskList.add(new Deadline(inputs[1], inputs[3])); // Deadline(name, by)
            break;
        case EVENT:
            Event e = new Event(inputs[1], inputs[3], inputs[5]); // Event(name, from, to)
            if (!e.isCorrectOrder()) {
                throw new DukeWrongDateOrderException();
            }
            taskList.add(e);
            break;
        default:
            return "Some catastrophic error has occurred when adding tasks!";
        }

        s = "Got it added this task:\n  " + taskList.get(taskList.size() - 1);
        s += String.format("Now you have %d tasks in the list.", taskList.size());

        return s;
    }

    private void checkValidFormat(String[] inputs, Command command) {
        switch (command) {
        case MARK: // Command: mark listOfTaskNumbers
        case UNMARK: // Command: unmark listOfTaskNumbers
        case DELETE:// Command: delete listOfTaskNumbers
            if (inputs.length < 2) {
                throw new DukeEmptyArgumentException();
            }
            break;
        case FIND: // Command: find taskName
        case TODO: // Command: todo taskName
            if (inputs.length < 2) {
                throw new DukeEmptyArgumentException();
            } else if (inputs.length > 2) {
                throw new DukeErroneousArgumentException();
            }
            break;
        case DEADLINE: // Command: deadline taskName /by when
            if (inputs.length < 4) {
                throw new DukeEmptyArgumentException();
            } else if (!inputs[2].equals("/by") || inputs.length > 4) {
                throw new DukeErroneousArgumentException();
            }
            break;
        case EVENT: // Command: event taskName /from start /to end
            if (inputs.length < 6) {
                throw new DukeEmptyArgumentException();
            } else if (inputs.length > 6 || !(inputs[2].equals("/from") && inputs[4].equals("/to"))) {
                throw new DukeErroneousArgumentException();
            }
            break;
        default:
        }
    }

}
