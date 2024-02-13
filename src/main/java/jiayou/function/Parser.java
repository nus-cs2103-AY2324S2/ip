package jiayou.function;

import java.time.LocalDate;

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
    private static enum CommandType {
        TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, SEARCH_BY_DATE, SEARCH_BY_KEYWORD, HELP
    }

    /**
     * Parses the input command and invokes the corresponding methods in the task list to process it.
     *
     * @param tasks the task list of the chatbot.
     * @param input the input string to be parsed.
     * @return a response message.
     */
    public String parseCommand(TaskList tasks, String input) {
        String response = "";
        try {
            String[] parts = input.split(" ", 2);
            String commandString = parts[0];
            Parser.CommandType command = Parser.CommandType.valueOf(commandString.toUpperCase());
            String content = parts.length > 1 ? parts[1] : "";

            switch (command) {
            case LIST:
                response = tasks.printList();
                break;
            case HELP:
                response = getGuide();
                break;
            case MARK:
                if (content.isEmpty()) {
                    throw new JiayouException("OOPS!!! I don't know which task to mark.\n"
                            + "Please add the index after the keyword mark!");
                } else {
                    String[] taskIds = content.split(" ");
                    response = tasks.markTask(taskIds);
                    break;
                }
            case UNMARK:
                if (content.isEmpty()) {
                    throw new JiayouException("OOPS!!! I don't know which task to unmark.\n"
                            + "Please add the index after the keyword unmark!");
                } else {
                    String[] taskIds = content.split(" ");
                    response = tasks.unmarkTask(taskIds);
                    break;
                }
            case DELETE:
                if (content.isEmpty()) {
                    throw new JiayouException("OOPS!!! I don't know which task to delete.\n"
                            + "Please add the index after the keyword delete!");
                } else {
                    String[] taskIds = content.split(" ");
                    response = tasks.deleteTask(taskIds);
                    break;
                }
            case TODO:
                if (content.isEmpty()) {
                    throw new JiayouException("OOPS!!! The description of a todo cannot be empty.\n"
                            + "Please add a description after the keyword todo!");
                } else {
                    ToDo newToDo = new ToDo(content);
                    response = tasks.addTask(newToDo);
                    break;
                }
            case DEADLINE:
                String[] deadlineParts = content.split(" /by ");
                Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                response = tasks.addTask(newDeadline);
                break;
            case EVENT:
                String[] eventParts = content.split(" /from | /to ");
                Event newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                response = tasks.addTask(newEvent);
                break;
            case SEARCH_BY_DATE:
                LocalDate date = LocalDate.parse(content);
                response = tasks.searchByDate(date);
                break;
            case SEARCH_BY_KEYWORD:
                response = tasks.searchByKeyword(content);
                break;
            default:
                break;
            }
        } catch (IllegalArgumentException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :\")\n"
                    + "Enter 'help' to know what functionalities are available! > <";
        } catch (JiayouException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * Provides an instruction on commands of the chatbot.
     *
     * @return a string containing all the command usages of the chatbot.
     */
    private String getGuide() {
        String response = "1. To get the whole list of tasks: list\n";
        response += "2. To mark/unmark the task(s) with ids x, y and z: mark/unmark x y z\n";
        response += "3. To delete the task(s) with ids x, y and z: delete x y z\n";
        response += "4. To search tasks on a certain date: search_by_date YYYY-MM-DD\n";
        response += "5. To search tasks with the keyword x: search_by_keyword x\n";
        response += "6. To add a todo task: todo description\n";
        response += "7. To add a deadline task: deadline description /by YYYY-MM-DD\n";
        response += "8. To add an event task: event description /from YYYY-MM-DD /to YYYY-MM-DD";
        return response;
    }
}
