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
    public static void unmark() throws GeneralException {

        if (TaskList.taskList.isEmpty()) throw new GeneralException("What you tryna unmark huh?");
        try {
            Task t = TaskList.taskList.get(Integer.parseInt(Parser.name.substring(7, 8)) - 1);
            if (t.getStatusIcon().equals(" ")) {
                System.out.println("You haven't even started this tsundere.task dummy!");
            } else {
                t.unMark();
                System.out.println(t);
            }
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you spell?");
        } catch (IndexOutOfBoundsException e ) {
            throw new GeneralException("Im pretty sure that's the wrong tsundere.task number! Check again!");
        }

    }

    /**
     * Sets selected Task's status to done.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static void mark() throws GeneralException {

        if (TaskList.taskList.isEmpty()) throw new GeneralException("What you tryna mark huh?");
        try {
            Task t = TaskList.taskList.get(Integer.parseInt(Parser.name.substring(5, 6)) - 1);
            if (t.getStatusIcon().equals("X")) {
                System.out.println("You already finished this!");
            } else {
                t.markAsDone();
                System.out.println(t);
            }
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you spell?");
        } catch (IndexOutOfBoundsException e ) {
            throw new GeneralException("Im pretty sure that's the wrong tsundere.task number! Check again!");
        }

    }

    /**
     * Removes selected Task from TaskList.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static void delete() throws GeneralException {

        if (TaskList.taskList.isEmpty()) throw new GeneralException("What you tryna delete huh?");

        try {
            int idx = Integer.parseInt(Parser.name.substring(7, 8)) - 1;
            Task t = TaskList.taskList.get(idx);
            TaskList.taskList.remove(idx);
            getListSize("deleted", t);
        } catch (NumberFormatException e) {
            throw new GeneralException("Can't you spell?");
        } catch (IndexOutOfBoundsException e ) {
            throw new GeneralException("Im pretty sure that's the wrong tsundere.task number! Check again!");
        }

    }

    /**
     * Displays all Tasks stored in TaskList.
     *
     * @throws GeneralException If TaskList is empty.
     */
    public static void list() throws GeneralException {

        int size = TaskList.taskList.size();
        if (TaskList.taskList.isEmpty()) throw new GeneralException("Aren't you pretty free now? "
                + "Go find something to do!");
        for (int i = 0; i < size; i++) {
            Task t = TaskList.taskList.get(i);
            System.out.println((i + 1) + ". " + t.toString());
        }

    }

    /**
     * Adds ToDo task to TaskList.
     *
     * @throws GeneralException If command given violates given format.
     */
    public static void addToDo() throws GeneralException {

        try {
            String todo = Parser.name.split(" ", 2)[1];

            Task t = new ToDo(todo);
            TaskList.taskList.add(t);
            getListSize("added", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "todo [tsundere.task]");
        }

    }

    /**
     * Adds Event task to TaskList.
     *
     * @throws GeneralException If command given violates given format.
     */
    public static void addEvent() throws GeneralException {

        try {
            String event = Parser.name.split(" ", 2)[1];
            String[] x = event.split(",");

            Task t = new Event(x[0], x[1].split(" ", 2)[1], x[2].split(" ", 2)[1]);
            TaskList.taskList.add(t);
            getListSize("added", t);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "event [tsundere.task] ,from [date],to [date]");
        }

    }

    /**
     * Adds Deadline task to TaskList.
     *
     * @throws GeneralException If command given violates given format.
     */
    public static void addDeadline() throws GeneralException {

        try {
            String deadline = Parser.name.split(" ", 2)[1];
            String[] x = deadline.split(",");
            LocalDate d1 = LocalDate.parse(x[1].split(" ", 2)[1]);
            String date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Task t = new Deadline(x[0], date);
            TaskList.taskList.add(t);
            getListSize("added", t);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new GeneralException("Can't you even remember the proper format for this?\n"
                    + "deadline [tsundere.task] ,by [yyyy-mm-dd]");
        }

    }

    /**
     * Prints number of Tasks in TaskList.
     */
    public static void getListSize(String str, Task t) {

        int size = TaskList.taskList.size();
        System.out.println("Noted...");
        System.out.println(" " + t.toString() + " has been " + str);

        if (size > 0) {
            System.out.println("Get to work! You still have " + size + " " + (size > 1 ? "tasks" : "task") + " left!");
        } else {
            System.out.println("You finally have free time?");
        }

    }
}
