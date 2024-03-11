package tsundere.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tsundere.exception.GeneralException;
import tsundere.ui.Parser;

/**
 * Encapsulates a TaskList object that contains all Task objects.
 */
public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<>();
    private static final String INVALID_TASK_NUMBER_INPUTTED_MSG = "Im pretty sure that's the wrong task number! "
            + "Check again!";

    /**
     * Sets selected Task's status to undone.
     *
     * @return String confirmation that Task's status has been unmarked.
     * @throws GeneralException If TaskList is empty.
     */
    public static String unmarkTask() throws GeneralException {

        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("There's nothing to unmark here");
        }
        try {
            Task t = TaskList.taskList.get(Integer.parseInt(Parser.getName().substring(7, 8)) - 1);
            if (t.getStatusIcon().equals(" ")) {
                return "You haven't even started this task dummy!";
            } else {
                t.unMarkTask();
                return getListSize("unmarked", t);
            }
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "unmark [task no.]");
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException(INVALID_TASK_NUMBER_INPUTTED_MSG);
        }

    }

    /**
     * Sets selected Task's status to done.
     *
     * @return String confirmation that Task's status has been marked as done.
     * @throws GeneralException If TaskList is empty.
     */
    public static String markTask() throws GeneralException {

        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("There's nothing to mark here!");
        }
        try {
            Task t = TaskList.taskList.get(Integer.parseInt(Parser.getName().substring(5, 6)) - 1);
            if (t.isDone) {
                return "You already marked this!";
            } else {
                t.markTaskAsDone();
                return getListSize("marked", t);
            }
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "unmark [task no.]");
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException(INVALID_TASK_NUMBER_INPUTTED_MSG);
        }

    }

    /**
     * Removes selected Task from TaskList.
     *
     * @return String confirmation that Task has been removed.
     * @throws GeneralException If TaskList is empty.
     */
    public static String deleteTask() throws GeneralException {

        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("There's nothing to delete here!");
        }

        try {
            int idx = Integer.parseInt(Parser.getName().substring(7, 8)) - 1;
            Task t = TaskList.taskList.get(idx);
            TaskList.taskList.remove(idx);
            return getListSize("deleted", t);
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "unmark [task no.]");
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException(INVALID_TASK_NUMBER_INPUTTED_MSG);
        }

    }

    /**
     * Displays all Tasks stored in TaskList.
     *
     * @return String containing details of all Tasks in the TaskList.
     * @throws GeneralException If TaskList is empty.
     */
    public static String listTasks() throws GeneralException {
        StringBuilder response = new StringBuilder();
        int size = TaskList.taskList.size();
        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("Aren't you pretty free now? "
                    + "Go find something to do!");
        }
        for (int i = 0; i < size; i++) {
            Task t = TaskList.taskList.get(i);
            response.append((i + 1)).append(". ").append(t).append("\n");
        }
        return response.toString();
    }

    /**
     * Adds ToDo task to TaskList.
     *
     * @return String confirmation that ToDo has been added.
     * @throws GeneralException If command given violates given format.
     */
    public static String addToDoTask() throws GeneralException {

        try {
            String todo = Parser.getName().split(" ", 2)[1];

            Task t = new ToDo(todo);
            TaskList.taskList.add(t);
            return getListSize("added", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "todo [task]");
        }

    }

    /**
     * Adds Event task to TaskList.
     *
     * @return String confirmation that Event has been added.
     * @throws GeneralException If command given violates given format.
     */
    public static String addEventTask() throws GeneralException {

        try {
            String event = Parser.getName().split(" ", 2)[1];
            String[] x = event.split(",");

            Task t = new Event(x[0], x[1].trim().split(" ", 2)[1], x[2].trim().split(" ", 2)[1]);
            TaskList.taskList.add(t);
            return getListSize("added", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "event [task], from [date], to [date]");
        }

    }

    /**
     * Adds Deadline task to TaskList.
     *
     * @return String confirmation that Deadline has been added.
     * @throws GeneralException If command given violates given format.
     */
    public static String addDeadlineTask() throws GeneralException {

        try {
            String deadline = Parser.getName().split(" ", 2)[1];
            String[] x = deadline.split(",");
            LocalDate d1 = LocalDate.parse(x[1].trim().split(" ", 2)[1]);
            String date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Task t = new Deadline(x[0], date);
            TaskList.taskList.add(t);
            return getListSize("added", t);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "deadline [task], by [yyyy-mm-dd]");
        }

    }

    /**
     * Displays Tasks that contain given keyword.
     *
     * @return String containing details of all Tasks that match the keyword.
     * @throws GeneralException If TaskList is empty.
     */
    public static String findTasks() throws GeneralException {

        int size = TaskList.taskList.size();
        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("Theres's nothing to find here!");
        }
        StringBuilder response = new StringBuilder();
        try {
            int count = 0;
            String keyword = Parser.getName().split(" ", 2)[1];
            for (int i = 0; i < size; i++) {
                Task t = TaskList.taskList.get(i);
                if (t.description.contains(keyword)) {
                    response.append((i + 1)).append(". ").append(t).append("\n");
                    count++;
                }
            }
            if (count == 0) {
                return "I couldn't find anything related to that!";
            } else {
                return response.toString();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException("Proper format is 'find [keyword]' just so you know...");
        }

    }

    /**
     * Adds tag to selected Task.
     *
     * @return String confirmation that tag has been added.
     * @throws GeneralException if command violates given format.
     */
    public static String tagTask() throws GeneralException {
        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("Theres's nothing to find here!");
        }
        try {
            String[] x = Parser.getName().split(" ");
            int idx = Integer.parseInt(x[1]) - 1;
            String tag = x[2];
            Task t = TaskList.taskList.get(idx);
            t.tagTask(tag);
            return getListSize("tagged", t);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "tag [task no.] [tagname]");
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException(INVALID_TASK_NUMBER_INPUTTED_MSG);
        }
    }

    /**
     * Removes tag from selected Task.
     *
     * @return String confirmation that tag has been removed.
     * @throws GeneralException if command violates given format.
     */
    public static String untagTask() throws GeneralException {
        if (TaskList.taskList.isEmpty()) {
            throw new GeneralException("Theres's nothing to find here!");
        }
        try {
            String[] x = Parser.getName().split(" ");
            int idx = Integer.parseInt(x[1]) - 1;
            String tag = x[2];
            Task t = TaskList.taskList.get(idx);
            t.untagTask(tag);
            return getListSize("untagged", t);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "untag [task no.] [tagname]");
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException(INVALID_TASK_NUMBER_INPUTTED_MSG);
        }
    }

    /**
     * Returns number of Tasks in TaskList.
     *
     * @return String containing number of Tasks left if any in the TaskList.
     */
    public static String getListSize(String str, Task t) {
        String response = "";
        int size = TaskList.taskList.size();
        response += "Noted...";
        response += " " + t.toString() + " has been " + str + ".\n";

        if (size > 0) {
            response += "Get to work! You still have " + size + " " + (size > 1 ? "tasks" : "task") + " left!";
        } else {
            response += "You finally have free time?";
        }
        return response;

    }

    /**
     * Returns a list of all possible command.
     *
     * @return String containing all the command formats.
     */
    public static String listCommands() {
        return "It's merely a coincidence I have the list of commands here...\n"
                + "1. todo [task]\n"
                + "2. event [task], from [ ], to [ ]\n"
                + "3. deadline [task], by [yyyy-mm-dd]\n"
                + "4. find [keyword]\n"
                + "5. list\n"
                + "6. mark [task no.]\n"
                + "7. unmark [task no.]\n"
                + "8. delete [task no.]\n"
                + "9. tag [task no.] [tagname]\n"
                + "10. untag [task no.] [tagname]\n"
                + "11. help\n"
                + "12. bye";
    }
}
