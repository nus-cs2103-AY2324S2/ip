package lamball;

import lamball.task.Deadline;
import lamball.task.Event;
import lamball.task.Task;
import lamball.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * A class that handles the to-do list operations.
 *
 * @author ongzhili
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    private String lastDoneTask;


    /**
     * Constructor for TaskList
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.lastDoneTask = "";
    }

    /**
     * Returns last done task.
     *
     * @return last done task in String format.
     */
    public String getLastDoneTask() {
        return this.lastDoneTask;
    }

    private void printList(ArrayList<Task> lst) {
        String listStr = "Here aaaaare the taaaasks in your list:";
        for(int i = 0; i < lst.size(); i++) {
            listStr += "\n    " + (i + 1) + ". " + lst.get(i).toString() + "";
        }
        lastDoneTask = listStr;
    }

    private boolean mark(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if index is within range of list
        int idx = Integer.valueOf(parts[1]) - 1;
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException("Taaask index out of range, baa.");
        }
        Task temp = tasks.get(idx);
        temp.mark();
        lastDoneTask = "I have maaarked the task as done:\n" + "    " + temp.toString();
        if (!isInit) {
            Storage.replaceLine("1 | " + temp.command(), idx);
        }
        return true;
    }

    private boolean unMark(String[] parts) throws LamballParseException {
        // Checks if index is within range of list
        int idx = Integer.valueOf(parts[1]) - 1;
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException("Taaask index out of range, baa.");
        }
        Task temp = tasks.get(idx);
        temp.unMark();
        lastDoneTask = "I have maaarked the task as undone:\n" + "    " + temp.toString();
        Storage.replaceLine("0 | " + temp.command(), idx);
        return true;
    }

    private boolean toDo(String[] parts, boolean isInit) throws LamballParseException {
        Task temp = new ToDo(parts[1]);
        tasks.add(temp);
        lastDoneTask = "Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size()
                + " tasks in the list.";
        if (!isInit) {
            Storage.writeToFile("0 | " + temp.command());
        }
        return true;
    }

    private boolean deadline(String[] parts, boolean isInit) throws LamballParseException {
        String[] furtherSplit = parts[1].split(" /", 2);
        if (furtherSplit.length < 2 || !furtherSplit[1].substring(0,3).equals("by ")) {
            throw new LamballParseException("Deadline is in the wrong formaaaaaaat, baa. :(\n    Correct fo" + "rmaaat " +
                    "is: deadline <name> /by <time>, baa.");
        }
        try {
            Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
            tasks.add(temp);
            lastDoneTask = "Added Deadline:\n        " + temp.toString() + "\n    Now you have " +
                    tasks.size() + " tasks in the list.";
            if (!isInit) {
                Storage.writeToFile("0 | " + temp.command());
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new LamballParseException("Date is in the wrong formaaaaaaat, baa. :(\n    Correct fo" + "rmaaat is: " +
                    "yyyy-mm-dd (e.g 2001-01-20)");
        }
    }

    private boolean event(String[] parts, boolean isInit) throws LamballParseException {
        String[] furtherSplit = parts[1].split(" /", 3);
        if (furtherSplit.length < 3 || !furtherSplit[1].substring(0,5).equals("from ") ||
                !furtherSplit[2].substring(0,3).equals("to ")) {
            throw new LamballParseException("Event is in the wrong formaaaaaaat, baa. :(\n    Correct " +
                    "formaaat is: event <name> /from <time> /to <time>, baa.");
        }
        try {
            Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""),
                    furtherSplit[2].replaceFirst("to ", ""));
            tasks.add(temp);
            lastDoneTask = "Added Event:\n        " + temp.toString() + "\n    Now you have "
                    + tasks.size() + " tasks in the list.";
            if (!isInit) {
                Storage.writeToFile("0 | " + temp.command());
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new LamballParseException("Dates are in the wrong formaaaaaaat, baa. :(\n    Correct fo" +
                    "rmaaat is: yyyy-mm-dd (e.g 2001-01-20)");
        }
    }

    private boolean deleteFromList(String[] parts) throws LamballParseException {
        // Checks if index is within range of list
        int idx = Integer.valueOf(parts[1]) - 1;
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException("Taaask index out of range, baa.");
        }
        Task temp = tasks.remove(idx);
        Storage.deleteLine(idx);
        lastDoneTask = "I have removed this taaask:\n" + "        " + temp.toString() + "\n    Now you have "
                + tasks.size() + " tasks in the list.";
        return true;
    }

    private boolean find(String[] parts) throws LamballParseException {
        ArrayList<Task> positives = new ArrayList<>();
        for (Task temp : tasks) {
            if (temp.containing(parts[1])) {
                positives.add(temp);
            }
        }
        printList(positives);
        return true;
    }

    /**
     * Runs the command when provided with the parsed format.
     *
     * @param command 2 Element string array in the format command type, arguments
     * @param isInit Whether this marking was during the initialization process.
     * @return Boolean indicating whether to keep the chatbot active after the command.
     * @throws LamballParseException if invalid arguments provided.
     */
    public boolean runComd(String[] command, boolean isInit) throws LamballParseException {
        switch(command[0]) {
            case "mark": {
                mark(command, isInit);
                return true;
            }
            case "unmark": {
                unMark(command);
                return true;
            }
            case "bye": {
                return false;
            }
            case "list": {
                printList(this.tasks);
                return true;
            }
            case "todo": {
                toDo(command, isInit);
                return true;
            }
            case "deadline": {
                deadline(command, isInit);
                return true;
            }
            case "event": {
                event(command, isInit);
                return true;
            }
            case "delete": {
                deleteFromList(command);
                return true;
            }
            case "find": {
                find(command);
                return true;
            }

        }
        // Should not reach here.
        return false;
    }
}
