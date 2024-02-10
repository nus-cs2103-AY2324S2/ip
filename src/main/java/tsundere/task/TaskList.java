package tsundere.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.util.ArrayList;

import tsundere.ui.Parser;
import tsundere.exception.GeneralException;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Sets selected Task's status to undone.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static String unmark() throws GeneralException {

        if (TaskList.taskList.isEmpty()) throw new GeneralException("What you tryna unmark huh?");
        try {
            Task t = TaskList.taskList.get(Integer.parseInt(Parser.name.substring(7, 8)) - 1);
            if (t.getStatusIcon().equals(" ")) {
                return("You haven't even started this task dummy!");
            } else {
                t.unMark();
                return(t.toString());
            }
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you spell?");
        } catch (IndexOutOfBoundsException e ) {
            throw new GeneralException("Im pretty sure that's the wrong task number! Check again!");
        }

    }

    /**
     * Sets selected Task's status to done.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static String mark() throws GeneralException {

        if (TaskList.taskList.isEmpty()) throw new GeneralException("What you tryna mark huh?");
        try {
            Task t = TaskList.taskList.get(Integer.parseInt(Parser.name.substring(5, 6)) - 1);
            if (t.getStatusIcon().equals("X")) {
                return("You already finished this!");
            } else {
                t.markAsDone();
                return(t.toString());
            }
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you spell?");
        } catch (IndexOutOfBoundsException e ) {
            throw new GeneralException("Im pretty sure that's the wrong task number! Check again!");
        }

    }

    /**
     * Removes selected Task from TaskList.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static String delete() throws GeneralException {

        if (TaskList.taskList.isEmpty()) throw new GeneralException("What you tryna delete huh?");

        try {
            int idx = Integer.parseInt(Parser.name.substring(7, 8)) - 1;
            Task t = TaskList.taskList.get(idx);
            TaskList.taskList.remove(idx);
            return getListSize("deleted", t);
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you spell?");
        } catch (IndexOutOfBoundsException e ) {
            throw new GeneralException("Im pretty sure that's the wrong task number! Check again!");
        }

    }

    /**
     * Displays all Tasks stored in TaskList.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static String list() throws GeneralException {
        StringBuilder response = new StringBuilder();
        int size = TaskList.taskList.size();
        if (TaskList.taskList.isEmpty()) throw new GeneralException("Aren't you pretty free now? "
                + "Go find something to do!");
        for (int i = 0; i < size; i++) {
            Task t = TaskList.taskList.get(i);
            response.append((i + 1)).append(". ").append(t).append("\n");
        }
        return response.toString();
    }

    /**
     * Adds ToDo task to TaskList.
     *
     * @throws GeneralException If command given violates given format.
     */
    public static String addToDo() throws GeneralException {

        try {
            String todo = Parser.name.split(" ", 2)[1];

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
     * @throws GeneralException If command given violates given format.
     */
    public static String addEvent() throws GeneralException {

        try {
            String event = Parser.name.split(" ", 2)[1];
            String[] x = event.split(",");

            Task t = new Event(x[0], x[1].split(" ", 2)[1], x[2].split(" ", 2)[1]);
            TaskList.taskList.add(t);
            return getListSize("added", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "event [task] ,from [date],to [date]");
        }

    }

    /**
     * Adds Deadline task to TaskList.
     *
     * @throws GeneralException If command given violates given format.
     */
    public static String addDeadline() throws GeneralException {

        try {
            String deadline = Parser.name.split(" ", 2)[1];
            String[] x = deadline.split(",");
            LocalDate d1 = LocalDate.parse(x[1].split(" ", 2)[1]);
            String date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Task t = new Deadline(x[0], date);
            TaskList.taskList.add(t);
            return getListSize("added", t);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "deadline [task] ,by [yyyy-mm-dd]");
        }

    }

    /**
     * Displays Tasks that contain given keyword.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static String find() throws GeneralException {

        int size = TaskList.taskList.size();
        if (TaskList.taskList.isEmpty()) throw new GeneralException("Theres's nothing to find here!");
        StringBuilder response = new StringBuilder();
        try {
            int count = 0;
            String keyword = Parser.name.split(" ", 2)[1];
            for (int i = 0; i < size; i++) {
                Task t = TaskList.taskList.get(i);
                if (t.description.contains(keyword)) {
                    response.append((i + 1)).append(". ").append(t).append("\n");
                    count++;
                }
            }
            if (count == 0) {
                return("I couldn't find anything related to that!");
            } else {
                return response.toString();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GeneralException("Proper format is 'find [keyword]' just so you know...");
        }

    }

    public static String tag() throws GeneralException {
        if (TaskList.taskList.isEmpty()) throw new GeneralException("Theres's nothing to find here!");
        try {
            String[] x = Parser.name.split(" ");
            int idx = Integer.parseInt(x[1]) - 1;
            String tag = x[2];
            Task t = TaskList.taskList.get(idx);
            t.tagTask(tag);
            return getListSize("tagged", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "tag [task no.] [tagname]");
        }
    }

    public static String untag() throws GeneralException {
        if (TaskList.taskList.isEmpty()) throw new GeneralException("Theres's nothing to find here!");
        try {
            String[] x = Parser.name.split(" ");
            int idx = Integer.parseInt(x[1]) - 1;
            String tag = x[2];
            Task t = TaskList.taskList.get(idx);
            t.untagTask(tag);
            return getListSize("untagged", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "untag [task no.] [tagname]");
        }
    }

    /**
     * Prints number of Tasks in TaskList.
     */
    public static String getListSize(String str, Task t) {
        String response = "";
        int size = TaskList.taskList.size();
        response += ("Noted...");
        response += (" " + t.toString() + " has been " + str + "\n");

        if (size > 0) {
            response += ("Get to work! You still have " + size + " " + (size > 1 ? "tasks" : "task") + " left!");
        } else {
            response += ("You finally have free time?");
        }
        return response;

    }
}
