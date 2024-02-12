package duchess.util;

import duchess.tasks.Deadline;
import duchess.tasks.Event;
import duchess.tasks.Task;
import duchess.tasks.ToDo;

import java.io.IOException;

public class Parser {
    private static final String LINE_BREAK = "\n------------------------------------------";

    /**
     * Creates new Parser object
     */
    public Parser() {}

    /**
     * Returns action specified in input.
     * If no valid action identified, error is thrown.
     *
     * @param input input by user.
     * @return String response.
     * @throws DuchessException If no valid action identified.
     */
    public String getAction(String input, TaskList tasks, Ui ui, Storage storage) throws DuchessException {
        String response = "";
        assert storage.checkFileIsOpen();   // Assert that "bye" command has not been run and .txt file is still open
        try {
            if (input.substring(0, 3).toUpperCase().contains("BYE")) {
                try {
                    storage.save(tasks.retrieveTaskList());

                    return ui.printExit();
                } catch (IOException e) {
                    e.printStackTrace();

                    return "\nAn error occurred.";
                }
            } else if (input.substring(0, 4).toUpperCase().contains("MENU")) {
                return ui.printMenu();
            } else if (input.substring(0, 4).toUpperCase().contains("FIND")) {
                String keyword = getKeyword(input);

                return tasks.findTasks(keyword);
            } else if (input.substring(0, 4).equalsIgnoreCase("list")) {
                return tasks.printTaskList();
            } else if (input.substring(0, 4).toUpperCase().contains("MARK")) {
                try {
                    int itemIndex = Character.getNumericValue(input.charAt(5));
                    return tasks.markTask(itemIndex);
                } catch (IndexOutOfBoundsException e) {
                    response += LINE_BREAK;
                    response += "\nOOPS!!! Please specify a valid task number.";
                    response += LINE_BREAK;
                    e.printStackTrace(System.err);

                    return response;
                }
            } else if (input.substring(0, 4).toUpperCase().contains("TODO")) {
                try {
                    String[] toDoDetails = getToDoDetails(input);
                    Task t = new ToDo(toDoDetails[1]);

                    return tasks.createTask(t);
                } catch (StringIndexOutOfBoundsException e) {
                    return e.getMessage();
                } catch (ArrayIndexOutOfBoundsException e) {
                    response += LINE_BREAK;
                    response += "\nIncorrect input.";
                    response += "\nTo create a 'To Do': \ntodo <description>";
                    response += LINE_BREAK;
                    e.printStackTrace(System.err);

                    return response;
                }
            } else if (input.substring(0, 5).toUpperCase().contains("TASKS")) {
                return ui.printTasksMenu();
            } else if (input.substring(0, 5).toUpperCase().contains("EVENT")) {
                try {
                    String[] eventDetails = getEventDetails(input);
                    Task t = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);

                    return tasks.createTask(t);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response += LINE_BREAK;
                    response += "\nIncorrect input. ";
                    response += "\nTo create an 'Event': \nevent <description> /from <from> /to <to>";
                    response += LINE_BREAK;
                    e.printStackTrace(System.err);

                    return response;
                }
            } else if (input.substring(0, 6).toUpperCase().contains("DELETE")) {
                try {
                    int itemIndex = Character.getNumericValue(input.charAt(7));

                    return tasks.deleteTask(itemIndex);
                } catch (IndexOutOfBoundsException e) {
                    response += LINE_BREAK;
                    response += "\nPlease specify a valid task number.";
                    response += LINE_BREAK;
                    e.printStackTrace(System.err);

                    return response;
                }
            } else if (input.substring(0, 6).toUpperCase().contains("UNMARK")) {
                try {
                    int itemIndex = Character.getNumericValue(input.charAt(7));

                    return tasks.unmarkTask(itemIndex);
                } catch (IndexOutOfBoundsException e) {
                    response += LINE_BREAK;
                    response += "\nOOPS!!! Please specify a valid task number.";
                    response += LINE_BREAK;
                    e.printStackTrace(System.err);

                    return response;
                }
            } else if (input.substring(0, 8).toUpperCase().contains("DEADLINE")) {
                try {
                    String[] deadlineDetails = getDeadlineDetails(input);
                    Task t = new Deadline(deadlineDetails[0], deadlineDetails[1]);

                    return tasks.createTask(t);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response += LINE_BREAK;
                    response += "\nIncorrect input. ";
                    response += "\nTo create a 'Deadline': \ndeadline <description> /by <by>";
                    response += LINE_BREAK;
                    e.printStackTrace(System.err);

                    return response;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            response += LINE_BREAK;
            response += "\nOOPS!!! I'm sorry, but I don't know what that means.";
            response += "\nEnter 'MENU' to view available options.";
            response += "\n" + LINE_BREAK;

            throw new DuchessException(response);
        }

        return response;
    }

    /**
     * Returns String[].
     * Retrieve details of ToDo task from user input.
     *
     * @param input input by user.
     * @return String[] details.
     */
    public String[] getToDoDetails(String input) {
        String[] details = input.split("todo ");

        return details;
    }

    /**
     * Returns String[].
     * Retrieve details of Event task from user input.
     *
     * @param input input by user.
     * @return String[] details.
     */
    public String[] getEventDetails(String input) {
        String[] shortenedInput = input.split("event ");
        String[] shortenedInputNew = shortenedInput[1].split(" /from ");
        String[] fromTo = shortenedInputNew[1].split(" /to ");
        String[] details = new String[3];

        details[0] = shortenedInputNew[0];
        details[1] = fromTo[0];
        details[2] = fromTo[1];

        return details;
    }

    /**
     * Returns String[].
     * Retrieve details of Deadline task from user input.
     *
     * @param input input by user.
     * @return String[] details.
     */
    public String[] getDeadlineDetails(String input) {
        String[] shortenedInput = input.split("deadline ");
        String[] details = shortenedInput[1].split(" /by ");

        return details;
    }

    /**
     * Returns String.
     * Retrieve keyword from user input.
     *
     * @param input input by user.
     * @return String keyword.
     */
    public String getKeyword(String input) {
        return input.split("find ")[1];
    }

}
