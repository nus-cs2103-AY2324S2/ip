package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the parsing of inputs from the user.
 */
public class Parser {
    Duke duke;
    /**
     * Handles the parsing of inputs from the user
     * Runs another function based on the input
     *
     * @param taskList the current list of tasks the user has
     * @param request the input from the user
     * @param reading true if this method is used to read the save file, else false
     * @param isDone whether to mark tasks created by this method to be done or not
     */
    public static void commands(TaskList taskList, String request, boolean reading, boolean isDone, Duke duke) {
        String[] words = request.split(" ", 0);
        int length = words.length;
        boolean addSuccessful = false;

        assert length > 0 : "Command cannot be empty";

        switch (words[0]) {
            case "true": {
                commands(taskList, request.substring(5), reading, true, duke);
                break;
            }
            case "false": {
                commands(taskList, request.substring(6), reading, false, duke);
                break;
            }
            case "list": {
                duke.output(taskList.list());
                break;
            }
            case "find": {
                if (words.length < 2) {
                    duke.output(Help.getFindHelp());
                } else {
                    StringBuilder searchTerm = new StringBuilder();
                    searchTerm.append(words[1]);
                    for(int i = 2; i < words.length; i++) {
                        searchTerm.append(" ").append(words[i]);
                    }
                    taskList.find(searchTerm.toString());
                }
                break;
            }
            case "mark": {
                if (words.length < 2) {
                    duke.output(Help.getClearHelp());
                } else {
                    int index = Integer.parseInt(words[1]) - 1;
                    if (index > taskList.getSize() - 1 || index < 0) {
                        duke.output("Invalid index!");
                    } else {
                        String announce = taskList.get(index).mark();
                        duke.output(announce);
                    }
                }
                break;
            }
            case "unmark": {
                if (words.length < 2) {
                    duke.output(Help.getUnmarkHelp());
                } else {
                    int index = Integer.parseInt(words[1]) - 1;
                    if (index > taskList.getSize() - 1 || index < 0) {
                        duke.output("Invalid index!");
                    } else {
                        String announce = taskList.get(index).unmark();
                        duke.output(announce);
                    }
                }
                break;
            }
            case "delete": {
                if (words.length < 2) {
                    duke.output(Help.getDeleteHelp());
                } else {
                    int index = Integer.parseInt(words[1]) - 1;
                    taskList.delete(index);
                }
                break;
            }
            case "t":
            case "todo": {
                addSuccessful = taskList.addToDo(words, !reading, isDone);
                break;
            }
            case "deadline":
            case "d": {
                addSuccessful = taskList.addDeadline(words, !reading, isDone);
                break;
            }
            case "e":
            case "event": {
                addSuccessful = taskList.addEvent(words, !reading, isDone);
                break;
            }
            case "clear": {
                taskList.clear();
                break;
            }
            case "help": {
                if (words.length < 2) {
                    duke.output("help: Returns help for the specified command\n" +
                            "Syntax: help <command_name>\n" +
                            "List of commands: bye, list, find, mark, unmark,\n" +
                            "delete, todo, deadline, event, clear, schedule");
                } else {
                    String command = words[1];
                    duke.output(Help.getHelp(command));
                }
                break;
            }
            case "schedule": {
                if (words.length < 2) {
                    String activities = taskList.searchDate();
                    duke.output(activities);
                } else {
                    LocalDate date = null;
                    try {
                        date = LocalDate.parse(words[1], DateTimeFormatter.ofPattern("d/M/yy"));
                    } catch (DateTimeParseException error) {
                        duke.output("Invalid Date format!");
                    }
                    if (date != null) {
                        String activities = taskList.searchDate(date);
                        duke.output(activities);
                    }
                }
                break;
            }
            default:
                duke.output("Sorry, I don't know this command :(");
                break;
        }
    }
}
