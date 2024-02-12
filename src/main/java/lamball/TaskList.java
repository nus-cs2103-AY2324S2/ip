package lamball;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import lamball.task.Deadline;
import lamball.task.Event;
import lamball.task.Task;
import lamball.task.ToDo;



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
     * Returns size of task list.
     *
     * @return Intger value of size of task list.
     */
    public int size() {
        return this.tasks.size();
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
        for (int i = 0; i < lst.size(); i++) {
            listStr += "\n    " + (i + 1) + ". " + lst.get(i).toString() + "";
        }
        lastDoneTask = listStr;
    }

    public boolean printList() {
        printList(this.tasks);
        return true;
    }

    public boolean mark(int idx, boolean isInit) {
        Task temp = tasks.get(idx);
        temp.mark();
        lastDoneTask = "I have maaarked the task as done:\n" + "    " + temp.toString();
        if (!isInit) {
            Storage.replaceLine("1 | " + temp.command(), idx);
        }
        return true;
    }

    public boolean unMark(int idx) {
        Task temp = tasks.get(idx);
        temp.unMark();
        lastDoneTask = "I have maaarked the task as undone:\n" + "    " + temp.toString();
        Storage.replaceLine("0 | " + temp.command(), idx);
        return true;
    }

    public boolean toDo(String arg, boolean isInit) {
        Task temp = new ToDo(arg);
        tasks.add(temp);
        lastDoneTask = "Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size()
                + " tasks in the list.";
        if (!isInit) {
            Storage.writeToFile("0 | " + temp.command());
        }
        return true;
    }

    public boolean deadline(String[] furtherSplit, boolean isInit) {
        try {
            Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
            tasks.add(temp);
            lastDoneTask = "Added Deadline:\n        " + temp.toString() + "\n    Now you have "
                    + tasks.size() + " tasks in the list.";
            if (!isInit) {
                Storage.writeToFile("0 | " + temp.command());
            }
        } catch (DateTimeParseException e) {
            this.lastDoneTask = "Date is in the wrong formaaaaaaat, baa. :(\n    Correct fo" + "rmaaat is: "
                    + "yyyy-mm-dd (e.g 2001-01-20)";
            return true;
        }
        return true;
    }

    public boolean event(String[] furtherSplit, boolean isInit) throws DateTimeParseException {
        try {
            Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""),
                    furtherSplit[2].replaceFirst("to ", ""));
            tasks.add(temp);
            lastDoneTask = "Added Event:\n        " + temp.toString() + "\n    Now you have "
                    + tasks.size() + " tasks in the list.";
            if (!isInit) {
                Storage.writeToFile("0 | " + temp.command());
            }
        } catch (DateTimeParseException e) {
            this.lastDoneTask = "Dates are in the wrong formaaaaaaat, baa. :(\n    Correct fo"
                    + "rmaaat is: yyyy-mm-dd (e.g 2001-01-20)";
            return true;
        }
        return true;
    }

    public boolean deleteFromList(int idx) {
        Task temp = tasks.remove(idx);
        Storage.deleteLine(idx);
        lastDoneTask = "I have removed this taaask:\n" + "        " + temp.toString() + "\n    Now you have "
                + tasks.size() + " tasks in the list.";
        return true;
    }

    public boolean find(String toFind) {
        ArrayList<Task> positives = new ArrayList<>();
        for (Task temp : tasks) {
            if (temp.containing(toFind)) {
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
//    public boolean runComd(String[] command, boolean isInit) throws LamballParseException {
//        switch(command[0]) {
//        case "mark": {
//            mark(command, isInit);
//            return true;
//        }
//        case "unmark": {
//            unMark(command);
//            return true;
//        }
//        case "bye": {
//            return false;
//        }
//        case "list": {
//            printList(this.tasks);
//            return true;
//        }
//        case "todo": {
//            toDo(command, isInit);
//            return true;
//        }
//        case "deadline": {
//            deadline(command, isInit);
//            return true;
//        }
//        case "event": {
//            event(command, isInit);
//            return true;
//        }
//        case "delete": {
//            deleteFromList(command);
//            return true;
//        }
//        case "find": {
//            find(command);
//            return true;
//        }
//        default:
//            return false;
//        }
//    }
}
