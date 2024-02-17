package jiayou.function;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jiayou.exception.JiayouException;
import jiayou.task.Deadline;
import jiayou.task.Event;
import jiayou.task.TaskList;
import jiayou.task.ToDo;

/**
 * Represents the parser of the chatbot to parse the user commmand.
 * @author Liu Jiayao
 */
public class Parser {
    private enum CommandType {
        TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, SEARCH_BY_DATE,
        SEARCH_BY_KEYWORD, HELP, RESCHEDULE, UNKNOWN;

        private static CommandType toEnumValue(String name) {
            for (CommandType value : CommandType.values()) {
                if (value.name().equalsIgnoreCase(name)) {
                    return value;
                }
            }
            return UNKNOWN;
        }
    }

    /**
     * Parses the input command and invokes the corresponding methods in the task list to process it.
     *
     * @param tasks the task list of the chatbot.
     * @param input the input string to be parsed.
     * @return a response message.
     */
    public String parseCommand(TaskList tasks, String input) {
        try {
            String[] parts = input.split(" ", 2);
            String commandString = parts[0];
            Parser.CommandType command = Parser.CommandType.toEnumValue(commandString.toUpperCase());
            String content = parts.length > 1 ? parts[1] : "";

            switch (command) {
            case LIST:
                return tasks.printList();
            case HELP:
                return handleHelp();
            case MARK:
                return handlemark(tasks, content);
            case UNMARK:
                return handleUnmark(tasks, content);
            case DELETE:
                return handleDelete(tasks, content);
            case TODO:
                return handleTodo(tasks, content);
            case DEADLINE:
                return handleDeadline(tasks, content);
            case EVENT:
                return handleEvent(tasks, content);
            case SEARCH_BY_DATE:
                return handleSearchByDate(tasks, content);
            case SEARCH_BY_KEYWORD:
                return tasks.searchByKeyword(content);
            case RESCHEDULE:
                return handleReschedule(tasks, content);
            default:
                throw new JiayouException("OOPS!!! I'm sorry, but I don't know what that means :\")\n"
                        + "Enter 'help' to know what functionalities are available! > <");
            }
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the search by date command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleSearchByDate(TaskList tasks, String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return tasks.searchByDate(date);
        } catch (DateTimeParseException e) {
            return "OOPS!!! The date should be in the form of YYYY-MM-DD.";
        }
    }

    /**
     * Handles the event command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleEvent(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! The description of an event cannot be empty.\n"
                        + "Please add a description after the keyword todo!");
            }

            String[] parts = input.split(" /from | /to ");
            if (parts.length < 3) {
                throw new JiayouException("OOPS!!! Your format is wrong.\n"
                        + "Please follow the format: event <description> /from <date> /to <date>.");
            }

            LocalDate from = LocalDate.parse(parts[1]);
            LocalDate to = LocalDate.parse(parts[2]);
            Event newEvent = new Event(parts[0], from, to);
            return tasks.addTask(newEvent);
        } catch (DateTimeParseException e) {
            return "OOPS!!! The date should be in the form of YYYY-MM-DD.";
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the deadline command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleDeadline(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! The description of a deadline cannot be empty.\n"
                        + "Please add a description after the keyword todo!");
            }

            String[] parts = input.split(" /by ");
            if (parts.length < 2) {
                throw new JiayouException("OOPS!!! Your format is wrong.\n"
                        + "Please follow the format: deadline <description> /by <date>.");
            }

            LocalDate by = LocalDate.parse(parts[1]);
            Deadline newDeadline = new Deadline(parts[0], by);
            return tasks.addTask(newDeadline);
        } catch (DateTimeParseException e) {
            return "OOPS!!! The date should be in the form of YYYY-MM-DD.";
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the todo command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleTodo(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! The description of a todo cannot be empty.\n"
                        + "Please add a description after the keyword todo!");
            }

            ToDo newToDo = new ToDo(input);
            return tasks.addTask(newToDo);
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the mark command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handlemark(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! I don't know which task to mark.\n"
                    + "Please add the id(s) after the keyword mark!");
            }

            String[] taskIds = input.split(" ");
            return tasks.markTask(taskIds);
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the unmark command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleUnmark(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! I don't know which task to unmark.\n"
                        + "Please add the id(s) after the keyword mark!");
            }

            String[] taskIds = input.split(" ");
            return tasks.unmarkTask(taskIds);
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the delete command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleDelete(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! I don't know which task to delete.\n"
                        + "Please add the index after the keyword delete!");
            }

            String[] taskIds = input.split(" ");
            return tasks.deleteTask(taskIds);
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the reschedule command.
     *
     * @param tasks the task list.
     * @param input the input command content.
     * @return a response message.
     */
    private String handleReschedule(TaskList tasks, String input) {
        try {
            if (input.isEmpty()) {
                throw new JiayouException("OOPS!!! I don't know which task to reschedule.\n"
                        + "Please add the index after the keyword reschedule!");
            }

            String[] partsOfReschedule = input.split(" ", 3);
            if (partsOfReschedule.length < 3) {
                throw new JiayouException("OOPS!!! Incorrect format for rescheduling.\n"
                        + "Please use the format: reschedule <ID> /from <date> or /to <date> or /by <date>.");
            }

            String id = partsOfReschedule[0];
            String dateType = partsOfReschedule[1];
            String newDate = partsOfReschedule[2];
            return tasks.reschedule(id, dateType, newDate);
        } catch (DateTimeParseException e) {
            return "OOPS!!! The date should be in the form of YYYY-MM-DD.";
        } catch (JiayouException e) {
            return e.getMessage();
        }
    }

    /**
     * Provides an instruction on commands of the chatbot.
     *
     * @return a string containing all the command usages of the chatbot.
     */
    private String handleHelp() {
        String response = ">w< Here is the instruction!\n\n";
        response += "1. To get the whole list of tasks: list\n";
        response += "2. To mark/unmark the task(s) with ids x, y and z: mark/unmark <x y z>\n";
        response += "3. To delete the task(s) with ids x, y and z: delete <x y z>\n";
        response += "4. To search tasks on a certain date: search_by_date <date>\n";
        response += "5. To search tasks with the keyword: search_by_keyword <keyword>\n";
        response += "6. To add a todo task: todo <description>\n";
        response += "7. To add a deadline task: deadline <description> /by <date>\n";
        response += "8. To add an event task: event <description> /from <date> /to <date>\n";
        response += "9. To reschedule a task: reschedule <ID> /from <date> or /to <date> or /by <date>\n\n";
        response += "** All the dates should be in the form of YYYY-MM-DD **";
        return response;
    }

}
