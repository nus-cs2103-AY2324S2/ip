package luke.parser;

import luke.Storage;
import luke.exception.DateException;
import luke.exception.FileException;
import luke.exception.LukeException;
import luke.exception.TaskException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.TaskList;
import luke.task.Todo;
import luke.ui.Ui;


/**
 * Represents a parser that parses the user input and executes the corresponding command.
 */
public class Parser {
    private String input;

    /**
     * Constructor for the parser.
     *
     * @param input The user input.
     */
    public Parser(String input) {
        this.input = input;

    }

    /**
     * Parses the user input and executes the corresponding command to add, delete, mark, unmark or list the tasks
     * as well as to exit the program.
     *
     * @param list The list of tasks.
     * @param ui The user interface.
     * @param storage The storage of the tasks.
     */
    public String parse(TaskList list, Ui ui, Storage storage) {
        if (isExit()) {
            return ui.goodbye();
        } else if (this.input.equals("help")) {
            return ui.help();
        } else if (this.input.equals("list")) {
            return ui.printList(list);
        } else if (this.input.startsWith("find")) {
            try {
                if (input.equals("find") || input.equals("find ")) {
                    throw new LukeException("Hold up!! There must be a keyword to find!\n"
                            + "Please enter a keyword after find.");
                }
                return findCommand(list, ui);
            } catch (LukeException e) {
                return ui.getErrorMessage(e.getMessage());
            }
        } else if (this.input.contains("mark")) {
            try {
                if (input.equals("mark") || input.equals("unmark")
                        || input.equals("mark ") || input.equals("unmark ")) {
                    throw new LukeException("Hold up!! There must be a task to " + input + "!\n"
                            + "Please enter an index after " + input + ".");
                }
                return markCommand(list, storage, ui);

            } catch (LukeException e) {
                return ui.getErrorMessage(e.getMessage());
            }
        } else if (input.startsWith("delete")) {
            try {
                if (input.equals("delete") || input.equals("delete ")) {
                    throw new LukeException("Hold up!! There must be a task to delete!\n"
                            + "Please enter an index after " + input + ".");
                }
                return deleteCommand(list, storage, ui);

            } catch (LukeException e) {
                return ui.getErrorMessage(e.getMessage());
            }
        } else {
            try {
                if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                        || input.equals("todo ") || input.equals("deadline ") || input.equals("event ")) {
                    throw new LukeException("Hold up!! The description of a " + input + " cannot be empty.\n"
                            + "Please enter a description after your " + input + ".");
                }
                String[] instruction = input.split(" ");
                String type = instruction[0];

                if (type.equals("todo")) {
                    list.add(todoCommand());

                } else if (type.equals("deadline")) {
                    try {
                        list.add(deadlineCommand());

                    } catch (StringIndexOutOfBoundsException e) {
                        throw new TaskException("Hold up!! The description and /by of a deadline cannot be empty.\n"
                                + "Please follow this format: deadline <description> /by <date/day/time>.");
                    }

                } else if (type.equals("event")) {
                    try {
                        list.add(eventCommand());
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new TaskException("Hold up!! The description, /from and /to of an event "
                                + "cannot be empty.\n"
                                + "Please follow this format: event <description> "
                                + "/from <date/day/time> /to <date/day/time>.");
                    }
                } else {
                    throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");

                }
                storage.writeTask(list);
                return ui.printTaskAdded(list.get(list.size() - 1), list.size());

            } catch (LukeException | TaskException | FileException e) {
                return ui.getErrorMessage(e.getMessage());
            } catch (DateException e) {
                return ui.getErrorMessage(e.getMessage() + "\nPlease enter the date in proper format such as "
                        + "dd/MM/yyyy or yyyy-MM-dd\nYou can also enter the time in 24-hour format such as "
                        + "HH[:MM] after the date");
            }
        }
    }

    private String findCommand(TaskList list, Ui ui) {
        // 5 is the index after "find ", so starts from index 5
        String keyword = input.substring(5);
        TaskList foundList = list.find(keyword);
        return ui.printTaskFound(foundList);
    }

    private String markCommand(TaskList list, Storage storage, Ui ui) throws LukeException {
        try {
            String returnString = "";
            String[] instruction = input.split(" ");
            String markOrUnmark = instruction[0];

            if (!markOrUnmark.equals("mark") && !markOrUnmark.equals("unmark")) {
                throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
            }

            int index = Integer.parseInt(instruction[1]) - 1; // array is 0-indexed

            if (index >= list.size()) {
                throw new LukeException("Hold up!! There is no such task in the list.\n"
                        + "Please enter a valid index after " + input.split(" ")[0] + ".");
            }

            if (markOrUnmark.equals("mark")) {
                list.get(index).markAsDone();
                returnString = ui.printTaskMarked(list.get(index));

            } else {
                list.get(index).markAsUndone();
                returnString = ui.printTaskMarked(list.get(index));

            }

            storage.writeTask(list);
            return returnString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new LukeException("Hold up!! Please enter a valid index after "
                    + input.split(" ")[0] + ".");
        } catch (FileException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    private String deleteCommand(TaskList list, Storage storage, Ui ui) throws LukeException {
        try {
            String returnString = "";
            String[] instruction = input.split(" ");
            String delete = instruction[0];

            if (!delete.equals("delete")) {
                throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
            }

            int index = Integer.parseInt(instruction[1]) - 1; // array is 0-indexed

            if (index >= list.size()) {
                throw new LukeException("Hold up!! There is no such task in the list.\n"
                        + "Please enter a valid index after delete.");
            }

            Task removedTask = list.get(index);
            list.remove(index);
            returnString = ui.printTaskDeleted(removedTask, list.size());
            storage.writeTask(list);
            return returnString;

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new LukeException("Hold up!! Please enter a valid index after delete.");
        } catch (FileException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    private Todo todoCommand() {
        // 5 is the index after "todo ", so starts from index 5
        String description = input.substring(5);
        return new Todo(description);
    }

    private Deadline deadlineCommand() throws DateException {
        // 9 is the index after "deadline ", so starts from index 9
        // -1 to remove the space before "/by"
        String description = input.substring(9, input.indexOf("/by") - 1);
        // +4 to remove the "/by " and start from the index after "/by "
        String by = input.substring(input.indexOf("/by") + 4);
        return new Deadline(description, by);
    }

    private Event eventCommand() throws DateException {
        // 6 is the index after "event ", so starts from index 6
        // -1 to remove the space before "/from"
        String description = input.substring(6, input.indexOf("/from") - 1);
        // +6 to remove the "/from " and start from the index after "/from "
        // -1 to remove the space before "/to"
        String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
        // +4 to remove the "/to " and start from the index after "/to "
        String to = input.substring(input.indexOf("/to") + 4);
        return new Event(description, from, to);
    }

    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Checks if the user input is "bye".
     *
     * @return True if the user input is "bye", false otherwise.
     */
    public boolean isExit() {
        return input.equals("bye");
    }
}
