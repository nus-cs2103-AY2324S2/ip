package duke;

/**
 * Class that provides the help for the various commands.
 */
public class Help {
    String byeHelp =
            "bye: Quits the program\n" +
                    "Syntax: bye\n";

    String listHelp =
            "list: Lists all your tasks\n" +
            "Syntax: list\n";
    String findHelp =
            "find: Searches for tasks containing the search term in its name\n" +
                    "Syntax: find <searchTerm>\n";
    String markHelp =
            "mark: Marks a task as complete\n" +
                    "Syntax: mark <index_of_task>\n";
    String unmarkHelp =
            "unmark: Marks a task as not done\n" +
                    "Syntax: unmark <index_of_task>\n";
    String deleteHelp =
            "delete: Deletes the specified task from the task list\n" +
                    "Syntax: delete <index_of_task>\n";
    String todoHelp =
            "todo or t: Adds a todo task with the specified name\n" +
                    "Syntax: todo <taskName> or t <taskName>\n";
    String deadlineHelp =
            "deadline or d: Adds a deadline task with the specified name and deadline\n" +
                    "Syntax: deadline <taskName> /by <deadline> or d <taskName> /by <deadline>\n" +
                    "deadline to be specified in the format d/M/YY HH:mm (e.g. 21/2/24 10:00)";
    String eventHelp =
            "event or e: Adds an event task with the specified name, start date/time, and end date/time\n" +
                    "Syntax: event <taskName> /from <startDate> /to <endDate>\n" +
                    "or e <taskName> /from <startDate> /to <endDate>\n" +
                    "startDate and endDate to be specified in the format d/M/YY HH:mm (e.g. 21/2/24 10:00)";
    String clearHelp =
            "clear: Clears your entire list\n" +
            "Syntax: clear\n";
    String scheduleHelp =
            "schedule: Retrieves all your deadline tasks and events\n" +
                    "that are happening on the specified date\n" +
                    "Syntax: schedule <date>\n" +
                    "date to be specified in the format d/M/YY (e.g. 21/2/24)." +
                    "If date is not specified, it defaults to today";

    /**
     * Returns the help for the clear command
     * @return help for the clear command
     */
    public static String getClearHelp() {
        Help h = new Help();
        return h.clearHelp;
    }

    /**
     * Returns the help for the deadline command
     * @return help for the deadline command
     */
    public static String getDeadlineHelp() {
        Help h = new Help();
        return h.deadlineHelp;
    }

    /**
     * Returns the help for the delete command
     * @return help for the delete command
     */
    public static String getDeleteHelp() {
        Help h = new Help();
        return h.deleteHelp;
    }

    /**
     * Returns the help for the event command
     * @return help for the event command
     */
    public static String getEventHelp() {
        Help h = new Help();
        return h.eventHelp;
    }

    /**
     * Returns the help for the find command
     * @return help for the find command
     */
    public static String getFindHelp() {
        Help h = new Help();
        return h.findHelp;
    }

    /**
     * Returns the help for the list command
     * @return help for the list command
     */
    public static String getListHelp() {
        Help h = new Help();
        return h.listHelp;
    }

    /**
     * Returns the help for the mark command
     * @return help for the mark command
     */
    public static String getMarkHelp() {
        Help h = new Help();
        return h.markHelp;
    }

    /**
     * Returns the help for the schedule command
     * @return help for the schedule command
     */
    public static String getScheduleHelp() {
        Help h = new Help();
        return h.scheduleHelp;
    }

    /**
     * Returns the help for the todo command
     * @return help for the todo command
     */
    public static String getTodoHelp() {
        Help h = new Help();
        return h.todoHelp;
    }

    /**
     * Returns the help for the bye command
     * @return help for the bye command
     */
    public static String getByeHelp() {
        Help h = new Help();
        return h.byeHelp;
    }

    /**
     * Returns the help for the unmark command
     * @return help for the unmark command
     */
    public static String getUnmarkHelp() {
        Help h = new Help();
        return h.unmarkHelp;
    }

    /**
     * Returns the help for the command specified
     *
     * @param command the command to return help for
     *
     * @return help for the command specified
     */
    public static String getHelp(String command) {
        String str;
        switch (command) {
            case "bye": {
                str = getByeHelp();
                break;
            }
            case "list": {
                str = getListHelp();
                break;
            }
            case "find": {
                str = getFindHelp();
                break;
            }
            case "mark": {
                str = getMarkHelp();
                break;
            }
            case "unmark": {
                str = getUnmarkHelp();
                break;
            }
            case "delete": {
                str = getDeleteHelp();
                break;
            }
            case "todo": {
                str = getTodoHelp();
                break;
            }
            case "deadline": {
                str = getDeadlineHelp();
                break;
            }
            case "event": {
                str = getEventHelp();
                break;
            }
            case "clear": {
                str = getClearHelp();
                break;
            }
            case "schedule": {
                str = getScheduleHelp();
                break;
            }
            default:
                return "I don't know this command :(";
        }
        return str;
    }
}